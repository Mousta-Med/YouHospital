import {Patient} from "./Patient.model";
import {Auditable} from "./Auditable.model";

export interface Bill extends Auditable{

    amount: number;

    patientId: string;


   status: 'PENDING'|'UNPAID'|'PAID';

    patient?: Patient;

}
