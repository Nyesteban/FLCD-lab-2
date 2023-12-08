
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static void printToFile(String filePath, Object object) {
        try(PrintStream printStream = new PrintStream(filePath)) {
            printStream.println(object);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void run(String filePath) {
        ProgramScanner scanner = new ProgramScanner(filePath);
        scanner.scan();
        printToFile(filePath.replace(".txt", "ST.txt"), scanner.getSymbolTable());
        printToFile(filePath.replace(".txt", "PIF.txt"), scanner.getPif());
    }

    private static void printMenu() {
        System.out.println("1. Print states.");
        System.out.println("2. Print initial state.");
        System.out.println("3. Print final states.");
        System.out.println("4. Print alphabet.");
        System.out.println("5. Print transitions.");
        System.out.println("6. Print if the FA is deterministic or not.");
        System.out.println("7. Check if sequence is accepted by a DFA.");
        System.out.println("0. Exit.");
    }

    private static void DFAOperations() {

        FiniteAutomaton finiteAutomaton = new FiniteAutomaton("fas/FA.in");

        System.out.println("FA read from file.");
        printMenu();
        System.out.println("Your option: ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        while (option != 0) {

            switch (option) {
                case 1:
                    System.out.println("States: ");
                    System.out.println(finiteAutomaton.getStates());
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Initial state: ");
                    System.out.println(finiteAutomaton.getInitialState());
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Final states: ");
                    System.out.println(finiteAutomaton.getFinalStates());
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Alphabet: ");
                    System.out.println(finiteAutomaton.getAlphabet());
                    System.out.println();
                    break;

                case 5:
                    System.out.println(finiteAutomaton.writeTransitions());
                    break;

                case 6:
                    System.out.println(finiteAutomaton.checkIfDeterministic());
                    break;

                case 7: {
                    System.out.println("Your sequence: ");
                    Scanner scannerSeq = new Scanner(System.in);
                    String sequence = scannerSeq.nextLine();

                    if (finiteAutomaton.acceptsSequence(sequence))
                        System.out.println("Valid sequence");
                    else
                        System.out.println("Invalid sequence");
                }
                break;

                default:
                    System.out.println("Invalid command!");
                    break;

            }
            printMenu();
            System.out.println("Your option: ");
            option = scanner.nextInt();
        }
    }

    private static void printGrammarMenu() {
        System.out.println("1. Print non-terminals");
        System.out.println("2. Print terminals");
        System.out.println("3. Print starting symbol");
        System.out.println("4. Print all productions");
        System.out.println("5. Print all productions for a non terminal");
        System.out.println("6. Is the grammar a CFG ?");
        System.out.println("7. Get FIRST and FOLLOW sets");
        System.out.println("0. Exit");
    }

    private static void testGrammar(){
        Grammar grammar = new Grammar("gs/g3.txt");
        Parser parser = new Parser(grammar);
        var FIRST = parser.getFirstSet();
        var FOLLOW = parser.getFollowSet();
        if(!FIRST.get("A").contains("+"))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("A").contains("EPSILON"))
            throw new AssertionError("FIRST fail");
        if(!(FIRST.get("A").size() == 2))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("B").contains("a"))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("B").contains("("))
            throw new AssertionError("FIRST fail");
        if(!(FIRST.get("B").size() == 2))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("S").contains("a"))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("S").contains("("))
            throw new AssertionError("FIRST fail");
        if(!(FIRST.get("S").size() == 2))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("C").contains("*"))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("C").contains("EPSILON"))
            throw new AssertionError("FIRST fail");
        if(!(FIRST.get("C").size() == 2))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("D").contains("a"))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("D").contains("("))
            throw new AssertionError("FIRST fail");
        if(!(FIRST.get("D").size() == 2))
            throw new AssertionError("FIRST fail");
        if(!FOLLOW.get("A").contains(")"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("A").contains("EPSILON"))
            throw new AssertionError("FOLLOW fail");
        if(!(FOLLOW.get("A").size() == 2))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("B").contains(")"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("B").contains("+"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("B").contains("EPSILON"))
            throw new AssertionError("FOLLOW fail");
        if(!(FOLLOW.get("B").size() == 3))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("S").contains(")"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("S").contains("EPSILON"))
            throw new AssertionError("FOLLOW fail");
        if(!(FOLLOW.get("S").size() == 2))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("C").contains(")"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("C").contains("+"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("C").contains("EPSILON"))
            throw new AssertionError("FOLLOW fail");
        if(!(FOLLOW.get("C").size() == 3))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("D").contains(")"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("D").contains("*"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("D").contains("+"))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("D").contains("EPSILON"))
            throw new AssertionError("FOLLOW fail");
        if(!(FOLLOW.get("D").size() == 4))
            throw new AssertionError("FOLLOW fail");
        grammar = new Grammar("gs/g1.txt");
        parser = new Parser(grammar);
        FIRST = parser.getFirstSet();
        FOLLOW = parser.getFollowSet();
        if(!FIRST.get("A").contains("a"))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("A").contains("b"))
            throw new AssertionError("FIRST fail");
        if(!(FIRST.get("A").size() == 2))
            throw new AssertionError("FIRST fail");
        if(!FIRST.get("S").contains("a"))
            throw new AssertionError("FIRST fail");
        if(!(FIRST.get("S").size() == 1))
            throw new AssertionError("FIRST fail");
        if(!FOLLOW.get("A").contains("EPSILON"))
            throw new AssertionError("FOLLOW fail");
        if(!(FOLLOW.get("A").size() == 1))
            throw new AssertionError("FOLLOW fail");
        if(!FOLLOW.get("S").contains("EPSILON"))
            throw new AssertionError("FOLLOW fail");
        if(!(FOLLOW.get("S").size() == 1))
            throw new AssertionError("FOLLOW fail");
    }

    private static void runGrammar() {
        Grammar grammar = new Grammar("gs/g3.txt");
        Parser parser = new Parser(grammar);
        printGrammarMenu();
        System.out.println("Enter your option: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while(option != 0) {
            switch (option) {
                case 1:
                    System.out.println("Non-terminals: " + grammar.getNonTerminals());
                    break;
                case 2:
                    System.out.println("Terminals: " + grammar.getTerminals());
                    break;
                case 3:
                    System.out.println("Starting symbol: " + grammar.getStartingSymbol());
                    break;
                case 4:
                    System.out.println("All productions: ");
                    grammar.getProductions().forEach((lhs, rhs)-> System.out.println(lhs + " -> " + rhs));
                    break;
                case 5:
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter a non-terminal: ");
                    String nonTerminal= sc.nextLine();
                    System.out.println("\n\n Productions for the non-terminal: " + nonTerminal);
                    List<String> key = new ArrayList<>();
                    key.add(nonTerminal);
                    try {
                        grammar.getProductions().get(key).forEach((rhs) -> System.out.println(key + " -> " + rhs));
                    } catch (NullPointerException e) {
                        System.out.println("Value is not a defined non-terminal");
                    }
                    break;
                case 6:
                    System.out.println(grammar.isCFG());
                    break;
                case 7:
                    System.out.println(parser.getFirstSet());
                    System.out.println(parser.getFollowSet());
                    break;
            }
            printGrammarMenu();
            System.out.println("Your option: ");
            option = scanner.nextInt();
        }
    }

    public static void main(String[] args) {
        /*
        System.out.println("1. Scanner");
        System.out.println("2. FA");
        System.out.println("Your option: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                run("programs/p1.txt");
                run("programs/p2.txt");
                run("programs/p3.txt");
                run("programs/p1err.txt");
            }
            case 2 -> DFAOperations();
            default -> System.out.println("Invalid command!");
        }
        */
        testGrammar();
        runGrammar();
    }
}