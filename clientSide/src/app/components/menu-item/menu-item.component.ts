import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.scss']
})
export class MenuItemComponent {

  routeFromUrl: string;

  @Input()
  menuItem: MenuItem = {};

  constructor(
    private router: Router
  ) {
    this.routeFromUrl = this.router.url;
  }

}
