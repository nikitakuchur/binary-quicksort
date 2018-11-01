package sort;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Pane pane;

    @FXML
    public Canvas canvas;

    @FXML
    public Button playButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        draw(canvas.getGraphicsContext2D());

        pane.prefWidthProperty().bind(pane.widthProperty());
        pane.prefHeightProperty().bind(pane.heightProperty());

        pane.prefWidthProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
            draw(canvas.getGraphicsContext2D());
        });

        pane.prefHeightProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
            draw(canvas.getGraphicsContext2D());
        });
    }

    public void handleRunButtonClick() {

    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
