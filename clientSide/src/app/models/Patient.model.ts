import {Person} from "./Person.model";
import {Department} from "./Department.model";
import {Room} from "./Room.model";
import {Recipe} from "./Recipe.model";
import {Operation} from "./Operation.model";
import {Bill} from "./Bill.model";
import {Examination} from "./Examination.model";

export interface Patient extends Person {

  dateOfBirth: string;

  patientType: string;

  departmentId: string;

  roomId: string;

  department?: Department;

  room?: Room;

  bills?: Array<Bill>;

  recipes?: Array<Recipe>;

  examinations?: Array<Examination>;

  operations?: Array<Operation>;
}
