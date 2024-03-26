import {Component, EventEmitter, OnChanges, OnInit, Output} from '@angular/core';
import {MenuItem} from "primeng/api";
import {SharedService} from "../../services/shared.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit{

  menuItems: Array<MenuItem> = [
    {label: 'Dashboard', icon: 'pi pi-home', routerLink: '/admin'},
    {label: 'Hospital', icon: 'pi pi-building', routerLink: '/admin/hospital'},
    {label: 'Staff', icon: 'pi pi-users', routerLink: '/admin/staff'},
    {label: 'Department', icon: 'pi pi-sitemap', routerLink: '/admin/department'},
    {label: 'Room', icon: 'pi pi-home', routerLink: '/admin/room'},
  ];
  constructor(
    private sharedService: SharedService,
    private route: ActivatedRoute
  ) {
  }

  hasChildren(): boolean {
    return this.route.snapshot.children.length > 0;
  }

  ngOnInit(): void {
    this.sharedService.setMenu(this.menuItems);
  }

}
