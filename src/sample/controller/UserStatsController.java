package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.JPA.user.ObservableUser;
import sample.JPA.user.User;
import sample.Main;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import static sample.JPA.user.UserDAO.getAllUsers;

public class UserStatsController extends Main implements Initializable {

    @FXML
    public TableColumn number;
    @FXML
    public TableColumn company;
    @FXML
    public TableColumn name;
    @FXML
    public TableColumn surname;
    @FXML
    public TableColumn email;
    @FXML
    public TableColumn registered_on;
    @FXML
    public TableColumn last_login;
    @FXML
    public TableColumn is_blocked;
    @FXML
    public TableColumn login_count;
    @FXML
    public TableView<ObservableUser> user_table_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillUserTable();
    }


    private void fillUserTable() {

        login_count.setSortable(false);
        user_table_view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        company.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        company.setMinWidth(50);
        name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        name.setMinWidth(50);
        surname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        surname.setMinWidth(50);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.setMinWidth(50);
        registered_on.setCellValueFactory(new PropertyValueFactory<>("registered"));
        registered_on.setMinWidth(50);
        last_login.setCellValueFactory(new PropertyValueFactory<>("lastLogin"));
        last_login.setMinWidth(50);
        is_blocked.setCellValueFactory(new PropertyValueFactory<>("isBlockedCheck"));
        is_blocked.setMinWidth(50);
        login_count.setCellValueFactory(new PropertyValueFactory<>("timeSpentInDate"));
        login_count.setMinWidth(50);

        List<User> userList = getAllUsers();
        ObservableList<ObservableUser> filteredUsers = FXCollections.observableArrayList();

        for (User user : userList) {
            String registeredDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getUserCreationDate());
            String lastLoginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getLastLogin());
            //KONVERTUOJA User Į ObservableUser objektą
            filteredUsers.add(new ObservableUser(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getCompanyName(), registeredDate, lastLoginDate, user.isBlocked(), user.getTimeSpend()));
        }

        user_table_view.setItems(filteredUsers);

    }

}
