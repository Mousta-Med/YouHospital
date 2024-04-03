import {Admin} from "./Admin.model";
import {Staff} from "./Staff.model";
import {Patient} from "./Patient.model";

export interface AuthenticationResponse {

  token: string,
  role: 'ADMIN' | 'PATIENT' | 'NURSE' | 'RECEPTIONIST',
  admin?: Admin,
  staff?: Staff,
  patient?: Patient
}
