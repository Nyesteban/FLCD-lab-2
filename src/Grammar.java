import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Grammar {


    private final String ELEMENT_SEPARATOR = "%";

    private final String SEPARATOR_OR_TRANSITION = "\\$";
    private final String TRANSITION_CONCATENATION = " ";
    private final String EPSILON = "EPSILON";
    private final String SEPARATOR_LEFT_RIGHT_HAND_SIDE = "->";

    // LR(0)
    private Set<String> nonTerminals;
    private Set<String> terminals;
    private Map<String, Set<List<String>>> productions;
    private String startingSymbol;
    private boolean isCFG;

    private void processProduction(String production) {
        String[] leftAndRightHandSide = production.split(this.SEPARATOR_LEFT_RIGHT_HAND_SIDE);
        String splitLHS = leftAndRightHandSide[0];
        String[] splitRHS = leftAndRightHandSide[1].split(this.SEPARATOR_OR_TRANSITION);

        this.productions.putIfAbsent(splitLHS, new HashSet<>());
        for (String splitRH : splitRHS) {
            this.productions.get(splitLHS).add(Arrays.stream(splitRH.split(this.TRANSITION_CONCATENATION)).collect(Collectors.toList()));
        }
    }

    private void loadFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            this.nonTerminals = new HashSet<>(List.of(scanner.nextLine().split(this.ELEMENT_SEPARATOR)));
            this.terminals = new HashSet<>(List.of(scanner.nextLine().split(this.ELEMENT_SEPARATOR)));
            this.startingSymbol = scanner.nextLine();

            this.productions = new HashMap<>();
            while (scanner.hasNextLine()) {
                this.processProduction(scanner.nextLine());
            }

            this.isCFG = this.checkIfCFG();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfCFG() {
        if (!this.nonTerminals.contains(this.startingSymbol)) {
            return false;
        }

        for (String leftHandSide : this.productions.keySet()) {
            if (!this.nonTerminals.contains(leftHandSide)) {
                return false;
            }

            for (List<String> possibleNextMoves : this.productions.get(leftHandSide)) {
                for (String possibleNextMove : possibleNextMoves) {
                    if (!this.nonTerminals.contains(possibleNextMove) && !this.terminals.contains(possibleNextMove) && !possibleNextMove.equals(this.EPSILON)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public Grammar(String filePath) {
        this.loadFromFile(filePath);
    }

    public Set<String> getNonTerminals() {
        return this.nonTerminals;
    }

    public Set<String> getTerminals() {
        return this.terminals;
    }

    public Map<String, Set<List<String>>> getProductions() {
        return this.productions;
    }

    public Set<List<String>> getProductionsForNonTerminal(String key) {
        return getProductions().get(key);
    }

    Map<String, Set<List<String>>> getProductionsContainingNonterminal(String nonterminal) {
        Map<String, Set<List<String>>> productionsForNonterminal = new HashMap<>();
        for (var production : productions.entrySet()) {
            for (List<String> rule : production.getValue())
                if (rule.contains(nonterminal))
                    productionsForNonterminal.put(production.getKey(), production.getValue());
        }
        return productionsForNonterminal;
    }

    public String getStartingSymbol() {
        return this.startingSymbol;
    }

    public boolean isCFG() {
        return this.isCFG;
    }

}