import {Component, OnInit} from '@angular/core';
import {Department} from "../../models/Department.model";
import {DepartmentService} from "../../services/department.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.scss']
})
export class DepartmentComponent implements OnInit {

  departments: Array<Department> = [];

  sidebarVisible: boolean = false;

  operation: 'create' | 'update' = 'create';

  department!: Department;

  constructor(
    private departmentService: DepartmentService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {
  }

  ngOnInit() {
    this.getAllDepartments();
  }

  createDepartment() {
    this.sidebarVisible = true;
    this.operation = 'create';
    this.department = {name: '', hospitalId: ''};
  }

  save(department: Department) {
    if (department) {
      if (this.operation === 'create') {
        this.departmentService.save(department)
          .subscribe({
            next: () => {
              this.getAllDepartments();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Department Created Successfully'
              });
              this.department = {name: '', hospitalId: ''};
            }
          });
      } else if (this.operation === 'update') {
        this.departmentService.update(this.department.id, department)
          .subscribe({
            next: () => {
              this.getAllDepartments();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Department Updated Successfully'
              });
            }
          });
      }
      this.sidebarVisible = false;
    }
  }

  deleteDepartment(department: Department) {
    this.confirmationService.confirm({
      header: 'Delete Department',
      message: 'Are you sure you want to delete this Department you can\'t undo this action after confirmation',
      accept: () => {
        this.departmentService.delete(department.id)
          .subscribe({
            next: () => {
              this.getAllDepartments();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Department Deleted Successfully'
              });
            }
          });
      }
    });
  }

  updateDepartment(department: Department) {
    this.department = department;
    this.sidebarVisible = true;
    this.operation = 'update';
  }

  cancel() {
    this.sidebarVisible = false;
  }

  private getAllDepartments() {
    this.departmentService.findAll()
      .subscribe({
        next: (data) => {
          this.departments = data;
        }
      })
    ;
  }

}
