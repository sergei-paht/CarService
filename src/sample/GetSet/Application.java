package sample.GetSet;

import java.sql.ResultSet;

public class Application {
    private int idapplications;
    private String firstnameUsers;
    private String lastnameUsers;
    private String carBrandUsers;
    private String numService;


    public Application( String firstnameUsers, String lastnameUsers, String carBrandUsers, String numService) {

        this.firstnameUsers = firstnameUsers;
        this.lastnameUsers = lastnameUsers;
        this.carBrandUsers = carBrandUsers;
        this.numService = numService;
    }

    public Application() {
    }

    public Application(int idapplications, String firstnameUsers, String lastnameUsers, String carBrandUsers, String numService) {
        this.idapplications = idapplications;
        this.firstnameUsers = firstnameUsers;
        this.lastnameUsers = lastnameUsers;
        this.carBrandUsers = carBrandUsers;
        this.numService = numService;
    }


    public Integer getIdapplications() {
        return idapplications;
    }

    public void setIdapplications(Integer idapplications) {
        this.idapplications = idapplications;
    }

    public String getFirstname() {
        return firstnameUsers;
    }

    public void setFirstname(String firstnameUsers) {
        this.firstnameUsers = firstnameUsers;
    }

    public String getLastname() {
        return lastnameUsers;
    }

    public void setLastname(String lastnameUsers) {
        this.lastnameUsers = lastnameUsers;
    }

    public String getCarBrand() {
        return carBrandUsers;
    }

    public void setCarBrand(String carBrandUsers) {
        this.carBrandUsers = carBrandUsers;
    }
    public String getNumService() {
        return numService;
    }

    public void setNumService(String numService) {
        this.numService = numService;
    }
}