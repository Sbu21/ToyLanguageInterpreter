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

public class CloseReadFile implements IStmt {

    private final Exp filePath;

    public CloseReadFile(Exp filePath){
        this.filePath = filePath;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception{

        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value filePathValue = filePath.eval(symTable, heap);

        if(!fileTable.containsKey((StringValue) filePathValue)){
            throw new MyException("The file path: " + filePathValue + "is not defined in the file table!\n");
        }

        BufferedReader fileBuffer = fileTable.getValue((StringValue) filePathValue);
        fileBuffer.close();
        fileTable.remove((StringValue) filePathValue);

        return null;
    }
    public String toString(){
        return "closeRead(" + this.filePath + ");\n";
    }

    @Override
    public IStmt deepCopy() {
        return new CloseReadFile(filePath);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeExpression = this.filePath.typeCheck(typeEnv);
        if(!typeExpression.equals(new StringType())){
            throw new Exception("CloseReadFileStatement: file path should be a stringValue!");
        }
        return typeEnv;
    }

}