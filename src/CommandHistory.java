import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private final List<String> history = new ArrayList<>();
    public static final Integer SIZE = 20;

    public List<String> getHistory() {
        return history;
    }

    public void add(String command) {
        if (history.size() >= SIZE) {
            history.remove(0);
        }

        history.add(command);
    }

    public String get(int index) {
        if (index == -1) {
            return getLast();
        }

        return history.get(index - 1);
    }

    public String getLast() {
        return history.get(history.size() - 1);
    }
}
