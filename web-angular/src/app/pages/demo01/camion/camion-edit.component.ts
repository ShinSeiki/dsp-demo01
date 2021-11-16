import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LogTransporteComponent } from 'src/app/core/comp/base-model-component.model';
import { Camion, CamionFilter } from 'src/app/core/model/base-model.model';
import { CamionService, EntityFilterService } from 'src/app/core/service/baseentity.service';
import { BasicUtils } from 'src/app/core/util/basic-utils';
import { NavigationModelCommandComponent } from '../util/component/navigation-model-command.component';
import { CamionDialogComponent } from './camion-dialog.component';

@Component({
  selector: 'app-camion-edit',
  templateUrl: './camion-edit.component.html',
  styleUrls: ['./camion-edit.component.scss']
})
export class CamionEditComponent extends LogTransporteComponent<Camion, CamionFilter> implements OnInit {

  @ViewChild('navEntCommand') navEntCmd: NavigationModelCommandComponent<Camion, number>;

  constructor(serviceCamion: CamionService, private _serviceCommon: EntityFilterService, 
      private _formBuilder: FormBuilder, 
      route: ActivatedRoute, router: Router, 
      private _modalService: NgbModal
  ) {
    super(serviceCamion, _serviceCommon, _formBuilder, route, router, _modalService);
  }

  ngOnInit(): void {
    this.ngOnInitValidation({
      "ccamion": ['', Validators.nullValidator],
      "placaveh": ['', Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z]{3}\\d{3}$')])],
    });

    this.dialogTitle = 'Gestion Logistica Camiones';
  }

  ngAfterViewInit(): void {
    this.navEntCmd.navCommand = this;
  }

  getCodeFromEntity(entity: Camion): number {
    return entity ? entity.ccamion : this.getDefaultNewId();
  }

  doAlmacenamientoLoad(): void {
    this.doAlmacenamientoLoadByType("Bodega");
  }

  doLoad(): void {
    let dialogCamion: NgbModalRef;
    
    dialogCamion = BasicUtils.doShowDialog(CamionDialogComponent, this.modalService);
    dialogCamion.result.then(
      res => {
        if (!res)
          return;

        this.doLoadById(res.ccamion);
      }
    );
  }

}
