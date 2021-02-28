package app.Controller;

import app.AlertMessage.Message;
import app.DBConnection.connection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OdaberTreneraController implements Initializable {

    @FXML
    private Label userDataLbl;

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
    private Label imeTxt;

    @FXML
    private Label prezimeTxt;

    @FXML
    private Label vrstaTxt;

    private Trener odabraniTrener;
    Connection con;
    connection conClass = new connection();
    private PreparedStatement ps;

    Message msg = new Message();



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
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/Korisnik.fxml"));
        Scene scene = new Scene(root, 906, 681);
        back.setTitle("Gym System");
        back.setScene(scene);
        back.show();
    }

    @FXML
    public void chooseTrener(javafx.scene.input.MouseEvent mouseEvent) {
        this.odabraniTrener = this.table.getSelectionModel().getSelectedItem();
        if(this.odabraniTrener != null) {
            this.imeTxt.setText(this.odabraniTrener.getIme());
            this.prezimeTxt.setText(this.odabraniTrener.getPrezime());
            this.vrstaTxt.setText(this.odabraniTrener.getVrsta());
        }

    }

    @FXML
    void save(ActionEvent event) throws ClassNotFoundException, SQLException {


        if (odabraniTrener != null) {
            con = conClass.getConnection();
            String str1 = "UPDATE korisnik SET IDTrener=? WHERE IDUser=?";
            ps = con.prepareStatement(str1);
            ps.setInt(1, this.odabraniTrener.getSifra());
            ps.setInt(2, LoginController.korisnik.getSifra());
            ps.executeUpdate();
            msg.setMessage("Data saved successfully.");

        }
    }
}
