package model.statement;

import model.ADT.Heap.MyIHeap;
import model.PrgState;
import model.expression.Exp;
import Exceptions.MyException;
import model.value.Value;
import model.ADT.Dictionary.MyIDictionary;
import model.type.Type;
public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }
    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp);
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception{
        Type tVar = typeEnv.getValue(this.id);
        Type tExp = exp.typeCheck(typeEnv);
        if(tVar.equals(tExp)) {
            return typeEnv;
        } else {
            throw new MyException("The sides have different types!");
        }
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(symTbl.containsKey(id)){
            Value newExpVal = exp.eval(symTbl, heap);
            symTbl.update(id, newExpVal);
        } else {
            throw new MyException("Variable " + id + " is not defined!");
        }
        return null;
    }
}
