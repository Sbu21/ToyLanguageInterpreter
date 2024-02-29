package model.statement;

import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.ADT.List.MyIList;
import model.ADT.Stack.MyIStack;
import model.PrgState;
import model.expression.Exp;
import Exceptions.MyException;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;
public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;
    public IfStmt(Exp e, IStmt t, IStmt el) {
        this.exp=e;
        this.thenS=t;
        this.elseS=el;
    }

    @Override
    public String toString(){
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp, thenS, elseS);
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeExp = this.exp.typeCheck(typeEnv);
        if(typeExp.equals(new BoolType())){
            this.thenS.typeCheck(typeEnv.copy());
            this.elseS.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else{
            throw new MyException("The cond IF is not boolean!");
        }
    }

    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value condExpVal = exp.eval(symTbl, heap);
        if(((BoolValue) condExpVal).getVal()){
            stk.push(thenS);
        }
        else {
            stk.push(elseS);
        }
        return null;
    }
}