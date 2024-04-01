import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Department} from "../../models/Department.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SharedService} from "../../services/shared.service";
import {Hospital} from "../../models/Hospital.model";

@Component({
  selector: 'app-manage-department',
  templateUrl: './manage-department.component.html',
  styleUrls: ['./manage-department.component.scss']
})
export class ManageDepartmentComponent implements OnInit, OnChanges {

  title = '';

  hospital: Hospital = {address: '', phone: '', name: ''};

  @Input()
  department: Department = {name: '', hospitalId: ''};

  @Input()
  operation: 'create' | 'update' = 'create'

  @Output()
  submit: EventEmitter<Department> = new EventEmitter<Department>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  departmentForm: FormGroup = new FormGroup<any>({
    name: new FormControl('', [Validators.required]),
    hospitalId: new FormControl('00000000-0000-0064-0000-000000000064'),
  });

  constructor(
  ) {
  }


  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.operation === 'update') {
      this.title = 'Update Department';
    } else {
      this.title = 'New Department';
    }
    if (this.department) {
      this.departmentForm.setValue({
        name: this.department.name,
        hospitalId: "00000000-0000-0064-0000-000000000064"
      })
    }
  }


  onSubmit() {
    this.department = this.departmentForm.value;
    this.submit.emit(this.department);
  }

  onCancel() {
    this.cancel.emit();
  }
}
