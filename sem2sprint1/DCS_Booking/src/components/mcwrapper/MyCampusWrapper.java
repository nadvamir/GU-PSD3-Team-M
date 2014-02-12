package components.mcwrapper;

public interface MyCampusWrapper {
    /**
     * Establish secure connection to MyCampus
     * so that allows us to query the data.
     */
    public void authorise() throws Exception;
    
    /**
     * Close the connection to MyCampus.
     */
    public void disconnect();

    /**
     * Get the list of all available courses.
     */
    public ArrayList<MyCampusCourse> getAllCourses() throws Exception;

    /**
     * Get one course you really need.
     * @param name: name of that course, like CS1P
     */
    public MyCampusCourse getCourse(String name) throws Exception;
}
