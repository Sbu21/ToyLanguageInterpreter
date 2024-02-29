package model.statement.HeapPack;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.PrgState;
import model.expression.Exp;
import model.statement.IStmt;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class HeapAllocationStatement implements IStmt {
    private final Exp exp;
    private final String varName;

    public HeapAllocationStatement(String varName, Exp exp) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(!symTable.containsKey(this.varName)){
            throw new MyException(this.varName+" is not defined in the sym table\n");
        }

        Value expValue = this.exp.eval(symTable, heap);
        RefValue refVal = ((RefValue) symTable.getValue(this.varName));
        Type innerType = ((RefType) refVal.getType()).getInner();

        int copyAddress = heap.getFirstAvailablePosition();
        heap.insert(copyAddress, expValue);
        symTable.update(this.varName, new RefValue(copyAddress, innerType));

        return null;
    }

    @Override
    public String toString() {
        return "new("+this.varName+","+this.exp.toString()+");\n";
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocationStatement(this.varName, this.exp);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeVariable = typeEnv.getValue(this.varName);
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeVariable.equals(new RefType(typeExp))){
            return typeEnv;
        }
        else
            throw new Exception("HeapAllocationStatement: different types!\n");
    }
}
