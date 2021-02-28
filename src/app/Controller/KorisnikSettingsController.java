package app.Controller;

import app.AlertMessage.Message;
import app.DBConnection.connection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KorisnikSettingsController implements Initializable {

    @FXML
    private Label userDataLbl;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXTextField imeTxt;

    @FXML
    private JFXTextField prezimeTxt;

    @FXML
    private JFXTextField visinaTxt;

    @FXML
    private JFXTextField tezinaTxt;

    private Korisnik odabraniKorisnik;

    Connection con;
    connection conClass = new connection();
    private PreparedStatement ps;

    Message msg = new Message();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataLbl.setText(LoginController.korisnik.getFullName());


        try {
            con = conClass.getConnection();
            String str = "SELECT *FROM korisnik where IDUser=?";
            ps = con.prepareStatement(str);
            ps.setInt(1, LoginController.korisnik.getSifra());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                odabraniKorisnik = new Korisnik(rs.getInt("ID"), rs.getString("Ime"), rs.getString("Prezime"),
                         rs.getString("Visina"), rs.getString("Tezina"),
                         rs.getInt("IDUser"), rs.getInt("IDTrener"));
                imeTxt.setText(odabraniKorisnik.getIme());
                prezimeTxt.setText(odabraniKorisnik.getPrezime());
                visinaTxt.setText(odabraniKorisnik.getVisina());
                tezinaTxt.setText(odabraniKorisnik.getTezina());
            }else{
                odabraniKorisnik = null;
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
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/Korisnik.fxml"));
        Scene scene = new Scene(root, 906, 681);
        back.setTitle("Gym System");
        back.setScene(scene);
        back.show();
    }

    @FXML
    void save(ActionEvent event) throws ClassNotFoundException, SQLException {


        if(odabraniKorisnik == null){
            String insert = "INSERT INTO korisnik(Ime, Prezime, Visina, Tezina, IDUser)" + "Values (?, ?, ?, ?,?)";
            ps = con.prepareStatement(insert);
            ps.setString(1, imeTxt.getText());
            ps.setString(2, prezimeTxt.getText());
            ps.setString(3, visinaTxt.getText());
            ps.setString(4, tezinaTxt.getText());
            ps.setInt(5,LoginController.korisnik.getSifra());

            ps.executeUpdate();
            msg.setMessage("Data saved successfully.");
        }else{
            String str1 = "UPDATE korisnik SET Ime=?, Prezime=?, Visina=?, Tezina=? WHERE IDUser=?";
            ps = con.prepareStatement(str1);
            ps.setString(1, imeTxt.getText());
            ps.setString(2, prezimeTxt.getText());
            ps.setString(3, visinaTxt.getText());
            ps.setString(4, tezinaTxt.getText());
            ps.setInt(5,LoginController.korisnik.getSifra());
            ps.executeUpdate();
            msg.setMessage("Data saved successfully.");
        }

    }
}
