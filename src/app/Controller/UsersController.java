package app.Controller;

import app.AlertMessage.Message;
import app.DBConnection.connection;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersController implements Initializable {


    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> fullname;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TableColumn<User, String> role;

    @FXML
    private Label userDataLbl;

    @FXML
    private JFXTextField fullnameTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private ChoiceBox<String> roleTxt;


    private User odabraniUser;

    Connection con;
    connection conObj = new connection();

    PreparedStatement ps;

    Message msg = new Message();




    @Override
    public void initialize(URL location, ResourceBundle resources){
        userDataLbl.setText(LoginController.korisnik.getFullName());


        ObservableList<String> roleList = FXCollections.observableArrayList();
        roleList.addAll("","Administrator", "Trener", "Korisnik");
        this.roleTxt.setItems(roleList);

        roleTxt.setValue("");

        try {
            ObservableList<User> data = User.listaKontakata();
            id.setCellValueFactory(new PropertyValueFactory<User, Integer>("sifra"));
            fullname.setCellValueFactory(new PropertyValueFactory<User, String>("fullName"));
            email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
            password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
            role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
            table.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    public void chooseUser(javafx.scene.input.MouseEvent mouseEvent) {
        this.odabraniUser = this.table.getSelectionModel().getSelectedItem();
        if(this.odabraniUser != null) {
            this.fullnameTxt.setText(this.odabraniUser.getFullName());
            this.emailTxt.setText(this.odabraniUser.getEmail());
            this.passwordTxt.setText(this.odabraniUser.getPassword());
            this.roleTxt.setValue(this.odabraniUser.getRole());
        }

    }

    @FXML
    public void deleteUser() throws ClassNotFoundException, SQLException {
        if(this.odabraniUser != null) {
            con = conObj.getConnection();
            String str = "DELETE FROM user where ID=?";
            ps = con.prepareStatement(str);
            ps.setInt(1, this.odabraniUser.getSifra());
            ps.executeUpdate();

            msg.setMessage("User deleted successfully.");

            ObservableList<User> data = User.listaKontakata();
            table.setItems(data);

            this.odabraniUser = null;
            fullnameTxt.setText("");
            emailTxt.setText("");
            passwordTxt.setText("");
            roleTxt.setValue("");
        }
    }

    @FXML
    public void saveUser(ActionEvent e) throws ClassNotFoundException, SQLException {
        if(this.odabraniUser == null){
            con = conObj.getConnection();
            if(fullnameTxt.getText().isEmpty()){
                msg.setMessage("Please enter name.");
            }else if(emailTxt.getText().isEmpty()){
                msg.setMessage("Please enter email.");
            }else if(passwordTxt.getText().isEmpty() || passwordTxt.getText().length() < 8){
                msg.setMessage("The password must be at least 8 characters long.");
            }else if(roleTxt.getValue().isEmpty()){
                msg.setMessage("Please choose role.");
            } else {

                String insert = "INSERT INTO user(KorisnickoIme, Email, Password, uloga)" + "Values (?, ?, ?, ?)";
                ps = con.prepareStatement(insert);
                ps.setString(1, fullnameTxt.getText());
                ps.setString(2, emailTxt.getText());
                ps.setString(3, passwordTxt.getText());
                ps.setString(4, roleTxt.getValue());
                ps.executeUpdate();

                msg.setMessage("Registration successfull.");

                fullnameTxt.setText("");
                emailTxt.setText("");
                passwordTxt.setText("");
                roleTxt.setValue("");

                ObservableList<User> data = User.listaKontakata();
                table.setItems(data);

            }
        }else{
            con = conObj.getConnection();
            String str = "UPDATE user SET KorisnickoIme=?, Email=?, Password=?, uloga=? WHERE ID=?";
            ps = con.prepareStatement(str);
            ps.setString(1, fullnameTxt.getText());
            ps.setString(2, emailTxt.getText());
            ps.setString(3, passwordTxt.getText());
            ps.setString(4, roleTxt.getValue());
            ps.setInt(5,this.odabraniUser.getSifra());
            ps.executeUpdate();

            msg.setMessage("User updated successfull.");
            this.odabraniUser = null;
            fullnameTxt.setText("");
            emailTxt.setText("");
            passwordTxt.setText("");
            roleTxt.setValue("");

            ObservableList<User> data = User.listaKontakata();
            table.setItems(data);
        }
    }

    @FXML
    public void backUserToNull(MouseEvent e) throws SQLException, ClassNotFoundException {
        this.odabraniUser = null;
        fullnameTxt.setText("");
        emailTxt.setText("");
        passwordTxt.setText("");
        roleTxt.setValue("");
        ObservableList<User> data = User.listaKontakata();
        table.setItems(data);
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        Stage back = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/HomePage.fxml"));
        Scene scene = new Scene(root, 906, 681);
        back.setTitle("Gym System");
        back.setScene(scene);
        back.show();
    }

}

