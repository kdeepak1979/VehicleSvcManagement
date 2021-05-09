package com.innomind.vehiclesvc.mgmt.security;

import java.util.Optional;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TenantResolver implements CurrentTenantIdentifierResolver{


    static final String DEFAULT_TENANT = "public";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant =  Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter((authentication -> !(authentication instanceof AnonymousAuthenticationToken)))
                .map(auth -> getTenant(auth))
                .orElse(DEFAULT_TENANT);
        System.out.println("TenantResolver.resolveCurrentTenantIdentifier() tenant : "+tenant);
        return tenant;
    }

	private String getTenant(Authentication auth) {
		return auth.getAuthorities().stream().filter(ga -> ga.getAuthority().contains("ADMIN")).findAny()
				.map(g -> DEFAULT_TENANT).orElse(auth.getName());
	}

	@Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
