package gui;

import controller.Contrl;
import controller.IContrl;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.SelectionMode;
import javafx.util.StringConverter;
import model.ADT.Dictionary.MyDictionary;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyHeap;
import model.ADT.List.MyList;
import model.ADT.MyBarrierTable;
import model.ADT.Stack.MyStack;
import model.PrgState;
import model.expression.*;
import model.statement.*;
import model.statement.FilePack.CloseReadFile;
import model.statement.FilePack.OpenReadFile;
import model.statement.FilePack.ReadFile;
import model.statement.HeapPack.HeapAllocationStatement;
import model.statement.HeapPack.HeapWritingStatement;
import model.type.*;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.IRepo;
import repository.Repo;
import view.RunExampleCommand;
import java.io.BufferedReader;

public class SelectController {
    @FXML
    private ListView<RunExampleCommand> examplesListView;

    public ListView<RunExampleCommand> getExamplesListView() {
        return this.examplesListView;
    }

    @FXML
    public void initialize() throws Exception {
        String FOLDER_PATH = "C:\\Users\\Sirbu\\IdeaProjects\\lab14";


        /// EXAMPLE 2
        /// int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt declare_a = new VarDeclStmt("a", new IntType());
        IStmt declare_b = new VarDeclStmt("b", new IntType());
        Exp multiply_a = new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3);
        Exp add_a = new ArithExp(multiply_a, new ValueExp(new IntValue(2)), 1);
        IStmt assign_a = new AssignStmt("a", add_a);
        Exp add_b = new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1);
        IStmt assign_b = new AssignStmt("b", add_b);
        IStmt print_b = new PrintStmt(new VarExp("b"));

        IStmt programExample2 = new CompStmt(declare_a, new CompStmt(declare_b,
                new CompStmt(assign_a, new CompStmt(assign_b, print_b))));

        MyIDictionary<String, Type> typeEnvironment2 = new MyDictionary<String, Type>();
        programExample2.typeCheck(typeEnvironment2);

        PrgState crtPrgState2 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample2, new MyBarrierTable());

        IRepo repo2 = new Repo(FOLDER_PATH + "\\log2.in");
        IContrl controller2 = new Contrl(repo2);

        controller2.addProgramState(crtPrgState2);


        /// EXAMPLE 3
        /// bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt declare_a3 = new VarDeclStmt("a", new BoolType());
        IStmt declare_v3 = new VarDeclStmt("v", new IntType());
        IStmt assign_a3 = new AssignStmt("a", new ValueExp(new BoolValue(true)));
        IStmt assign_v_1 = new AssignStmt("v", new ValueExp(new IntValue(2)));
        IStmt assign_v_2 = new AssignStmt("v", new ValueExp(new IntValue(3)));
        IStmt if_statement3 = new IfStmt(new VarExp("a"), assign_v_1, assign_v_2);
        IStmt print_v3 = new PrintStmt(new VarExp("v"));

        IStmt programExample3 = new CompStmt(declare_a3, new CompStmt(declare_v3, new CompStmt(assign_a3,
                new CompStmt(if_statement3, print_v3))));

        MyIDictionary<String, Type> typeEnvironment3 = new MyDictionary<String, Type>();
        programExample3.typeCheck(typeEnvironment3);

        PrgState crtPrgState3 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample3, new MyBarrierTable());

        IRepo repo3 = new Repo(FOLDER_PATH + "\\log3.in");
        IContrl controller3 = new Contrl(repo3);

        controller3.addProgramState(crtPrgState3);


        ///EXEMPLE 4
        IStmt stringDeclaration = new VarDeclStmt("varf", new StringType());
        IStmt assignment = new AssignStmt("varf", new ValueExp(new StringValue("test.in")));
        IStmt open = new OpenReadFile(new VarExp("varf"));
        IStmt intDeclaration = new VarDeclStmt("varc", new IntType());
        IStmt readFile = new ReadFile(new VarExp("varf"), "varc");
        IStmt print = new PrintStmt(new VarExp("varc"));
        IStmt close = new CloseReadFile(new VarExp("varf"));

        IStmt programExample4 = new CompStmt(stringDeclaration, new CompStmt(assignment, new CompStmt(open,
                new CompStmt(intDeclaration, new CompStmt(readFile, new CompStmt(print,
                        new CompStmt(readFile, new CompStmt(print, close))))))));

        MyIDictionary<String, Type> typeEnvironment4 = new MyDictionary<String, Type>();
        programExample4.typeCheck(typeEnvironment4);

        PrgState currentProgramState4 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample4, new MyBarrierTable());
        IRepo repo4 = new Repo(FOLDER_PATH + "\\log4.in");
        IContrl controller4 = new Contrl(repo4);

        controller4.addProgramState(currentProgramState4);


        ///EXEMPLE 5
        ///int a; a = 25; int b; b = 30; IF (a > b) THEN print(a) ELSE print(b)

        IStmt declare_a5 = new VarDeclStmt("a", new IntType());
        IStmt assign_a5 = new AssignStmt("a", new ValueExp(new IntValue(25)));
        IStmt declare_b5 = new VarDeclStmt("b", new IntType());
        IStmt assign_b5 = new AssignStmt("b", new ValueExp(new IntValue(30)));
        Exp relationalExpression5 = new RelationalExp(new VarExp("a"), new VarExp("b"), "<");
        IStmt print_a5 = new PrintStmt(new VarExp("a"));
        IStmt print_b5 = new PrintStmt(new VarExp("b"));
        IStmt if_statement5 = new IfStmt(relationalExpression5, print_a5, print_b5);

        IStmt programExample5 = new CompStmt(declare_a5, new CompStmt(assign_a5,
                new CompStmt(declare_b5, new CompStmt(assign_b5, if_statement5))));

        MyIDictionary<String, Type> typeEnvironment5 = new MyDictionary<String, Type>();
        programExample5.typeCheck(typeEnvironment5);

        PrgState currentProgramState5 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample5, new MyBarrierTable());
        IRepo repo5 = new Repo(FOLDER_PATH + "\\log5.in");
        IContrl controller5 = new Contrl(repo5);

        controller5.addProgramState(currentProgramState5);


        /// EXAMPLE 6
        ///Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)

        IStmt declare_v6 = new VarDeclStmt("v", new RefType(new IntType()));
        IStmt alloc_v6 = new HeapAllocationStatement("v", new ValueExp(new IntValue(20)));
        IStmt declare_a6 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        IStmt alloc_a6 = new HeapAllocationStatement("a", new VarExp("v"));
        IStmt print_v6 = new PrintStmt(new VarExp("v"));
        IStmt print_a6 = new PrintStmt(new VarExp("a"));

        IStmt programExample6 = new CompStmt(declare_v6, new CompStmt(alloc_v6,
                new CompStmt(declare_a6, new CompStmt(alloc_a6, new CompStmt(print_v6, print_a6)))));

        MyIDictionary<String, Type> typeEnvironment6 = new MyDictionary<String, Type>();
        programExample6.typeCheck(typeEnvironment6);

        PrgState currentProgramState6 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample6, new MyBarrierTable());
        IRepo repo6 = new Repo(FOLDER_PATH + "\\log6.in");
        IContrl controller6 = new Contrl(repo6);

        controller6.addProgramState(currentProgramState6);


        ///EXAMPLE 7
        ///Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)

        IStmt declare_v7 = new VarDeclStmt("v", new RefType(new IntType()));
        IStmt alloc_v7 = new HeapAllocationStatement("v", new ValueExp(new IntValue(20)));
        IStmt declare_a7 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        IStmt alloc_a7 = new HeapAllocationStatement("a", new VarExp("v"));
        Exp read_v7 = new HeapReadingExp(new VarExp("v"));
        IStmt print_v7 = new PrintStmt(read_v7);
        Exp read_a7 = new HeapReadingExp(new HeapReadingExp(new VarExp("a")));
        Exp add7 = new ArithExp(read_a7, new ValueExp(new IntValue(5)), 1);
        IStmt print_a7 = new PrintStmt(add7);

        IStmt programExample7 = new CompStmt(declare_v7, new CompStmt(alloc_v7, new CompStmt(declare_a7,
                new CompStmt(alloc_a7, new CompStmt(print_v7, print_a7)))));

        MyIDictionary<String, Type> typeEnvironment7 = new MyDictionary<String, Type>();
        programExample7.typeCheck(typeEnvironment7);

        PrgState currentProgramState7 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample7, new MyBarrierTable());
        IRepo repo7 = new Repo(FOLDER_PATH + "\\log7.in");
        IContrl controller7 = new Contrl(repo7);

        controller7.addProgramState(currentProgramState7);


        ///EXAMPLE 8
        ///Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);

        IStmt declare_v8 = new VarDeclStmt("v", new RefType(new IntType()));
        IStmt alloc_v8 = new HeapAllocationStatement("v", new ValueExp(new IntValue(20)));
        Exp read_v8 = new HeapReadingExp(new VarExp("v"));
        IStmt print_v8 = new PrintStmt(read_v8);
        IStmt write_v8 = new HeapWritingStatement("v", new ValueExp(new IntValue(30)));
        Exp read_v8_2 = new HeapReadingExp(new VarExp("v"));
        Exp add8 = new ArithExp(read_v8_2, new ValueExp(new IntValue(5)), 1);
        IStmt print_v8_2 = new PrintStmt(add8);

        IStmt programExample8 = new CompStmt(declare_v8, new CompStmt(alloc_v8, new CompStmt(print_v8,
                new CompStmt(write_v8, print_v8_2))));

        MyIDictionary<String, Type> typeEnvironment8 = new MyDictionary<String, Type>();
        programExample8.typeCheck(typeEnvironment8);

        PrgState currentProgramState8 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample8, new MyBarrierTable());
        IRepo repo8 = new Repo(FOLDER_PATH + "\\log8.in");
        IContrl controller8 = new Contrl(repo8);

        controller8.addProgramState(currentProgramState8);


        ///EXAMPLE 9
        ///Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStmt declare_v9 = new VarDeclStmt("v", new RefType(new IntType()));
        IStmt alloc_v9_1 = new HeapAllocationStatement("v", new ValueExp(new IntValue(20)));
        IStmt declare_a9 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        IStmt alloc_a9 = new HeapAllocationStatement("a", new VarExp("v"));
        IStmt alloc_v9_2 = new HeapAllocationStatement("v", new ValueExp(new IntValue(30)));
        Exp read_a_1 = new HeapReadingExp(new VarExp("a"));
        Exp read_a_2 = new HeapReadingExp(read_a_1);
        IStmt print_a9 = new PrintStmt(read_a_2);

        IStmt programExample9 = new CompStmt(declare_v9, new CompStmt(alloc_v9_1, new CompStmt(declare_a9,
                new CompStmt(alloc_a9, new CompStmt(alloc_v9_2, print_a9)))));

        MyIDictionary<String, Type> typeEnvironment9 = new MyDictionary<String, Type>();
        programExample9.typeCheck(typeEnvironment9);

        PrgState currentProgramState9 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample9, new MyBarrierTable());
        IRepo repo9 = new Repo(FOLDER_PATH + "\\log9.in");
        IContrl controller9 = new Contrl(repo9);

        controller9.addProgramState(currentProgramState9);


        ///EXAMPLE 10
        ///int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt declare_v10 = new VarDeclStmt("v", new IntType());
        IStmt assign_v10_1 = new AssignStmt("v", new ValueExp(new IntValue(4)));
        Exp rel_expr10 = new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">");
        IStmt print_v10_1 = new PrintStmt(new VarExp("v"));
        Exp arithmetic_v10 = new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2);
        IStmt assign_v10_2 = new AssignStmt("v", arithmetic_v10);
        IStmt compoundStatement_v10 = new CompStmt(print_v10_1, assign_v10_2);
        IStmt whileStatement_v10 = new WhileStmt(rel_expr10, compoundStatement_v10);
        IStmt print_v10_2 = new PrintStmt(new VarExp("v"));

        IStmt programExample10 = new CompStmt(declare_v10, new CompStmt(assign_v10_1, new CompStmt(whileStatement_v10, print_v10_2)));

        MyIDictionary<String, Type> typeEnvironment10 = new MyDictionary<String, Type>();
        programExample10.typeCheck(typeEnvironment10);

        PrgState currentProgramState10 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample10, new MyBarrierTable());
        IRepo repo10 = new Repo(FOLDER_PATH + "\\log10.in");
        IContrl controller10 = new Contrl(repo10);

        controller10.addProgramState(currentProgramState10);


        ///EXAMPLE 11
        /// int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))
        IStmt declare_v11 = new VarDeclStmt("v", new IntType());
        IStmt declare_a11 = new VarDeclStmt("a", new RefType(new IntType()));
        IStmt assign_v11_1 = new AssignStmt("v", new ValueExp(new IntValue(10)));
        IStmt alloc_v11 = new HeapAllocationStatement("a", new ValueExp(new IntValue(22)));
        IStmt write_a11 = new HeapWritingStatement("a", new ValueExp(new IntValue(30)));
        IStmt assign_v11_2 = new AssignStmt("v", new ValueExp(new IntValue(32)));
        IStmt print_v11_1 = new PrintStmt(new VarExp("v"));
        Exp read_v11 = new HeapReadingExp(new VarExp("a"));
        IStmt print_v11_2 = new PrintStmt(read_v11);
        IStmt fork_11 = new ForkStmt(new CompStmt(write_a11, new CompStmt(assign_v11_2, new CompStmt(print_v11_1, print_v11_2))));

        IStmt programExample11 = new CompStmt(declare_v11, new CompStmt(declare_a11, new CompStmt(assign_v11_1,
                new CompStmt(alloc_v11, new CompStmt(fork_11, new CompStmt(print_v11_1, print_v11_2))))));

        MyIDictionary<String, Type> typeEnvironment11 = new MyDictionary<String, Type>();
        programExample11.typeCheck(typeEnvironment11);

        PrgState currentProgramState11 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample11, new MyBarrierTable());
        IRepo repo11 = new Repo(FOLDER_PATH + "\\log11.in");
        IContrl controller11 = new Contrl(repo11);

        controller11.addProgramState(currentProgramState11);


        ///EXEMPLE 12
        ///Ref int a; int counter; while(counter < 10){ fork(fork({new (a, counter); print(rH(a))})); counter++;}

        IStmt declare_a12 = new VarDeclStmt("a", new RefType(new IntType()));
        IStmt declare_counter12 = new VarDeclStmt("counter", new IntType());
        Exp rel_expr12 = new RelationalExp(new VarExp("counter"), new ValueExp(new IntValue(10)), "<");
        IStmt alloc_v12 = new HeapAllocationStatement("a", new VarExp("counter"));
        Exp read_v12 = new HeapReadingExp(new VarExp("a"));
        IStmt print_v12 = new PrintStmt(read_v12);
        IStmt fork_12 = new ForkStmt(new CompStmt(alloc_v12, print_v12));
        IStmt fork_12_2 = new ForkStmt(fork_12);
        Exp arithmetic_v12 = new ArithExp(new VarExp("counter"), new ValueExp(new IntValue(1)), 1);
        IStmt assign_v12 = new AssignStmt("counter", arithmetic_v12);
        IStmt compstmt_v12 = new CompStmt(fork_12_2, assign_v12);
        IStmt whileStatement_v12 = new WhileStmt(rel_expr12, compstmt_v12);

        IStmt programExample12 = new CompStmt(declare_a12, new CompStmt(declare_counter12, whileStatement_v12));

        MyIDictionary<String, Type> typeEnvironment12 = new MyDictionary<String, Type>();
        programExample12.typeCheck(typeEnvironment12);

        PrgState currentProgramState12 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample12, new MyBarrierTable());
        IRepo repo12 = new Repo(FOLDER_PATH + "\\log12.in");
        IContrl controller12 = new Contrl(repo12);

        controller12.addProgramState(currentProgramState12);

        ///EXEMPLE FOR
        ///Ref int a; new(a,20);(for(v=0;v<3;v=v+1) fork(print(v);v=v*rh(a)));print(rh(a))

        IStmt declare_a13 = new VarDeclStmt("a", new RefType(new IntType()));
        IStmt alloc_v13 = new HeapAllocationStatement("a", new ValueExp(new IntValue(20)));
        IStmt declare_a13_2 = new VarDeclStmt("v", new IntType());
        IStmt assign_v13 = new AssignStmt("v", new ValueExp(new IntValue(0)));
        Exp rel_expr13 = new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), "<");
        Exp arithmetic_v13 = new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 1);
        IStmt assign_v13_3 = new AssignStmt("v", arithmetic_v13);
        IStmt print_v13 = new PrintStmt(new VarExp("v"));
        Exp read_v13 = new HeapReadingExp(new VarExp("a"));
        Exp multiply_a13 = new ArithExp(new VarExp("v"), read_v13, 3);
        IStmt assign_v13_2 = new AssignStmt("v", multiply_a13);
        IStmt fork_13 = new ForkStmt(new CompStmt(print_v13, assign_v13_2));
        IStmt for_13 = new ForStmt(assign_v13, rel_expr13, assign_v13_3, fork_13);
        Exp read_v13_2 = new HeapReadingExp(new VarExp("a"));
        IStmt print_v13_2 = new PrintStmt(read_v13_2);

        IStmt programExample13 =new CompStmt(declare_a13, new CompStmt(declare_a13_2, new CompStmt(alloc_v13, new CompStmt(for_13, print_v13_2))));

        MyIDictionary<String, Type> typeEnvironment13 = new MyDictionary<String, Type>();
        programExample13.typeCheck(typeEnvironment13);

        PrgState currentProgramState13 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample13, new MyBarrierTable());
        IRepo repo13 = new Repo(FOLDER_PATH + "\\log13.in");
        IContrl controller13 = new Contrl(repo13);

        controller13.addProgramState(currentProgramState13);

        ///EXEMPLE REPEAT UNTIL
        ///int v; int x; int y; v=0;(repeat (fork(print(v);v=v-1);v=v+1) until v==3); x=1; nop; y=3; nop; print(v*10)
        IStmt declare_a14 = new VarDeclStmt("v", new IntType());
        IStmt declare_a14_2 = new VarDeclStmt("x", new IntType());
        IStmt declare_a14_3 = new VarDeclStmt("y", new IntType());
        IStmt assign_v14 = new AssignStmt("v", new ValueExp(new IntValue(0)));
        IStmt print_v14 = new PrintStmt(new VarExp("v"));
        Exp arithmetic_v14 = new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2);
        IStmt assign_v14_2 = new AssignStmt("v", arithmetic_v14);
        Exp arithmetic_v14_2 = new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 1);
        IStmt assign_v14_3 = new AssignStmt("v", arithmetic_v14_2);
        Exp rel_expr14 = new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), "==");
        IStmt fork_14 = new ForkStmt(new CompStmt(print_v14, assign_v14_2));
        IStmt ru_14 = new RepeatUntilStmt(new CompStmt(fork_14, assign_v14_3), rel_expr14);
        IStmt assign_v14_4 = new AssignStmt("x", new ValueExp(new IntValue(1)));
        IStmt nopv14 = new NopStmt();
        IStmt assign_v14_5 = new AssignStmt("y", new ValueExp(new IntValue(3)));
        IStmt nopv14_2 = new NopStmt();
        Exp arithmetic_v14_3 = new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), 3);
        IStmt print_v14_2 = new PrintStmt(arithmetic_v14_3);

        IStmt programExample14 = new CompStmt(declare_a14, new CompStmt(declare_a14_2, new CompStmt(declare_a14_3, new CompStmt(assign_v14, new CompStmt(ru_14, new CompStmt(assign_v14_4, new CompStmt(nopv14, new CompStmt(assign_v14_5, new CompStmt(nopv14_2, print_v14_2)))))))));

        MyIDictionary<String, Type> typeEnvironment14 = new MyDictionary<String, Type>();
        programExample14.typeCheck(typeEnvironment14);

        PrgState currentProgramState14 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample14, new MyBarrierTable());
        IRepo repo14 = new Repo(FOLDER_PATH + "\\log14.in");
        IContrl controller14 = new Contrl(repo14);

        controller14.addProgramState(currentProgramState14);

        ///EXEMPLE SLEEP
        ///v=10;(fork(v=v-1;v=v-1;print(v)); sleep(10);print(v*10)
        IStmt declare_a15 = new VarDeclStmt("v", new IntType());
        IStmt assign_v15 = new AssignStmt("v", new ValueExp(new IntValue(10)));
        Exp arithmetic_v15 = new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2);
        IStmt assign_v15_2 = new AssignStmt("v", arithmetic_v15);
        IStmt print_v15 = new PrintStmt(new VarExp("v"));
        IStmt fork_15 = new ForkStmt(new CompStmt(assign_v15_2, new CompStmt(assign_v15_2, print_v15)));
        IStmt sleep_15 = new SleepStmt(10);
        Exp arithmetic_v15_2 = new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), 3);
        IStmt print_v15_2 = new PrintStmt(arithmetic_v15_2);

        IStmt programExample15 = new CompStmt(declare_a15, new CompStmt(assign_v15, new CompStmt(fork_15, new CompStmt(sleep_15, print_v15_2))));

        MyIDictionary<String, Type> typeEnvironment15 = new MyDictionary<String, Type>();
        programExample15.typeCheck(typeEnvironment15);

        PrgState currentProgramState15 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample15, new MyBarrierTable());
        IRepo repo15 = new Repo(FOLDER_PATH + "\\log15.in");
        IContrl controller15 = new Contrl(repo15);

        controller15.addProgramState(currentProgramState15);

        ///EXEMPLE BARRIER
        ///Ref int v1; Ref int v2; Ref int v3; int cnt;
        ///new(v1,2); new(v2,3); new(v3,4); newBarrier(cnt, rH(v2));
        ///fork(await(cnt);wh(v1,rh(v1)*10);print(rh(v1)));
        ///fork(await(cnt);wh(v2,rh(v2)*10);wh(v2,rh(v2)*10);print(rh(v2)));
        ///await(cnt);print(rH(v3));

        IStmt declare_v16 = new VarDeclStmt("v1", new RefType(new IntType()));
        IStmt declare_v16_2 = new VarDeclStmt("v2", new RefType(new IntType()));
        IStmt declare_v16_3 = new VarDeclStmt("v3", new RefType(new IntType()));
        IStmt declare_v16_4 = new VarDeclStmt("cnt", new IntType());

        IStmt alloc_v16 = new HeapAllocationStatement("v1", new ValueExp(new IntValue(2)));
        IStmt alloc_v16_2 = new HeapAllocationStatement("v2", new ValueExp(new IntValue(3)));
        IStmt alloc_v16_3 = new HeapAllocationStatement("v3", new ValueExp(new IntValue(4)));

        Exp read_v16 = new HeapReadingExp(new VarExp("v1"));
        Exp read_v16_2 = new HeapReadingExp(new VarExp("v2"));
        Exp read_v16_3 = new HeapReadingExp(new VarExp("v3"));

        IStmt newBarrier_v16 = new newBarrier("cnt", read_v16_2);

        IStmt awaitBarrier_v16 = new awaitBarrier("cnt");
        IStmt fork16 = new ForkStmt(
                new CompStmt(
                        awaitBarrier_v16,
                        new CompStmt(
                                new HeapWritingStatement("v1", new ArithExp(read_v16, new ValueExp(new IntValue(10)), 3)),
                                new PrintStmt(read_v16)
                        )
                )
        );
        IStmt fork16_2 = new ForkStmt(
                new CompStmt(
                        awaitBarrier_v16,
                        new CompStmt(
                                new HeapWritingStatement("v2", new ArithExp(read_v16_2, new ValueExp(new IntValue(10)), 3)),
                                new CompStmt(
                                        new HeapWritingStatement("v2", new ArithExp(read_v16_2, new ValueExp(new IntValue(10)), 3)),
                                        new PrintStmt(read_v16_2))
                        )
                )
        );
        IStmt awaitBarrier_v16_2 = new awaitBarrier("cnt");
        IStmt print_v16 = new PrintStmt(read_v16_3);

        IStmt programExample16 = new CompStmt(declare_v16, new CompStmt(declare_v16_2, new CompStmt(declare_v16_3, new CompStmt(declare_v16_4, new CompStmt(alloc_v16, new CompStmt(alloc_v16_2, new CompStmt(alloc_v16_3, new CompStmt(newBarrier_v16, new CompStmt(fork16, new CompStmt(fork16_2, new CompStmt(awaitBarrier_v16_2, print_v16)))))))))));

        MyIDictionary<String, Type> typeEnvironment16 = new MyDictionary<String, Type>();
        programExample16.typeCheck(typeEnvironment16);

        PrgState currentProgramState16 = new PrgState(new MyStack<>(), new MyDictionary<String, Value>(),
                new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), PrgState.generateNewID(), programExample16, new MyBarrierTable());
        IRepo repo16 = new Repo(FOLDER_PATH + "\\log16.in");
        IContrl controller16 = new Contrl(repo16);

        controller16.addProgramState(currentProgramState16);

        this.examplesListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<RunExampleCommand>() {
            @Override
            public String toString(RunExampleCommand runExampleCommand) {
                return runExampleCommand.toString();
            }

            @Override
            public RunExampleCommand fromString(String s) {
                return null;
            }
        }));

        this.examplesListView.getItems().add(new RunExampleCommand("2", "int a; int b; a=2+3*5; b=a+1; Print(b)", controller2));
        this.examplesListView.getItems().add(new RunExampleCommand("3", "bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)", controller3));
        this.examplesListView.getItems().add(new RunExampleCommand("4", "string varf; " +
                " varf=\"test.in\"; " +
                " openRFile(varf); " +
                " int varc; " +
                " readFile(varf,varc) ;print(varc); " +
                " readFile(varf,varc); print(varc) " +
                " closeRFile(varf) ", controller4));
        this.examplesListView.getItems().add(new RunExampleCommand("5", "int a; a = 25; int b; b = 30; IF (a < b) THEN print(a) ELSE print(b)", controller5));
        this.examplesListView.getItems().add(new RunExampleCommand("6", "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)", controller6));
        this.examplesListView.getItems().add(new RunExampleCommand("7", "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)", controller7));
        this.examplesListView.getItems().add(new RunExampleCommand("8", "Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);", controller8));
        this.examplesListView.getItems().add(new RunExampleCommand("9", "Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))", controller9));
        this.examplesListView.getItems().add(new RunExampleCommand("10", "int v; v=4; (while (v>0) print(v);v=v-1);print(v)", controller10));
        this.examplesListView.getItems().add(new RunExampleCommand("11", "int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))", controller11));
        this.examplesListView.getItems().add(new RunExampleCommand("12", "int v; Ref int  a; v=10; new(a, 22); Fork( wH(a, 30); v=32; print(v);print(rH(a)); (Fork( v=16; print(v));(print(v);print(rH(a));", controller12));
        this.examplesListView.getItems().add(new RunExampleCommand("13", "Ref int a; new(a,20);(for(v=0;v<3;v=v+1) fork(print(v);v=v*rh(a)));print(rh(a))", controller13));
        this.examplesListView.getItems().add(new RunExampleCommand("14", "int v; int x; int y; v=0;(repeat (fork(print(v);v=v-1);v=v+1) until v==3); x=1; nop; y=3; nop; print(v*10)", controller14));
        this.examplesListView.getItems().add(new RunExampleCommand("15", "v=10;(fork(v=v-1;v=v-1;print(v)); sleep(10);print(v*10)", controller15));
        this.examplesListView.getItems().add(new RunExampleCommand("16", "Barrier example", controller16));

        this.examplesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
