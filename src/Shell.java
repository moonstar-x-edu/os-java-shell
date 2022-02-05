import java.util.Scanner;

public class Shell {
    public static final String PS2 = ">";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CommandRunner runner = new CommandRunner();

        do {
            System.out.print(PS2 + " ");

            String command = in.nextLine();
            runner.run(command);
        } while (!runner.shouldExit());
    }
}
