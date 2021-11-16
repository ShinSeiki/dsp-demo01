package com.apso.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dem_tipoproducto")
public class TipoProducto extends BaseModel<TipoProducto, Integer> {

	private static final long serialVersionUID = 5193434321862223159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ctipoproducto;

	@Column(name = "vtipoproducto", columnDefinition = "text")
	private String vtipoproducto;

	public int getCtipoproducto() {
		return ctipoproducto;
	}

	public void setCtipoproducto(int ctipoproducto) {
		this.ctipoproducto = ctipoproducto;
	}

	public String getVtipoproducto() {
		return vtipoproducto;
	}

	public void setVtipoproducto(String vtipoproducto) {
		this.vtipoproducto = vtipoproducto;
	}

	@Override
	public Integer getID() {
		return ctipoproducto;
	}
	
}
