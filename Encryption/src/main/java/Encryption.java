
import java.util.Base64;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author s.hofmann
 */
public class Encryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type your message:");
        String message = scanner.nextLine();
        System.out.println("Provide key:");
        String key = scanner.nextLine();

        // prints input
        System.out.println("Your input was: " + message + ", " + key);

        // encodes original string and prints result
        String encodedString = encode(message, key);
        String encodedResult = Base64.getEncoder().encodeToString(encodedString.getBytes());
        System.out.println("Encoded message: " + encodedResult);

        // decodes encoded string and prints result (which is the original string)
        byte[] decodedBytes = Base64.getDecoder().decode(encodedResult);
        String decodedString = new String(decodedBytes);
        String decodedResult = encode(decodedString, key);
        System.out.println("Decoded message: " + decodedResult);
    }

    public static String encode(String message, String key) {
        int messageLen = message.length();
        int keyLen = key.length();
        String result = "";

        for (int i = 0; i < messageLen; i++) {
            int first = Integer.valueOf(message.charAt(i));
            int second = Integer.valueOf(key.charAt(i % keyLen));
            int c = first ^ second;
            // System.out.println(first + " -- " + second + " -- " + c);
            result += (char) c; // c MUST be > 32 && < 127, otherwise this doesn't work!!
        }
        return result;

    }
}
