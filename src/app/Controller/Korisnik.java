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

public class Korisnik {
    private final SimpleIntegerProperty sifra;
    private final SimpleStringProperty ime;
    private final SimpleStringProperty prezime;
    private final SimpleStringProperty visina;
    private final SimpleStringProperty tezina;
    private final SimpleIntegerProperty sifraUser;
    private final SimpleIntegerProperty sifraTrener;


    Korisnik(Integer id, String ime, String prezime, String visina, String tezina, Integer IDUser, Integer IDTrener){
        this.sifra = new SimpleIntegerProperty(id);
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.visina = new SimpleStringProperty(visina);
        this.tezina = new SimpleStringProperty(tezina);
        this.sifraUser = new SimpleIntegerProperty(IDUser);
        this.sifraTrener = new SimpleIntegerProperty(IDTrener);
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

    public int getSifraUser() {
        return sifraUser.get();
    }


    public void setSifraUser(int sifraUser) {
        this.sifraUser.set(sifraUser);
    }

    public int getSifraTrener() {
        return sifraTrener.get();
    }

    public void setSifraTrener(int sifraTrener) {
        this.sifraTrener.set(sifraTrener);
    }


    public static ObservableList<Korisnik> listaKorisnika () throws ClassNotFoundException, SQLException {
        Connection con;
        connection conObj = new connection();
        PreparedStatement ps;
        con = conObj.getConnection();
        ObservableList<Korisnik> lista = FXCollections.observableArrayList();

        String str = "Select *from coach WHERE IDUser=?";
        ps = con.prepareStatement(str);
        ps.setInt(1, LoginController.korisnik.getSifra());
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                String str1 = "Select *from korisnik WHERE IDTrener=?";
                ps = con.prepareStatement(str1);
                ps.setInt(1, rs.getInt("ID"));
                ResultSet rs1 = ps.executeQuery();
                while (rs1.next()) {
                    lista.add(new Korisnik(rs1.getInt("ID"), rs1.getString("Ime"), rs1.getString("Prezime"),
                            rs1.getString("Visina"), rs1.getString("Tezina"),
                            rs1.getInt("IDUser"), rs1.getInt("IDTrener")));
                }

            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja.");
        }
        return lista;
    }

}
