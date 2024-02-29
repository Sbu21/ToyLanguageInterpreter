package model.expression;

import model.ADT.Heap.MyIHeap;
import model.type.Type;
import model.value.Value;
import model.ADT.Dictionary.MyIDictionary;
import Exceptions.MyException;
public class ValueExp implements Exp{
    private Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws MyException{
        return e;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        return this.e.getType();
    }
 }
