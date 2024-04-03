import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Patient} from "../../models/Patient.model";
import {Staff} from "../../models/Staff.model";
import {Recipe} from "../../models/Recipe.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PatientService} from "../../services/patient.service";
import {StaffService} from "../../services/staff.service";

@Component({
  selector: 'app-manage-recipe',
  templateUrl: './manage-recipe.component.html',
  styleUrls: ['./manage-recipe.component.scss']
})
export class ManageRecipeComponent implements OnInit, OnChanges {


  patients: Patient[] = [];
  staffs: Staff[] = [];

  minDate = new Date();

  endDate = new Date(this.minDate);


  @Input()
  recipe: Recipe = {
    medications: "", endDate: "", instructions: "", patientId: "", staffId: "", startDate: ""
  };

  @Output()
  submit: EventEmitter<Recipe> = new EventEmitter<Recipe>();

  @Output()
  cancel: EventEmitter<void> = new EventEmitter<void>();

  recipeForm: FormGroup = new FormGroup({
    medications: new FormControl('', [Validators.required]),
    instructions: new FormControl('', [Validators.required]),
    endDate: new FormControl(0, [Validators.required]),
    startDate: new FormControl(0, [Validators.required]),
    patientId: new FormControl('no', [Validators.required, Validators.minLength(3)]),
    staffId: new FormControl([], [Validators.required]),
  });

  constructor(
    private patientService: PatientService,
    private staffService: StaffService,
  ) {
  }

  ngOnInit() {
    this.endDate.setDate(this.endDate.getDate() + 1);
    this.patientService.findAll().subscribe({
      next: (data) => {
        this.patients = data;
      }
    });
    this.staffService.findAll().subscribe({
      next: (data) => {
        this.staffs = data.filter(item => item.role === "DOCTOR");
      }
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.recipeForm.reset();
    if (this.recipe) {
      this.recipeForm.setValue({
        medications: this.recipe.medications,
        instructions: this.recipe.instructions,
        endDate: this.recipe.endDate,
        startDate: this.recipe.startDate,
        patientId: this.recipe.patientId,
        staffId: this.recipe.staffId,
      });
    }
  }

  onSubmit() {
    this.recipe = this.recipeForm.value;
    this.submit.emit(this.recipe);
    this.recipeForm.reset();
  }

  onCancel() {
    this.cancel.emit();
    this.recipeForm.reset();
  }
}
