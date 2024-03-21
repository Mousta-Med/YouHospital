import {Component, OnInit} from '@angular/core';
import {Staff} from "../../models/Staff.model";
import {StaffService} from "../../services/staff.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.scss']
})
export class StaffComponent implements OnInit{

  staffs: Staff[] = [];

  staff!: Staff ;

  operation: 'update' | 'create' = "create";

  title: string = '';

  visible: boolean = false;
  constructor(
    private staffService: StaffService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
  }

  ngOnInit(): void {
    this.findAllStaff();
  }

  findAllStaff() {
    this.staffService.findAll()
      .subscribe({
        next: (data) => {
          console.log(data)
          this.staffs = data;
        }
      });
  }

  save(newStaff: Staff) {
    if (newStaff) {
      if (this.operation === 'create') {
        this.staffService.save(newStaff)
          .subscribe({
            next: () => {
              this.findAllStaff();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Created Successfully'
              });
              this.staff = {
                address: "",
                adminId: "",
                departmentId: "",
                email: "",
                firstName: "",
                gender: "MALE",
                identityCode: "",
                identityType: "PASSPORT",
                lastName: "",
                operationId: "",
                pass: "",
                phone: "",
                recruitmentDate: "",
                role: "NURSE",
                specialization: ""
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
        this.staffService.update(this.staff.id, newStaff)
          .subscribe({
            next: () => {
              this.findAllStaff();
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

  createStaff() {
    this.title = 'New Staff';
    this.operation = 'create';
    this.visible = true;
    this.staff = {
      address: "",
      adminId: "",
      departmentId: "",
      email: "",
      firstName: "",
      gender: "MALE",
      identityCode: "",
      identityType: "PASSPORT",
      lastName: "",
      operationId: "",
      pass: "",
      phone: "",
      recruitmentDate: "",
      role: "NURSE",
      specialization: ""
    };
  }


  deleteStaff(deletedStaff: Staff) {
    this.confirmationService.confirm({
      header: 'Delete Staff',
      message: `Are you sure you want to delete? You can\'t undo this action afterwords`,
      accept: () => {
        this.staffService.delete(deletedStaff.id)
          .subscribe({
            next: () => {
              this.findAllStaff();
              this.messageService.add({
                severity: 'success', summary: 'Staff deleted', detail: `Staff was successfully deleted`
              });
            },error:(err) =>{
              console.log(err);
            }
          });
      }
    });
  }


  updateStaff(updatedStaff: Staff) {
    this.title = 'Update Staff';
    this.operation = 'update';
    this.staff = updatedStaff;
    this.visible = true;
  }

}
