package sort;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class BinaryQuicksortPanel extends Service<Void> {
    private double[] array;

    private long sleepTime;

    private Canvas canvas;
    private GraphicsContext gc;

    public BinaryQuicksortPanel(Canvas canvas) {
        generateArray(50);
        sleepTime = 10;
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                sort(0, array.length - 1, 0);
                return null;
            }
        };
    }

    public void generateArray(int size) {
        Random rand = new Random();
        array = new double[size];

        for (int i = 0; i < size; i++)
            array[i] = rand.nextDouble();
    }

    public double[] getArray(){
        return array;
    }

    private void sort(int left, int right, int d) throws InterruptedException {
        if (right <= left || d > Double.SIZE)
            return;

        int i = left, j = right;
        while (i != j) {
            while (getDigit(array[i], d) == 0 && (i < j))
                i++;
            while (getDigit(array[j], d) == 1 && (i < j))
                j--;

            swap(i, j);
            draw();
            Thread.sleep(sleepTime);
        }

        if (getDigit(array[right], d) == 0)
            j++;

        sort(left, j - 1, d + 1);
        sort(j, right, d + 1);
    }

    private void swap(int i, int j) {
        double tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private long getDigit(double number, int i) {
        long bNumber = Double.doubleToLongBits(number);

        // Check sign
        if (number < 0) {
            if (i == 0)
                return 0;
            bNumber = ~bNumber;
        } else if (i == 0)
            return 1;

        return bNumber >> (Double.SIZE - i - 1) & 1;
    }

    public void draw() {
        Platform.runLater(() -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            double w = canvas.getWidth() / array.length;
            for (int i = 0; i < array.length; i++) {
                double h = canvas.getHeight() * array[i];
                gc.setFill(Color.hsb(array[i] * 60, 0.9, 0.9));
                gc.fillRect(i * w, canvas.getHeight() - h, w + 1, h);
            }
        });
    }
}
