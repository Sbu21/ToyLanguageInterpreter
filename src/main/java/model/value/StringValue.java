package model.value;

import model.type.StringType;
import model.type.Type;

public class StringValue implements Value{
    public static final String DEFAULT_STRING_VALUE = "";
    private final String value;

    public StringValue() {
        this.value = StringValue.DEFAULT_STRING_VALUE;
    }
    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another){
        return (another instanceof StringValue && ((StringValue)another).getValue().equals(this.value));
    }

    public String getValue() {
        return value;
    }

    public String toString(){
        return value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
