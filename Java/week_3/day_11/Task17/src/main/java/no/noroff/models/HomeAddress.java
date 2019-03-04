package no.noroff.models;

public class HomeAddress {
    private String streetAddress;
    private String postCode;
    private String mailingAddress;
    private String country;

    public HomeAddress(String streetAddress, String postCode, String mailingAddress, String country) {
        this.streetAddress = streetAddress;
        this.postCode = postCode;
        this.mailingAddress = mailingAddress;
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostNumber(String postNumber) {
        this.postCode = postNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
