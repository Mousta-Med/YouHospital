import {Patient} from "./Patient.model";
import {Staff} from "./Staff.model";
import {Auditable} from "./Auditable.model";

export interface Operation extends Auditable {

  date: string;

  time: string;

  duration: number;

  cost: number;

  patientId: string;

  staffId: string;

  patientRes?: Patient;

  staffRes?: Staff;

  patient?: Patient;

  staff?: Staff;
}
