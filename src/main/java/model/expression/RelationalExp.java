package model.expression;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;


public class RelationalExp implements Exp{

    private final Exp firstExpression;
    private final Exp secondExpression;
    private final String operator;

    public RelationalExp(Exp firstExpression, Exp secondExpression, String operator){
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Integer, Value> heap) throws Exception {
        Value firstValue, secondValue;

        firstValue = this.firstExpression.eval(table, heap);
        secondValue = this.secondExpression.eval(table, heap);

        int firstInt = ((IntValue)firstValue).getVal();
        int secondInt = ((IntValue)secondValue).getVal();

        if(this.operator.equals("<")){
            return new BoolValue(firstInt < secondInt);
        }

        if(this.operator.equals("<=")){
            return new BoolValue(firstInt <= secondInt);
        }

        if(this.operator.equals(">")){
            return new BoolValue(firstInt > secondInt);
        }

        if(this.operator.equals(">=")){
            return new BoolValue(firstInt >= secondInt);
        }

        if(this.operator.equals("==")){
            return new BoolValue(firstInt == secondInt);
        }

        if(this.operator.equals("!=")){
            return new BoolValue(firstInt != secondInt);
        }
        else {

            throw new Exception("Invalid DND");
        }
    }
    public String toString(){
        String representation = "";
        representation += (firstExpression.toString());
        representation += " " + this.operator + " ";
        representation += (this.secondExpression.toString());
        return representation;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type type1, type2;
        type1 = this.firstExpression.typeCheck(typeEnv);
        type2 = this.secondExpression.typeCheck(typeEnv);

        if(!type1.equals(new IntType())){
            throw new Exception("First expression is not an integer!\n");
        }

        if(!type2.equals(new IntType())){
            throw new Exception("Second expression is not an integer!\n");
        }

        return new BoolType();
    }

}
