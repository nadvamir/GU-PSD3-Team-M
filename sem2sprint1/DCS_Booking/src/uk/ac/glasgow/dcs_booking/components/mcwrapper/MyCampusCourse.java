package uk.ac.glasgow.dcs_booking.components.mcwrapper;

public class MyCampusCourse {
    private String id;
    private String title;

    public MyCampusCourse(String n, String fn) {
        id = n; title = fn;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
