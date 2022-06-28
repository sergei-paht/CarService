package sample.GetSet;

public class User {
    public int idUsers;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private String role;

    public User(String role, String firstname, String lastname, String login, String password){
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public User(String role, int idUsers, String firstname, String lastname, String login, String password) {
        this.role = role;
        this.idUsers = idUsers;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public Integer getIdUsers(){
        return idUsers;
    }

    public void setIdUsers(Integer idUsers){
        this.idUsers = idUsers;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
