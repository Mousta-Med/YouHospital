import {Component, OnInit} from '@angular/core';
import {Recipe} from "../../models/Recipe.model";
import {RecipeService} from "../../services/recipe.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.scss']
})
export class RecipeComponent implements OnInit {

  recipes: Recipe[] = [];

  recipe!: Recipe;

  recipeType: 'update' | 'create' = "create";

  title: string = '';

  visible: boolean = false;

  constructor(
    private recipeService: RecipeService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
  }

  ngOnInit(): void {
    this.findAllRecipe();
  }

  findAllRecipe() {
    this.recipeService.findAll()
      .subscribe({
        next: (data) => {
          this.recipes = data;
        }
      });
  }

  save(newRecipe: Recipe) {
    if (newRecipe) {
      if (this.recipeType === 'create') {
        this.recipeService.save(newRecipe)
          .subscribe({
            next: () => {
              this.findAllRecipe();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Created Successfully'
              });
              this.recipe = {
                medications: "", endDate: "", instructions: "", patientId: "", staffId: "", startDate: ""
              };
            }, error: (err) => {
              this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: err.error?.endDate || err.error?.code
              });
            }
          });
      } else if (this.recipeType === 'update') {
        this.recipeService.update(this.recipe.id, newRecipe)
          .subscribe({
            next: () => {
              this.findAllRecipe();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Updated Successfully'
              });
            }
          });
      }
      this.visible = false;
    }
  }

  cancel() {
    this.visible = false;
  }

  createRecipe() {
    this.title = 'New Recipe';
    this.recipeType = 'create';
    this.visible = true;
    this.recipe = {
      medications: "", endDate: "", instructions: "", patientId: "", staffId: "", startDate: ""
    };
  }


  deleteRecipe(deletedRecipe: Recipe) {
    this.confirmationService.confirm({
      header: 'Delete Recipe',
      message: `Are you sure you want to delete? You can\'t undo this action afterwords`,
      accept: () => {
        this.recipeService.delete(deletedRecipe.id)
          .subscribe({
            next: () => {
              this.findAllRecipe();
              this.messageService.add({
                severity: 'success', summary: 'Recipe deleted', detail: `Recipe was successfully deleted`
              });
            }, error: (err) => {
              console.log(err);
            }
          });
      }
    });
  }


  updateRecipe(updatedRecipe: Recipe) {
    this.title = 'Update Recipe';
    this.recipeType = 'update';
    this.recipe = updatedRecipe;
    this.visible = true;
  }

}
