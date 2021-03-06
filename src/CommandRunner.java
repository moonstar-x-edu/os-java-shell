import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandRunner {
    private final CommandHistory history = new CommandHistory();
    private boolean finished = false;

    private String pwd = System.getProperty("user.dir");
    private final boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

    private final Pattern previousCommandPattern = Pattern.compile("!(\\d+|#)");

    public void run(String command) {
        if (!command.matches(previousCommandPattern.toString())) {
            history.add(command);
        }

        String[] commands = command.split("\\^");

        for (String cmd : commands) {
            try {
                System.out.println(cmd.trim() + ":");
                runSingle(cmd.trim());
            } catch (Exception e) {
                System.err.println(e.getMessage());
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

            case "pwd":
                handlePwd(command, args);
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
                Integer previousCommandIndex = matchPreviousCommand(command);

                if (previousCommandIndex == null) {
                    throw new UnsupportedOperationException(commandName + " is not a valid command!");
                }

                handlePrevious(previousCommandIndex);
        }
    }

    private void handleLs(String command, String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder().inheritIO();
        pb.directory(new File(pwd));

        pb.command(command.split(" ")).start().waitFor();
    }

    private void handleCd(String command, String[] args) throws IOException {
        String pathArgument = args.length < 1 ? System.getProperty("user.dir") : args[0];

        Path tentativePath = Paths.get(pwd).resolve(pathArgument);
        File pathAsFile = tentativePath.toFile();

        if (!pathAsFile.exists() || !pathAsFile.isDirectory()) {
            throw new IllegalArgumentException("A valid directory should be specified.");
        }

        pwd = pathAsFile.getCanonicalPath();
    }

    private void handlePwd(String command, String[] args) {
        System.out.println(pwd);
    }

    private void handleEcho(String command, String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder().inheritIO();
        pb.directory(new File(pwd));

        pb.command(command.split(" ")).start().waitFor();
    }

    private void handlePing(String command, String[] args) throws IOException, InterruptedException {
        String[] keys = { "-n" };
        HashMap<String, String> parsedArgs = Utils.parseArgs(args, keys);

        String numArgFlag = isWindows ? "-n" : "-c";
        String numberArgValue = parsedArgs.get("-n");
        String host = parsedArgs.get("positional-0");

        ProcessBuilder processBuilder = numberArgValue != null ?
                new ProcessBuilder("ping", numArgFlag, numberArgValue, host) :
                new ProcessBuilder("ping", host);

        processBuilder.inheritIO().start().waitFor();
    }

    private void handleIpConfig(String command) throws IOException, InterruptedException {
        if (!isWindows) {
            throw new IllegalArgumentException("This command is only available on Windows.");
        }

        ProcessBuilder pb = new ProcessBuilder().inheritIO();
        pb.directory(new File(pwd));

        pb.command("ipconfig").start().waitFor();
    }

    private void handleIfConfig(String command) throws IOException, InterruptedException {
        if (isWindows) {
            throw new IllegalArgumentException("This command is only available on Unix.");
        }

        ProcessBuilder pb = new ProcessBuilder().inheritIO();
        pb.directory(new File(pwd));

        pb.command("ifconfig").start().waitFor();
    }

    private void handleHistory(String command, String[] args) {
        String[] keys = { "-n" };
        HashMap<String, String> parsedArgs = Utils.parseArgs(args, keys);

        List<String> historyList = history.getHistory();
        int n = Integer.parseInt(parsedArgs.getOrDefault("-n", CommandHistory.SIZE.toString()));

        if (n < 1 || n > CommandHistory.SIZE) {
            throw new IllegalArgumentException("-n must be higher than 1 and lesser than " + CommandHistory.SIZE);
        }

        int maxHistory = Math.min(historyList.size(), n);
        for (int i = 0; i < maxHistory; i++) {
            System.out.printf("%d\t%s%n", i + 1, historyList.get(i));
        }
    }

    private Integer matchPreviousCommand(String command) {
        Matcher matcher = previousCommandPattern.matcher(command);

        if (matcher.find()) {
            String index = matcher.group(1);

            return index.equals("#") ? -1 : Integer.parseInt(index);
        }

        return null;
    }

    private void handlePrevious(int index) {
        run(history.get(index));
    }

    private void handleExit() {
        finished = true;
    }

    public boolean shouldExit() {
        return finished;
    }

    public String getPWD() {
        return pwd;
    }
}
