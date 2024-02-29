package model.statement.FilePack;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.PrgState;
import model.expression.Exp;
import model.statement.IStmt;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements IStmt {

    private Exp filePath;

    public OpenReadFile(Exp filePath){
        this.filePath = filePath;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value filePathValue = this.filePath.eval(symTbl, heap);

        if(fileTable.containsKey((StringValue) filePathValue)){
            throw new MyException("The filepath is already a key in FileTable!");
        }

        try{
            BufferedReader fileBuffer = new BufferedReader(new FileReader(((StringValue) filePathValue).getValue()));
            fileTable.insert((StringValue) filePathValue, fileBuffer);
        }
        catch (FileNotFoundException e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString(){
        return "openRead(" + this.filePath + ");\n";
    }

    @Override
    public IStmt deepCopy() {
        return new OpenReadFile(filePath);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeExpression = this.filePath.typeCheck(typeEnv);
        if(!typeExpression.equals(new StringType())){
            throw new Exception("OpenReadStatement: file path should be a stringValue!\n");
        }
        return typeEnv;
    }
}
