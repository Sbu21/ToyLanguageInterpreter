package model.DataTransferObjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.util.Pair;

import java.util.List;

public class BarrierTableEntry {
    private final int address;
    private final int value;
    private final List<Integer> list;

    public BarrierTableEntry(int address, int value, List<Integer> list) {
        this.address = address;
        this.value = value;
        this.list = list;
    }

    public int getAddress() {
        return this.address;
    }

    public Integer getValue() {
        return this.value;
    }

    public SimpleStringProperty addressProperty() {
        return new SimpleStringProperty(String.valueOf(this.address));
    }

    public SimpleStringProperty valueProperty() {
        return new SimpleStringProperty(String.valueOf(this.value));
    }
    public SimpleStringProperty listProperty() {
        return new SimpleStringProperty(String.valueOf(this.list));
    }
}
