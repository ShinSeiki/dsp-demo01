import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Almacenamiento, BaseModel, Camion, CamionFilter, Cliente, Flota, FlotaFilter, LogTransporte, LogTransporteFilter, TipoProducto } from '../model/base-model.model';

export abstract class BaseRowsService {

  constructor(protected http: HttpClient, public uri: String) { }

  getRows<U extends BaseModel<U, S>, S>(filter: Object = {}, appendPath: String = ''): Observable<U[]> {
    let query = '';

    if (!appendPath)
      appendPath = '';

    for (let key in filter) {
      query = `${query}&${key}=${filter[key]}`;
    }

    const uri = `${this.uri}${appendPath}?${query}`;

    return this.http.get<U[]>(uri).pipe(
      tap(
        response => console.log(`getRows: response [${response.length}]`)
      )
    );
  }

}

export abstract class BaseDetailService<T extends BaseModel<T, S>, U extends BaseModel<U, S>, S> {

  constructor(protected http: HttpClient, public uri: String) { }

  abstract getEntityClass(): new () => T;

  newEntityInstance(): T {
    let c: new() => T = this.getEntityClass();

    return new c();
  }

  getRows(filter: Object): Observable<U[]> {
    let query = '';

    for (let key in filter) {
      query = `${query}&${key}=${filter[key]}`;
    }

    const uri = `${this.uri}?${query}`;

    return this.http.get<U[]>(uri).pipe(
      tap(
        response => console.log(`getRows: response []`)
      )
    );
  }

  getEntity(centity: S): Observable<T> {
    const uri = `${this.uri}/${centity}`;

    return this.http.get<T>(uri).pipe(
      tap(
        response => console.log(`getEntity: response []`)
      ), map(
        response => {
          let value: T;

          value = this.newEntityInstance();
          value = Object.assign(value, response);
          return value;
        }
      )
    );
  }

  doSave(entity: T): Observable<T> {
    return this.http.post<T>(`${this.uri}/`, entity).pipe(
      tap(
        response => console.log(`doSave: response []`)
      )
    );
  }

  doDelete(centity: S): Observable<T> {
    return this.http.delete<T>(`${this.uri}/${centity}`).pipe(
      tap(
        response => console.log(`doDelete: response []`)
      )
    );
  }

}

@Injectable({
  providedIn: 'root'
})
export class EntityFilterService extends BaseRowsService {

  static URI = `${environment.uri}`;

  constructor(http: HttpClient) {
    super(http, EntityFilterService.URI);
   }

  getClientes(ccliente: number, filter: string): Observable<Cliente[]> {
    if (!ccliente)
      ccliente = 0;
    if (!filter)
      filter = '';
    
    filter = encodeURI(filter);

    let options = {
      ccliente: ccliente,
      filter: filter
    }

    return this.getRows<Cliente, number>(options, '/cliente').pipe(
      tap(
        response => console.log(`getClientes: response [${response.length}]`)
      )
    );
  }

  getTipoProductos(): Observable<TipoProducto[]> {
    return this.getRows<TipoProducto, number>({}, '/tipoproducto').pipe(
      tap(
        response => console.log(`getTipoProductos: response [${response.length}]`)
      )
    );
  }

  getAlmacenamientos(calmacenamiento: number, filter: string, talmacenamiento: string, tregion: string): Observable<Almacenamiento[]> {
    if (!calmacenamiento)
      calmacenamiento = 0;
    if (!filter)
      filter = '';
    if (!talmacenamiento)
      talmacenamiento = '';  
    if (!tregion)
      tregion = '';

    let options = {
      calmacenamiento: calmacenamiento,
      region: tregion,
      tipo: talmacenamiento,
      filter: filter,
    };

    return this.getRows<Almacenamiento, number>(options, '/almacenamiento').pipe(
      tap(
        response => console.log(`getAlmacenamientos: response [${response.length}]`)
      )
    );
  }

  getCamiones(ccamion: number, filterCliente: string, filterPlaca: string, filterNumguia: string): Observable<CamionFilter[]> {
    if (!ccamion)
      ccamion = 0;
    if (!filterCliente)
      filterCliente = '';
    if (!filterPlaca)
      filterPlaca = '';
    if (!filterNumguia)
      filterNumguia = '';

    let options = {
      ccamion: ccamion,
      cliente: filterCliente,
      placa: filterPlaca,
      numguia: filterNumguia,
    };

    return this.getRows<CamionFilter, number>(options, '/camion').pipe(
      tap(
        response => console.log(`getCamiones: response [${response.length}]`)
      )
    );
  }

  getFlotas(cflota: number, filterCliente: string, filterFlota: string, filterNumguia: string): Observable<FlotaFilter[]> {
    if (!cflota)
      cflota = 0;
    if (!filterCliente)
      filterCliente = '';
    if (!filterFlota)
      filterFlota = '';
    if (!filterNumguia)
      filterNumguia = '';

    let options = {
      cflota: cflota,
      cliente: filterCliente,
      flota: filterFlota,
      numguia: filterNumguia,
    };

    return this.getRows<FlotaFilter, number>(options, '/flota').pipe(
      tap(
        response => console.log(`getFlotas: response [${response.length}]`)
      )
    );
  }

}

@Injectable({
  providedIn: 'root'
})
export class ClienteService extends BaseDetailService<Cliente, Cliente, number> {

  static URI = `${environment.uri}/cliente`;

  constructor(http: HttpClient) {
    super(http, ClienteService.URI);
  }

  getEntityClass(): new () => Cliente {
    return Cliente;
  }

}

@Injectable({
  providedIn: 'root'
})
export class TipoProductoService extends BaseDetailService<TipoProducto, TipoProducto, number> {

  static URI = `${environment.uri}/tipoproducto`;

  constructor(http: HttpClient) {
    super(http, TipoProductoService.URI);
  }

  getEntityClass(): new () => TipoProducto {
    return TipoProducto;
  }

}

@Injectable({
  providedIn: 'root'
})
export class AlmacenamientoService extends BaseDetailService<Almacenamiento, Almacenamiento, number> {

  static URI = `${environment.uri}/almacenamiento`;

  constructor(http: HttpClient) {
    super(http, AlmacenamientoService.URI);
  }

  getEntityClass(): new () => Almacenamiento {
    return Almacenamiento;
  }

}

export abstract class LogTransporteService<T extends LogTransporte<T, ID>, S extends LogTransporteFilter<S, ID>, ID> 
    extends BaseDetailService<T, S, ID>
{

  constructor(private _http: HttpClient, _uri: string) {
    super(_http, _uri);
  }

  doSave(camion: T): Observable<T> {
    let camionSend: T;

    camionSend = Object.assign({}, camion);
    camionSend.fregistro = `${camion.fregistro}T00:00:00.0`;
    camionSend.fentrega = `${camion.fentrega}T00:00:00.0`;

    return super.doSave(camionSend);
  }

}

@Injectable({
  providedIn: 'root'
})
export class CamionService extends LogTransporteService<Camion, CamionFilter, number> {

  static URI = `${environment.uri}/camion`;

  constructor(http: HttpClient) {
    super(http, CamionService.URI);
  }

  getEntityClass(): new () => Camion {
    return Camion;
  }

}

@Injectable({
  providedIn: 'root'
})
export class FlotaService extends LogTransporteService<Flota, FlotaFilter, number> {

  static URI = `${environment.uri}/flota`;

  constructor(http: HttpClient) {
    super(http, FlotaService.URI);
  }

  getEntityClass(): new () => Flota {
    return Flota;
  }

}
