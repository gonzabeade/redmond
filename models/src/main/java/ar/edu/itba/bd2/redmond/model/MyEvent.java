package ar.edu.itba.bd2.redmond.model;

public class MyEvent {

    private String eventId;
    private String description;

    public MyEvent(String eventId, String description) {
        this.eventId = eventId;
        this.description = description;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
