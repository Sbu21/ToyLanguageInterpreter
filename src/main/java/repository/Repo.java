package repository;

import Exceptions.MyException;
import model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Repo implements IRepo{

    private List<PrgState> programStateQueue;
    private final String logFilePath;

    public Repo(String filePath) {

        this.programStateQueue = new ArrayList<>();
        this.logFilePath = filePath;
        try{
            clearFile();
        }
        catch (MyException ignored){}
    }

    @Override
    public void clearFile() throws MyException {
        try{
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, false)));
        logFile.append("");
        logFile.close();
        }
        catch (IOException e){
            throw new MyException("Error opening file!");
        }

    }

    @Override
    public List<PrgState> getPrgList() {
        return this.programStateQueue;
    }

    @Override
    public void setProgramList(List<PrgState> prg) {
        this.programStateQueue = prg;
    }

    @Override
    public void logPrgStateExec(PrgState program) throws MyException{
        try
        {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(program.toString());
            logFile.close();
        }
        catch (IOException e){
            throw new MyException("Error opening file!");
        }
    }

    @Override
    public void addProgramState(PrgState state) {
        programStateQueue.add(state);
    }
}
