import {Auditable} from "./Auditable.model";

export interface Person extends Auditable{

  firstName: string;

  lastName: string;

  gender: 'MALE' | 'FEMALE';

  phone: string;

  email: string;

  pass: string;

  identityType: 'NATIONAL_ID' | 'PASSPORT' | 'DRIVING_LICENSE';

  identityCode: string;

}
