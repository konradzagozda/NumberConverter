package converter;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    void start() {
        System.out.print("Source base: ");
        if (!scanner.hasNext()){
            System.out.println("error: end of input");
            return;
        }
        String sourceBaseString = scanner.nextLine().trim();
        if (sourceBaseString.contains(" ") || !sourceBaseString.matches("\\d+")) {
            System.out.println("error: Wrong source base");
            return;
        }
        int sourceBase = Integer.parseInt(sourceBaseString);
        if (sourceBase > 36 || sourceBase < 1) {
            System.out.println("error: Wrong source base");
            return;
        }

        System.out.print("Number to convert: ");
        if (!scanner.hasNext()){
            System.out.println("error: end of input");
            return;
        }
        String numberStr = scanner.nextLine().trim();

        System.out.print("destination base: ");
        if (!scanner.hasNext()){
            System.out.println("error: end of input");
            return;
        }
        String newBaseString = scanner.nextLine().trim();
        if (newBaseString.contains(" ") || !newBaseString.matches("\\d+")) {
            System.out.println("error: Wrong destination base");
            return;
        }
        int newBase = Integer.parseInt(newBaseString);
        if (newBase > 36 || newBase < 1){
            System.out.println("error: Wrong destination base");
            return;
        }


        String out;
        if(numberStr.contains(".")){
            out = Converter.convertFloatingPoint(sourceBase, numberStr, newBase);
        } else {
            out = Converter.convertDecimal(sourceBase, numberStr, newBase);
        }
        System.out.print("converted: ");
        System.out.println(out);
    }
}
