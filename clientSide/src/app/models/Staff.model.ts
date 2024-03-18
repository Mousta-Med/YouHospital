import {Person} from "./Person.model";
import {Department} from "./Department.model";
import {Operation} from "./Operation.model";
import {Recipe} from "./Recipe.model";
import {Examination} from "./Examination.model";

export interface Staff extends Person {

  recruitmentDate: string;

  address: string;

  specialization: string;

  role: 'DOCTOR' | 'NURSE' | 'RECEPTIONIST';

  departmentId: string;

  operationId: string;

  adminId: string;

  department?: Department;

  operation?: Operation;

  recipes?: Array<Recipe>;

  examinations?: Array<Examination>;
}
