import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TipoProducto } from 'src/app/core/model/base-model.model';
import { EntityFilterService } from 'src/app/core/service/baseentity.service';
import { BaseNavRecord } from 'src/app/core/util/base-nav-record';

@Component({
  selector: 'app-tipo-producto-dialog',
  templateUrl: './tipo-producto-dialog.component.html',
  styleUrls: ['./tipo-producto-dialog.component.scss']
})
export class TipoProductoDialogComponent extends BaseNavRecord<TipoProducto, number> implements OnInit {

  constructor(private serviceFilter: EntityFilterService, public modal: NgbActiveModal) { 
    super();
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  doLoad(): void {
    this.serviceFilter.getTipoProductos().subscribe(
      res => this.rows = res
    );
  }

}
