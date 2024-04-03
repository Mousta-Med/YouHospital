import {Patient} from "./Patient.model";
import {Staff} from "./Staff.model";
import {Auditable} from "./Auditable.model";

export interface Examination extends Auditable{

    problem: string;

    dateTime: string;

    patientId: string;

    staffId: string;

    patientRes?: Patient;

    patient?: Patient;

    staff?: Staff;
}
