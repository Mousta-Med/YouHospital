import {Person} from "./Person.model";
import {Staff} from "./Staff.model";

export interface Admin extends Person {

  staffs?: Array<Staff>;
}
