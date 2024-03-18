import {Staff} from "./Staff.model";
import {Patient} from "./Patient.model";
import {Room} from "./Room.model";
import {Hospital} from "./Hospital.model";
import {Auditable} from "./Auditable.model";

export interface Department extends Auditable{

    name: string;

    hospitalId: string;

    hospital: Hospital;

    staffs?: Array<Staff>;

    patients?: Array<Patient>;

    rooms?: Array<Room>;

}
