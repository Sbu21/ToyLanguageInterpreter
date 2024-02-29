package repository;

import Exceptions.MyException;
import model.PrgState;

import java.util.List;

public interface IRepo {
    void logPrgStateExec(PrgState program) throws MyException;
    void clearFile() throws Exception;
    List<PrgState> getPrgList();
    void setProgramList(List<PrgState> prg);

    void addProgramState(PrgState state);
}
