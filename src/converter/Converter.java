package converter;

public class Converter {

    static int getLastDigitOfOctal(int num) {
        return num % 8;
    }

    static String decimalToBinary(int num) {
        return Integer.toBinaryString(num);
    }

    static String decimalToOctal(int num) {
        return Integer.toOctalString(num);
    }

    static String decimalToHex(int num) {
        return Integer.toHexString(num);
    }

    static int oneToDecimal(String numberStr) {
        return numberStr.length();
    }

    static String decimalToOne(int number) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < number; i++) {
            out.append(1);
        }
        return out.toString();
    }


    static String convertDecimal(int sourceBase, String numberStr, int newBase) {
        int number;
        if (sourceBase == 1) {
            number = oneToDecimal(numberStr);
        } else {
            number = Integer.parseInt(numberStr, sourceBase);
        }

        if (newBase == 1) {
            return decimalToOne(number);
        } else {
            return Integer.toString(number, newBase);
        }
    }

    static String convertFloatToBase10(int sourceBase, String numberStr) {
        String[] parts = numberStr.split("\\.");
        String decimal = parts[0];
        String floatingPoint = parts[1];
        String decimalConverted = convertDecimal(sourceBase, decimal, 10);

        // use formula for floatingPoint part

        double sum = 0;
        String[] digits = floatingPoint.split("");
        for (int i = 0; i < digits.length; i++) {
            sum += Integer.parseInt(digits[i], sourceBase) / Math.pow(sourceBase, i + 1);
        }
        return decimalConverted + Double.toString(sum).substring(1);

    }


    static String convertFloatingPoint(int sourceBase, String numberStr, int newBase) {
        if (sourceBase != 10) {
            numberStr = convertFloatToBase10(sourceBase, numberStr);
            sourceBase = 10;
        }

        String[] parts = numberStr.split("\\.");
        String decimal = parts[0];
        String floatingPoint = parts[1];

        String decimalConverted = convertDecimal(sourceBase, decimal, newBase);
        StringBuilder sb = new StringBuilder(decimalConverted).append('.');
        // calculate after point value
        /*
        0.5168 -> 19
        0.5168 * 19 -> 9.8192 (get 9) (int) > move floating part to the next multiplication
        0.8192 * 19 -> 15.5648 (get 15) (int)
        5 times ( 5 digits need )
        */

        double x = Double.parseDouble("0." + floatingPoint);
        for (int i = 0; i < 5; i++) {
            x *= newBase;
            sb.append(Integer.toString((int) x, newBase));
            x = x - (int) x;
        }
        return sb.toString();

    }

    static String convert(int number, int radix) {
        switch (radix) {
            case 2:
                return "0b" + decimalToBinary(number);
            case 8:
                return "0" + decimalToOctal(number);
            case 16:
                return "0x" + decimalToHex(number);
            default:
                return "";
        }
    }
}
