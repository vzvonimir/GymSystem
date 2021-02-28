package app.AlertMessage;

import javafx.scene.control.Alert;

public class Message {

    public void setMessage(String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.show();
    }

}
