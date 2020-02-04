package validations;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Predicate;

public class db_validations {

    public static Scanner sc = new Scanner(System.in);

    //Method to validate if the user uses a valid date.
    public static String returnValidDate() {

        String validDate;
        boolean isInvalid = false;
        do {
            if (isInvalid) {
                System.out.println(" Invalid Value! ");
            }
            validDate = sc.nextLine().trim();
            try {
                LocalDate ld = LocalDate.parse(validDate);
                isInvalid = false;
            } catch (DateTimeParseException ex) {
                isInvalid = true;
            }
        } while (isInvalid);
        return validDate;
    }

    //Method to validate if the user uses numbers only and inside the parameters i set.
    public static int returnValidNumber(Predicate<Integer> p) {

        Integer i = null;
        boolean isInvalid = false;
        do {
            if (isInvalid) {
                System.out.println("Invalid Input");
            }
            try {
                i = Integer.parseInt(sc.nextLine().trim());
                isInvalid = p.test(i);
            } catch (NumberFormatException ex) {
                isInvalid = true;
            }
        } while (isInvalid);
        return i;
    }

    //Method to validate if the user uses letters only.
    public static String loopAndGetValidString(Predicate<String> p) {
        String s;
        do {
            s = sc.next();
            if (!p.test(s)) {
                System.out.println(" Invalid Input! ");
            }
        } while (!p.test(s));
        return s.trim();
    }
}
