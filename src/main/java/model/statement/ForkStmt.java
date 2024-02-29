package model.statement;

import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Stack.MyIStack;
import model.ADT.Stack.MyStack;
import model.PrgState;
import model.type.Type;
import model.value.Value;

public class ForkStmt implements IStmt{
    IStmt statement;

    public ForkStmt(IStmt statement){
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> newStack = new MyStack<>();
        MyIDictionary<String , Value> symTable = state.getSymTable();
        MyIDictionary<String, Value> newDictionary = symTable.copy();

        return new PrgState(newStack, newDictionary, state.getOutput(), state.getFileTable(),
                state.getHeap(), PrgState.generateNewID(), this.statement, state.getBarrierTable());

    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(this.statement);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        this.statement.typeCheck(typeEnv.copy());
        return typeEnv;
    }

    @Override
    public String toString(){
        return "fork( " + this.statement + " );";
    }

}