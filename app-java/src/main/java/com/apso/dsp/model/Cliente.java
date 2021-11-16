package com.apso.dsp.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.type.IntegerType;
import org.hibernate.type.LocalDateType;
import org.hibernate.type.StringType;

import com.apso.dsp.model.filter.CamionFilter;
import com.apso.dsp.model.filter.FlotaFilter;
import com.apso.dsp.util.BasicConstants;


@SqlResultSetMappings({
	@SqlResultSetMapping(
		name = BasicConstants.MAP_CLIENTE_FILTER,
		entities = @EntityResult(
			entityClass = Cliente.class,
			fields = {
				@FieldResult(name = "ccliente", column = "ccliente"),
				@FieldResult(name = "vnombre", column = "vnombre"),
				@FieldResult(name = "vapellido", column = "vapellido"),
			}
		)
	), @SqlResultSetMapping(
		name = BasicConstants.MAP_CAMION_FILTER,
		classes = @ConstructorResult(
			targetClass = CamionFilter.class,
			columns = {
				@ColumnResult(name = "ccamion", type = IntegerType.class),
				@ColumnResult(name = "placaveh", type = StringType.class),
				@ColumnResult(name = "calmacenamiento", type = IntegerType.class),
				@ColumnResult(name = "valmacenamiento", type = StringType.class),
				@ColumnResult(name = "ctipoproducto", type = IntegerType.class),
				@ColumnResult(name = "vtipoproducto", type = StringType.class),
				@ColumnResult(name = "ccliente", type = IntegerType.class),
				@ColumnResult(name = "vnombre", type = StringType.class),
				@ColumnResult(name = "vapellido", type = StringType.class),
				@ColumnResult(name = "fregistro", type = LocalDateType.class),
				@ColumnResult(name = "numguia", type = StringType.class),
			}
		)
	), @SqlResultSetMapping(
		name = BasicConstants.MAP_FLOTA_FILTER,
		classes = @ConstructorResult(
			targetClass = FlotaFilter.class,
			columns = {
				@ColumnResult(name = "cflota", type = IntegerType.class),
				@ColumnResult(name = "numflota", type = StringType.class),
				@ColumnResult(name = "calmacenamiento", type = IntegerType.class),
				@ColumnResult(name = "valmacenamiento", type = StringType.class),
				@ColumnResult(name = "ctipoproducto", type = IntegerType.class),
				@ColumnResult(name = "vtipoproducto", type = StringType.class),
				@ColumnResult(name = "ccliente", type = IntegerType.class),
				@ColumnResult(name = "vnombre", type = StringType.class),
				@ColumnResult(name = "vapellido", type = StringType.class),
				@ColumnResult(name = "fregistro", type = LocalDateType.class),
				@ColumnResult(name = "numguia", type = StringType.class),
			}
		)
	)
})
@Entity
@Table(name = "dem_cliente")
public class Cliente extends BaseModel<Cliente, Integer> {

	private static final long serialVersionUID = -8260648807821235675L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ccliente;
	
	@Column(name = "vnombre", columnDefinition = "text")
	private String vnombre;
	
	@Column(name = "vapellido", columnDefinition = "text")
	private String vapellido;

	public int getCcliente() {
		return ccliente;
	}

	public void setCcliente(int ccliente) {
		this.ccliente = ccliente;
	}

	public String getVnombre() {
		return vnombre;
	}

	public void setVnombre(String vnombre) {
		this.vnombre = vnombre;
	}

	public String getVapellido() {
		return vapellido;
	}

	public void setVapellido(String vapellido) {
		this.vapellido = vapellido;
	}

	@Override
	public Integer getID() {
		return ccliente;
	}
	
}
