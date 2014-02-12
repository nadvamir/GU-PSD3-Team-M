package components.mcwrapper;

import java.util.ArrayList;

public class MyCampusController extends MyCampusWrapper {
    /**
     * Establish secure connection to MyCampus
     * so that allows us to query the data.
     */
    public void authorise() throws Exception {
        // does the connection
        // specifies the parameters from some config
        // authorises itself
        // throws exception if data was wrong
    }
    
    /**
     * Close the connection to MyCampus.
     */
    public void disconnect() {
        // closes the connection
        // always a good practice
    }

    /**
     * Get the list of all available courses.
     */
    public ArrayList<MyCampusCourse> getAllCourses() throws Exception {
        // the result will go here
        ArrayList<MyCampusCourse> courses = new ArrayList<MyCampusCourse> ();

        // generate things we would get from internet
        String names[] = {
            "CS1P", "CS1Q", "JP2", "OOSE2", "ADS2", "IM2", "AF2"
        };
        String fullNames[] = {
            "CS1P", "CS1Q", "Java Programming",
            "Object-Oriented Software Engineering",
            "Algorithms and Data Structures",
            "Information Management",
            "Algorithmic Foundations"
        };

        for (int i = 0; i < names.length(); i++) {
            courses.add(new MyCampusCourse(names[i], fullNames[i]));
        }

        // returning the result
        return courses;
    }

    /**
     * Get one course you really need.
     * @param name: name of that course, like CS1P
     */
    public MyCampusCourse getCourse(String name) throws Exception {
        // a bit of cheating
        ArrayList<MyCampusCourse> courses = getAllCourses();

        for (MyCampusCourse c: courses) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        throw new Exception("Course was not found");
        return null;
    }
}
