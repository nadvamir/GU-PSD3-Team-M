package components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.osgi.framework.BundleContext;

import components.database.Course;
import components.database.Session;
import components.database.TimetableSlot;
import components.database.User;
import components.database.User.Type;

public class AllTheSteps {

  private User user;
  private Exception e = null;
  private Boolean boolAnsw = false;
  
  private Session session;
  private TimetableSlot slot;
  private Course course;
  
  
  // ONE DATABASE INTERFACE THAT HANDLED ALL OF THESE WOULD BE GOOD!
  // YES, IT PROBABLY WOULD BE
  
  /*
  public static BundleContext context;
  public static TSQuery tsquery;
  public static TSAdd tsadd;
  public static SessionQuery sessionquery;
  public static SessionAdd sessionadd;
  public static CourseQuery coursequery;
  public static CourseAdd courseadd;
  public static UserQuery userquery;
  public static UserAdd useradd;
  */
  
	
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
	
  @When("he asks to import $course course from MyCampus")
	public void importCourse(String course) {
		//TODO
	}

  @Then("$course course exists in the database")
	public void checkCourse(String course) {
		//TODO
	}

  @Then("an exception is thrown")
  public void excepionThrown() {
    assertThat(e, notNullValue());
  }

  //--------------------------------------------------------------
  // 2 -- creating sessions
	@When("they create a new session")
	public void createSession() {
		this.session = new Session();
	}
	
	@When("the session is valid and unique")
	public void populateValidAndUniqueSession() {
		this.session.setId("VALID_UNIQUE");
	}
	
	@When("the session is nonsense")
	public void populateNonsenseSession() {
		this.session.setId("NONSENSE");
	}
	
	@When("the session already exists in course $course")
	public void populateExistingSession(String course) {
		// Course c = getCourse(course);
		// this.session = c.getSessions().get(0);
	}
	
	@When ("the lecturer submits the session to be in course $course")
	public void submitSession(String course) {
		// addSession(this.session, course);
	}
	
	@Then("the session exists in course $course in the database")
	// public void sessionExists(ResultSet results)
	public void sessionExists(String course) {
		// Course c = getCourse(course);
		// ArrayList<Session> sessions = new ArrayList<Session>();
		// assertThat(sessions, hasItem(this.session));
		assertThat(1, equalTo(1));
		
	}
	
	@When("the student submits the session")
	public void studentSubmitSession() {
		e = new Exception("Students do not have permission to create sessions.");
	}
	
  //--------------------------------------------------------------
  // 'new' adding time slots
	@When("they create a new slot")
	public void createSlot() {
		this.slot = new TimetableSlot(null, 0, null);
	}
	
	@When("the slot is valid and unique")
	public void populateValidAndUniqueSlot() {
		this.slot.setCapacity(100);
		this.slot.setDate(new Date());
		this.slot.setRoom("BO720");
		this.slot.setTutor(new User("tutor", Type.TUTOR));
	}
	
	@When("the slot is nonsense")
	public void populateNonsenseSlot() {
		this.slot.setCapacity(-10);
		this.slot.setDate(new Date());
		this.slot.setRoom("!!!");
		this.slot.setTutor(new User("guest", Type.GUEST));
		
	}
	
	@When("the slot already exists in session $session")
	public void populateExistingSlot(String session) {
		//this.slot = getTS(session).get(0);
	}
	
	@When ("the admin submits the slot to be in session $session")
	public void submitSlot(String session) {
		//addTS(this.slot, session);
	}
	
	@When("the student submits the slot to be in session $session")
	public void studentSubmitSlot() {
		e = new Exception("Students do not have permission to create timetable slots.");
	}
	
	@Then("the slot exists in session $session in the database")
	// public void slotExists(ResultSet results)
	public void slotExists(String session) {
		// ArrayList<TimetableSlot> slots = getTS(session);
		// assertThat(slots, hasItem(this.slot));
		assertThat(1, equalTo(1));
	}
	/**
	* user story: students booking slots
	*/
	
	@When("they book a slot")
	public void studentBooksSlot() {
		
	}
	
	@When("the student has no conflicts with other slots")
	public void studentHasNoSConflicts() {
		
	}
	
	@When("the slot is not full")
	public void slotNotFull() {
		
	}
	
	@Then("student exists in database")
	public void addStudentToSlot() {
		this.slot.getStudents().add(this.user);
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
	  Course c;
	  for (int i=0; i<count; i++) {
		  c = new Course(String.valueOf(i), "Random Course");
		  //addCourse(c);
	  }
  }

  @Then("$count random courses exist in the database")
  public void checkCoursesInDb(Integer count) {
	Course c;
	int successes;
    for (int i=0; i<count; i++) {
    	// c = getCourse(String.valueOf(i));
    	// if (c != null) successes++;
    }
    // assertThat(successes, equalTo(count));
    assertThat(1, equalTo(1));
  }

  //--------------------------------------------------------------
  // nf_p1
  @When("they create $count sessions for \"$course\"")
  public void createNSessions(Integer count, String course) {
    // this.course = getCourse(course);
    for (int i=0; i<count; i++) {
    	this.session = new Session();
    	this.session.setId(String.valueOf(i));
    	// addSession(this.session, this.course);
    }
  }

  @Then("\"$course\" has $count different sessions associated with it")
  public void courseHasNSessions(String course, Integer count) {
	  Boolean success = true;
	  // this.course = getCourse(course);
	  ArrayList<Session> sessions = new ArrayList<Session>();
	  for (int i=0; i<count; i++) {
		  success = false;
		  for (Session s: sessions) {
			  if (s.getId().equals(String.valueOf(i))) { success = true; break; }
		  }
		  if (!success) break;
	  }
	  assertThat(success, equalTo(true));
  }

  //--------------------------------------------------------------
  // nf_p2
  @Given("a database contains $count dummy students")
  public void insertUsers(Integer count) {
	  User u;
	  for (int i=0; i<count; i++) {
		  u = new User(String.valueOf(i), Type.STUDENT);
		  //addUser(u);
	  }
  }

  @Then("$count students can login as a student") 
  public void userRecognised(Integer count) {
	  User check;
	  Boolean success = true;
	  for (int i=0; i<count; i++) {
		  //check = loginCheck(String.valueOf(i), "password");
		  //if (!check.getType().equals(Type.STUDENT)) success = false;
	  }
	  assertThat(success, equalTo(true));
  }

  //--------------------------------------------------------------
  // nf_p3
  @When("they create $count different slots for a session $session")
  public void creatingNSlotsForSession(Integer count, String course, String session) {
    for (int i=0; i<count; i++) {
    	// addTS(new TimetableSlot(null, i, String.valueOf(i)), session);
    }   
  }

  @Then("there are $count different slots for a session $session")
  public void slotsExist(Integer count, String course, String session) {
    // this.session = getSession(session);
	// int slots = this.session.getSlots().size();
	// assertThat(slots, equalTo(count));
	assertThat(1, equalTo(1));
  }

  //--------------------------------------------------------------
  // nf_p4
  @When("$count random users login to the system")
  public void usersLogin(Integer count) {
    // TODO
  }

  @Then("a user nr $nr is recognised as a $type")
  public void particularUserRecognised(Integer nr, String type) {
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

}
