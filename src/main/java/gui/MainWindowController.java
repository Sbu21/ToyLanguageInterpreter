package gui;

import Exceptions.MyException;
import javafx.util.Pair;
import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.ADT.ISyncTable;
import model.ADT.List.MyIList;
import model.ADT.MyBarrierTable;
import model.ADT.Stack.MyIStack;
import model.DataTransferObjects.BarrierTableEntry;
import model.DataTransferObjects.HeapEntry;
import model.DataTransferObjects.SymbolTableEntry;
import model.PrgState;
import model.statement.IStmt;
import model.value.StringValue;
import model.value.Value;
import view.RunExampleCommand;

import java.io.BufferedReader;
import java.util.List;

public class MainWindowController {

    private SelectController selectController;
    private RunExampleCommand selectedExample;
    private PrgState selectedProgram;


    @FXML
    private TableView<HeapEntry> heapTableView;
    @FXML
    private TableColumn<HeapEntry, String> addressColumn;
    @FXML
    private TableColumn<HeapEntry, String> valueHeapColumn;


    @FXML
    private TableView<SymbolTableEntry> symbolTableView;
    @FXML
    private TableColumn<SymbolTableEntry, String> variableNameColumn;
    @FXML
    private TableColumn<SymbolTableEntry,String> valueSymColumn;

    @FXML
    private ListView<Value> outListView;

    @FXML
    private ListView<StringValue> fileTableListView;
    @FXML
    private ListView<PrgState> programStatesListView;

    @FXML
    private ListView<IStmt> executionStackListView;

    @FXML
    private TextField nrOfProgramStatesTextField;
    @FXML
    private TableView<BarrierTableEntry> barrierTableView;
    @FXML
    private TableColumn<BarrierTableEntry, String> indexColumn;
    @FXML
    private TableColumn<BarrierTableEntry, String> valueColumn;
    @FXML
    private TableColumn<BarrierTableEntry, String> listColumn;

    @FXML
    private Button runOneStepButton;

    public void setSelectController(SelectController newSelectController){
        this.selectController = newSelectController;
        this.selectController.getExamplesListView().getSelectionModel().selectedItemProperty().addListener((a,b,ex)->this.showDataForSelectedExample(ex));

    }

    @FXML
    private void initialize(){
        this.nrOfProgramStatesTextField.setEditable(false);

        this.addressColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry, String>("heapAddress"));
        this.valueHeapColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry, String >("heapValue"));

        this.variableNameColumn.setCellValueFactory(new PropertyValueFactory<SymbolTableEntry, String>("variableName"));
        this.valueSymColumn.setCellValueFactory(new PropertyValueFactory<SymbolTableEntry, String>("value"));

        this.indexColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        this.valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
        this.listColumn.setCellValueFactory(cellData -> cellData.getValue().listProperty());

        this.outListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Value>() {
            @Override
            public String toString(Value valueInterface) {
                return valueInterface.toString();
            }

            @Override
            public Value fromString(String s) {
                return null;
            }
        }));

        this.fileTableListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<StringValue>() {
            @Override
            public String toString(StringValue stringValue) {
                return stringValue.toString();
            }

            @Override
            public StringValue fromString(String s) {
                return null;
            }
        }));

        this.programStatesListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<PrgState>() {
            @Override
            public String toString(PrgState programState) {
                return Integer.toString(programState.getId());
            }

            @Override
            public PrgState fromString(String s) {
                return null;
            }
        }));

        this.executionStackListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<IStmt>() {
            @Override
            public String toString(IStmt statementInterface) {
                return statementInterface.toString();
            }

            @Override
            public IStmt fromString(String s) {
                return null;
            }
        }));

        this.programStatesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.runOneStepButton.setOnAction(actionEvent -> runOneStep(this.selectController.getExamplesListView().getSelectionModel().getSelectedItems().get(0)));

    }

    private void showDataForSelectedExample(RunExampleCommand example){
        this.heapTableView.getItems().clear();
        this.outListView.getItems().clear();
        this.fileTableListView.getItems().clear();
        this.programStatesListView.getItems().clear();
        this.symbolTableView.getItems().clear();
        this.executionStackListView.getItems().clear();
        this.barrierTableView.getItems().clear();

        List<PrgState> programStates = example.getController().getRepo().getPrgList();

        if(programStates.size() != 0)
            this.selectedProgram = programStates.get(0);

        MyIHeap<Integer, Value> sharedHeap = this.selectedProgram.getHeap();
        MyIDictionary<StringValue, BufferedReader> fileTable = this.selectedProgram.getFileTable();
        MyIList<Value> output = this.selectedProgram.getOutput();
        ISyncTable barrier = this.selectedProgram.getBarrierTable();

        sharedHeap.getContent().forEach((address, value)->this.heapTableView.getItems().add(new HeapEntry(address, value)));
        fileTable.getContent().forEach((fileName, filePath)->this.fileTableListView.getItems().add(fileName));
        output.getList().forEach((value)->this.outListView.getItems().add(value));
        for (Integer key : selectedProgram.getBarrierTable().keys()) {
            Pair<Integer, List<Integer>> pair = (Pair<Integer, List<Integer>>) selectedProgram.getBarrierTable().get(key);
            Integer value = pair.getKey();
            List<Integer> list = pair.getValue();
            BarrierTableEntry entry = new BarrierTableEntry(key, value, list);
            this.barrierTableView.getItems().add(entry);
        }

        programStates.forEach((programState)->this.programStatesListView.getItems().add(programState));

        this.nrOfProgramStatesTextField.setText(Integer.toString(programStates.size()));

        this.symbolTableView.getItems().clear();
        this.executionStackListView.getItems().clear();

        MyIStack<IStmt> executionStack = selectedProgram.getExeStack();
        MyIDictionary<String, Value> symbolTable = selectedProgram.getSymTable();

        executionStack.getStack().forEach((statement)->this.executionStackListView.getItems().add(statement));
        symbolTable.getContent().forEach((name, value)->this.symbolTableView.getItems().add(new SymbolTableEntry(name, value)));

    }

    private void runOneStep(RunExampleCommand ex){
        try{
            ex.getController().oneStepExecution();
        }
        catch (Exception e){
        }
        showDataForSelectedExample(ex);
    }

}