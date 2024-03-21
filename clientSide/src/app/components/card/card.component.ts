import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Staff} from "../../models/Staff.model";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {

  @Input() staff!: Staff;

  @Output() delete: EventEmitter<Staff> = new EventEmitter<Staff>();

  @Output() update: EventEmitter<Staff> = new EventEmitter<Staff>();

  onDelete(member: Staff) {
    this.delete.emit(member);
  }

  onUpdate(member: Staff) {
    this.update.emit(member);
  }
}
