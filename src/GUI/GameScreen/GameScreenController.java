package GUI.GameScreen;

import GUI.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class GameScreenController extends Controller {
    @FXML private TextField playerBidText;
    @FXML private ToggleButton playerToggleButton;
    @FXML private ToggleButton bankerToggleButton;

    public void mouseClickActionPB() {
        playerBidText.setStyle("-fx-text-fill: black");
        playerBidText.setFont(Font.font("System", FontWeight.NORMAL, 16));

        try {
            System.out.println(this.connection.getPort());
        } catch (Exception e) {
            System.out.println("This print shouldn't be showing...");
        }

        if ((playerBidText.getText()).equals("Player Bid"))
            playerBidText.setText("");
    }

    public void mouseExitActionPB() {
        if ((playerBidText.getText()).equals("")) {
            playerBidText.setText("Player Bid");
            playerBidText.setStyle("-fx-text-fill: gray");
            playerBidText.setFont(Font.font("System Italic", FontPosture.ITALIC, 16));
        }
    }

    public void toggleButton() {
        group = new ToggleGroup();
        playerToggleButton.setToggleGroup(group);
        bankerToggleButton.setToggleGroup(group);

        if (playerToggleButton.isSelected()) {
            playerToggleButton.setStyle("-fx-background-color: #ed7117");
            bankerToggleButton.setStyle("-fx-background-color: orange");
        }

        else if (bankerToggleButton.isSelected()) {
            bankerToggleButton.setStyle("-fx-background-color: #ed7117");
            playerToggleButton.setStyle("-fx-background-color: orange");
        }

        else if (!(bankerToggleButton.isSelected() || playerToggleButton.isSelected())) {
            bankerToggleButton.setStyle("-fx-background-color: orange");
            playerToggleButton.setStyle("-fx-background-color: orange");
        }
    }
}
