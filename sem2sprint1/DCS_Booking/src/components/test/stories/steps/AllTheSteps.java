package components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import components.database.User;
import components.database.User.Type;

public class AllTheSteps {

  private User user;
  private Exception e = null;
  private Boolean boolAnsw = false;
	
  //--------------------------------------------------------------
  // 1st story
	@Given("a user is a $utype")
	public void userType(String utype) {
    if (utype.equals("admin"))
  		this.user = new User("admin", Type.ADMIN);
    else if (utype.equals("lecturer"))
  		this.user = new User("lecturer", Type.LECTURER);
    else if (utype.equals("tutor"))
      this.user = new User("tutor", Type.TUTOR);
    else if (utype.equals("student"))
  		this.user = new User("student", Type.STUDENT);
    else
  		this.user = new User("guest", Type.GUEST);
	}
	
  @When("he asks to import \"$course\" course from MyCampus")
	public void importCourse(String course) {
		//TODO
	}

  @Then("\"$course\" course exists in the database")
	public void checkCourse(String course) {
		//TODO
	}

  @Then("an exception is thrown")
  public void excepionThrown() {
    assertThat(e, null);
  }

  //--------------------------------------------------------------
  // creating sessions
	@When("they create a new session")
	public void createSession() {
		//TODO
	}
	
	@When("the session is valid and unique")
	public void populateValidAndUniqueSession() {
		//TODO
	}
	
	@When("the session is nonsense")
	public void populateNonsenseSession() {
		//TODO
	}
	
	@When("the session already exists")
	public void populateExistingSession() {
		//TODO
	}
	
	@When ("the lecturer submits the session")
	public void submitSession() {
		//TODO
	}
	
	@Then("the session exists in the database")
	// public void sessionExists(ResultSet results)
	public void sessionExists() {
		//TODO
	}
	
	@When("the student submits the session")
	public void studentSubmitSession() {
		//TODO
	}
	
  //--------------------------------------------------------------
  // addig time slots
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
	public void submitSlot() {
		//TODO
	}
	
	@Then("the slot exists in the database")
	// public void slotExists(ResultSet results)
	public void slotExists() {
		//TODO
	}
	
	@When("the student submits the slot")
	public void studentSubmitSlot() {
		//TODO
	}
	
  //--------------------------------------------------------------
  // 12 story
  @When("\"$course\" is compulsory")
  public void courseIsCompulsory(String course) {
    // TODO
  }

  @When("a user signed up for \"$course\"")
  public void userSignedForCourse(String course) {
    // TODO
  }

  @When("When he asks if he has signed up for all compulsory courses")
  public void checkForSignedUp() {
    // TODO
  }

  @Then("a \"$answer\" answer is given")
  public void answerIsGiven(String answer) {
    // TODO
  }

  //--------------------------------------------------------------
  // nf_p0
  @When("he asks to import $count random courses from MyCampus")
  public void importNCourses(Integer count) {
    // TODO
  }

  @Then("$count random courses exist in the database")
  public void checkCoursesInDb(Integer count) {
    // TODO
  }

  //--------------------------------------------------------------
  // nf_p2
  @Given("a database contains $count dummy users")
  public void insertUsers(Integer count) {
    // TODO
  }

  @When("they choose to login")
  public void chooseLogin() {
    // TODO
  }

  @When("they anter \"$username\" as a username")
  public void enterUsername(String username) {
    // TODO
  }

  @When("they enter \"$password\" as a password") 
  public void enterPassword(Sring password) {
    // TODO
  }

  @Then("a user is recognised as a $type") 
  public void userRecognised(String type) {
    // TODO
  }

  //--------------------------------------------------------------
  // nf_p4
  @When("$count random users login to the system")
  public void usersLogin(Integer count) {
    // TODO
  }

  @Then("a user nr $nr is recognised as a $type")
  public void particularUserRecognised(Intger nr, String type) {
    // TODO
  }

  //--------------------------------------------------------------
  // nf_s0
  @Then("a request to MyCampus is sent")
  public void requestWentToMyCampus() {
    // TODO
  }

  //--------------------------------------------------------------
  // nf_s1 will pretty much work if the rest will work

  //--------------------------------------------------------------
  //--------------------------------------------------------------
}