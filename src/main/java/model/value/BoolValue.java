package model.value;

import model.type.BoolType;
import model.type.Type;

public class BoolValue implements Value{

    public static final boolean DEFAULT_BOOL_VALUE = false;
    boolean val;
    public BoolValue(boolean v){
        this.val = v;
    }
    public BoolValue(){
        this.val = BoolValue.DEFAULT_BOOL_VALUE;
    }

    @Override
    public boolean equals(Object another){
        return (another instanceof BoolValue && ((BoolValue)another).getVal() == this.val);
    }
    public boolean getVal(){return val;}

    public String toString() {
        return "val=" + val;
    }

    public Type getType(){
        return new BoolType();
    }
}
