package app.Controller;

import app.DBConnection.connection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Trener {

    private final SimpleIntegerProperty sifra;
    private final SimpleStringProperty ime;
    private final SimpleStringProperty prezime;
    private final SimpleStringProperty vrsta;
    private final SimpleStringProperty visina;
    private final SimpleStringProperty tezina;
    private final SimpleIntegerProperty sifraKorisnika;
    private final SimpleIntegerProperty sifraUser;


    Trener(Integer id, String ime, String prezime, String vrsta, String visina, String tezina, Integer sifraKorisnika, Integer sifraUser){
        this.sifra = new SimpleIntegerProperty(id);
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.vrsta = new SimpleStringProperty(vrsta);
        this.visina = new SimpleStringProperty(visina);
        this.tezina = new SimpleStringProperty(tezina);
        this.sifraKorisnika = new SimpleIntegerProperty(sifraKorisnika);
        this.sifraUser = new SimpleIntegerProperty(sifraUser);
    }

    public int getSifra() {
        return sifra.get();
    }


    public void setSifra(int sifra) {
        this.sifra.set(sifra);
    }

    public String getIme() {
        return ime.get();
    }


    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }


    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public String getVrsta() {
        return vrsta.get();
    }


    public void setVrsta(String vrsta) {
        this.vrsta.set(vrsta);
    }

    public String getVisina() {
        return visina.get();
    }


    public void setVisina(String visina) {
        this.visina.set(visina);
    }

    public String getTezina() {
        return tezina.get();
    }


    public void setTezina(String tezina) {
        this.tezina.set(tezina);
    }

    public int getSifraKorisnika() {
        return sifraKorisnika.get();
    }


    public void setSifraKorisnika(int sifraKorisnika) {
        this.sifraKorisnika.set(sifraKorisnika);
    }

    public int getSifraUser() {
        return sifraUser.get();
    }

    public void setSifraUser(int sifraUser) {
        this.sifraUser.set(sifraUser);
    }


    public static ObservableList<Trener> listaTrenera () throws ClassNotFoundException, SQLException {
        Connection con;
        connection conObj = new connection();
        PreparedStatement ps;
        con = conObj.getConnection();
        ObservableList<Trener> lista = FXCollections.observableArrayList();

        String str = "Select *from coach";
        ps = con.prepareStatement(str);
        ResultSet rs = ps.executeQuery();
        try {
            while (rs.next()) {
                lista.add(new Trener(rs.getInt("ID"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("Vrsta"), rs.getString("Visina"),rs.getString("Tezina"),rs.getInt("IDKorisnika"),rs.getInt("IDUser")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja.");
        }
        return lista;
    }

}
