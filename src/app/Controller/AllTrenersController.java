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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AllTrenersController implements Initializable {

    @FXML
    private TableView<Trener> table;

    @FXML
    private TableColumn<Trener, Integer> id;

    @FXML
    private TableColumn<Trener, String> ime;

    @FXML
    private TableColumn<Trener, String> prezime;

    @FXML
    private TableColumn<Trener, String> vrsta;

    @FXML
    private Label userDataLbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataLbl.setText(LoginController.korisnik.getFullName());

        try {
            ObservableList<Trener> data = Trener.listaTrenera();
            id.setCellValueFactory(new PropertyValueFactory<Trener, Integer>("sifra"));
            ime.setCellValueFactory(new PropertyValueFactory<Trener, String>("ime"));
            prezime.setCellValueFactory(new PropertyValueFactory<Trener, String>("prezime"));
            vrsta.setCellValueFactory(new PropertyValueFactory<Trener, String>("vrsta"));
            table.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
