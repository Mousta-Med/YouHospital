import { Component } from '@angular/core';
import {MenuItem} from "primeng/api";
import {SharedService} from "../../services/shared.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-nurse',
  templateUrl: './nurse.component.html',
  styleUrls: ['./nurse.component.scss']
})
export class NurseComponent {

  menuItems: Array<MenuItem> = [
    {label: 'Home', icon: 'pi pi-home', routerLink: '/nurse'},
    {label: 'Operation', icon: 'pi pi-cog', routerLink: '/nurse/operation'},
    {label: 'Recipe', icon: 'pi pi-file-edit', routerLink: '/nurse/recipe'},
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
