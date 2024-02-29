package model.expression;

import model.ADT.Heap.MyIHeap;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;
import model.ADT.Dictionary.MyIDictionary;
import Exceptions.MyException;

public class ArithExp implements Exp{
    private Exp e1;
    private Exp e2;
    private int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public String toString() {
        return e1.toString() + op + e2.toString();
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type t1;
        Type t2;
        t1 = this.e1.typeCheck(typeEnv);
        t2 = this.e2.typeCheck(typeEnv);

        if(t1.equals(new IntType())){
            if(t2.equals(new IntType())){
                return new IntType();
            } else{
                throw new MyException("Invalid type for the second exp!");
            }
        } else{
            throw new MyException("Invalid type for the first exp!");
        }
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws Exception {
        Value v1,v2;
        v1 = e1.eval(tbl, heap);
        v2 = e2.eval(tbl, heap);
        int i1 = ((IntValue) v1).getVal();
        int i2 = ((IntValue) v2).getVal();
        if (op == 1){
            return new IntValue(i1 + i2);
        }
        if (op == 2){
            return new IntValue(i1 - i2);
        }
        if (op == 3){
            return new IntValue(i1 * i2);
        }
        if (op == 4){
            if(i2 == 0)
                throw new MyException("Division by 0");
            else
                return new IntValue(i1 / i2);
        } else {
            throw new MyException("Invalid int operators");
        }
    }
}