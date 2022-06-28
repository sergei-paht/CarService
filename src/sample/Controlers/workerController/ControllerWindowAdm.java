package sample.Controlers.workerController;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.Pane;
import sample.DataBaseHandler;
import sample.GetSet.Application;
import sample.GetSet.Service;
import sample.GetSet.User;
import sample.Main;

public class ControllerWindowAdm {
    private ObservableList<Service> service = FXCollections.observableArrayList();
    private ObservableList<User> user = FXCollections.observableArrayList();
    private ObservableList<Application> application = FXCollections.observableArrayList();
    private Service selectedService = new Service();
    private User selectedUser = new User();
    private Application selectedApplication = new Application();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addservice;

    @FXML
    private Button delservice;

    @FXML
    private Button mainButton;

    @FXML
    private TableView<Service> tableServicefull;

    @FXML
    private TableColumn<Service, Integer> idService;

    @FXML
    private TableColumn<Service, String> nameService;

    @FXML
    private TableColumn<Service, String> priceService;

    @FXML
    private TableView<User> tableUsersfull;

    @FXML
    private TableColumn<User, Integer> idusers;

    @FXML
    private TableColumn<User, String> roleuser;

    @FXML
    private TableColumn<User, String> firstname;

    @FXML
    private TableColumn<User, String> lastname;

    @FXML
    private TableColumn<User, String> loginUsers;

    @FXML
    private TableColumn<User, String> passwordUsers;

    @FXML
    private Button delusers;

    @FXML
    private Button addusers;

    @FXML
    private TableColumn<Application, String> carBrand;

    @FXML
    private Button delapp;

    @FXML
    private TableColumn<Application, Integer> idapplication;

    @FXML
    private TableColumn<Application, String> lastNameUser;

    @FXML
    private TableColumn<Application, String> numService;

    @FXML
    private TableView<Application> tableApplicationfull;

    @FXML
    private TableColumn<Application, String> userNameApp;


    // paneAddUser

        @FXML
        private Button addButt1;

        @FXML
        private Button backButt1;

        @FXML
        private TextField roleUserField;

        @FXML
        private TextField lastNameUser1;

        @FXML
        private TextField loginUser;

        @FXML
        private TextField nameUser;

        @FXML
        private Pane paneAddUser;

        @FXML
        private TextField passwordUser;

        // paneAddService

        @FXML
        private Button addButt2;

        @FXML
        private Button backButt2;

        @FXML
        private TextField nameService1;

        @FXML
        private Pane paneAddService;

        @FXML
        private TextField priceService1;


    @FXML
    void initialize() {

        //paneAddUser

        paneAddUser.setVisible(false);
        addusers.setOnAction(event -> {
            paneAddUser.setVisible(true);
        });
        addButt1.setOnAction(event -> {
            String textRoleUser = roleUserField.getText().trim();
            String textNameUser = nameUser.getText().trim();
            String textLastNameUser1 = lastNameUser1.getText().trim();
            String textLoginUser = loginUser.getText().trim();
            String textPasswordUser = passwordUser.getText().trim();
            if(!textRoleUser.equals("") && (textRoleUser.equals("user")||textRoleUser.equals("worker")||textRoleUser.equals("admin"))
                    && !textNameUser.equals("") && !textLastNameUser1.equals("") &&
                    !textLoginUser.equals("") && !textPasswordUser.equals("")){
                addNewUser();
                addButt1.getScene().getWindow().hide();
                Main.OpenIcon("/sample/SceneFxml/worker/windowAdmins.fxml");
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля не заполнены!");
                alert.setContentText("Заполните все поля!");

                alert.showAndWait();
            }
        });

        //paneAddService
        paneAddService.setVisible(false);
        addservice.setOnAction(event -> {
            paneAddService.setVisible(true);
        });

        addButt2.setOnAction(event -> {
            String textNameService = nameService1.getText().trim();
            String textPriceService = priceService1.getText().trim();
            if(!textNameService.equals("") && !textPriceService.equals("")){
                addNewService();
                addButt2.getScene().getWindow().hide();
                Main.OpenIcon("/sample/SceneFxml/worker/windowAdmins.fxml");
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля не заполнены!");
                alert.setContentText("Заполните все поля!");

                alert.showAndWait();
            }
            paneAddService.setVisible(false);


        });
        backButt1.setOnAction(event -> {
            backButt1.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });

        backButt2.setOnAction(event -> {
            backButt2.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });

        mainButton.setOnAction(event -> {
            mainButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });

        //таблица услуг
        initDataService();
        idService.setCellValueFactory(new PropertyValueFactory<Service, Integer>("idService"));
        nameService.setCellValueFactory(new PropertyValueFactory<Service, String>("NameService"));
        priceService.setCellValueFactory(new PropertyValueFactory<Service, String>("PriceService"));

        //таблица пользователей
        initDataUser();
        idusers.setCellValueFactory(new PropertyValueFactory<User, Integer>("idUsers"));
        roleuser.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
        loginUsers.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        passwordUsers.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        //таблица заявок
        initDataApp();
        idapplication.setCellValueFactory(new PropertyValueFactory<Application, Integer>("idapplications"));
        userNameApp.setCellValueFactory(new PropertyValueFactory<Application, String>("firstname"));
        lastNameUser.setCellValueFactory(new PropertyValueFactory<Application, String>("lastname"));
        carBrand.setCellValueFactory(new PropertyValueFactory<Application, String>("carBrand"));
        numService.setCellValueFactory(new PropertyValueFactory<Application, String>("NumService"));


        // удаление услуг по id
        delservice.setOnAction(event ->{
            selectedService = tableServicefull.getSelectionModel().getSelectedItem();
            selectedService.getIdService();
            DataBaseHandler dataBaseHandler = new DataBaseHandler();
            dataBaseHandler.deleteRow(selectedService.getIdService(), "services");
            tableServicefull.getItems().remove(selectedService);
        });
        addservice.setOnAction(event -> {
            addservice.getScene().getWindow();
            Main.OpenIcon("/sample/SceneFxml/worker/addService.fxml");
        });

        // удаление пользователя по id
        delusers.setOnAction(event ->{
            selectedUser = tableUsersfull.getSelectionModel().getSelectedItem();
            selectedUser.getIdUsers();
            DataBaseHandler dataBaseHandler = new DataBaseHandler();
            dataBaseHandler.deleteRowUser(selectedUser.getIdUsers(), "users");
            tableUsersfull.getItems().remove(selectedUser);
        });

        // удаление заявки по id
        delapp.setOnAction(event ->{
            selectedApplication = tableApplicationfull.getSelectionModel().getSelectedItem();
            selectedApplication.getIdapplications();
            DataBaseHandler dataBaseHandler = new DataBaseHandler();
            dataBaseHandler.deleteRowApp(selectedApplication.getIdapplications(), "applications");
            tableApplicationfull.getItems().remove(selectedApplication);
        });


    }

    private void addNewUser() {
        DataBaseHandler dBHandler = new DataBaseHandler();
        String firstName = nameUser.getText();
        String roleUser = roleUserField.getText();
        String lastName1 = lastNameUser1.getText();
        String login = loginUser.getText();
        String password = passwordUser.getText();

        User user = new User(roleUser, firstName, lastName1, login, password);

        dBHandler.addUserAdm(user);
    }

    private void addNewService() {
        DataBaseHandler dBHandler = new DataBaseHandler();
        String nameservice = nameService.getText();
        String priceservice = priceService.getText();
        Service service = new Service(nameservice, priceservice);

        dBHandler.addService(service);
    }




    private void initDataApp() {
        try {
            dbConnection = getDbConnection();
            ResultSet resSet = dbConnection.createStatement().executeQuery("SELECT * FROM applications");
            while (resSet.next()) {
                application.add(new Application( resSet.getInt(1),
                        resSet.getString(2), resSet.getString(3),
                        resSet.getString(4), resSet.getString(5)));
            }
            tableApplicationfull.setItems(application);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initDataUser() {
        try {
            dbConnection = getDbConnection();
            ResultSet resSet = dbConnection.createStatement().executeQuery("SELECT * FROM users");
            while (resSet.next()) {
                user.add(new User(resSet.getString("role"), resSet.getInt("idusers"), resSet.getString("firstname"),
                        resSet.getString("lastname"), resSet.getString("login"),
                        resSet.getString("password")));
            }
            tableUsersfull.setItems(user);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initDataService() {
        try {
            dbConnection = getDbConnection();
            ResultSet resSet = dbConnection.createStatement().executeQuery("SELECT * FROM service");
            while (resSet.next()) {
                service.add(new Service(resSet.getInt("idService"), resSet.getString("NameService"), resSet.getString("PriceService")));
            }
            tableServicefull.setItems(service);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + "localHost" + ":" + "3306" + "/" + "CarService";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "1234");
        return dbConnection;
    }
}
