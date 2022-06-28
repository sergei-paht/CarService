package sample;

import sample.GetSet.*;

import java.sql.*;

public class DataBaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, databaseUser, databasePass);
        return dbConnection;
    }

    // Запись пользователя в бд
    public void singUpUser(User user) {
        String insert = "INSERT INTO " + Сonstants.USER_TABLE + "(" + Сonstants.ROLE +","+ Сonstants.USER_FIRSTNAME + ","
                + Сonstants.USER_LASTNAME + "," + Сonstants.USER_LOGIN + "," + Сonstants.USER_PASSWORD + ")" +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getRole());
            prSt.setString(2, user.getFirstname());
            prSt.setString(3, user.getLastname());
            prSt.setString(4, user.getLogin());
            prSt.setString(5, user.getPassword());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Авторизация
    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Сonstants.USER_TABLE + " WHERE " + Сonstants.USER_LOGIN +
                "=? AND " + Сonstants.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet  getRoles(User user){
        ResultSet resSet = null;
        String select = "SELECT " + Сonstants.ROLE + " FROM " + Сonstants.USER_TABLE + " WHERE "+
                Сonstants.USER_LOGIN + " =?" + " AND " + Сonstants.USER_PASSWORD + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    // Запись заявки пользователя в бд
    public void sendAnApplication(Application application) {
        String insert = "INSERT INTO " + Сonstants.APPLICATIONS_TABLE+ "(" + Сonstants.APPLICATIONS_FIRSTNAMEUSERS + ","
                + Сonstants.APPLICATIONS_LASTNAMEUSERS + "," +
                Сonstants.APPLICATIONS_CARBRAND + "," + Сonstants.APPLICATIONS_NUMSERVICE + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, application.getFirstname());
            prSt.setString(2, application.getLastname());
            prSt.setString(3, application.getCarBrand());
            prSt.setString(4, application.getNumService());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Удаление строки услуг по id
    public void deleteRow(int idService, String services) {
        String querry = "DELETE FROM service WHERE idService = "+idService;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(querry);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Удаление строки пользователей
    public void deleteRowUser(int idUser, String user) {
        String querry = "DELETE FROM users WHERE idusers = "+idUser;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(querry);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //удаление строки заявок
    public void deleteRowApp(int idApp, String applications) {
        String querry = "DELETE FROM applications WHERE idapplications = "+idApp;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(querry);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //добавление услуг
    public void addService(Service service) {
        String insert = "INSERT INTO " + Сonstants.SERVICE_TABLE + "(" + Сonstants.SERVICE_NAMESERVICE + ","
                + Сonstants.SERVICE_PRICESERVICE + ")" +
                "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, service.getNameService());
            prSt.setString(2, service.getPriceService());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Добавление пользователя администратором
    public void addUserAdm(User user) {
        String insert = "INSERT INTO " + Сonstants.USER_TABLE + "(" + Сonstants.USER_ROLE + ","
                + Сonstants.USER_FIRSTNAME + "," + Сonstants.USER_LASTNAME + ","
                + Сonstants.USER_LOGIN + "," + Сonstants.USER_PASSWORD + ")" +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getRole());
            prSt.setString(2, user.getFirstname());
            prSt.setString(3, user.getLastname());
            prSt.setString(4, user.getLogin());
            prSt.setString(5, user.getPassword());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet  getName(User user){
        ResultSet resSet = null;
        String select = "SELECT firstname, lastname FROM " + Сonstants.USER_TABLE + " WHERE "+
                Сonstants.ID_USER + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setInt(1, user.getIdUsers());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }
    public ResultSet  getId(User user){
        ResultSet resSet = null;
        String select = "SELECT idusers FROM " + Сonstants.USER_TABLE + " WHERE "+
                Сonstants.USER_LOGIN + " =?" + " AND " + Сonstants.USER_PASSWORD + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}