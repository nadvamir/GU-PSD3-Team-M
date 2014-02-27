package components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import components.database.User;
import components.database.User.Type;

public class AddTimetableSlotToSessionSteps {
	
	private User user;

	@Given("an admin")
	public void anAdmin() {
		this.user = new User("admin", Type.ADMIN);
	}
	
	@When("they create a new slot")
	public void createSlot() {
		//TODO
	}
	
	@When("the slot is valid and unique")
	public void populateValidAndUniqueSlot() {
		//TODO
	}
	
	@When("the slot is nonsense")
	public void populateNonsenseSlot() {
		//TODO
	}
	
	@When("the slot already exists")
	public void populateExistingSlot() {
		//TODO
	}
	
	@When ("the admin submits the slot")
	public void submitSession() {
		//TODO
	}
	
	@Then("the slot exists in the database")
	// public void slotExists(ResultSet results)
	public void slotExists() {
		//TODO
	}
	
	@Given("a student")
	public void aStudent() {
		this.user = new User("student", Type.STUDENT);
	}
	
	@When("the student submits the slot")
	public void studentSubmitSlot() {
		//TODO
	}
	
	@Then("an exception is thrown")
	public void throwException() {
		//TODO
	}

}
