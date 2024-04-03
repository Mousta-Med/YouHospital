import {Component, OnInit} from '@angular/core';
import {Patient} from "../../models/Patient.model";
import {PatientService} from "../../services/patient.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.scss']
})
export class PatientsComponent implements OnInit {

  patients: Patient[] = [];

  patient!: Patient;

  operation: 'update' | 'create' = "create";

  title: string = '';

  visible: boolean = false;
  searchQuery: string = '';

  constructor(
    private patientService: PatientService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
  }

  ngOnInit(): void {
    this.findAllPatient();
  }

  findAllPatient() {
    this.patientService.findAll()
      .subscribe({
        next: (data) => {
          this.patients = data;
        }
      });
  }

  save(newPatient: Patient) {
    if (newPatient) {
      if (this.operation === 'create') {
        this.patientService.save(newPatient)
          .subscribe({
            next: () => {
              this.findAllPatient();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Created Successfully'
              });
              this.patient = {
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
            }, error: (err) => {
              console.log(err)
              this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: err.error?.error || err.error?.code
              });
            }
          });
      } else if (this.operation === 'update') {
        this.patientService.update(this.patient.id, newPatient)
          .subscribe({
            next: () => {
              this.findAllPatient();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Updated Successfully'
              });
            }
          });
      }
      this.visible = false;
    }
  }

  cancel() {
    this.visible = false;
  }

  createPatient() {
    this.title = 'New Patient';
    this.operation = 'create';
    this.visible = true;
    this.patient = {
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
  }

  deletePatient(deletedPatient: Patient) {
    this.confirmationService.confirm({
      header: 'Delete Patient',
      message: `Are you sure you want to delete? You can\'t undo this action afterwords`,
      accept: () => {
        this.patientService.delete(deletedPatient.id)
          .subscribe({
            next: () => {
              this.findAllPatient();
              this.messageService.add({
                severity: 'success', summary: 'Patient deleted', detail: `Patient was successfully deleted`
              });
            }, error: (err) => {
              console.log(err);
            }
          });
      }
    });
  }

  filterItems() {
    if (this.searchQuery.trim() === '') {
      this.findAllPatient();
    } else {
      this.patients = this.patients.filter(item =>
        item.lastName.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }
  }

  updatePatient(updatedPatient: Patient) {
    this.title = 'Update Patient';
    this.operation = 'update';
    this.patient = updatedPatient;
    this.visible = true;
  }

}
