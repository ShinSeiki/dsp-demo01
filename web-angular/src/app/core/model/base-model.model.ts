
export abstract class BaseModel <T extends BaseModel<T, ID>, ID> {

    abstract getID(): ID;

}

export class Cliente extends BaseModel<Cliente, number> {

    ccliente: number = 0;
	
	vnombre: string = '';
	
	vapellido: string = '';

    getID(): number {
        return this.ccliente;
    }

}

export class TipoProducto extends BaseModel<TipoProducto, number> {

    ctipoproducto: number;

    vtipoproducto: string;

    getID(): number {
        return this.ctipoproducto;
    }

}

export class Almacenamiento extends BaseModel<Almacenamiento, number> {

    calmacenamiento: number;
	
	valmacenamiento: string;
	
	vdireccion: string;
	
	talmacenamiento: string;
	
	tregion: string;

    getID(): number {
        return this.calmacenamiento;
    }

}

export abstract class LogTransporte<T extends LogTransporte<T, ID>, ID> extends BaseModel<T, ID> {
    
    cantidad: number;
	
	fregistro: string;
	
	fentrega: string;
	
	precioenvio: number;
	
	numguia: string;

	ctipoproducto: number;
	
	ccliente: number;
	
	calmacenamiento: number;    

}

export class Camion extends LogTransporte<Camion, number> {

    ccamion: number;
	
	placaveh: string;

    getID(): number {
        return this.ccamion;
    }

}

export class Flota extends LogTransporte<Flota, number> {

    cflota: number;

    numflota: string;

    getID(): number {
        return this.cflota;
    }
    
}

export abstract class LogTransporteFilter<T extends LogTransporteFilter<T, ID>, ID> extends BaseModel<T, ID> {

    calmacenamiento: number;
	
	valmacenamiento: string;
	
	ctipoproducto: number;
	
	vtipoproducto: string;
	
	ccliente: number;
	
	vnombre: string;
	
	vapellido: string;
	
	fregistro: string;
	
	numguia: string;

}

export class CamionFilter extends LogTransporteFilter<CamionFilter, number> {

    ccamion: number;

    placaveh: string;

    getID(): number {
        return this.ccamion;
    }

}

export class FlotaFilter extends LogTransporteFilter<FlotaFilter, number> {

    cflota: number;

    numflota: string;

    getID(): number {
        return this.cflota;
    }

}
