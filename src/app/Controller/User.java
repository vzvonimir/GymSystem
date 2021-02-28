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



public class User {

    private final SimpleIntegerProperty sifra;
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty password;
    private final SimpleStringProperty role;


    User(Integer id, String fullname, String email, String password, String role){
         this.sifra = new SimpleIntegerProperty(id);
         this.fullName = new SimpleStringProperty(fullname);
         this.email = new SimpleStringProperty(email);
         this.password = new SimpleStringProperty(password);
         this.role = new SimpleStringProperty(role);
    }

    public int getSifra(){
        return sifra.get();
    }

    public void setSifra(int x){
        this.sifra.set(x);
    }

    public String getFullName(){
        return fullName.get();
    }

    public void setFullName(String fname){
        this.fullName.set(fname);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }




    public static ObservableList<User> listaKontakata () throws ClassNotFoundException, SQLException {
        Connection con;
        connection conObj = new connection();
        PreparedStatement ps;
        con = conObj.getConnection();
        ObservableList<User> lista = FXCollections.observableArrayList();

        String str = "Select *from user";
        ps = con.prepareStatement(str);
        ResultSet rs = ps.executeQuery();
        try {
            while (rs.next()) {
                lista.add(new User(rs.getInt("ID"), rs.getString("KorisnickoIme"), rs.getString("Email"), rs.getString("Password"),
                        rs.getString("uloga")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja.");
        }
        return lista;
    }

}
