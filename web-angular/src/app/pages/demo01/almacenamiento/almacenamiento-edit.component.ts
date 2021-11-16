import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { BaseModelNumberComponent, BaseModelNumberComponentWithDialog } from 'src/app/core/comp/base-model-component.model';
import { Almacenamiento } from 'src/app/core/model/base-model.model';
import { AlmacenamientoService } from 'src/app/core/service/baseentity.service';
import { BasicUtils } from 'src/app/core/util/basic-utils';
import { NavigationModelCommandComponent } from '../util/component/navigation-model-command.component';
import { ConfirmDialogComponent } from '../util/dialog/confirm-dialog.component';
import { WaitDialogComponent } from '../util/dialog/wait-dialog.component';
import { AlmacenamientoDialogComponent } from './almacenamiento-dialog.component';

@Component({
  selector: 'app-almacenamiento-edit',
  templateUrl: './almacenamiento-edit.component.html',
  styleUrls: ['./almacenamiento-edit.component.scss']
})
export class AlmacenamientoEditComponent extends BaseModelNumberComponentWithDialog<Almacenamiento, Almacenamiento> implements OnInit {

  @ViewChild('navEntCommand') navEntCmd: NavigationModelCommandComponent<Almacenamiento, number>;

  dialogTitle: string = 'Gestion Almacenamiento';

  constructor(private serviceAlmacenamiento: AlmacenamientoService, private _formBuilder: FormBuilder, 
    route: ActivatedRoute, router: Router, 
    protected modalService: NgbModal
  ) {
    super(serviceAlmacenamiento, _formBuilder, route, router, modalService);
  }

  ngOnInit(): void {
    super.ngOnInit();

    this.formEntity = this.formBuilder.group({
      "calmacenamiento": ['', Validators.required],
      "valmacenamiento": ['', Validators.required],
      "vdireccion": ['', Validators.required],
      "talmacenamiento": ['', Validators.required],
      "tregion": ['', Validators.required],
    });
  }

  ngAfterViewInit(): void {
    this.navEntCmd.navCommand = this;
  }

  getCodeFromEntity(entity: Almacenamiento): number {
    return !entity ? this.getDefaultNewId() : entity.calmacenamiento;
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
              this.doLoadById(res.calmacenamiento);
            }, this.onSubscribeError.bind(this, dialogWait, this.modalService, this.dialogTitle)
            , () => {
              dialogWait.close();
            }
          );
        }
    );
  }

  doLoad(): void {
    let dialogAlm: NgbModalRef;
    
    dialogAlm = BasicUtils.doShowDialog(AlmacenamientoDialogComponent, this.modalService);
    dialogAlm.result.then(
      res => {
        if (!res)
          return;

        this.doLoadById(res.calmacenamiento);
      }
    );
  }

}
