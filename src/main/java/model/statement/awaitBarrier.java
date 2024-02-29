package model.statement;

import Exceptions.MyException;
import javafx.util.Pair;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.ISyncTable;
import model.ADT.Stack.MyIStack;
import model.PrgState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.util.List;

public class awaitBarrier implements IStmt{
    private final String id;

    public awaitBarrier(String id) {
        this.id = id;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stack = state.getExeStack();
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        ISyncTable barrierTable = state.getBarrierTable();

        if (!symbolTable.containsKey(this.id)) {
            throw new MyException("Variable " + this.id + " is not defined!");
        }
        Value val = symbolTable.getValue(this.id);
        if (!val.getType().equals(new IntType())) {
            throw new MyException("Variable " + this.id + " is not of type int!");
        }

        int address = ((IntValue) val).getVal();
        if (!barrierTable.search(address)) {
            throw new MyException("Address " + address + " is not defined in the barrier table!");
        }

        Pair<Integer, List<Integer>> pair = (Pair<Integer, List<Integer>>) barrierTable.get(address);
        int listLen = pair.getValue().size();
        if (pair.getKey() > listLen) {
            if (!pair.getValue().contains(state.getId())) {
                pair.getValue().add(state.getId());
            }
            stack.push(this);
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        if (!typeEnvironment.containsKey(this.id)) {
            throw new MyException("Variable " + this.id + " is not defined!");
        }
        Type typeId = typeEnvironment.getValue(this.id);
        if (!typeId.equals(new IntType())) {
            throw new MyException("Variable " + this.id + " is not of type int!");
        }

        return typeEnvironment;
    }

    @Override
    public IStmt deepCopy() {
        return new awaitBarrier(id);
    }

    public String toString() {
        return "awaitBarrier(" + id + ")";
    }
}
