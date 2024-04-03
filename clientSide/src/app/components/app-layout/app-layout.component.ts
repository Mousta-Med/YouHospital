import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MenuItem} from "primeng/api";
import {SharedService} from "../../services/shared.service";

@Component({
  selector: 'app-app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.scss']
})
export class AppLayoutComponent implements OnInit {

  menus: Array<MenuItem> = [];

  constructor(
    private router: Router,
    private sharedService: SharedService,
    private cdr: ChangeDetectorRef
  ) {
  }

  ngOnInit(): void {
    this.sharedService.getMenu().subscribe(menu => {
      this.menus = menu;
      this.cdr.detectChanges();
    });
  }


  logOut() {
    localStorage.removeItem('user');
    this.router.navigate(['login']);
  }

}
