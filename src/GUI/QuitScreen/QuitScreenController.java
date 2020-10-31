package GUI.QuitScreen;

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

public class QuitScreenController extends Controller {
    @FXML private Button playAgainButton;
    @FXML private Button exitButton;

    public void playAgainButtonAction(Event e) throws IOException {
        Parent welcomeScreen = FXMLLoader.load(getClass().getResource("../WelcomeScreen/WelcomeScreen.fxml"));
        Scene welcomeScene = new Scene(welcomeScreen);

        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(welcomeScene);
        window.setTitle("Connect to game server");
        window.show();
    }

    public void exitButtonAction(Event e) {
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.close();
    }
}
