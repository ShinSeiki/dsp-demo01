import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-info-dialog',
  templateUrl: './info-dialog.component.html',
  styleUrls: ['./info-dialog.component.scss']
})
export class InfoDialogComponent implements OnInit {

  classAlert: String = 'alert alert-primary';

  title: String = "Accion";

  message: String = "Info accion."

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  static doShow(modalService: NgbModal, classAlert: String, title: String, message: String): NgbModalRef {
    let modalRef: NgbModalRef;

    modalRef = modalService.open(InfoDialogComponent, {
    });
    if (classAlert)
      modalRef.componentInstance.classAlert = classAlert;
    if (title)
      modalRef.componentInstance.title = title;
    if (message)
      modalRef.componentInstance.message = message;

    return modalRef;
  }

}
