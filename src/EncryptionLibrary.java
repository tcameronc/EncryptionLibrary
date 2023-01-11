import java.lang.*;
import java.security.SecureRandom;
import java.util.*;

public class EncryptionLibrary {
    public String teaEncryption(int key0, int key1, int key2, int key3, int L, int R) {
        int delta = 0x9e3779b9;
        int sum = 0;

        for (int i = 0; i < 32; i++) {// weird plus is xor
            sum += delta;
            L += ((R << 4) + key0) ^ (R + sum) ^ ((R >> 5) + key1); //keys 0, 1
            R += ((L << 4) + key2) ^ (L + sum) ^ ((L >> 5) + key3); //keys 2, 3
        }
        return (String.format("%x", L) + " " + String.format("%x", R));
    }

    public String caesarEncryption(int offset, String plaintext) {
        String output = "";
        char[] shiftobet = new char[81];

        char alphabet[] = {                                         //list of all accepted characters
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=',
                '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+',
                '[', ']', ',', '.', '/',
        };

        for (int n = 0; n < 81; n++) //builds the shifted alphabet based on offset
        {
            shiftobet[(n + offset) % 81] = alphabet[n]; //add alphabet based on given offset
        }

        for (int n = 0; n < plaintext.length(); n++) //replaces the appropriate characters, traverses the plaintext
        {
            for (int m = 0, o = 0; m < 81; m++) {
                if (plaintext.charAt(n) == alphabet[m]) { //if the char at the plaintext location matches the alphabet
                    output += shiftobet[m]; //add the shiftobet location char
                }
            }
        }

        return output;
    }

    public String simpleEncryption(String plaintext, String keystring) {
        int i = 0;
        int[] ciphertext = new int[plaintext.length()];

        while (keystring.length() < plaintext.length()) {
            keystring += '0';
        }
        for (int n = 0; n < plaintext.length(); n++) {
            i = (plaintext.charAt(n) ^ keystring.charAt(n));
            ciphertext[n] = i;

        }
        i = 0;
        System.out.println("Ciphertext is: ");
        while (i < plaintext.length()) {
            System.out.print(ciphertext[i] + "/");
            i++;
        }
        System.out.println("\nYou have " + plaintext.length() + " numbers. ");
        return "";
    }

    public String cbcEncryption (String plaintext, String keystring) {
        int i = 0, block1; SecureRandom srand = new SecureRandom();
        int[] ciphertext = new int[plaintext.length()]; //array the same size as the plaintext string given.
        int IV = srand.nextInt(100);

        while (keystring.length() < plaintext.length()) {
            keystring += '0';
        }
                                                                              //initialization Vector 1 to 10000
        block1 = IV ^ plaintext.charAt(0);                                   //xor first block with IV
        ciphertext[0] = block1 ^ keystring.charAt(0);                       //xor already XOR'ed block with key

        for (int n = 1; n < plaintext.length(); n++)
        {
            ciphertext[n] = ciphertext[n-1] ^ plaintext.charAt(n) ^ keystring.charAt(n);          //xor plaintext with previous ciphertext block
                                                                            //xor the result with the key
        }
        i = 0;
        System.out.println("Encrypted plaintext is: ");
        while (i < plaintext.length()) {
            System.out.print(ciphertext[i] + "/" );
            i++;
        }
        System.out.println(" \nYou have " + plaintext.length() + " numbers. ");
        System.out.println("\nInitialization Vector is: "+IV);

        return "";

    }
}
