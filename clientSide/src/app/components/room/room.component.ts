import {Component} from '@angular/core';
import {Room} from "../../models/Room.model";
import {RoomService} from "../../services/room.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent {

  rooms: Array<Room> = [];

  sidebarVisible: boolean = false;

  operation: 'create' | 'update' = 'create';

  room!: Room;

  constructor(
    private roomService: RoomService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {
  }

  ngOnInit() {
    this.getAllRooms();
  }

  createRoom() {
    this.sidebarVisible = true;
    this.operation = 'create';
    this.room = {roomNum: 0, departmentId: '', roomType: 'SINGLE', location: ''};
  }

  save(room: Room) {
    if (room) {
      if (this.operation === 'create') {
        this.roomService.save(room)
          .subscribe({
            next: () => {
              this.getAllRooms();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Room Created Successfully'
              });
              this.room = {roomNum: 0, departmentId: '', roomType: 'SINGLE', location: ''};
            }
          });
      } else if (this.operation === 'update') {
        this.roomService.update(this.room.id, room)
          .subscribe({
            next: () => {
              this.getAllRooms();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Room Updated Successfully'
              });
            }
          });
      }
      this.sidebarVisible = false;
    }
  }

  deleteRoom(room: Room) {
    this.confirmationService.confirm({
      header: 'Delete Room',
      message: 'Are you sure you want to delete this Room you can\'t undo this action after confirmation',
      accept: () => {
        this.roomService.delete(room.id)
          .subscribe({
            next: () => {
              this.getAllRooms();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Room Deleted Successfully'
              });
            }
          });
      }
    });
  }

  updateRoom(room: Room) {
    this.room = room;
    this.sidebarVisible = true;
    this.operation = 'update';
  }

  cancel() {
    this.sidebarVisible = false;
  }

  private getAllRooms() {
    this.roomService.findAll()
      .subscribe({
        next: (data) => {
          this.rooms = data;
        }
      })
    ;
  }

}
