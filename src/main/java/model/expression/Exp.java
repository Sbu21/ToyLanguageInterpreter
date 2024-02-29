package model.expression;

import model.ADT.Heap.MyIHeap;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;
import model.ADT.Dictionary.MyIDictionary;
import Exceptions.MyException;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws Exception;
    String toString();
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception;
}
