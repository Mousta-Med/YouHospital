import {Department} from "./Department.model";
import {Auditable} from "./Auditable.model";

export interface Hospital extends Auditable {

  name: string;

  address: string;

  phone: string;

  department?: Array<Department>;
}
