package no.noroff.models;

public class ContactNumbers {
    private String work;
    private String home;
    private String mobile;

    public ContactNumbers(String work, String home, String mobile) {
        this.work = work;
        this.home = home;
        this.mobile = mobile;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
