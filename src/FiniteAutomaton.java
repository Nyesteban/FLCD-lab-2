import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class FiniteAutomaton {

    private boolean isDeterministic;

    private String initialState;

    private List<String> finalStates;

    private List<String> states;

    private List<String> alphabet;

    private Map<Pair<String, String>, Set<String>> transitions;

    private void readFromFile(String filePath) {

        try (Scanner scanner = new Scanner(new File(filePath))) {

            this.states = new ArrayList<>(List.of(scanner.nextLine().split(",")));

            this.initialState = scanner.nextLine();

            this.finalStates = new ArrayList<>(List.of(scanner.nextLine().split(",")));

            this.alphabet = new ArrayList<>(List.of(scanner.nextLine().split(",")));

            while (scanner.hasNextLine()) {

                String transitionLine = scanner.nextLine();
                String[] transitionComponents = transitionLine.split(" ");


                if (states.contains(transitionComponents[0]) && alphabet.contains(transitionComponents[1]) && states.contains(transitionComponents[2])) {

                    Pair<String, String> transitionStates = new Pair<>(transitionComponents[0], transitionComponents[1]);

                    if (!transitions.containsKey(transitionStates)) {
                        Set<String> transitionStatesSet = new HashSet<>();
                        transitionStatesSet.add(transitionComponents[2]);
                        transitions.put(transitionStates, transitionStatesSet);
                    } else {
                        transitions.get(transitionStates).add(transitionComponents[2]);
                    }

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.isDeterministic = checkIfDeterministic();
    }

    public boolean checkIfDeterministic() {
        return this.transitions.values().stream().allMatch(list -> list.size() <= 1);
    }

    public FiniteAutomaton(String filePath) {
        this.transitions = new HashMap<>();
        this.readFromFile(filePath);
    }

    public List<String> getStates() {
        return this.states;
    }

    public String getInitialState() {
        return this.initialState;
    }

    public List<String> getFinalStates() {
        return this.finalStates;
    }

    public List<String> getAlphabet() {
        return this.alphabet;
    }

    public Map<Pair<String, String>, Set<String>> getTransitions() {
        return this.transitions;
    }

    public String writeTransitions(){
        StringBuilder builder = new StringBuilder();
        builder.append("Transitions: \n");
        transitions.forEach((K, V) -> {
            builder.append(K.getFirst()).append("-").append(K.getSecond()).append("->").append(V).append("\n");
        });

        return builder.toString();
    }

    public boolean acceptsSequence(String sequence) {
        if (!this.isDeterministic) {
            return false;
        }
        if(sequence.length() == 0)
            return finalStates.contains(initialState);
        String currentState = this.initialState;
        for (int i = 0; i < sequence.length(); i++) {
            String currentAlphabet = sequence.substring(i, i + 1);
            Pair<String, String> transition = new Pair<>(currentState, currentAlphabet);
            if (this.transitions.containsKey(transition)) {
                currentState = this.transitions.get(transition).iterator().next();
            } else {
                return false;
            }
        }
        return this.finalStates.contains(currentState);
    }

}
