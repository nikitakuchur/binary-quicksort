package sort;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField size;

    @FXML
    public Pane pane;

    @FXML
    public Canvas canvas;

    @FXML
    public Button playButton;

    private Double[] arr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arr = generateArray(100);
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
        BinaryQuicksort.sort(arr, Double.SIZE, (number, i) -> {
            long bNumber = Double.doubleToLongBits(number);

            // Check sign
            if (number < 0) {
                if (i == 0)
                    return 0;
                bNumber = ~bNumber;
            }
            else if (i == 0)
                return 1;

            return bNumber >> (Double.SIZE - i - 1) & 1;
        });

        draw(canvas.getGraphicsContext2D());
    }

    public void handleGenerateButtonClick() {
        int size = Integer.parseInt(this.size.getText());
        arr = generateArray(size);
        draw(canvas.getGraphicsContext2D());
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double w = canvas.getWidth() / arr.length;
        for (int i = 0; i < arr.length; i++) {
            double h = canvas.getHeight() * arr[i];
            gc.setFill(Color.hsb(arr[i] * 60, 0.9, 0.9));
            gc.fillRect(i * w, canvas.getHeight() - h, w + 1, h);
        }
    }

    private Double[] generateArray(int size) {
        Random rand = new Random();
        Double[] arr = new Double[size];

        for (int i = 0; i < size; i++)
            arr[i] = rand.nextDouble();

        return arr;
    }

}
