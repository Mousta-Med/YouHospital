import {Component, OnInit} from '@angular/core';
import {Operation} from "../../models/Operation.model";
import {OperationService} from "../../services/operation.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-operation',
  templateUrl: './operation.component.html',
  styleUrls: ['./operation.component.scss']
})
export class OperationComponent implements OnInit{

  operations: Operation[] = [];

  operation!: Operation ;

  operationType: 'update' | 'create' = "create";

  title: string = '';

  visible: boolean = false;
  constructor(
    private operationService: OperationService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
  }

  ngOnInit(): void {
    this.findAllOperation();
  }

  findAllOperation() {
    this.operationService.findAll()
      .subscribe({
        next: (data) => {
          this.operations = data;
        }
      });
  }

  save(newOperation: Operation) {
    if (newOperation) {
      if (this.operationType === 'create') {
        this.operationService.save(newOperation)
          .subscribe({
            next: () => {
              this.findAllOperation();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Created Successfully'
              });
              this.operation = {
                cost: 0, date: "", duration: 0, patientId: "", staffsId: [], time: ""
              };
            }, error: (err) => {
              console.log(err)
              this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: err.error?.error || err.error?.code
              });
            }
          });
      } else if (this.operationType === 'update') {
        this.operationService.update(this.operation.id, newOperation)
          .subscribe({
            next: () => {
              this.findAllOperation();
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

  createOperation() {
    this.title = 'New Operation';
    this.operationType = 'create';
    this.visible = true;
    this.operation = {
      cost: 0, date: "", duration: 0, patientId: "", staffsId: [], time: ""
    };
  }


  deleteOperation(deletedOperation: Operation) {
    this.confirmationService.confirm({
      header: 'Delete Operation',
      message: `Are you sure you want to delete? You can\'t undo this action afterwords`,
      accept: () => {
        this.operationService.delete(deletedOperation.id)
          .subscribe({
            next: () => {
              this.findAllOperation();
              this.messageService.add({
                severity: 'success', summary: 'Operation deleted', detail: `Operation was successfully deleted`
              });
            },error:(err) =>{
              console.log(err);
            }
          });
      }
    });
  }



  updateOperation(updatedOperation: Operation) {
    this.title = 'Update Operation';
    this.operationType = 'update';
    this.operation = updatedOperation;
    this.visible = true;
  }

}
