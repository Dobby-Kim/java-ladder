package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String input(String inputRequestMessage) {
        System.out.println(inputRequestMessage);
        return scanner.nextLine();
    }

    public static List<String> inputNames(String inputRequestMessage) {
        String initialInput = input(inputRequestMessage);
        return Arrays.stream(initialInput.split(","))
                     .map(String::trim)
                     .toList();
    }
}
