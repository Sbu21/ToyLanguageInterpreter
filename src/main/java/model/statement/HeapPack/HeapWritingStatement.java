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

public class HeapWritingStatement implements IStmt {
    String heapAddress;
    Exp exp;

    public HeapWritingStatement(String heapAddress, Exp exp) {
        this.heapAddress = heapAddress;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        if(!symTable.containsKey(this.heapAddress)){
            throw new MyException("The key address: "+this.heapAddress+" is not defined in the symTbl!\n");
        }

        Value value = symTable.getValue(this.heapAddress);
        RefValue refValue = (RefValue) value;
        int address = refValue.getHeapAddress();

        if(!heap.containsKey(address)){
            throw new MyException("The key address: "+this.heapAddress+" is not defined in the symTbl!\n");
        }

        Value expValue = this.exp.eval(symTable, heap);
        heap.update(address, expValue);
        return null;
    }

    @Override
    public String toString() {
        return "writeHeap("+this.heapAddress+","+this.exp.toString()+");\n";
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocationStatement(this.heapAddress, this.exp);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeVar = typeEnv.getValue(this.heapAddress);
        Type typeExp = this.exp.typeCheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp))){
            return typeEnv;
        }
        else
            throw new Exception("HeapWritingStatement: Expression can't be eval to:"+typeExp);
    }
}
