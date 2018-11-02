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

public class StringsTabController  implements Initializable {

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
            list.getItems().set(t.getIndex(), t.getNewValue());

            if (t.getIndex() == items.size() - 1)
                list.getItems().add("");

            list.getSelectionModel().select(t.getIndex() + 1);
            list.scrollTo(t.getIndex() + 1);
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
        int maxLength = 0;
        String[] array = new String[size];

        for (int i = 0; i < size; i++) {
            array[i] = items.get(i);

            if (array[i].length() > maxLength)
                maxLength = array[i].length();
        }

        // Sort
        BinaryQuicksort.sort(array, maxLength * Character.SIZE, (str, i) -> {
            int bSize = Character.SIZE;
            char[] characters = str.toCharArray();

            if (i > characters.length * bSize - 1)
                return 0;

            return (characters[i / bSize] >> (bSize - i % bSize - 1)) & 1;
        });

        sortedList.getItems().clear();

        // Add to the sortedList
        for (int i = 0; i < size; i++)
            sortedList.getItems().add(array[i]);

        sortedList.getItems().add("");
    }
}
