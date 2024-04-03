import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Operation} from "../../models/Operation.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Patient} from "../../models/Patient.model";
import {PatientService} from "../../services/patient.service";
import {Staff} from "../../models/Staff.model";
import {StaffService} from "../../services/staff.service";

@Component({
  selector: 'app-manage-operation',
  templateUrl: './manage-operation.component.html',
  styleUrls: ['./manage-operation.component.scss']
})
export class ManageOperationComponent implements OnInit, OnChanges {


  patients: Patient[] = [];

  staffs: Staff[] = [];

  minDate = new Date();

  @Input()
  operation: Operation = {
    cost: 0, date: "", duration: 0, patientId: "", staffId: "", time: ""
  };

  @Output()
  submit: EventEmitter<Operation> = new EventEmitter<Operation>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  operationForm: FormGroup = new FormGroup({
    date: new FormControl('', [Validators.required]),
    time: new FormControl('', [Validators.required]),
    duration: new FormControl(0, [Validators.required]),
    cost: new FormControl(0, [Validators.required]),
    patientId: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    staffId: new FormControl('', [Validators.required]),
  });

  constructor(
    private patientService: PatientService,
    private staffService: StaffService,
  ) {
  }

  ngOnInit() {
    this.patientService.findAll().subscribe({
      next: (data) => {
        this.patients = data;
      }
    });
    this.staffService.findAll().subscribe({
      next: (data) => {
        this.staffs = data.filter(item => item.role === "DOCTOR");
      }
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.operationForm.reset();
    if (this.operation) {
      this.operationForm.setValue({
        date: this.operation.date,
        time: this.operation.time,
        duration: this.operation.duration,
        cost: this.operation.cost,
        patientId: this.operation.patientId,
        staffId: this.operation.staffId,
      });
    }
  }

  onSubmit() {
    this.operation = this.operationForm.value;
    this.submit.emit(this.operation);
    this.operationForm.reset();
  }

  onCancel() {
    this.cancel.emit();
    this.operationForm.reset();
  }
}
