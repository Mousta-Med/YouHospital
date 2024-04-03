import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Staff} from "../../models/Staff.model";
import {Department} from "../../models/Department.model";
import {DepartmentService} from "../../services/department.service";
import {AuthenticationResponse} from "../../models/Authentication-response.model";

@Component({
  selector: 'app-manage-staff',
  templateUrl: './manage-staff.component.html',
  styleUrls: ['./manage-staff.component.scss']
})
export class ManageStaffComponent implements OnInit, OnChanges {


  departments: Department[] = [];

  @Input()
  staff: Staff = {
    address: '',
    adminId: '',
    departmentId: '',
    email: '',
    firstName: '',
    gender: "MALE",
    identityCode: '',
    identityType: "PASSPORT",
    lastName: '',
    operationId: '',
    pass: '',
    phone: '',
    recruitmentDate: '',
    role: "NURSE",
    specialization: ''
  };

  @Output()
  submit: EventEmitter<Staff> = new EventEmitter<Staff>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  adminId: string | undefined = '';

  staffForm: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    pass: new FormControl(''),
    address: new FormControl('', [Validators.required]),
    specialization: new FormControl('', [Validators.required]),
    role: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    gender: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    departmentId: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    identityType: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    identityCode: new FormControl('', [Validators.required]),
    recruitmentDate: new FormControl('', [Validators.required]),
  });

  constructor(
    private departmentService: DepartmentService,
  ) {
  }

  ngOnInit() {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      this.adminId = authResponse.admin?.id;
    }
    this.departmentService.findAll().subscribe({
      next: (data) => {
        this.departments = data;
      }
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.staffForm.reset();
    if (this.staff) {
      this.staffForm.setValue({
        firstName: this.staff.firstName,
        lastName: this.staff.lastName,
        email: this.staff.email,
        phone: this.staff.phone,
        pass: this.staff.pass,
        address: this.staff.address,
        role: this.staff.role,
        gender: this.staff.gender,
        specialization: this.staff.specialization,
        departmentId: this.staff.departmentId,
        recruitmentDate: this.staff.recruitmentDate,
        identityType: this.staff.identityType,
        identityCode: this.staff.identityCode,
      });
    }
  }

  onSubmit() {
    this.staff = this.staffForm.value;
    this.staff.adminId = this.adminId;
    this.submit.emit(this.staff);
    this.staffForm.reset();
  }

  onCancel() {
    this.cancel.emit();
    this.staffForm.reset();
  }
}
