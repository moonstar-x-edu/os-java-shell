import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    // capaz sea mejor idea usar un stack para asegurar que no sobrepase el tamano maximo
    private final List<String> history = new ArrayList<>();
    private static final Integer SIZE = 20;

    public List<String> getHistory() {
        return history;
    }

    public void add(String command) {
        history.add(command);
    }
}
