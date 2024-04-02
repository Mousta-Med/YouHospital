import {Staff} from "./Staff.model";
import {Patient} from "./Patient.model";
import {Auditable} from "./Auditable.model";

export interface Recipe extends Auditable{

  medications: string;

  instructions: string;

  startDate: string;

  endDate: string;

  patientId: string;

  staffId: string;

  patient?: Patient;

  staff?: Staff;

}
