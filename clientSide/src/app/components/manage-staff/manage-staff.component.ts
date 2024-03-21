import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Staff} from "../../models/Staff.model";

@Component({
  selector: 'app-manage-staff',
  templateUrl: './manage-staff.component.html',
  styleUrls: ['./manage-staff.component.scss']
})
export class ManageStaffComponent implements OnChanges{

  title!: string;

  @Input()
  staff!: Staff;

  @Output()
  submit: EventEmitter<Staff> = new EventEmitter<Staff>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  staffForm: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName:  new FormControl('',[Validators.required]),
    recruitmentDate:  new FormControl('',[Validators.required]),
    identityType:  new FormControl('', [Validators.required, Validators.minLength(3)]),
    identityCode:  new FormControl('',[Validators.required]),
  });

  ngOnChanges(changes: SimpleChanges): void {
    this.staffForm.reset();
    if (this.staff){
      this.staffForm.setValue({
        firstName: this.staff.firstName,
        lastName: this.staff.lastName,
        recruitmentDate: this.staff.recruitmentDate,
        identityType: this.staff.identityType,
        identityCode: this.staff.identityCode,
      });
    }
  }

  onSubmit() {
    this.staff = this.staffForm.value;
    this.submit.emit(this.staff);
    this.staffForm.reset();
  }

  onCancel() {
    this.cancel.emit();
    this.staffForm.reset();
  }
}
