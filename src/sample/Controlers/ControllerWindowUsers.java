package sample.Controlers;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DataBaseHandler;
import sample.GetSet.Application;
import sample.GetSet.User;
import sample.Main;
import sample.GetSet.Service;

public class ControllerWindowUsers implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Заполнение заявки
        sendButton.setOnAction(event -> {
            String textBrandCar = carBrandField.getText().trim();
            String textNumService = numServiceField.getText().trim();
            if(!textBrandCar.equals("") && !textNumService.equals("")){
                try {
                    sendNewAnApplication();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                sendButton.getScene().getWindow().hide();
                Main.OpenIcon("/sample/SceneFxml/windowUsers.fxml");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Сообщение");
                alert.setHeaderText("Ваша заявка отправлена!");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля не заполнены!");
                alert.setContentText("Заполните все поля!");

                alert.showAndWait();
            }


        });

        //таблица услуг
        initData();
        idService.setCellValueFactory(new PropertyValueFactory<Service, Integer>("idService"));
        nameService.setCellValueFactory(new PropertyValueFactory<Service, String>("NameService"));
        priceService.setCellValueFactory(new PropertyValueFactory<Service, String>("PriceService"));
        // заполняем таблицу данными
        tableServicefull.setItems(service);

        mainButton.setOnAction(event -> {
            mainButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });
    }

    private void sendNewAnApplication() throws SQLException {
        DataBaseHandler dBHandler = new DataBaseHandler();

        User user = new User();
        String carBrand = carBrandField.getText();
        String numService = numServiceField.getText();
        user.setIdUsers(ControllerAuth.id);
        ResultSet firstName = dBHandler.getName(user);
        if(firstName.next()){
            Application application = new Application(firstName.getString(1), firstName.getString(2),
                    carBrand, numService);

            dBHandler.sendAnApplication(application);
        }
    }

    private void initData() {
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

    private ObservableList<Service> service = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainButton;

    @FXML
    private TextField carBrandField;

    @FXML
    private TextField numServiceField;

    @FXML
    private Button sendButton;

    @FXML
    private TableColumn<Service, Integer> idService;

    @FXML
    private TableColumn<Service, String> nameService;

    @FXML
    private TableColumn<Service, String> priceService;

    @FXML
    private TableView<Service> tableServicefull;

}