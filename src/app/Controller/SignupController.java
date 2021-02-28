package app.Controller;

import app.AlertMessage.Message;
import app.DBConnection.connection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private JFXTextField fullname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signup;

    @FXML
    private Button login;

    private PreparedStatement ps;

    connection conOBJ = new connection();
    Connection con;

    Message msg = new Message();

    @FXML
    public void goLogin(ActionEvent event) throws IOException {
        //login.getScene().getWindow().hide();

        Stage login = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/Login.fxml"));
        Scene scene = new Scene(root, 906, 681);
        login.setTitle("Gym System");
        login.setScene(scene);
        login.show();
    }

    @FXML
    public void signup(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        con = conOBJ.getConnection();
        if(fullname.getText().isEmpty()){
            msg.setMessage("Please enter your name.");
        }else if(email.getText().isEmpty()){
            msg.setMessage("Please enter your email.");
        }else if(password.getText().isEmpty() || password.getText().length() < 8){
            msg.setMessage("The password must be at least 8 characters long.");
        }else {

            String insert = "INSERT INTO user(KorisnickoIme, Email, Password)" + "Values (?, ?, ?)";
            ps = con.prepareStatement(insert);
            ps.setString(1, fullname.getText());
            ps.setString(2, email.getText());
            ps.setString(3, password.getText());

            ps.executeUpdate();

            msg.setMessage("Registration successfull.");


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        fullname.setStyle("-fx-text-inner-color: #fff;");
        email.setStyle("-fx-text-inner-color: #fff;");
        password.setStyle("-fx-text-inner-color: #fff;");
    }


}
