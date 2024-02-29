package model.statement.FilePack;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.PrgState;
import model.expression.Exp;
import model.statement.IStmt;
import model.type.IntType;
import model.type.StringType;
import model.type.Type;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {

    private Exp filePath;
    private String variableName;

    public ReadFile(Exp filePath, String variableName){
        this.filePath = filePath;
        this.variableName = variableName;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception{
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(!symTable.containsKey(this.variableName)){
            throw new MyException("The variable name is not defined in the symbol table!\n");
        }
        Value filePathValue = filePath.eval(symTable, heap);

        if(!fileTable.containsKey((StringValue) filePathValue)){
            throw new MyException("The file path value is not defined in file table!\n");
        }
        try {
            BufferedReader fileBuffer = fileTable.getValue((StringValue) filePathValue);
            String line = fileBuffer.readLine();
            if (line == null)
            {
                symTable.update(this.variableName, new IntValue());
            }
            else
            {
                try {
                    symTable.update(this.variableName, new IntValue(Integer.parseInt(line)));
                } catch (Exception ignored) {
                    throw new Exception("Cannot read value because EOF has been reached!\n");
                }
            }
        }
        catch(IOException ex){
            throw new IOException("An error has occurred while reading!\n");
        }
        return null;

    }

    @Override
    public String toString(){
        return "readFile(" + this.filePath + ");\n";
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFile(filePath, variableName);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeVariable = typeEnv.getValue(this.variableName);
        Type typeExpression = this.filePath.typeCheck(typeEnv);
        if(typeVariable.equals(new IntType())){
            if(typeExpression.equals(new StringType())){
                return typeEnv;
            }
            else
                throw new Exception("ReadFileStatement: file path be a stringValue!\n");
        }
        else
            throw new Exception("ReadFileStatement" + this.variableName + " is not an integer!\n");
    }
}
