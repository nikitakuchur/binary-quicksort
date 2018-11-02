package sort.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import sort.BinaryQuicksort;

import java.net.URL;
import java.util.ResourceBundle;

public class NumbersTabController implements Initializable {

    @FXML
    public ListView<String> list;

    @FXML
    public ListView<String> sortedList;

    public ObservableList<String> items;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = FXCollections.observableArrayList();
        list.setCellFactory(TextFieldListCell.forListView());
        list.setItems(items);
        list.getItems().add("");

        list.setOnEditCommit(t -> {
            try {
                String str = t.getNewValue();
                int num = Integer.parseInt(str);
                list.getItems().set(t.getIndex(), "" + num);
                if (t.getIndex() == list.getItems().size() - 1)
                    list.getItems().add("");
            } catch (NumberFormatException ex) {
            }
        });

        list.setOnKeyPressed(e -> {
            int i = list.getSelectionModel().getSelectedIndex();

            if (i == -1 || i == items.size() - 1)
                return;

            if (e.getCode().equals(KeyCode.DELETE))
                list.getItems().remove(i);
        });

        sortedList.getItems().add("");
    }

    @FXML
    public void handleSortButtonClick() {
        int size = items.size() - 1;
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++)
            array[i] = Integer.parseInt(items.get(i));

        // Sort
        BinaryQuicksort.sort(array, Integer.SIZE, (number, i) -> {

            // Check sign
            if (number < 0 && i == 0)
                return 0;
            else if(i == 0)
                return 1;

            return (number >> (Integer.SIZE - i - 1)) & 1;
        });

        sortedList.getItems().clear();

        // Add to the sortedList
        for (int i = 0; i < size; i++)
            sortedList.getItems().add("" + array[i]);

        sortedList.getItems().add("");
    }
}
