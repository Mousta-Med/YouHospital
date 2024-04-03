import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Room} from "../../models/Room.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Department} from "../../models/Department.model";
import {DepartmentService} from "../../services/department.service";

@Component({
  selector: 'app-manage-room',
  templateUrl: './manage-room.component.html',
  styleUrls: ['./manage-room.component.scss']
})
export class ManageRoomComponent implements OnInit, OnChanges {

  departments: Department[] = [];

  title = '';

  @Input()
  room: Room = {roomNum: 0, departmentId: '', roomType: 'SINGLE', location: ''};

  @Input()
  operation: 'create' | 'update' = 'create'

  @Output()
  submit: EventEmitter<Room> = new EventEmitter<Room>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  roomForm: FormGroup = new FormGroup<any>({
    roomNum: new FormControl(0, [Validators.required]),
    departmentId: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    location: new FormControl('', [Validators.required]),
    roomType: new FormControl('no', [Validators.required, Validators.minLength(3)]),
  });

  constructor(
    private departmentService: DepartmentService
  ) {
  }


  ngOnInit() {
    this.departmentService.findAll().subscribe({
      next: (data) => {
        this.departments = data;
      }
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.operation === 'update') {
      this.title = 'Update Room';
    } else {
      this.title = 'New Room';
    }
    if (this.room) {
      this.roomForm.setValue({
        roomNum: this.room.roomNum,
        departmentId: this.room.departmentId,
        location: this.room.location,
        roomType: this.room.roomType,
      })
    }
  }


  onSubmit() {
    this.room = this.roomForm.value;
    this.submit.emit(this.room);
  }

  onCancel() {
    this.cancel.emit();
  }
}
