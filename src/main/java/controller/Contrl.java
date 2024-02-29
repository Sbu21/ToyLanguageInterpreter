package controller;

import Exceptions.MyException;
import model.ADT.Heap.MyIHeap;
import model.PrgState;
import model.value.RefValue;
import model.value.Value;
import repository.IRepo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Contrl implements IContrl{

    public boolean debugFlag;
    private ExecutorService executor;
    private IRepo repository;
    public Contrl(IRepo repository) {
        this.repository = repository;
    }
    private List<Integer> getAddressFromSymTbl(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v ->{RefValue v1 = (RefValue) v; return v1.getHeapAddress();})
                .collect(Collectors.toList());
    }

    private List<Integer> getAddressesFromHeap(Map<Integer, Value> heap) {
        return heap.values().stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getHeapAddress();
                })
                .collect(Collectors.toList());
    }
    private Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddresses,
                                                              List<Integer> heapReferencedAddressed,
                                                              PrgState currProgram){

        MyIHeap<Integer,Value> heap = currProgram.getHeap();
        return heap.getContent().entrySet().stream()
                .filter(elem-> symTableAddresses.contains(elem.getKey()) || heapReferencedAddressed.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void oneStepExecution(){
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> programs = removeCompletedProgram(this.repository.getPrgList());
        if(programs.size() > 0) {
            Collection<Value> addresses = programs.stream()
                    .flatMap(program -> program.getSymTable().getContent().values().stream())
                    .collect(Collectors.toList());

            programs.get(0).getHeap().setContent(this.safeGarbageCollector(this.getAddressFromSymTbl(addresses),
                    this.getAddressesFromHeap(programs.get(0).getHeap().getContent()), programs.get(0)));

            try {
                this.oneStepForAllPrograms(programs);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            programs = removeCompletedProgram(this.repository.getPrgList());
        }
        executor.shutdown();
        this.repository.setProgramList(programs);
    }

    @Override
    public void oneStepForAllPrograms(List<PrgState> programStatesList) throws InterruptedException {
        programStatesList.forEach(p -> {

            try {
                this.repository.logPrgStateExec(p);
            } catch (MyException e) {
                e.printStackTrace();
            }

        });

        List<Callable<PrgState>> callList = programStatesList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStepExecution))
                .collect(Collectors.toList());

        List<PrgState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future ->
                {
                    try {
                        return future.get();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.toString());
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        programStatesList.addAll(newProgramsList);

        programStatesList.forEach(p -> {
            try {
                this.repository.logPrgStateExec(p);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });

        this.repository.setProgramList(programStatesList);
    }

    @Override
    public List<PrgState> removeCompletedProgram(List<PrgState> inProgramList) {
        return inProgramList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public void addProgramState(PrgState state) {
        repository.addProgramState(state);
    }

    @Override
    public IRepo getRepo(){
        return this.repository;
    }

    //@Override
    //public PrgState oneStep(PrgState state) throws Exception {
    //    MyIStack<IStmt> stack = state.getExeStack();
    //    if(stack.isEmpty()){
    //        throw new MyException("ProgramState stack is empty!");
    //    }
    //    IStmt crtStmt = stack.pop();
    //    return crtStmt.execute(state);
    //}
}
