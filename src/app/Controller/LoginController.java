package app.Controller;

import app.AlertMessage.Message;
import app.DBConnection.connection;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane anPane;

    @FXML
    private Pane pane;

    @FXML
    private Button forgotBtn;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Button login;

    @FXML
    private Button register;

    private PreparedStatement ps;


    static User korisnik;

    Connection con;

    connection conClass = new connection();

    Message msg = new Message();

    @FXML
    void createLogin(ActionEvent event) throws Exception {

            con = conClass.getConnection();

            String str = "SELECT *FROM user where Email=? and Password=?";
            ps = con.prepareStatement(str);
            ps.setString(1, email.getText());
            ps.setString(2, password.getText());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                korisnik = new User(rs.getInt("ID"), rs.getString("KorisnickoIme"), rs.getString("Email"), rs.getString("Password"),
                        rs.getString("uloga"));

                Stage home = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Parent root;
                if(korisnik.getRole().equals("Administrator")){
                    root = FXMLLoader.load(getClass().getResource("../FXML_files/HomePage.fxml"));
                }else if(korisnik.getRole().equals("Trener")){
                     root = FXMLLoader.load(getClass().getResource("../FXML_files/Trener.fxml"));
                }else{
                      root = FXMLLoader.load(getClass().getResource("../FXML_files/Korisnik.fxml"));
                }
                home.setScene(new Scene(root, 906,681));
                home.setTitle("Gym System");
                home.show();

            } else {
                msg.setMessage("Login failed. Check your email and password.");
            }
    }

    @FXML
    void createRegister(ActionEvent event) throws IOException{

        Stage signup = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/Signup.fxml"));
        Scene scene = new Scene(root, 906, 681);
        signup.setTitle("Gym System");
        signup.setScene(scene);
        signup.show();

    }





    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}
