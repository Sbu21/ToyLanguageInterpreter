package model.statement;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.PrgState;
import model.type.Type;

public class NopStmt implements IStmt{
    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "NOP statement";
    }
}