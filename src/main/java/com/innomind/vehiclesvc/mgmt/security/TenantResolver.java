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
    private String forcedTenant;

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant =  Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter((authentication -> !(authentication instanceof AnonymousAuthenticationToken)))
                .map(auth -> getTenant(auth))
                .orElse(deriveDefaultTenant());        
        return tenant;
    }

	private String deriveDefaultTenant() {
		String tenant = null;
		if(forcedTenant != null) {
		    tenant = forcedTenant;
		}else {
			tenant = DEFAULT_TENANT;	
		}
		return tenant;
	}

	private String getTenant(Authentication auth) {
		return auth.getAuthorities().stream().filter(ga -> ga.getAuthority().contains("DEALER_ADMIN")).findAny()
				.map(g -> deriveDefaultTenant()).orElse(auth.getName());		
	}

	@Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
	
	/**
	 * 
	 * @param tenant
	 */
	public void setActiveTenant(String tenant) {
		forcedTenant = tenant;
	}
}
