package model.expression;

import model.ADT.Heap.MyIHeap;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;
import model.ADT.Dictionary.MyIDictionary;
import Exceptions.MyException;
public class LogicExp implements Exp{
    private Exp e1;
    private Exp e2;
    private int op;

    public LogicExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws Exception {
        Value v1, v2;
        v1 = this.e1.eval(tbl, heap);
        v2 = this.e2.eval(tbl, heap);
        boolean b1 = ((BoolValue) v1).getVal();
        boolean b2 = ((BoolValue) v2).getVal();
        if(op == 1){
            return new BoolValue(b1 && b2);
        }
        if(op == 2){
            return new BoolValue(b1 || b2);
        }
        else{
            throw new MyException("Invalid boolean operators");
        }
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type t1, t2;
        t1 = this.e1.typeCheck(typeEnv);
        t2 = this.e2.typeCheck(typeEnv);
        if(!t1.equals(new BoolType())){
            throw new Exception("First expression is not boolean!");
        }
        if(!t2.equals(new BoolType())){
            throw new Exception("Second expression is not boolean!");
        }
        return new BoolType();
    }
}
