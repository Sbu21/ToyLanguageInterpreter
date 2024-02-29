package controller;

import Exceptions.MyException;
import model.PrgState;
import repository.IRepo;

import java.util.List;

public interface IContrl {
    //PrgState oneStep(PrgState state) throws Exception;
   // void allStep() throws Exception;

    void oneStepExecution();

    void oneStepForAllPrograms(List<PrgState> programStatesList) throws InterruptedException;

    List<PrgState> removeCompletedProgram(List<PrgState> inProgramList);
    void addProgramState(PrgState state);

    IRepo getRepo();
}
