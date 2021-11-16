package com.apso.dsp.util;

import java.util.HashMap;
import java.util.Map;

public class StmProperty extends HashMap<String, Object> {

	private static final long serialVersionUID = -858414049518995031L;

	public StmProperty() {
	}

	public StmProperty(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public StmProperty(int initialCapacity) {
		super(initialCapacity);
	}

	public StmProperty(Map<? extends String, ? extends Object> m) {
		super(m);
	}

}
