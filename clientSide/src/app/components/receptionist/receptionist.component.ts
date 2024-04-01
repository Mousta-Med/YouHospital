import {Component, OnInit} from '@angular/core';
import {StaffService} from "../../services/staff.service";
import {SharedService} from "../../services/shared.service";
import {ActivatedRoute} from "@angular/router";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-receptionist',
  templateUrl: './receptionist.component.html',
  styleUrls: ['./receptionist.component.scss']
})
export class ReceptionistComponent implements  OnInit{

  menuItems: Array<MenuItem> = [
    {label: 'Home', icon: 'pi pi-home', routerLink: '/receptionist'},
    {label: 'Patients', icon: 'pi pi-users', routerLink: '/receptionist/patient'},
    {label: 'Examination', icon: 'pi pi-file-excel', routerLink: '/receptionist/examination'},
    {label: 'Bills', icon: 'pi pi-money-bill', routerLink: '/receptionist/bill'},
  ];

  constructor(
    private sharedService: SharedService,
    private route: ActivatedRoute
  ) {
  }

  hasChildren(): boolean {
    return this.route.snapshot.children.length > 0;
  }

  ngOnInit() {
    this.sharedService.setMenu(this.menuItems);
  }
}
