package com.innomind.vehiclesvc.mgmt.security;

public class SchemaException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SchemaException(String msg, Exception e) {
		super(msg, e);
	}

}
