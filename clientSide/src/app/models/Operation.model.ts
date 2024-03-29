import {Patient} from "./Patient.model";
import {Staff} from "./Staff.model";
import {Auditable} from "./Auditable.model";

export interface Operation extends Auditable{

  date: string;

  time: string;

  duration: string;

  cost: number;

  patientId: string;

  staffsId: Array<string>;

  patient?: Patient;

  staffs?: Array<Staff>;
}
