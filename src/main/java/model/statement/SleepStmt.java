package model.statement;

import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Stack.MyIStack;
import model.PrgState;
import model.type.Type;

public class SleepStmt implements IStmt{
    private final int number;

    public SleepStmt(int number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stack = state.getExeStack();

        if (this.number != 0) {
            stack.push(new SleepStmt(this.number - 1));
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        return typeEnvironment;
    }

    @Override
    public IStmt deepCopy() {
        return new SleepStmt(this.number);
    }

    public String toString() {
        return "sleep(" + this.number + ")";
    }
}
