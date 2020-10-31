package GUI.RoundStatsScreen;

import GUI.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RoundStatsScreenController extends Controller {
    @FXML private Button backButton;

    public void backButtonAction(Event e) throws IOException {
        Parent gameScreen = FXMLLoader.load(getClass().getResource("../GameScreen/GameScreen.fxml"));
        Scene gameScene = new Scene(gameScreen);

        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setTitle("Play game");
        window.show();
    }
}
