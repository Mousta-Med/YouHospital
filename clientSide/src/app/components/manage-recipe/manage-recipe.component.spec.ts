import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageRecipeComponent } from './manage-recipe.component';

describe('ManageRecipeComponent', () => {
  let component: ManageRecipeComponent;
  let fixture: ComponentFixture<ManageRecipeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageRecipeComponent]
    });
    fixture = TestBed.createComponent(ManageRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
