package no.noroff.models;

public class Emails {
    private String work;
    private String personal;

    public Emails(String work, String personal) {
        this.work = work;
        this.personal = personal;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
}
