package model.ADT.List;

import java.util.LinkedList;

public interface MyIList<T> {
    void add(T v);
    LinkedList<T> getList();
}
