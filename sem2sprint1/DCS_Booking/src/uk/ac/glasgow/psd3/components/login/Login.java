package uk.ac.glasgow.psd3.components.login;

import uk.ac.glasgow.psd3.components.database.User;

public interface Login {
	
	public User loginCheck(String username, String password);

}
