package app.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PopisKorisnikaController implements Initializable {

    @FXML
    private AnchorPane usersBackPane;

    @FXML
    private TableView<Korisnik> table;

    @FXML
    private TableColumn<Korisnik, String> imeTXt;

    @FXML
    private TableColumn<Korisnik, String> prezimeTxt;

    @FXML
    private TableColumn<Korisnik, String> visinaTxt;

    @FXML
    private TableColumn<Korisnik, String> tezinaTxt;

    @FXML
    private Label userDataLbl;

    @FXML
    void Back(MouseEvent event) throws IOException {
        Stage back = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/Trener.fxml"));
        Scene scene = new Scene(root, 906, 681);
        back.setTitle("Gym System");
        back.setScene(scene);
        back.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataLbl.setText(LoginController.korisnik.getFullName());

        try {
            ObservableList<Korisnik> data = Korisnik.listaKorisnika();
            imeTXt.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("ime"));
            prezimeTxt.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("prezime"));
            visinaTxt.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("visina"));
            tezinaTxt.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("tezina"));
            table.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
