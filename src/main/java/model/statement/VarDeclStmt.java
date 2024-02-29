package model.statement;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.PrgState;
import model.type.*;
import model.value.*;

public class VarDeclStmt implements IStmt{
    String name;
    Type typ;

    public VarDeclStmt(String name, Type typ) {
        this.name = name;
        this.typ = typ;
    }

    @Override
    public String toString(){
        return typ.toString() + " " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.containsKey(name)){
            throw new MyException("Variable " + name + "is already declared!");
        }
        else if(typ.equals(new IntType())){
            symTbl.insert(name, new IntValue());
        }
        else if(typ.equals(new BoolType())){
            symTbl.insert(name, new BoolValue());
        }
        else if(typ.equals(new StringType())){
            symTbl.insert(name, new StringValue());
        }
        else if(typ instanceof RefType refType){
            symTbl.insert(name, new RefValue(refType.getInner()));
        }
        else {
            throw new MyException("Invalid type!");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, typ);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.insert(this.name, this.typ);
        return typeEnv;
    }
}