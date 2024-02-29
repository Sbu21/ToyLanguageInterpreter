package model;

import Exceptions.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.ADT.ISyncTable;
import model.ADT.List.MyIList;
import model.ADT.Stack.MyIStack;
import model.statement.IStmt;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.EmptyStackException;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIHeap<Integer, Value> heap;
    private final ISyncTable barrierTable;
    private int id;
    private static int nextID = 0;

    public static synchronized int generateNewID(){
        return nextID++;
    }
    IStmt originalProgram; //optional field, but good to have
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value>
            ot, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<Integer, Value> heap, int id,IStmt prg, ISyncTable barrierTable){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = id;
        this.barrierTable = barrierTable;
        originalProgram= prg.deepCopy();//recreate the entire original prg
        stk.push(prg);
    }

    public MyIStack<IStmt> getStk(){
        return exeStack;
    }

    public MyIList<Value> getOutput(){return out;}

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }
    public MyIHeap<Integer, Value> getHeap(){
        return this.heap;
    }
    public void setHeap(MyIHeap<Integer, Value> newHeap){
        this.heap = newHeap;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIDictionary<String, Value> getSymTable(){return symTable;}

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }
    public ISyncTable getBarrierTable() {
        return this.barrierTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }
    public int getId(){
        return this.id;
    }

    public String outToString(){
        StringBuilder buf = new StringBuilder();
        for(Value ex: out.getList()) {
            buf.append(ex);
            buf.append("\n");
        }
        return buf.toString();
    }

    public String symTableToString(){
        StringBuilder buf = new StringBuilder();
        for(Value ex: symTable.getDictionary().values()) {
            buf.append(ex);
            buf.append("\n");
        }
        return buf.toString();
    }

    public String exeStkToString(){
        StringBuilder buf = new StringBuilder();
        for(IStmt ex: exeStack.getStack()) {
            buf.append(ex);
            buf.append("\n");
        }
        return buf.toString();
    }
    public String heapToString(){
        StringBuilder buf = new StringBuilder();
        for(Integer ex: heap.getContent().keySet()){
            buf.append(ex);
            buf.append("->");
            buf.append(heap.getValue(ex));
            buf.append("\n");
        }
        return buf.toString();
    }

    public String fileTblToString() {
        StringBuilder tableBuilder = new StringBuilder();

        for (StringValue fileName : fileTable.getContent().keySet()) {
            BufferedReader fileReader = fileTable.getValue(fileName);

            tableBuilder.append(fileName).append(": ");

            try {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    tableBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tableBuilder.toString();
    }

    public String barrierTbltoString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer key : this.barrierTable.keys()) {
            stringBuilder.append(key).append(" -> ").append(this.barrierTable.get(key)).append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStepExecution() throws Exception {
        if(this.exeStack.isEmpty()) throw new MyException("ProgramState stack is empty!");
        IStmt currentStatement = this.exeStack.pop();
        return currentStatement.execute(this);
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString(){
        return "\n------------------\n" +
            "Program ID: " + id + "\n"+
            "Execution Stack: \n" + this.exeStkToString() +
            "\nSymbol Table:\n" + this.symTableToString() +
            "\nOutput Table:\n" + this.outToString() +
            "\nHeap: \n" + this.heapToString() +
            "\nFile Table:\n" + this.fileTblToString()+
            "\nBarrier Table:\n" + this.barrierTbltoString();
    }
}
