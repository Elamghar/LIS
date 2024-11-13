package ma.ensa.lis.models;

import java.util.Date;

public class Patient {
    private Integer id;
    private String country;
    private  String login;
    private String pass;
    private Date date_ns;
    private String fullness;
    public Patient(int id, String name, String login, String pass, Date date_ns, String country) {
        this.id = id;
        this.fullness = name;
        this.login = login;
        this.pass = pass;
        this.date_ns = date_ns;
        this.country = country;
    }

    public Patient() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullness;
    }

    public void setFullname(String name) {
        this.fullness = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getDate_ns() {
        return date_ns;
    }

    public void setDate_ns(Date date_ns) {
        this.date_ns = date_ns;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}