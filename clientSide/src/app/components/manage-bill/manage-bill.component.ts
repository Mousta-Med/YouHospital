import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Bill} from "../../models/Bill.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Patient} from "../../models/Patient.model";
import {StaffService} from "../../services/staff.service";
import {PatientService} from "../../services/patient.service";

@Component({
  selector: 'app-manage-bill',
  templateUrl: './manage-bill.component.html',
  styleUrls: ['./manage-bill.component.scss']
})
export class ManageBillComponent implements OnInit, OnChanges {

  title = '';

  patients: Patient[] = [];

  @Input()
  bill: Bill = {
    status: 'UNPAID',
    patientId: "",
    amount: 0
  };

  @Input()
  operation: 'create' | 'update' = 'create'

  @Output()
  submit: EventEmitter<Bill> = new EventEmitter<Bill>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  minDate: string;

  billForm: FormGroup = new FormGroup<any>({
    amount: new FormControl(0, [Validators.required, Validators.min(0)]),
    patientId: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    status: new FormControl('no', [Validators.required, Validators.minLength(3)]),
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
  }

  getAllPatients() {
    this.patientService.findAll().subscribe({
      next: (data) => {
        this.patients = data;
      }
    })
  }


  ngOnChanges(changes: SimpleChanges): void {
    if (this.operation === 'update') {
      this.title = 'Update Bill';
    } else {
      this.title = 'New Bill';
    }
    if (this.bill) {
      this.billForm.setValue({
        amount: this.bill.amount,
        status: this.bill.status,
        patientId: this.bill.patientId
      })
    }
  }


  onSubmit() {
    this.bill = this.billForm.value;
    this.submit.emit(this.bill);
  }

  onCancel() {
    this.cancel.emit();
  }
}
