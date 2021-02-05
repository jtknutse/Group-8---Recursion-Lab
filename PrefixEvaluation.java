import java.util.Scanner;
import java.util.ArrayList;

public class PrefixEvaluation {

    /*
    Parses a string of tokens representing prefix arithmetic operations and returns the result.
    */
    private static int evaluate(Scanner scnr) {
        String token = scnr.next();
        char operator = 0;
        ArrayList<Integer> operands = new ArrayList<Integer>();

        if (token.compareTo("(") == 0)                                  // Strip this expression's open paren
            token = scnr.next();

        else if (isNumeric(token))                                      // If expression is a decimal
            return strToDecimal(token);                                 // Simply return decimal
        
        while (token.compareTo(")") != 0) {                             // While this operation not finished
            if (token.compareTo("(") == 0)                              // If we're starting new operation
                operands.add(evaluate(scnr));                           // Add result of operation to operands
            
            else if (isOperator(token))                                 // If token is +,-,*,/
                operator = token.charAt(0);                             // Set operator

            else if (isNumeric(token))                                  // If token is a decimal
                operands.add(strToDecimal(token));                      // Add it to operands list

            token = scnr.next();                                        // Get the next token
        }

        return evaluateArrayList(operands, operator);                   // Evaluate operands with the operator we scanned
    }

    /*
    Takes an arrayList of integers and either adds, subtracts, divides, or multiplies them and returns the result.
    */
    private static int evaluateArrayList(ArrayList<Integer> arrayList, char operator) {
        int total;
        switch (operator) {
            case '+':
                total = 0;
                for (int i = 0; i < arrayList.size(); i++)
                    total += arrayList.get(i);
                return total;

            case '-':
                total = arrayList.get(0);
                for (int i = 1; i < arrayList.size(); i++)
                    total -= arrayList.get(i);
                return total;
            
            case '*':
                total = 1;
                for (int i = 0; i < arrayList.size(); i++)
                    total *= arrayList.get(i);
                return total;

            case '/':
                total = arrayList.get(0);
                for (int i = 1; i < arrayList.size(); i++)
                    total /= arrayList.get(i);
                return total;
            
            default:
                return 0;
        }
    }

    /*
    Is the string entirely made up of numeric chars?
    */
    private static boolean isNumeric(String e) {
        for (int i = 0; i < e.length(); i++) {
            if (!Character.isDigit(e.charAt(i)))
                return false;
        }
        return true;
    }

    /*
    Return integer representation of string of decimals
    */
    private static int strToDecimal(String e) {
        int curDecimalPower = (int) Math.pow(10, e.length() - 1);       // Multiply each character by 10^place
        int total = 0;
        for (int i = 0; i < e.length(); i++) {
            int charValue = Character.getNumericValue(e.charAt(i));
            total += charValue * curDecimalPower;                       // Multiply current decimal by its place value
            curDecimalPower /= 10;                                      // Next decimal 10 times smaller
        }
        return total;
    }

    private static boolean isOperator(String str) {
        if (str.length() == 1) {
            switch (str.charAt(0)) {
                case '+':
                case '-':
                case '*':
                case '/':
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        System.out.println("PrefixEvaluation - Azeb Ayalneh, Matthew Chesser, Jeffrey Knutsen, Cong Qin");
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String curLine = scnr.nextLine();
        while (curLine.compareToIgnoreCase("Done") != 0) {
            Scanner strScanner = new Scanner(curLine);
            int answer = evaluate(strScanner);
            System.out.printf("Evaluates to %d\n\n", answer);
            System.out.print("Enter an expression: ");
            curLine = scnr.nextLine();
            strScanner.close();
        }
        scnr.close();
    }

}
