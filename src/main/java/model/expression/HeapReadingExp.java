package model.expression;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.type.RefType;
import model.type.Type;
import model.value.IntValue;
import model.value.RefValue;
import model.value.Value;

public class HeapReadingExp implements Exp{
    private Exp exp;
    public HeapReadingExp(Exp exp){
        this.exp = exp;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws Exception {
        Value value = exp.eval(tbl, heap);

        int address = ((RefValue) value).getHeapAddress();
        if(!heap.containsKey(address)){
            throw new MyException("The given key address: "+address+" is not defined\n");
        }
        return heap.getValue(address);
    }

    @Override
    public String toString(){
        return "readHeap("+exp.toString()+")";
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type type = this.exp.typeCheck(typeEnv);
        if(type instanceof RefType){
            RefType refType = (RefType) type;
            return refType.getInner();
        }
        else
            throw new Exception("the rH argument is not ref type");
    }
}
