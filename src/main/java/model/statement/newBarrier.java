package model.statement;

import Exceptions.MyException;
import javafx.util.Pair;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.ADT.ISyncTable;
import model.PrgState;
import model.expression.Exp;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

import java.util.ArrayList;
import java.util.List;

public class newBarrier implements IStmt{
    private final String id;
    private final Exp expression;

    public newBarrier(String id, Exp expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        ISyncTable barrierTable = state.getBarrierTable();

        Value valExp;
        try {
            valExp = this.expression.eval(symbolTable, heap);
            if (!valExp.getType().equals(new IntType())) {
                throw new MyException("Expression " + this.expression + " is not of type int!");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        if (!symbolTable.containsKey(this.id)) {
            throw new Exception("Variable " + this.id + " is not defined!");
        }
        Value val = symbolTable.getValue(this.id);
        if (!val.getType().equals(new IntType())) {
            throw new MyException("Variable " + this.id + " is not of type int!");
        }

        int value = ((IntValue) valExp).getVal();
        Pair<Integer, List<Integer>> pair = new Pair<>(value, new ArrayList<>());
        int address = barrierTable.add(pair);
        symbolTable.update(this.id, new IntValue(address));

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        try {
            Type typeExp1 = this.expression.typeCheck(typeEnvironment);
            if (!typeExp1.equals(new IntType())) {
                throw new MyException("Expression " + this.expression + " is not of type int!");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        if (!typeEnvironment.containsKey(this.id)) {
            throw new MyException("Variable " + this.id + " is not defined!");
        }
        Type typeId = typeEnvironment.getValue(this.id);
        if (!typeId.equals(new IntType())) {
            throw new Exception("Variable " + this.id + " is not of type int!");
        }

        return typeEnvironment;
    }

    @Override
    public IStmt deepCopy() {
        return new newBarrier(this.id, this.expression);
    }

    public String toString() {
        return "newBarrier(" + this.id + ", " + this.expression + ")";
    }
}
