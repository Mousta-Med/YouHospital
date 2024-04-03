import {Person} from "./Person.model";
import {Department} from "./Department.model";
import {Operation} from "./Operation.model";
import {Recipe} from "./Recipe.model";
import {Examination} from "./Examination.model";

export interface Staff extends Person {

  recruitmentDate: string;

  address: string;

  specialization: string;

  role: 'NURSE' | 'RECEPTIONIST' | 'DOCTOR';

  departmentId: string;

  operationId: string;

  adminId?: string;

  department?: Department;

  operations?: Array<Operation>;

  recipes?: Array<Recipe>;

  examinations?: Array<Examination>;
}
