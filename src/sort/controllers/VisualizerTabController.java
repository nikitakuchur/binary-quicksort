package sort.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import sort.BinaryQuicksortPanel;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualizerTabController implements Initializable {

    @FXML
    public TextField size;

    @FXML
    public Button generateButton;

    @FXML
    public Pane pane;

    @FXML
    public Canvas canvas;

    @FXML
    public Button playButton;

    @FXML
    public Button stopButton;

    private BinaryQuicksortPanel bqPanel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bqPanel = new BinaryQuicksortPanel(canvas);

        bqPanel.draw();

        pane.prefWidthProperty().bind(pane.widthProperty());
        pane.prefHeightProperty().bind(pane.heightProperty());

        pane.prefWidthProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
            bqPanel.draw();
        });

        pane.prefHeightProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
            bqPanel.draw();
        });

        bqPanel.setOnRunning(t -> {
            generateButton.setDisable(true);
            playButton.setDisable(true);
            stopButton.setDisable(false);
        });

        bqPanel.setOnSucceeded(t -> {
            bqPanel.reset();
        });

        bqPanel.setOnReady(t -> {
            generateButton.setDisable(false);
            playButton.setDisable(false);
            stopButton.setDisable(true);
        });
    }

    @FXML
    public void handleRunButtonClick() {
        bqPanel.start();
    }

    @FXML
    public void handleStopButtonClick() {
        bqPanel.cancel();
        bqPanel.reset();
    }

    @FXML
    public void handleGenerateButtonClick() {
        int size = Integer.parseInt(this.size.getText());
        bqPanel.generateArray(size);
        bqPanel.draw();
    }
}
