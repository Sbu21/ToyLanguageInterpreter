package model.type;

import model.value.RefValue;
import model.value.Value;

public class RefType implements Type{
    Type inner;

    public RefType(Type inner){
        this.inner = inner;
    }

    public Type getInner(){
        return this.inner;
    }
    public boolean equals(Object another){
        if(another instanceof RefType)
            return this.inner.equals(((RefType) another).getInner());
        return false;
    }
    public String toString(){
        return "Ref("+this.inner.toString()+")";
    }
    @Override
    public Value defaultValue() {
        return new RefValue(this.inner);
    }
}
