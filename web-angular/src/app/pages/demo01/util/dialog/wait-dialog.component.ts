import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-wait-dialog',
  templateUrl: './wait-dialog.component.html',
  styleUrls: ['./wait-dialog.component.scss']
})
export class WaitDialogComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  static doShow(modalService: NgbModal): NgbModalRef {
    let modalRef: NgbModalRef;

    modalRef = modalService.open(WaitDialogComponent, {
      backdrop: 'static',
      centered: true,
      keyboard: false,
      size: 'sm',
    });

    return modalRef;
  }

}
