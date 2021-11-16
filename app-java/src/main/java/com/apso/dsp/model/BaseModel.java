package com.apso.dsp.model;

import java.io.Serializable;

public abstract class BaseModel<T extends BaseModel<T, ID>, ID> implements Serializable {

	private static final long serialVersionUID = 3617376003853104554L;

	public abstract ID getID();
	
}
