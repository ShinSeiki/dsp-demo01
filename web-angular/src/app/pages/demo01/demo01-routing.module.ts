import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlmacenamientoEditComponent } from './almacenamiento/almacenamiento-edit.component';
import { CamionEditComponent } from './camion/camion-edit.component';
import { ClienteEditComponent } from './cliente/cliente-edit.component';
import { FlotaEditComponent } from './flota/flota-edit.component';
import { IndexComponent } from './index.component';
import { TipoProductoEditComponent } from './tipoproducto/tipo-producto-edit.component';


const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Demo 01'
    },
    children: [{
      path: '',
      component: IndexComponent
    }, {
      path: 'cliente',
      redirectTo: 'cliente/0'
    }, {
      path: 'cliente/:id',
      component: ClienteEditComponent
    }, {
      path: 'tipoproducto',
      redirectTo: 'tipoproducto/0'
    }, {
      path: 'tipoproducto/:id',
      component: TipoProductoEditComponent
    }, {
      path: 'almacenamiento',
      redirectTo: 'almacenamiento/0'
    }, {
      path: 'almacenamiento/:id',
      component: AlmacenamientoEditComponent
    }, {
      path: 'camion',
      redirectTo: 'camion/0'
    }, {
      path: 'camion/:id',
      component: CamionEditComponent,
      data: {
        title: 'Camiones'
      }
    }, {
      path: 'flota',
      redirectTo: 'flota/0'
    }, {
      path: 'flota/:id',
      component: FlotaEditComponent
    }]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Demo01RoutingModule { }
