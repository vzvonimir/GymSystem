package app.Controller;

import app.AlertMessage.Message;
import app.DBConnection.connection;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrenerSettingsController implements Initializable {

    @FXML
    private Label userDataLbl;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXTextField imeTxt;

    @FXML
    private JFXTextField prezimeTxt;

    @FXML
    private ChoiceBox<String> vrstaTxt;

    @FXML
    private JFXTextField visinaTxt;

    @FXML
    private JFXTextField tezinaTxt;

    private Trener odabraniTrener;

    Connection con;
    connection conClass = new connection();
    private PreparedStatement ps;

    Message msg = new Message();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataLbl.setText(LoginController.korisnik.getFullName());

        ObservableList<String> coachList = FXCollections.observableArrayList();
        coachList.addAll("","Kondicijski", "Fitnes", "Crossfit");
        this.vrstaTxt.setItems(coachList);

        vrstaTxt.setValue("");

        try {
            con = conClass.getConnection();
            String str = "SELECT *FROM coach where IDUser=?";
            ps = con.prepareStatement(str);
            ps.setInt(1, LoginController.korisnik.getSifra());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                odabraniTrener = new Trener(rs.getInt("ID"), rs.getString("Ime"), rs.getString("Prezime"),
                        rs.getString("Vrsta"), rs.getString("Visina"), rs.getString("Tezina"),
                        rs.getInt("IDKorisnika"), rs.getInt("IDUser"));
                imeTxt.setText(odabraniTrener.getIme());
                prezimeTxt.setText(odabraniTrener.getPrezime());
                vrstaTxt.setValue(odabraniTrener.getVrsta());
                visinaTxt.setText(odabraniTrener.getVisina());
                tezinaTxt.setText(odabraniTrener.getTezina());
            }else{
                odabraniTrener = null;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        Stage back = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/Trener.fxml"));
        Scene scene = new Scene(root, 906, 681);
        back.setTitle("Gym System");
        back.setScene(scene);
        back.show();
    }

    @FXML
    void save(ActionEvent event) throws ClassNotFoundException, SQLException {


        if(odabraniTrener == null){
            String insert = "INSERT INTO coach(Ime, Prezime, Vrsta, Visina, Tezina, IDUser)" + "Values (?, ?, ?, ?,?,?)";
            ps = con.prepareStatement(insert);
            ps.setString(1, imeTxt.getText());
            ps.setString(2, prezimeTxt.getText());
            ps.setString(3, vrstaTxt.getValue());
            ps.setString(4, visinaTxt.getText());
            ps.setString(5, tezinaTxt.getText());
            ps.setInt(6,LoginController.korisnik.getSifra());

            ps.executeUpdate();
            msg.setMessage("Data saved successfully.");
        }else{
            String str1 = "UPDATE coach SET Ime=?, Prezime=?, Vrsta=?, Visina=?, Tezina=? WHERE IDUser=?";
            ps = con.prepareStatement(str1);
            ps.setString(1, imeTxt.getText());
            ps.setString(2, prezimeTxt.getText());
            ps.setString(3, vrstaTxt.getValue());
            ps.setString(4, visinaTxt.getText());
            ps.setString(5, tezinaTxt.getText());
            ps.setInt(6,LoginController.korisnik.getSifra());
            ps.executeUpdate();
            msg.setMessage("Data saved successfully.");
        }

    }

}
