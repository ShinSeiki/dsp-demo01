import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { BaseModelComponent, BaseModelNumberComponent, BaseModelNumberComponentWithDialog } from 'src/app/core/comp/base-model-component.model';
import { Cliente } from 'src/app/core/model/base-model.model';
import { ClienteService } from 'src/app/core/service/baseentity.service';
import { BasicUtils } from 'src/app/core/util/basic-utils';
import { NavigationModelCommandComponent } from '../util/component/navigation-model-command.component';
import { ConfirmDialogComponent } from '../util/dialog/confirm-dialog.component';
import { WaitDialogComponent } from '../util/dialog/wait-dialog.component';
import { ClienteDialogComponent } from './cliente-dialog.component';

@Component({
  selector: 'app-cliente-edit',
  templateUrl: './cliente-edit.component.html',
  styleUrls: ['./cliente-edit.component.scss']
})
export class ClienteEditComponent extends BaseModelNumberComponentWithDialog<Cliente, Cliente> implements OnInit {
  
  @ViewChild('navEntCommand') navEntCmd: NavigationModelCommandComponent<Cliente, number>;

  dialogTitle: string = 'Gestion Cliente';

  constructor(private serviceCliente: ClienteService, private _formBuilder: FormBuilder, 
      route: ActivatedRoute, router: Router, 
      protected modalService: NgbModal
  ) {
    super(serviceCliente, _formBuilder, route, router, modalService);
  }

  ngOnInit(): void {
    super.ngOnInit();

    this.formEntity = this.formBuilder.group({
      "ccliente": ['', Validators.required],
      "vnombre": ['', Validators.required],
      "vapellido": ['', Validators.required],
    });
  }

  ngAfterViewInit(): void {
    this.navEntCmd.navCommand = this;
  }

  getCodeFromEntity(entity: Cliente): number {
    if (entity == null)
      return this.getDefaultNewId();
    else
      return entity.ccliente;
  }

  doSave(): void {
    let self = this;

    ConfirmDialogComponent.doShow(this.modalService, this.dialogTitle, 
      "¿Confirmar guardar cambios?").result.then(
        val => {
          let dialogWait: NgbModalRef;

          if (!val)
            return;

          dialogWait = WaitDialogComponent.doShow(this.modalService);
          this.serviceCliente.doSave(this.entity).subscribe(
            res => {
              this.doLoadById(res.ccliente);
            }, err => {
              dialogWait.close();

              BasicUtils.doShowHttpResponseErrorDialog(err, this.modalService, this.dialogTitle);
            }, () => {
              dialogWait.close();
            }
          );
        }
    );
  }

  doLoad(): void {
    let dialogCliente;

    dialogCliente = ClienteDialogComponent.doShow(this.modalService);
    dialogCliente.result.then(
      res => {
        if (!res)
          return;

        this.doLoadById(res.ccliente);
      }
    );
  }

}
