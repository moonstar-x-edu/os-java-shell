import java.util.HashMap;

public class Utils {
    public static <T> int arrayIndexOf(T[] arr, T o) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    public static HashMap<String, String> parseArgs(String[] args, String[] options) {
        HashMap<String, String> map = new HashMap<>();

        for (String option : options) {
            int keyIndex = arrayIndexOf(args, option);
            if (keyIndex > -1 && keyIndex + 1 < args.length) {
                map.put(option, args[keyIndex + 1]);
            }
        }

        int counter = 0;
        for (String arg : args) {
            if (!map.containsKey(arg) && !map.containsValue(arg)) {
                map.put("positional-" + counter, arg);
                counter++;
            }
        }

        return map;
    }
}
