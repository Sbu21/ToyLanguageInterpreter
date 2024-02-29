package model.statement;

import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.ADT.Stack.MyIStack;
import model.PrgState;
import model.expression.Exp;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class WhileStmt implements  IStmt{

    private final Exp whileCondition;
    private final IStmt whileBody;

    public WhileStmt(Exp whileCondition, IStmt whileBody){
        this.whileCondition = whileCondition;
        this.whileBody = whileBody;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIStack<IStmt> executionStack = state.getExeStack();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value evaluatedExpression = this.whileCondition.eval(symTable, heap);
        if(((BoolValue) evaluatedExpression).getVal()){
            executionStack.push(this);
            executionStack.push(this.whileBody);
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(this.whileCondition, this.whileBody);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeExpression = this.whileCondition.typeCheck(typeEnv);
        if(typeExpression.equals(new BoolType())){
           this.whileBody.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new Exception("WhileStatement: Conditional expression is not boolean!\n");
    }

    @Override
    public String toString(){
        return "while("+whileCondition.toString()+"){ "+whileBody.toString()+" }";
    }

}
