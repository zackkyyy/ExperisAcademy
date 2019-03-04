package no.noroff.models;

public class Family {
    private String firstname;
    private String lastname;
    private int relationId;
    private String status;

    public Family(String firstname, String lastname, int relationId, String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.relationId = relationId;
        this.status = status;
    }

    public int getRelationId() {
        return relationId;
    }

    public String getStatus() {
        return status;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
