package model.ADT.List;

import java.util.LinkedList;

public class MyList<T> implements MyIList<T> {
    private LinkedList<T> list;
    public MyList(){
        list = new LinkedList<>();
    }
    @Override
    public void add(T v) {
        list.add(v);
    }
    public LinkedList<T> getList(){
        return list;
    }
}
