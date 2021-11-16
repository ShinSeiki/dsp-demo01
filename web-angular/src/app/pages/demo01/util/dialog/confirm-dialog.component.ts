import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.scss']
})
export class ConfirmDialogComponent implements OnInit {

  title: String = "Accion";

  message: String = "¿Proceder accion?"

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  static doShow(modalService: NgbModal, title: String, message: String): NgbModalRef {
    let modalRef: NgbModalRef;

    modalRef = modalService.open(ConfirmDialogComponent, {
    });
    if (title)
      modalRef.componentInstance.title = title;
    if (message)
      modalRef.componentInstance.message = message;

    return modalRef;
  }

}
