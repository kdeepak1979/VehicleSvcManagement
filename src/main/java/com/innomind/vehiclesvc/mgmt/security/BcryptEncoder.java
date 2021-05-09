package com.innomind.vehiclesvc.mgmt.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptEncoder {

	public static void main(String[] args) {
		System.out.println(BCrypt.hashpw("password",BCrypt.gensalt(4)));
	}

}
