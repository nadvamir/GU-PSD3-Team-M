package uk.ac.glasgow.dcs_booking.components.login;

import uk.ac.glasgow.dcs_booking.components.database.User;

public interface Login {
	
	public User loginCheck(String username, String password);

}
