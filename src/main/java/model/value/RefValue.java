package model.value;

import model.type.RefType;
import model.type.Type;

public class RefValue implements Value{
    private final int heapAddress;
    public static final int DEFAULT_HEAP_ADDRESS = 0;
    private final Type innerReferencedType;
    public RefValue(Type innerReferencedType){
        this.heapAddress = DEFAULT_HEAP_ADDRESS;
        this.innerReferencedType = innerReferencedType;
    }
    public RefValue(int heapAddress, Type innerReferencedType){
        this.heapAddress = heapAddress;
        this.innerReferencedType = innerReferencedType;
    }
    public int getHeapAddress(){
        return this.heapAddress;
    }
    public String toString(){
        return "("+this.heapAddress+","+innerReferencedType.toString()+")";
    }
    @Override
    public Type getType() {
        return new RefType(this.innerReferencedType);
    }
}
