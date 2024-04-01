import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageExaminationComponent } from './manage-examination.component';

describe('ManageExaminationComponent', () => {
  let component: ManageExaminationComponent;
  let fixture: ComponentFixture<ManageExaminationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageExaminationComponent]
    });
    fixture = TestBed.createComponent(ManageExaminationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
