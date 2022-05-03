package models;

public class Courier {
    public int id;
    public String login;
    public String password;
    public String FirstName;

    public Courier(String login, String password, String FirstName) {
        this.login = login;
        this.password = password;
        this.FirstName = FirstName;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

     public Courier () {

     }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

