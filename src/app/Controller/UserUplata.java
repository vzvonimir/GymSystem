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

public class UserUplata {

    private final SimpleIntegerProperty sifra;
    private final SimpleStringProperty username;
    private final SimpleStringProperty datum;
    private final SimpleStringProperty period;


    UserUplata(){
        this.sifra = new SimpleIntegerProperty(0);
        this.username = new SimpleStringProperty(null);
        this.datum = new SimpleStringProperty(null);
        this.period = new SimpleStringProperty(null);
    }


    public int getSifra() {
        return sifra.get();
    }


    public void setSifra(int sifra) {
        this.sifra.set(sifra);
    }

    public String getUsername() {
        return username.get();
    }


    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getDatum() {
        return datum.get();
    }


    public void setDatum(String datum) {
        this.datum.set(datum);
    }

    public  String getPeriod() {
        return period.get();
    }


    public void setPeriod(String period) {
        this.period.set(period);
    }


    public static ObservableList<UserUplata> listaUplata () throws ClassNotFoundException, SQLException {
        Connection con;
        connection conObj = new connection();
        PreparedStatement ps;
        con = conObj.getConnection();
        ObservableList<UserUplata> lista = FXCollections.observableArrayList();

        String str = "Select *from user";
        ps = con.prepareStatement(str);
        ResultSet rs = ps.executeQuery();


        try {
            while (rs.next()) {

                UserUplata k = new UserUplata();
                k.setSifra(rs.getInt("ID"));
                k.setUsername(rs.getString("KorisnickoIme"));

                String str1 = "Select *from datum WHERE IDUser=?";
                ps = con.prepareStatement(str1);
                ps.setInt(1, rs.getInt("ID"));
                ResultSet rs1 = ps.executeQuery();
                while (rs1.next()) {
                    k.setDatum(rs1.getString("datumuplate"));
                    k.setPeriod(rs1.getString("period"));
                }

                lista.add(k);

            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja.");
        }
        return lista;
    }


}
