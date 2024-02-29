package view;

import controller.Contrl;
import Exceptions.MyException;
import controller.IContrl;
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

import java.io.BufferedReader;
import java.util.Scanner;

public class View {

    public static void main(String[] args) throws Exception {

        String FOLDER_PATH = "C:\\Users\\Sirbu\\IdeaProjects\\lab14";
        TextMenu textMenu = new TextMenu();
        try {
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
            textMenu.addCommand(new RunExampleCommand("2", "int a; int b; a=2+3*5; b=a+1; Print(b)", controller2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
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

            textMenu.addCommand(new RunExampleCommand("3", "bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)", controller3));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
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

            textMenu.addCommand(new RunExampleCommand("4", "string varf; " +
                    " varf=\"test.in\"; " +
                    " openRFile(varf); " +
                    " int varc; " +
                    " readFile(varf,varc) ;print(varc); " +
                    " readFile(varf,varc); print(varc) " +
                    " closeRFile(varf) ", controller4));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
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

            textMenu.addCommand(new RunExampleCommand("5", "int a; a = 25; int b; b = 30; IF (a < b) THEN print(a) ELSE print(b)", controller5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
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

            textMenu.addCommand(new RunExampleCommand("6", "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)", controller6));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
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

            textMenu.addCommand(new RunExampleCommand("7", "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)", controller7));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
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

            textMenu.addCommand(new RunExampleCommand("8", "Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);", controller8));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
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

            textMenu.addCommand(new RunExampleCommand("9", "Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))", controller9));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
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

            textMenu.addCommand(new RunExampleCommand("10", "int v; v=4; (while (v>0) print(v);v=v-1);print(v)", controller10));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
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

            textMenu.addCommand(new RunExampleCommand("11", "int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))", controller11));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
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

            textMenu.addCommand(new RunExampleCommand("12", "Ref int a; int counter; while(counter < 10){ fork(fork({new (a, counter); print(rH(a))})); counter++;}", controller12));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            textMenu.addCommand(new ExitCommand("0", "Exit program"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        textMenu.show();
    }
}
