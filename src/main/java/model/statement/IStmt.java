package model.statement;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.PrgState;
import model.type.Type;

public interface IStmt{
    PrgState execute(PrgState state) throws Exception;
    //which is the execution method for a model.statement.
    String toString();
    IStmt deepCopy();
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception;
}
