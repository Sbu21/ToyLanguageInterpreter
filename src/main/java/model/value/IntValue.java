package model.value;

import model.type.Type;
import model.type.IntType;

public class IntValue implements Value{
    public static final int DEFAULT_INT_VALUE = 0;
    private int val;
    public IntValue(){this.val = IntValue.DEFAULT_INT_VALUE;}
    public IntValue(int v){
        this.val = v;
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof IntValue && ((IntValue)another).getVal() == this.val);
    }
    public int getVal() {return val;}

    public String toString() {
        return "val=" + val;
    }

    public Type getType() { return new IntType();}
}
