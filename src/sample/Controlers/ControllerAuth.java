package sample.Controlers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DataBaseHandler;
import sample.Main;
import sample.GetSet.User;

public class ControllerAuth {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButt;

    @FXML
    private Button enterButton;

    @FXML
    private TextField loginFieldAurh;

    @FXML
    private PasswordField passwordFieldAuth;
    public static Integer id;

    @FXML
    void initialize() {

        enterButton.setOnAction(event -> {
            String textLogin = loginFieldAurh.getText().trim();
            String textPassword = passwordFieldAuth.getText().trim();
            if(!textLogin.equals("") && !textPassword.equals("")){
                try {
                    loginUser(textLogin, textPassword);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else{
                System.out.println("eror");
            }
        });
        backButt.setOnAction(event -> {
            backButt.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });

    }

    private void loginUser(String textLogin, String textPassword) throws SQLException {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(textLogin);
        user.setPassword(textPassword);
        ResultSet result = dbHandler.getUser(user);
        ResultSet resultRole = dbHandler.getRoles(user);
        ResultSet resultID = dbHandler.getId(user);
        if(result.next() && resultID.next() && resultRole.next()){
            enterButton.getScene().getWindow().hide();
            id = resultID.getInt(1);
            String userRole = resultRole.getString("role");
            if(userRole.equals("admin")){
                Main.OpenIcon("/sample/SceneFxml/worker/windowAdmins.fxml");}
            if(userRole.equals("worker")){
                Main.OpenIcon("/sample/SceneFxml/worker/windowWorker.fxml");}
            if(userRole.equals("user")){
                Main.OpenIcon("/sample/SceneFxml/windowUsers.fxml");
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пользователь не найден");
            alert.setContentText("Нажмине <Ок>, затем попробуйте сново, возможно вы где-то допустили ошибку.");

            alert.showAndWait();
        }
    }
}
