import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { BaseModelNumberComponent, BaseModelNumberComponentWithDialog } from 'src/app/core/comp/base-model-component.model';
import { TipoProducto } from 'src/app/core/model/base-model.model';
import { TipoProductoService } from 'src/app/core/service/baseentity.service';
import { BasicUtils } from 'src/app/core/util/basic-utils';
import { NavigationModelCommandComponent } from '../util/component/navigation-model-command.component';
import { ConfirmDialogComponent } from '../util/dialog/confirm-dialog.component';
import { WaitDialogComponent } from '../util/dialog/wait-dialog.component';
import { TipoProductoDialogComponent } from './tipo-producto-dialog.component';

@Component({
  selector: 'app-tipo-producto-edit',
  templateUrl: './tipo-producto-edit.component.html',
  styleUrls: ['./tipo-producto-edit.component.scss']
})
export class TipoProductoEditComponent extends BaseModelNumberComponentWithDialog<TipoProducto, TipoProducto> implements OnInit {

  @ViewChild('navEntCommand') navEntCmd: NavigationModelCommandComponent<TipoProducto, number>;

  dialogTitle: string = 'Gestion Tipo Producto';

  constructor(private serviceTipoProd: TipoProductoService, private _formBuilder: FormBuilder, 
    route: ActivatedRoute, router: Router, 
    protected modalService: NgbModal
  ) { 
    super(serviceTipoProd, _formBuilder, route, router, modalService);
  }

  ngOnInit(): void {
    super.ngOnInit();

    this.formEntity = this.formBuilder.group({
      "ctipoproducto": ['', Validators.required],
      "vtipoproducto": ['', Validators.required],
    });
  }

  ngAfterViewInit(): void {
    this.navEntCmd.navCommand = this;
  }

  getCodeFromEntity(entity: TipoProducto): number {
    return !entity ? this.getDefaultNewId() : entity.ctipoproducto;
  }

  doSave(): void {
    ConfirmDialogComponent.doShow(this.modalService, this.dialogTitle, 
      "¿Confirmar guardar cambios?").result.then(
        val => {
          let dialogWait: NgbModalRef;

          if (!val)
            return;

          dialogWait = WaitDialogComponent.doShow(this.modalService);
          this.serviceEntity.doSave(this.entity).subscribe(
            res => {
              this.doLoadById(res.ctipoproducto);
            }, this.onSubscribeError.bind(this, dialogWait, this.modalService, this.dialogTitle)
            , () => {
              dialogWait.close();
            }
          );
        }
    );
  }

  doLoad(): void {
    let dialogTipoProd: NgbModalRef;
    
    dialogTipoProd = BasicUtils.doShowDialog(TipoProductoDialogComponent, this.modalService);
    dialogTipoProd.result.then(
      res => {
        if (!res)
          return;

        this.doLoadById(res.ctipoproducto);
      }
    );
  }

}
