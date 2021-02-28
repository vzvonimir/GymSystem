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

public class KorisnikController implements Initializable {

    @FXML
    private Label userDataLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        userDataLbl.setText(LoginController.korisnik.getFullName());
    }

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
    void moveToSettings(ActionEvent event) throws IOException {
        Stage settings = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/KorisnikSettings.fxml"));
        Scene scene = new Scene(root, 906, 681);
        settings.setTitle("Gym System");
        settings.setScene(scene);
        settings.show();
    }

    @FXML
    void moveToTreners(ActionEvent event) throws IOException {
        Stage st = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/TrenerViewZaKorisnike.fxml"));
        Scene scene = new Scene(root, 906, 681);
        st.setTitle("Gym System");
        st.setScene(scene);
        st.show();
    }

}
