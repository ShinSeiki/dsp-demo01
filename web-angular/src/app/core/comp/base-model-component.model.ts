import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { AlmacenamientoDialogComponent } from "src/app/pages/demo01/almacenamiento/almacenamiento-dialog.component";
import { ClienteDialogComponent } from "src/app/pages/demo01/cliente/cliente-dialog.component";
import { ConfirmDialogComponent } from "src/app/pages/demo01/util/dialog/confirm-dialog.component";
import { WaitDialogComponent } from "src/app/pages/demo01/util/dialog/wait-dialog.component";
import { Almacenamiento, BaseModel, Cliente, LogTransporte, LogTransporteFilter, TipoProducto } from "../model/base-model.model";
import { BaseDetailService, EntityFilterService, LogTransporteService, TipoProductoService } from "../service/baseentity.service";
import { BasicUtils } from "../util/basic-utils";

export interface InavEntityCmd<T extends BaseModel<T, S>, S> {

  getCurrent(): T;

  doSave(): void;

  doNew(): void;

  doReload(): void;

  doLoad(): void;

  doLoadById(cseggrupo: S): void;

  doDelete(): void;

  getDefaultNewId(): S;

  getCodeFromEntity(entity: T): S;

}

export abstract class BaseModelComponent<T extends BaseModel<T, ID>, U extends BaseModel<U, ID>, ID> implements InavEntityCmd<T, ID> {

  entity: T;

  formEntity: FormGroup;

  constructor(protected serviceEntity: BaseDetailService<T, U, ID>, protected formBuilder: FormBuilder,
    protected route: ActivatedRoute, protected router: Router) {
  }

  getCurrent(): T {
    return this.entity;
  }
  
  
  abstract getCodeFromEntity(entity: T): ID;

  doSave(): void {
    throw new Error("Method not implemented.");
  }
  doLoad(): void {
    throw new Error("Method not implemented.");
  }
  doDelete(): void {
    throw new Error("Method not implemented.");
  }

  doReload(): void {
    let centity: ID;
    if (this.entity)
      centity = this.getCodeFromEntity(this.entity);
    else 
      centity = this.getDefaultNewId();
    
    this.doLoadById(centity);
  }

  ngOnInit(): void {
    this.entity = this.serviceEntity.newEntityInstance();
    
    this.route.paramMap.subscribe(params => {
      let value: ID;
      value = this.getValueToID(params.get('id'));
      
      this.doLoadById(value);
    });
  }
  
  doLoadEntity(entity: T): void {
    this.entity = entity;
  }

  doLoadById(centity: ID): void {
    this.serviceEntity.getEntity(centity).subscribe(
      res => {
        this.doLoadEntity(res);
      }, err => {
        console.log(err);
      }
    );
  }

  doNew(): void {
    this.doLoadById(this.getDefaultNewId());
    this.router.navigate([`../0`], { 
      relativeTo: this.route
    });
  }

  abstract getValueToID(value: string): ID;

  abstract getDefaultNewId(): ID;

  getValueToNumber(value: string): number {
    let valueNum: number = Number(value);
    if (isNaN(+value))
      valueNum = 0;

    return valueNum;
  }

  getNewNumber(): number {
      return 0;
  }

  onSubscribeError(dialogWait: NgbModalRef, modalService: NgbModal, dialogTitle: string, err: any) {
    dialogWait.close();

    BasicUtils.doShowHttpResponseErrorDialog(err, modalService, dialogTitle);
  }

}

export abstract class BaseModelNumberComponent<T extends BaseModel<T, number>, U extends BaseModel<U, number>> 
    extends BaseModelComponent<T, U, number> 
{

  getValueToID(value: string): number {
    return this.getValueToNumber(value);
  }

  getDefaultNewId(): number {
    return 0;
  }

}

export abstract class BaseModelNumberComponentWithDialog<T extends BaseModel<T, number>, U extends BaseModel<U, number>>
    extends BaseModelNumberComponent<T, U> 
{

  dialogTitle: string = 'Gestion T';

  constructor(protected serviceEntity: BaseDetailService<T, U, number>, protected formBuilder: FormBuilder,
      protected route: ActivatedRoute, protected router: Router, 
      protected modalService: NgbModal
  ) {
    super(serviceEntity, formBuilder, route, router);
  }

  doDelete(): void {
    if (this.getDefaultNewId() == this.getCodeFromEntity(this.entity))
      return;
    if (!this.formEntity.valid)
      return;
    
    ConfirmDialogComponent.doShow(this.modalService, this.dialogTitle, 
      "¿Confirmar guardar cambios?").result.then(
        val => {
          let dialogWait: NgbModalRef;

          if (!val)
            return;
          
          dialogWait = WaitDialogComponent.doShow(this.modalService);
          this.serviceEntity.doDelete(this.getCodeFromEntity(this.entity)).subscribe(
            res => {
              this.doLoadById(this.getDefaultNewId());
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
}

export abstract class LogTransporteComponent<T extends LogTransporte<T, number>, U extends LogTransporteFilter<U, number>> 
    extends BaseModelNumberComponentWithDialog<T, U> 
{

  bodega: Almacenamiento;

  cliente: Cliente;

  cacheTipoProds: TipoProducto[] = [];

  dialogTitle: string = 'Gestion Logistica';

  constructor(protected serviceEntity: LogTransporteService<T, U, number>, 
    protected serviceCommon: EntityFilterService, 
    protected formBuilder: FormBuilder,
    protected route: ActivatedRoute, protected router: Router,
    protected modalService: NgbModal
  ) {
    super(serviceEntity, formBuilder, route, router, modalService);
  }

  ngOnInitValidation(valExtra: any): void {
    let val: Object;
    let valFinal: Object;
    
    this.bodega = new Almacenamiento();
    this.cliente = new Cliente();

    super.ngOnInit();

    this.serviceCommon.getTipoProductos().subscribe(
      res => this.cacheTipoProds = res
    );

    val = {
      "ctipoproducto": ['', Validators.required],
      "valmacenamiento": ['', Validators.nullValidator],
      "vnombre": ['', Validators.nullValidator],
      "vapellido": ['', Validators.nullValidator],
      "fregistro": ['', Validators.nullValidator],
      "fentrega": ['', Validators.nullValidator],
      "numguia": ['', Validators.compose([Validators.required, Validators.pattern('^\\w{10}$')])],
      "cantidad": ['', Validators.nullValidator],
      "precioenvio": ['', Validators.nullValidator],
    };

    valFinal = Object.assign(val, valExtra);
    this.formEntity = this.formBuilder.group(valFinal);
  }

  doLoadEntity(camion: T): void {
    super.doLoadEntity(camion);

    if (camion.fregistro)
      camion.fregistro = camion.fregistro.substring(0, 10);
    if (camion.fentrega)
      camion.fentrega = camion.fentrega.substring(0, 10);
    
    this.bodega = new Almacenamiento();
    if (camion.calmacenamiento) {
      this.serviceCommon.getAlmacenamientos(camion.calmacenamiento, '', '', '').subscribe(
        rows => {
          if (rows && rows.length > 0)
            this.doAlmacenamientoSel(rows[0]);
        }
      );
    }

    this.cliente = new Cliente();
    if (camion.ccliente) {
      this.serviceCommon.getClientes(camion.ccliente, '').subscribe(
        rows => {
          if (rows && rows.length > 0)
            this.doClienteSel(rows[0]);
        }
      );
    }
  }

  doAlmacenamientoLoadByType(talmacenamiento: string): void {
    let dialogAlm: NgbModalRef;

    dialogAlm = BasicUtils.doShowDialog(AlmacenamientoDialogComponent, this.modalService);
    dialogAlm.componentInstance.filterTAlmacenamiento = talmacenamiento;
    dialogAlm.componentInstance.showType = false;
    dialogAlm.result.then(
      res => {
        this.doAlmacenamientoSel(res);
      }
    );
  }

  doAlmacenamientoSel(alm: Almacenamiento): void {
    this.bodega = alm;
    this.entity.calmacenamiento = alm.calmacenamiento;
  }

  doClienteLoad(): void {
    let dialogCliente: NgbModalRef;

    dialogCliente = BasicUtils.doShowDialog(ClienteDialogComponent, this.modalService);
    dialogCliente.result.then(
      cli => {
        this.doClienteSel(cli);
      }
    );
  }

  doClienteSel(cli: Cliente): void {
    this.cliente = cli;
    this.entity.ccliente = cli.ccliente;
  }

  doSave(): void {
    if (!this.formEntity.valid)
      return;

    ConfirmDialogComponent.doShow(this.modalService, this.dialogTitle, 
      "¿Confirmar guardar cambios?").result.then(
        val => {
          let dialogWait: NgbModalRef;

          if (!val)
            return;

          dialogWait = WaitDialogComponent.doShow(this.modalService);
          this.serviceEntity.doSave(this.entity).subscribe(
            res => {
              this.doLoadById(this.getCodeFromEntity(res));
            }, this.onSubscribeError.bind(this, dialogWait, this.modalService, this.dialogTitle)
            , () => {
              dialogWait.close();
            }
          );
        }
    );
  }

}
