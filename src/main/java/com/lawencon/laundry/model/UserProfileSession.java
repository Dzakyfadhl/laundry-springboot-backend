package com.lawencon.laundry.model;

import org.springframework.stereotype.Component;

/**
 * @author Dzaky Fadhilla Guci
 */

@Component
public class UserProfileSession {

	private Profiles activeProfiles;

	public Profiles getActiveProfiles() {
		return activeProfiles;
	}

	public void setActiveProfiles(Profiles activeProfiles) {
		this.activeProfiles = activeProfiles;
	}

}
