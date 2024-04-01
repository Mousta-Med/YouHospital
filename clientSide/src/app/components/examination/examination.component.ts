import {Component, OnInit} from '@angular/core';
import {Examination} from "../../models/Examination.model";
import {ExaminationService} from "../../services/examination.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-examination',
  templateUrl: './examination.component.html',
  styleUrls: ['./examination.component.scss']
})
export class ExaminationComponent implements  OnInit{

  examinations: Examination[] = []

  examination : Examination = {dateTime: "", patientId: "", problem: "", staffId: ''};

  sidebarVisible: boolean = false;

  operation: 'create' | 'update' = 'create';

  constructor(
    private examinationService: ExaminationService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {
  }
    ngOnInit(): void {
    this.findAllExaminations();
    }
  findAllExaminations(){
    this.examinationService.findAll().subscribe({
      next:(data)=>{
        this.examinations = data
      }
    })
  }

  createExamination() {
    this.sidebarVisible = true;
    this.operation = 'create';
    this.examination = {dateTime: "", patientId: "", problem: "", staffId: ''};
  }

  private getAllExaminations() {
    this.examinationService.findAll()
      .subscribe({
        next: (data) => {
          this.examinations = data;
        }
      })
    ;
  }

  save(examination: Examination) {
    if (examination) {
      if (this.operation === 'create') {
        this.examinationService.save(examination)
          .subscribe({
            next: () => {
              this.getAllExaminations();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Examination Created Successfully'
              });
              this.examination = {dateTime: "", patientId: "", problem: "", staffId: ''};
            }
          });
      } else if (this.operation === 'update') {
        this.examinationService.update(this.examination.id, examination)
          .subscribe({
            next: () => {
              this.getAllExaminations();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Examination Updated Successfully'
              });
            }
          });
      }
      this.sidebarVisible = false;
    }
  }

  deleteExamination(examination: Examination) {
    this.confirmationService.confirm({
      header: 'Delete Examination',
      message: 'Are you sure you want to delete this Examination you can\'t undo this action after confirmation',
      accept: () => {
        this.examinationService.delete(examination.id)
          .subscribe({
            next: () => {
              this.getAllExaminations();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Examination Deleted Successfully'
              });
            }
          });
      }
    });
  }

  updateExamination(examination: Examination) {
    this.examination = examination;
    this.sidebarVisible = true;
    this.operation = 'update';
  }

  cancel() {
    this.sidebarVisible = false;
  }
}

