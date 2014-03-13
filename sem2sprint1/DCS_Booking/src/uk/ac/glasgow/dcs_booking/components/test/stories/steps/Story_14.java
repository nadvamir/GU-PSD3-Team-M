package uk.ac.glasgow.dcs_booking.components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.glasgow.dcs_booking.components.database.User;
import uk.ac.glasgow.dcs_booking.components.database.User.Type;

public class Story_14 {
	private User user;
	private boolean detailsAreShown;
	
	
	@Given("any user")
	public void anyUser() {
		this.user = new User("lecturer", Type.LECTURER);
	}

	@When("opens the details of a timetable slot")
	public void timetableSlotDetailsRequested() {
		//detailsAreReturned = getDetails(params) - the method that looks something like this should be implemented		
		detailsAreShown = true;
	}	

	@Then("details are shown")
	public void detailsAreShown() {
		assertThat(detailsAreShown, equalTo(true));
	}	
}
