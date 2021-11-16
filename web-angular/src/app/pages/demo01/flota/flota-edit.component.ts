import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LogTransporteComponent } from 'src/app/core/comp/base-model-component.model';
import { Flota, FlotaFilter } from 'src/app/core/model/base-model.model';
import { EntityFilterService, FlotaService } from 'src/app/core/service/baseentity.service';
import { BasicUtils } from 'src/app/core/util/basic-utils';
import { NavigationModelCommandComponent } from '../util/component/navigation-model-command.component';
import { FlotaDialogComponent } from './flota-dialog.component';

@Component({
  selector: 'app-flota-edit',
  templateUrl: './flota-edit.component.html',
  styleUrls: ['./flota-edit.component.scss']
})
export class FlotaEditComponent extends LogTransporteComponent<Flota, FlotaFilter> implements OnInit {

  @ViewChild('navEntCommand') navEntCmd: NavigationModelCommandComponent<Flota, number>;

  constructor(serviceFlota: FlotaService, private _serviceCommon: EntityFilterService, 
    private _formBuilder: FormBuilder, 
    route: ActivatedRoute, router: Router, 
    private _modalService: NgbModal
  ) { 
    super(serviceFlota, _serviceCommon, _formBuilder, route, router, _modalService);
  }

  ngOnInit(): void {
    this.ngOnInitValidation({
      "cflota": ['', Validators.nullValidator],
      "numflota": ['', Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z]{3}\\d{4}[a-zA-Z]{1}$')])],
    });

    this.dialogTitle = 'Gestion Logistica Flota';
  }

  ngAfterViewInit(): void {
    this.navEntCmd.navCommand = this;
  }

  getCodeFromEntity(entity: Flota): number {
    return entity ? entity.cflota : this.getDefaultNewId();
  }

  doAlmacenamientoLoad(): void {
    this.doAlmacenamientoLoadByType("Puerto");
  }

  doLoad(): void {
    let dialogCamion: NgbModalRef;
    
    dialogCamion = BasicUtils.doShowDialog(FlotaDialogComponent, this.modalService);
    dialogCamion.result.then(
      res => {
        if (!res)
          return;

        this.doLoadById(res.cflota);
      }
    );
  }

}
