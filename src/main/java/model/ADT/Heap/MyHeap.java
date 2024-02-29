package model.ADT.Heap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<TKey, TValue> implements MyIHeap<TKey, TValue>{
    private Map<TKey, TValue> heap;
    int firstAvailablePosition;
    public MyHeap(){
        this.heap = new HashMap<>();
        this.firstAvailablePosition = 1;
    }
    @Override
    public Collection<TValue> values() {
        return this.heap.values();
    }

    @Override
    public void setContent(Map<TKey, TValue> newHeap) {
        this.heap.clear();
        this.heap.putAll(newHeap);
    }

    @Override
    public void setFirstAvailablePosition() {
        this.firstAvailablePosition = this.firstAvailablePosition + 1;
    }

    @Override
    public int getFirstAvailablePosition() {
        int positionCopy = this.firstAvailablePosition;
        this.setFirstAvailablePosition();
        return positionCopy;
    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public boolean containsKey(TKey key) {
        return this.heap.containsKey(key);
    }

    @Override
    public boolean containsValue(TValue tValue) {
        return this.heap.containsValue(tValue);
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    @Override
    public void update(TKey key, TValue tValue) {
        this.heap.replace(key, tValue);
    }

    @Override
    public void insert(TKey key, TValue tValue) {
        this.heap.put(key, tValue);
    }

    @Override
    public void clear() {
        this.heap.clear();
    }

    @Override
    public TValue getValue(TKey key) {
        return this.heap.get(key);
    }

    @Override
    public TValue remove(TKey key) {
        return this.remove(key);
    }

    @Override
    public Collection<TValue> getAllValues() {
        return this.heap.values();
    }

    @Override
    public Collection<TKey> getAllKeys() {
        return this.heap.keySet();
    }

    @Override
    public Map<TKey, TValue> getContent() {
        return this.heap;
    }
}
