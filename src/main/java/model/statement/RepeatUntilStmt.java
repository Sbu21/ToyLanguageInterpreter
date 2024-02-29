package model.statement;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.ADT.Stack.MyIStack;
import model.PrgState;
import model.expression.Exp;
import model.expression.UnaryExp;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class RepeatUntilStmt implements IStmt{

    private final Exp expresion;
    private final IStmt statement;

    public RepeatUntilStmt(IStmt statement, Exp expresion) {
        this.expresion = expresion;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIStack<IStmt> executionStack = state.getExeStack();
        MyIHeap<Integer, Value> heap = state.getHeap();

        try {
            Value stopConditionValue = this.expresion.eval(symTable, heap);
            if (!stopConditionValue.getType().equals(new BoolType())) {
                throw new MyException("stop condition is not a boolean");
            }
        } catch (Exception exception) {
            throw new MyException("stop condition could not be evaluated");
        }

        Exp invertedStopCondition = new UnaryExp("!", this.expresion);
        IStmt whileStatement = new WhileStmt(invertedStopCondition, this.statement);

        executionStack.push(whileStatement);
        executionStack.push(this.statement);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatUntilStmt(this.statement, this.expresion);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeExpression = this.expresion.typeCheck(typeEnv);
        if(typeExpression.equals(new BoolType())){
            this.statement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new Exception("WhileStatement: Conditional expression is not boolean!\n");
    }

    @Override
    public String toString() {
        return "Repeat(" + statement +") Until(" + expresion +')';
    }
}
