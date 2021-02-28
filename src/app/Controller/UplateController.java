package app.Controller;

import app.AlertMessage.Message;
import app.DBConnection.connection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UplateController implements Initializable {

    @FXML
    private TableView<UserUplata> table;

    @FXML
    private TableColumn<UserUplata, Integer> id;

    @FXML
    private TableColumn<UserUplata, String> username;

    @FXML
    private TableColumn<UserUplata, String> datum;

    @FXML
    private TableColumn<UserUplata, String> period;

    @FXML
    private Label userDataLbl;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private Label usernameTxt;


    @FXML
    private JFXComboBox<String> planTxt;

    @FXML
    private DatePicker datumTxt;

    private UserUplata uplata;

    Connection con;
    connection conClass = new connection();
    private PreparedStatement ps;
    Message msg = new Message();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataLbl.setText(LoginController.korisnik.getFullName());

        ObservableList<String> planList = FXCollections.observableArrayList();
        planList.addAll("","1 mjesec", "2 mjeseca", "6 mjeseci");
        this.planTxt.setItems(planList);
        planTxt.setValue("");

        try {
            ObservableList<UserUplata> data = UserUplata.listaUplata();
            id.setCellValueFactory(new PropertyValueFactory<UserUplata, Integer>("sifra"));
            username.setCellValueFactory(new PropertyValueFactory<UserUplata, String>("username"));
            datum.setCellValueFactory(new PropertyValueFactory<UserUplata, String>("datum"));
            period.setCellValueFactory(new PropertyValueFactory<UserUplata, String>("period"));
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

    @FXML
    public void chooseUser(MouseEvent mouseEvent) {
        this.uplata = this.table.getSelectionModel().getSelectedItem();
        if(this.uplata != null) {
            this.usernameTxt.setText(this.uplata.getUsername());
            if(this.uplata.getDatum() != null){
                this.datumTxt.setValue(LocalDate.parse(this.uplata.getDatum()));
            }else{
                this.datumTxt.setValue(null);
            }
            this.planTxt.setValue(this.uplata.getPeriod());
        }

    }

    @FXML
    void save(ActionEvent event) throws ClassNotFoundException, SQLException {
        con = conClass.getConnection();

        if(uplata.getDatum() == null && uplata.getPeriod() == null){
            String insert = "INSERT INTO datum(datumuplate, period, IDUser)" + "Values (?, ?, ?)";
            ps = con.prepareStatement(insert);
            ps.setString(1, String.valueOf(datumTxt.getValue()));
            ps.setString(2, planTxt.getValue());
            ps.setInt(3,uplata.getSifra());

            ps.executeUpdate();

            ObservableList<UserUplata> data = UserUplata.listaUplata();
            table.setItems(data);

            msg.setMessage("Data saved successfully.");
        }else{
            String str1 = "UPDATE datum SET datumuplate=?, period=? WHERE IDUser=?";
            ps = con.prepareStatement(str1);
            ps.setString(1, String.valueOf(datumTxt.getValue()));
            ps.setString(2, planTxt.getValue());
            ps.setInt(3,uplata.getSifra());
            ps.executeUpdate();

            ObservableList<UserUplata> data = UserUplata.listaUplata();
            table.setItems(data);

            msg.setMessage("Data saved successfully.");
        }

    }


}
