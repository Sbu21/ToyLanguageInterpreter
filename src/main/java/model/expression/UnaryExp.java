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

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class UnaryExp implements Exp {
    private final String operator;
    private final Exp expression;
    private static final Map<String, UnaryOperator<Boolean>> booleanOperators = new HashMap<>(
            Map.of("!", (a) -> !a)
    );
    private static final Map<String, UnaryOperator<Integer>> integerOperators = new HashMap<>(
            Map.of("--", (a) -> a - 1, "++", (a) -> a + 1, "~", (a) -> ~a)
    );

    public UnaryExp(String operator, Exp expression) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symbolTable, MyIHeap<Integer, Value> heap) throws Exception {
        Value value = this.expression.eval(symbolTable, heap);
        if (value.getType().equals(new BoolType())) {
            return new BoolValue(UnaryExp.booleanOperators.get(this.operator).apply(((BoolValue) value).getVal()));
        }
        if (value.getType().equals(new IntType())) {
            return new IntValue(UnaryExp.integerOperators.get(this.operator).apply(((IntValue) value).getVal()));
        }
        throw new MyException("invalid operand type");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        if (!booleanOperators.containsKey(this.operator) && !integerOperators.containsKey(this.operator)) {
            throw new MyException("invalid operator");
        }

        Type type = this.expression.typeCheck(typeEnvironment);
        if (type.equals(new BoolType())) {
            return new BoolType();
        }
        if (type.equals(new IntType())) {
            return new IntType();
        }
        throw new MyException("invalid operand type");
    }

    public String toString() {
        if (this.operator.equals("--") || this.operator.equals("++")) {
            return this.expression.toString() + this.operator;
        } else if (this.operator.equals("!") || this.operator.equals("~")) {
            return this.operator + this.expression.toString();
        }
        return "";
    }
}
