package id.web.runsel.udaraq.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String password_confirmation;

    public User(String name, String email, String password, String password_confirmation) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password, String password_confirmation) {
        this.name = name;
        this.password = password;
        this.password_confirmation = getPassword_confirmation();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

}
