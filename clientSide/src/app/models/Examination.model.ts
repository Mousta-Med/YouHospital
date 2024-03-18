import {Patient} from "./Patient.model";
import {Staff} from "./Staff.model";
import {Auditable} from "./Auditable.model";

export interface Examination extends Auditable{

    problem: string;

    dateTime: string;

    patientId: string;

    staffId: string;

    patient?: Patient;

    staff?: Staff;
}
