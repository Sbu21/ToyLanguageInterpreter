package model.expression;

import model.ADT.Heap.MyIHeap;
import model.type.Type;
import model.value.Value;
import model.ADT.Dictionary.MyIDictionary;
import Exceptions.MyException;
public class VarExp implements Exp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        if(!tbl.containsKey(id)){
            throw new MyException(id + "does not exist in SymTable!");
        }
        return tbl.getValue(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.getValue(this.id);
    }
 }
