package model.statement;

import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.ADT.List.MyIList;
import model.PrgState;
import Exceptions.MyException;
import model.expression.Exp;
import model.type.Type;
import model.value.Value;

public class PrintStmt implements IStmt{
    private Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString(){ return "print(" +exp.toString()+")";}

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        this.exp.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIList<Value> output = state.getOutput();
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        output.add(exp.eval(symbolTable, heap));
        return null;
    }
}