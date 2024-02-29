package model.ADT.Dictionary;

import Exceptions.MyException;
import view.Command;

import java.util.Collection;
import java.util.Map;

public interface MyIDictionary <K, T>{
    T remove(K k);
    T getValue(K k);

    Map<K, T> getContent();

    boolean containsKey(K k);
    void update(K k, T newVal) throws MyException;
    void insert(K k, T v) throws MyException;

    MyIDictionary<K,T> copy() throws MyException;

    Map<K, T> getDictionary();

    public Collection<T> getAllValues();
}
