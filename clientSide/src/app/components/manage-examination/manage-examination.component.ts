import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Examination} from "../../models/Examination.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Staff} from "../../models/Staff.model";
import {Patient} from "../../models/Patient.model";
import {StaffService} from "../../services/staff.service";
import {PatientService} from "../../services/patient.service";

@Component({
  selector: 'app-manage-examination',
  templateUrl: './manage-examination.component.html',
  styleUrls: ['./manage-examination.component.scss']
})
export class ManageExaminationComponent implements OnInit, OnChanges {

  title = '';

  staffs: Staff[] = [];

  patients: Patient[] = [];

  @Input()
  examination: Examination = {dateTime: "", patientId: "", problem: "", staffId: ''};

  @Input()
  operation: 'create' | 'update' = 'create'

  @Output()
  submit: EventEmitter<Examination> = new EventEmitter<Examination>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  minDate: string;

  examinationForm: FormGroup = new FormGroup<any>({
    problem: new FormControl('', [Validators.required]),
    patientId: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    staffId: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    dateTime: new FormControl('', [Validators.required]),
  });

  constructor(
    private staffService: StaffService,
    private patientService: PatientService,
  ) {
    const today = new Date();
    this.minDate = today.toISOString().slice(0, 16);
  }


  ngOnInit() {
    this.getAllPatients();
    this.getAllStaff();
  }

  getAllPatients() {
    this.patientService.findAll().subscribe({
      next: (data) => {
        this.patients = data;
      }
    })
  }

  getAllStaff() {
    this.staffService.findAll().subscribe({
      next: (data) => {
        this.staffs = data.filter(staff => staff.role === 'DOCTOR');
      }
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.operation === 'update') {
      this.title = 'Update Examination';
    } else {
      this.title = 'New Examination';
    }
    if (this.examination) {
      this.examinationForm.setValue({
        problem: this.examination.problem,
        patientId: this.examination.patientId,
        staffId: this.examination.staffId,
        dateTime: this.examination.dateTime
      })
    }
  }


  onSubmit() {
    this.examination = this.examinationForm.value;
    this.submit.emit(this.examination);
  }

  onCancel() {
    this.cancel.emit();
  }
}
