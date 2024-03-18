import {Department} from "./Department.model";
import {Patient} from "./Patient.model";
import {Auditable} from "./Auditable.model";

export interface Room extends Auditable{

  roomNum: number;

  location: string;

  roomType: 'WARD' | 'SINGLE' | 'DOUBLE' | 'ICU' | 'OR';

  departmentId: string;

  department?: Department;

  patients?: Array<Patient>;
}
