package model.statement;
import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Stack.MyIStack;
import model.PrgState;
import model.type.Type;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    @Override
    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }

    @Override
    public IStmt deepCopy(){
        return new CompStmt(first, snd);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        return this.snd.typeCheck(this.first.typeCheck(typeEnv));
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        stk.push(snd);
        stk.push(first);
        return null;
    }
}
