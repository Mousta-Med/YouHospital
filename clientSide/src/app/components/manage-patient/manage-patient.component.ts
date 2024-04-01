import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Department} from "../../models/Department.model";
import {Patient} from "../../models/Patient.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {DepartmentService} from "../../services/department.service";
import {Room} from "../../models/Room.model";
import {RoomService} from "../../services/room.service";

@Component({
  selector: 'app-manage-patient',
  templateUrl: './manage-patient.component.html',
  styleUrls: ['./manage-patient.component.scss']
})
export class ManagePatientComponent implements OnInit, OnChanges{


  departments: Department[] = [];
  rooms: Room[] = [];

  @Input()
  patient: Patient =  {
    dateOfBirth: "",
    patientType: "",
    roomId: "",
    departmentId: "",
    email: "",
    firstName: "",
    gender: "MALE",
    identityCode: "",
    identityType: "PASSPORT",
    lastName: "",
    pass: "",
    phone: ""
  };

  @Output()
  submit: EventEmitter<Patient> = new EventEmitter<Patient>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();


  patientForm: FormGroup = new FormGroup({
    pass: new FormControl(''),
    lastName: new FormControl('', [Validators.required]),
    firstName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    patientType: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    gender: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    departmentId: new FormControl('no', [Validators.required, Validators.minLength(3)] ),
    roomId: new FormControl('no', [Validators.required, Validators.minLength(3)] ),
    identityType: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    identityCode: new FormControl('', [Validators.required]),
    dateOfBirth: new FormControl('', [Validators.required]),
  });

  constructor(
    private router: Router,
    private departmentService: DepartmentService,
    private roomService: RoomService,
    ) {
  }

  ngOnInit() {
    this.departmentService.findAll().subscribe({
      next: (data) => {
        this.departments = data;
      }
    });
    this.roomService.findAll().subscribe({
      next: (data) => {
        this.rooms = data;
      }
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.patientForm.reset();
    if(this.patient){
      console.log(this.patient)
      this.patientForm.setValue({
        firstName: this.patient.firstName,
        lastName: this.patient.lastName,
        email: this.patient.email,
        phone: this.patient.phone,
        patientType: this.patient.patientType,
        pass: this.patient.pass,
        gender: this.patient.gender,
        roomId: this.patient.roomId,
        departmentId: this.patient.departmentId,
        identityType: this.patient.identityType,
        identityCode: this.patient.identityCode,
        dateOfBirth: this.patient.dateOfBirth,
      });
    }
  }


  onSubmit() {
    this.patient = this.patientForm.value;
    this.submit.emit(this.patient);
    this.patientForm.reset();
  }

  onCancel() {
    this.cancel.emit();
    this.patientForm.reset();
  }


}
