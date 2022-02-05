import java.io.IOException;
import java.util.Arrays;

public class CommandRunner {
    public static final String[] SUPPORTED_COMMANDS = {
            "ls",
            "cd",
            "echo",
            "ping",
            "ipconfig",
            "ifconfig",
            "history",
            "exit"
    };

    private CommandHistory history = new CommandHistory();
    private boolean finished = false;

    public void run(String command) {
        history.add(command);

        String[] commands = command.split("\\^");

        for (String cmd : commands) {
            try {
                runSingle(cmd.trim());
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    private void runSingle(String command) throws UnsupportedOperationException, IOException, InterruptedException {
        String[] commandSplit = command.split(" ");
        String commandName = commandSplit[0];
        String[] args = Arrays.copyOfRange(commandSplit, 1, commandSplit.length);

        switch (commandName) {
            case "ls":
                handleLs(command, args);
                break;

            case "cd":
                handleCd(command, args);
                break;

            case "echo":
                handleEcho(command, args);
                break;

            case "ping":
                handlePing(command, args);
                break;

            case "ipconfig":
                handleIpConfig(command);
                break;

            case "ifconfig":
                handleIfConfig(command);
                break;

            case "history":
                handleHistory(command, args);
                break;

            case "exit":
                handleExit();
                break;

            default:
                throw new UnsupportedOperationException(commandName + " is not a valid command!");
        }
    }

    private void handleLs(String command, String[] args) {

    }

    private void handleCd(String command, String[] args) {

    }

    private void handleEcho(String command, String[] args) {

    }

    private void handlePing(String command, String[] args) {
        // normalizar los argumentos -n / -c
    }

    private void handleIpConfig(String command) {
        // if platform Windows run
    }

    private void handleIfConfig(String command) {
        // if platform unix darwin run
    }

    private void handleHistory(String command, String[] args) {

    }

    private void handleExit() {
        finished = true;
    }

    public boolean shouldExit() {
        return finished;
    }
}
