import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Demo01RoutingModule } from './demo01-routing.module';
import { IndexComponent } from './index.component';
import { ClienteEditComponent } from './cliente/cliente-edit.component';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { WaitDialogComponent } from './util/dialog/wait-dialog.component';
import { NavigationModelCommandComponent } from './util/component/navigation-model-command.component';
import { ConfirmDialogComponent } from './util/dialog/confirm-dialog.component';
import { InfoDialogComponent } from './util/dialog/info-dialog.component';
import { ClienteDialogComponent } from './cliente/cliente-dialog.component';
import { TipoProductoEditComponent } from './tipoproducto/tipo-producto-edit.component';
import { TipoProductoDialogComponent } from './tipoproducto/tipo-producto-dialog.component';
import { AlmacenamientoEditComponent } from './almacenamiento/almacenamiento-edit.component';
import { AlmacenamientoDialogComponent } from './almacenamiento/almacenamiento-dialog.component';
import { CamionEditComponent } from './camion/camion-edit.component';
import { CamionDialogComponent } from './camion/camion-dialog.component';
import { FlotaEditComponent } from './flota/flota-edit.component';
import { FlotaDialogComponent } from './flota/flota-dialog.component';


@NgModule({
  declarations: [IndexComponent, ClienteEditComponent, WaitDialogComponent, NavigationModelCommandComponent, ConfirmDialogComponent, InfoDialogComponent, ClienteDialogComponent, TipoProductoEditComponent, TipoProductoDialogComponent, AlmacenamientoEditComponent, AlmacenamientoDialogComponent, CamionEditComponent, CamionDialogComponent, FlotaEditComponent, FlotaDialogComponent],
  imports: [
    CommonModule,
    Demo01RoutingModule,
    SharedModule,
  ]
})
export class Demo01Module { }
