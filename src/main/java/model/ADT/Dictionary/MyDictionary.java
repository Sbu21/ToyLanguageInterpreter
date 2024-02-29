package model.ADT.Dictionary;

import Exceptions.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, T> implements MyIDictionary<K,T> {
    private Map<K, T> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<K, T>();
    }

    @Override
    public T remove(K k) {
        if (isEmpty()) {
            throw new IllegalStateException("Dictionary is empty");
        }
        return dictionary.remove(k);
    }

    @Override
    public T getValue(K k) {
        return this.dictionary.get(k);
    }

    @Override
    public Map<K, T> getContent() {
        return this.dictionary;
    }

    @Override
    public boolean containsKey(K k) {
        return dictionary.containsKey(k);
    }

    @Override
    public void update(K k, T newVal) throws MyException {
        if(containsKey(k))
            this.dictionary.put(k, newVal);
        else
            throw new MyException("The key doesn't exist!");
    }

    @Override
    public void insert(K k, T v) throws MyException{
        if(containsKey(k))
            throw new MyException("The key was already defined");
        dictionary.put(k, v);
    }

    @Override
    public MyIDictionary<K, T> copy() throws MyException{
        MyIDictionary<K, T> newDict = new MyDictionary<>();

        for(K key: this.dictionary.keySet()){
            newDict.insert(key, this.dictionary.get(key));
        }

        return newDict;
    }

    @Override
    public Map<K, T> getDictionary() {
        return dictionary;
    }

    @Override
    public Collection<T> getAllValues() {
        return this.dictionary.values();
    }

    public boolean isEmpty(){
        return dictionary.isEmpty();
    }
}
