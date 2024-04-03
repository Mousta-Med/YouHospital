import {Component, OnInit} from '@angular/core';
import {Hospital} from "../../models/Hospital.model";
import {HospitalService} from "../../services/hospital.service";
import {MessageService} from "primeng/api";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-hospital',
  templateUrl: './hospital.component.html',
  styleUrls: ['./hospital.component.scss']
})
export class HospitalComponent implements OnInit {

  hospital: Hospital = {name: '', address: '', phone: ''}

  hospitalForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
  })

  constructor(
    private hospitalService: HospitalService,
    private messageService: MessageService
  ) {
  }

  getHospital() {
    this.hospitalService.find('00000000-0000-0064-0000-000000000064').subscribe({
      next: (data: Hospital) => {
        this.hospital = data;
        this.hospitalForm.setValue({
          name: this.hospital.name,
          address: this.hospital.address,
          phone: this.hospital.phone,
        });
      }
    })
  }

  ngOnInit() {
    this.getHospital();
  }


  updateHospital() {
    this.hospitalService.update('00000000-0000-0064-0000-000000000064', this.hospitalForm.value).subscribe({
      next: (data) => {
        this.getHospital();
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Hospital Updated Successfully'
        });
      }
    })
  }
}
