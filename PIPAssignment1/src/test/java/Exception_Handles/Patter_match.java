package Exception_Handles;

import java.util.Scanner;


public class Patter_match {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter a 10-digit phone number: ");
		String phoneNumber = scanner.nextLine();

		try {
			validatePhoneNumber(phoneNumber); // Call the validation method
			System.out.println("Valid phone number: " + phoneNumber);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid phone number. " + e.getMessage());
		}
	}

	public static void validatePhoneNumber(String number) throws IllegalArgumentException {
		// Check if the phone number matches the specified conditions using regular
		// expressions
		if (!number.matches("[0-9]{10}")) {
			throw new IllegalArgumentException("Phone number should have exactly 10 digits.");
		}

	}

}
