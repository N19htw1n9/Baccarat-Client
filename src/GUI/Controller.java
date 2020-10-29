package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Controller
{
    @FXML
    private TextField portNumberText;

    @FXML
    private TextField ipAddressText;

    @FXML
    public void MouseClickActionIP(ActionEvent e)
    {
        ipAddressText.setStyle("-fx-text-fill: black");
        ipAddressText.setFont(Font.font("System", FontWeight.NORMAL, 12));
        ipAddressText.setText("");
    }

    @FXML
    public void MouseClickActionPN(ActionEvent e)
    {
        portNumberText.setStyle("-fx-text-fill: black");
        portNumberText.setFont(Font.font("System", FontWeight.NORMAL, 12));
        portNumberText.setText("");
    }
}
