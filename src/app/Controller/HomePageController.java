package app.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Label userDataLbl;

    @FXML
    void Logout(MouseEvent event) throws IOException {
        Stage logout = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/Login.fxml"));
        Scene scene = new Scene(root, 906, 681);
        logout.setTitle("Gym System");
        logout.setScene(scene);
        logout.show();
    }

    @FXML
    void moveToUsers(ActionEvent event) throws IOException {
        Stage users = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/AllUsers.fxml"));
        Scene scene = new Scene(root, 906, 681);
        users.setTitle("Gym System");
        users.setScene(scene);
        users.show();
    }

    @FXML
    void moveToTreners(ActionEvent event) throws IOException {
        Stage treners = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/AllTreners.fxml"));
        Scene scene = new Scene(root, 906, 681);
        treners.setTitle("Gym System");
        treners.setScene(scene);
        treners.show();
    }

    @FXML
    void moveToUplate(ActionEvent event) throws IOException {
        Stage uplate = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/Uplate.fxml"));
        Scene scene = new Scene(root, 906, 681);
        uplate.setTitle("Gym System");
        uplate.setScene(scene);
        uplate.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        userDataLbl.setText(LoginController.korisnik.getFullName());
    }
}
