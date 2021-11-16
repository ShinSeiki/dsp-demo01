import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Cliente } from 'src/app/core/model/base-model.model';
import { EntityFilterService } from 'src/app/core/service/baseentity.service';
import { BaseNavRecord } from 'src/app/core/util/base-nav-record';

@Component({
  selector: 'app-cliente-dialog',
  templateUrl: './cliente-dialog.component.html',
  styleUrls: ['./cliente-dialog.component.scss']
})
export class ClienteDialogComponent extends BaseNavRecord<Cliente, number> implements OnInit {

  formFilter: FormGroup;

  filterText: string;

  constructor(private serviceFilter: EntityFilterService, private formBuilder: FormBuilder, 
      public modal: NgbActiveModal
  ) { 
    super();
  }

  ngOnInit(): void {
    super.ngOnInit();

    this.formFilter = this.formBuilder.group({
      "filterText": ['', Validators.required],
    });
  }

  doLoad(): void {
    this.serviceFilter.getClientes(0, this.filterText).subscribe(
      res => {
        this.rows = res;
      }
    );
  }

  static doShow(modalService: NgbModal): NgbModalRef {
    let modalRef: NgbModalRef;

    modalRef = modalService.open(ClienteDialogComponent, {
      size: 'lg',
      keyboard: false
    });

    return modalRef;
  }

}
