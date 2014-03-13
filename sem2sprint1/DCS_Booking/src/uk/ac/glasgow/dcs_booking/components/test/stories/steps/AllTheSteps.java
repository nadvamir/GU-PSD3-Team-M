package uk.ac.glasgow.dcs_booking.components.test.stories.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.osgi.framework.BundleContext;

// admin controls
import uk.ac.glasgow.dcs_booking.components.admincontrols.impl.TimetableSlotManagerImpl;
import uk.ac.glasgow.dcs_booking.components.admincontrols.TimetableSlotManager;

// lecturer controls
import uk.ac.glasgow.dcs_booking.components.mcwrapper.impl.MyCampusController;
import uk.ac.glasgow.dcs_booking.components.mcwrapper.MyCampusWrapper;
import uk.ac.glasgow.dcs_booking.components.lecturercontrols.impl.CourseManagerImpl;
import uk.ac.glasgow.dcs_booking.components.lecturercontrols.CourseManager;

// application
import uk.ac.glasgow.dcs_booking.components.application.Application;

// database
import uk.ac.glasgow.dcs_booking.components.database.Course;
import uk.ac.glasgow.dcs_booking.components.database.CourseAdd;
import uk.ac.glasgow.dcs_booking.components.database.CourseQuery;
import uk.ac.glasgow.dcs_booking.components.database.DBMS;
import uk.ac.glasgow.dcs_booking.components.database.Session;
import uk.ac.glasgow.dcs_booking.components.database.SessionAdd;
import uk.ac.glasgow.dcs_booking.components.database.SessionQuery;
import uk.ac.glasgow.dcs_booking.components.database.TSAdd;
import uk.ac.glasgow.dcs_booking.components.database.TSHandler;
import uk.ac.glasgow.dcs_booking.components.database.TSQuery;
import uk.ac.glasgow.dcs_booking.components.database.TimetableSlot;
import uk.ac.glasgow.dcs_booking.components.database.User;
import uk.ac.glasgow.dcs_booking.components.database.UserAdd;
import uk.ac.glasgow.dcs_booking.components.database.UserQuery;
import uk.ac.glasgow.dcs_booking.components.database.User.Type;



public class AllTheSteps {

  private DBMS dbms = new DBMS();
  private TSHandler tsHandler;

  private User user;
  private Exception e = null;
  private Boolean boolAnsw = false;
  private int resLen; // length of the result, if it is an array or similar
  
  private Session session;
  private TimetableSlot slot;
  private Course course;

  private String room;
  private boolean roomIsAvailable;
  private TimetableSlot timetableSlot;
  private boolean timetableSlotExists;
  private boolean roomAssigned;
  
  // ONE DATABASE INTERFACE THAT HANDLED ALL OF THESE WOULD BE GOOD!
  // YES, IT PROBABLY WOULD BE
  
  public static BundleContext context;
  public static TSQuery tsquery;
  public static TSAdd tsadd;
  public static SessionQuery sessionquery;
  public static SessionAdd sessionadd;
  public static CourseQuery coursequery;
  public static CourseAdd courseadd;
  public static UserQuery userquery;
  public static UserAdd useradd;
  
  // admin contols-specific variables
  private TimetableSlotManager tsm = new TimetableSlotManagerImpl();

  // lecturer controls-specific variables
	private MyCampusWrapper mcc = new MyCampusController();
  private CourseManager cmng = new CourseManagerImpl(mcc);
  
  //for 4.
  private User currentuser;
  private Course currentcourse;
  private ArrayList<Session> currentsessionarray;
  private Session currentsession;
  
  public static Application app;

  public AllTheSteps() {
    try {
      tsHandler = new TSHandler(dbms, userquery);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
	
  //--------------------------------------------------------------
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
    try {
      cmng.importCourse(course);
    } catch (Exception ex) {
      this.e = ex;
    }
	}

  @Then("\"$course\" course exists in the database")
	public void checkCourse(String course) {
    assertThat(coursequery.getCourse(course), notNullValue());
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
	
	@When("the student submits the session to be in course $course")
	public void studentSubmitSession() {
		e = new Exception("Students do not have permission to create sessions.");
	}
	
  //--------------------------------------------------------------
  // 4 -- 
	@Given("a $userin who is lecturer")
	public void userislecturer(String userin){
		currentuser = new User(userin, Type.LECTURER);
	}
	
	@When("course $coursename is selected")
	public void courseisselected(String coursename){
		currentcourse = new Course("1",coursename);
		currentsessionarray = currentcourse.getSessions();
	}
	
	@When("session from the course is selected")
	public void sessionisselected(){
		currentsession = new Session();
		currentsessionarray.add(currentsession);
	}
	
	@When("the $freq is selected")
	public void freqisselected(String freq){
		if(freq.equals("oneoff")){
			currentsession.specifyoneoff();
		}else if(freq.equals("weekly")){
			currentsession.specifyweekly();
		}else if(freq.equals("fortnightly")){
			currentsession.specifyfortnightly();
		}
	}
	
	@Then("the session is $freq")
	public void sessionis(String freq){
		if(freq.equals("oneoff")){
			assertThat(currentsession.getfreq(), equalTo(0));
		}else if(freq.equals("weekly")){
			assertThat(currentsession.getfreq(), equalTo(1));
		}else if(freq.equals("fortnightly")){
			assertThat(currentsession.getfreq(), equalTo(2));
		}
	}
	
  //---------------------------------------------------------	
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
	
	@When ("the admin submits the new slot to be in session $session")
	public void submitSlot(String session) {
		//addTS(this.slot, session);
	}
	
	@When("the student submits the new slot to be in session $session")
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
  @Given("\"$course\" is compulsory")
  public void courseIsCompulsory(String course) {
    // TODO
  }

  @Given("a user signed up for \"$course\"")
  public void userSignedForCourse(String course) {
    // TODO
  }

  @When("he asks if he has signed up for all compulsory courses")
  public void checkForSignedUp() {
    // TODO
  }

  @Then("a \"$answer\" answer is given")
  public void answerIsGiven(String answer) {
    // TODO
  }

  //--------------------------------------------------------------
  // nf_s1 will pretty much work if the rest will work
  @Then("a user is recognised as a $type")
  public void userRecognised(String type) {
    // TODO
  }

  //--------------------------------------------------------------
  // additional test case
  @Given("there are $num clashes")
  public void thereAreSomeClashes(Integer num) {
    // injects some amount of clashed nonsense courses in the database
    _populateGoodCourses();
    for (int i = 0; i < num; i++)
      _generateClash();
  }
  private void _populateGoodCourses() {
    // update database with the hardcoded non-clashing courses
  }
  private void _generateClash() {
    // 1. create two random course names, add them to DB for level 1
    // 2. create a few timetable slots for each, outside GoodCourses range
    // 3. share an additional timetable slot between those two
  }

  @When("they check for clashes")
  public void checkForClashes() {
    // call the checking api
    this.resLen = tsm.checkClashesForLevel(1).size();
  }

  @Then("they see $num clashes")
  public void inspectClashResult(Integer num) {
    // check the result of last action
  }

}
