import {Component, OnInit} from '@angular/core';
import {Bill} from "../../models/Bill.model";
import {BillService} from "../../services/bill.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.scss']
})
export class BillComponent implements OnInit {

  bills: Bill[] = [];

  bill: Bill = {
    status: 'UNPAID',
    patientId: "",
    amount: 0
  };


  operation: 'update' | 'create' = "create";

  title: string = '';

  visible: boolean = false;

  constructor(
    private billService: BillService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
  }

  ngOnInit(): void {
    this.findAllBill();
  }

  findAllBill() {
    this.billService.findAll()
      .subscribe({
        next: (data) => {
          this.bills = data;
        }
      });
  }

  getSeverity(status: string): string {
    switch (status.toLowerCase()) {
      case 'unpaid':
        return 'pink';
      case 'paid':
        return 'green';
      case 'pending':
        return 'yellow';
      default:
        throw new Error('Invalid status: ' + status);
    }
  }

  save(newBill: Bill) {
    if (newBill) {
      if (this.operation === 'create') {
        this.billService.save(newBill)
          .subscribe({
            next: () => {
              this.findAllBill();
              this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'Competition Created Successfully'
              });
              this.bill = {
                status: 'UNPAID',
                patientId: "",
                amount: 0
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
      } else if (this.operation === 'update') {
        this.billService.update(this.bill.id, newBill)
          .subscribe({
            next: () => {
              this.findAllBill();
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

  createBill() {
    this.title = 'New Bill';
    this.operation = 'create';
    this.visible = true;
    this.bill = {
      status: 'UNPAID',
      patientId: "",
      amount: 0
    };
  }


  deleteBill(deletedBill: Bill) {
    this.confirmationService.confirm({
      header: 'Delete Bill',
      message: `Are you sure you want to delete? You can\'t undo this action afterwords`,
      accept: () => {
        this.billService.delete(deletedBill.id)
          .subscribe({
            next: () => {
              this.findAllBill();
              this.messageService.add({
                severity: 'success', summary: 'Bill deleted', detail: `Bill was successfully deleted`
              });
            }, error: (err) => {
              console.log(err);
            }
          });
      }
    });
  }


  updateBill(updatedBill: Bill) {
    this.title = 'Update Bill';
    this.operation = 'update';
    this.bill = updatedBill;
    this.visible = true;
  }

}
