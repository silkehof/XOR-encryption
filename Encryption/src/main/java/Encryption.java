
import java.util.Base64;
import java.util.Scanner;

public class Encryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type your message:");
        String message = scanner.nextLine();
        System.out.println("Provide key:");
        String key = scanner.nextLine();
        System.out.println("Type 'e' for encoding or 'd' for decoding:");
        String command = scanner.nextLine();
        
        String fullCommand = "";
        if (command.equals("e")) {
            fullCommand = "encode";
        } else if (command.equals("d")) {
            fullCommand = "decode";
        }
        
        // Prints error if command was not "d" or "e", otherwise prints error message
        if (fullCommand.length() == 0) {
            System.out.println("Error: Invalid command!");
        } else {
            System.out.println("You chose to " + fullCommand + " " + "'" + message + "'" + " with key " + key);
        }

        // Encodes original string and prints result
        if (command.equals("e")) {
            String encodedString = encrypt(message, key);
            String encodedResult = Base64.getEncoder().encodeToString(encodedString.getBytes());
            System.out.println("Encoded message: " + encodedResult);
        }

        // Decodes encoded string and prints result
        if (command.equals("d")) {
            byte[] decodedBytes = Base64.getDecoder().decode(message);
            String decodedString = new String(decodedBytes);
            String decodedResult = encrypt(decodedString, key);
            System.out.println("Decoded message: " + decodedResult);
        }
    }

    public static String encrypt(String message, String key) {
        int messageLen = message.length();
        int keyLen = key.length();
        String result = "";

        for (int i = 0; i < messageLen; i++) {
            int first = Integer.valueOf(message.charAt(i));
            int second = Integer.valueOf(key.charAt(i % keyLen));
            int c = first ^ second;
            result += (char) c;
        }
        return result;

    }
}
