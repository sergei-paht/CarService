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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.GetSet.Application;
import sample.GetSet.Service;
import sample.Main;

public class ControllerWindowWork {
    private ObservableList<Service> service = FXCollections.observableArrayList();
    private ObservableList<Application> application = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Service> tableServicefull;

    @FXML
    private TableColumn<Service, Integer> idService;

    @FXML
    private TableColumn<Service, String> nameService;

    @FXML
    private TableColumn<Service, String> priceService;


    @FXML
    private Button mainButton;

    @FXML
    private TableView<Application> app;

    @FXML
    private TableColumn<Application, String> brandCarUser;

    @FXML
    private TableColumn<Application, String> nameUser;

    @FXML
    private TableColumn<Application, String> numService;

    @FXML
    private TableColumn<Application, Integer> idApp;

    @FXML
    private TableColumn<Application, String> lastnameUser;

    @FXML
    void initialize() {
        mainButton.setOnAction(event -> {
            mainButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });

        //таблица услуг
        initDataService();
        idService.setCellValueFactory(new PropertyValueFactory<Service, Integer>("idService"));
        nameService.setCellValueFactory(new PropertyValueFactory<Service, String>("NameService"));
        priceService.setCellValueFactory(new PropertyValueFactory<Service, String>("PriceService"));

        // таблица заявок
        initDataApp();
        idApp.setCellValueFactory(new PropertyValueFactory<Application, Integer>("idapplications"));
        nameUser.setCellValueFactory(new PropertyValueFactory<Application, String>("firstname"));
        lastnameUser.setCellValueFactory(new PropertyValueFactory<Application, String>("lastname"));
        brandCarUser.setCellValueFactory(new PropertyValueFactory<Application, String>("carBrand"));
        numService.setCellValueFactory(new PropertyValueFactory<Application, String>("numService"));

        // заполняем таблицу данными
        tableServicefull.setItems(service);
        app.setItems(application);

    }

    private void initDataApp() {
        try {
            dbConnection = getDbConnection();
            ResultSet resSet = dbConnection.createStatement().executeQuery("SELECT * FROM applications");
            while (resSet.next()) {
                application.add(new Application(
                        resSet.getInt("idapplications"),resSet.getString("firstnameUsers"), resSet.getString("lastnameUsers"),
                        resSet.getString("carBrandUsers"), resSet.getString("numService")));
            }
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
