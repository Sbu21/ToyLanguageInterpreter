package model.statement;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Stack.MyIStack;
import model.PrgState;
import model.expression.Exp;
import model.type.Type;
import model.value.IntValue;

public class ForStmt implements IStmt{

    private final IStmt init;
    private final Exp cond ;
    private final IStmt increment;
    private final IStmt statement;

    public ForStmt(IStmt init, Exp cond, IStmt increment, IStmt statement) {
        this.init = init;
        this.cond = cond;
        this.increment = increment;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stk = state.getExeStack();

        stk.push(new WhileStmt(this.cond, new CompStmt(this.statement, this.increment)));
        stk.push(this.init);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ForStmt(this.init, this.cond, this.increment, this.statement);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        try {
            this.init.typeCheck(typeEnv.copy());
            this.cond.typeCheck(typeEnv.copy());
            this.increment.typeCheck(typeEnv.copy());
            this.statement.typeCheck(typeEnv.copy());
        }
        catch (Exception e){
            throw new MyException("Exeption");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "For("+ init +" ; " + cond +" ; " + increment + ") " + statement;
    }
}
