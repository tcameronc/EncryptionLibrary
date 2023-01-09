import java.lang.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int key0 = 0, key1 = 0, key2 = 0, key3 = 0, Left = 0, Right = 0; // int variables are numbers
        int offsetKey;
        int[] key = new int[2]; int L_[] = new int[100]; int R_[] = new int[100];
        String output, keyString, plaintext, select = "";//strings are written out

        while (!select.equals("stop")) {
            DecryptionLibrary decrypt = new DecryptionLibrary();
            EncryptionLibrary encrypt = new EncryptionLibrary();
            Scanner userInput = new Scanner(System.in);

            System.out.println("\nChoose one or type stop to end: \n" +
                    "caesarEncrypt, caesarDecrypt, \n" +
                    "cbcEncrypt, cbcDecrypt, \n" +
                    "simpleEncrypt, simpleDecrypt. \n");

            select = userInput.nextLine().toLowerCase();

            switch (select) {

                case "simpledecrypt":
                    System.out.println("How many numbers do you have?");
                    key0 = userInput.nextInt();
                    System.out.println("Enter your encrypted plaintext (numbers), " +
                            "press enter at every space.");
                    key = new int[key0];
                    for (int n = 0; n < key0; n++) {
                        key[n] = userInput.nextInt();
                    }
                    System.out.println("Enter ciphertext to XOR with encrypted " +
                            "plaintext for original");
                    keyString = userInput.next();
                    decrypt.simpleDecryption(key, key0, keyString);
                    break;

                case "simpleencrypt":
                    System.out.println("Enter your plaintext");
                    plaintext = userInput.nextLine();
                    System.out.println("Enter ciphertext to XOR plaintext with");
                    keyString = userInput.nextLine();
                    encrypt.simpleEncryption(plaintext, keyString);
                    break;

                case "cbcdecrypt":
                    System.out.println("How many numbers do you have?");
                    key0 = userInput.nextInt();
                    key = new int[key0];

                    System.out.println("Enter your encrypted plaintext separated by forward slashes.");
                    keyString = userInput.next();

                    String[] keylist = keyString.trim().split("/",0);             //separate string into individual chunks
                    System.out.println(keylist[0]);

                    for (int n = 0; n < key0; n++) {
                        key[n] = Integer.parseInt(keylist[n]);
                    }
                    userInput.nextLine();
                    System.out.println("Enter previously given Initialization Vector");
                    int IV = userInput.nextInt();
                    userInput.nextLine();
                    System.out.println("Enter ciphertext that plaintext was XOR'ed with");
                    keyString = userInput.nextLine();
                    decrypt.cbcDecryption(key, key0, keyString, IV);
                    break;

                case "cbcencrypt":
                    System.out.println("Enter your plaintext");
                    plaintext = userInput.nextLine();
                    System.out.println("Enter ciphertext to XOR plaintext with");
                    keyString = userInput.nextLine();
                    encrypt.cbcEncryption(plaintext, keyString);
                    break;


                case "caesardecrypt":
                    System.out.println("Enter your encrypted plaintext");
                    plaintext = userInput.nextLine();
                    System.out.println("Enter the used offset");
                    offsetKey = userInput.nextInt();
                    System.out.println("Decrypted text is: " + decrypt.caesarDecryption(offsetKey, plaintext));
                    break;

                case "caesarencrypt":

                    System.out.println("Enter your plaintext");
                    plaintext = userInput.nextLine();
                    System.out.println("Enter the desired offset");
                    offsetKey = userInput.nextInt();
                    System.out.println("Encrypted text is " + encrypt.caesarEncryption(offsetKey, plaintext));
                    break;

                case "teadecrypt":
                    for (int n = 0; n < 6; n++)
                    {
                        if (n < 4)
                        {
                            System.out.println("Enter 4 hex characters of key" +
                                    ", left to right, pad left with 0's");
                            keyString = userInput.nextLine();
                            key[0] = Integer.parseInt(keyString, 16);
                            System.out.println("\n Enter next 4 hex characters of key, left to right");
                            keyString = userInput.nextLine();
                            key[1] = Integer.parseInt(keyString, 16);
                        }
                        if (n == 0)
                        {
                            key0 = key[1] | (key[0] << 16);
                            System.out.printf("%x", key0);
                            System.out.println("\n");
                        }
                        if (n == 1)
                        {
                            key1 = key[1] | (key[0] << 16);
                            System.out.printf("%x", key1);
                            System.out.println("\n");
                        }
                        if (n == 2)
                        {
                            key2 = key[1] | (key[0] << 16);
                            System.out.printf("%x", key2);
                            System.out.println("\n");
                        }
                        if (n == 3)
                        {
                            key3 = key[1] | (key[0] << 16);
                            System.out.printf("%x", key3);
                            System.out.println("\n");
                        }
                        if(n == 4  )
                        {
                            System.out.println("Enter first 4 hex characters of plaintext");
                            keyString = userInput.nextLine();
                            key[0] = Integer.parseInt(keyString, 16);
                            System.out.println("Enter second 4 hex characters of plaintext");
                            keyString = userInput.nextLine();
                            key[1] = Integer.parseInt(keyString, 16);

                            Left = key[1] | (key[0] << 16);
                            System.out.printf("%x", Left);
                            System.out.println("\n");
                        }
                        if( n == 5  )
                        {
                            System.out.println("Enter 4 hex characters of plaintext");
                            keyString = userInput.nextLine();
                            key[0] = Integer.parseInt(keyString, 16);
                            System.out.println("Enter 4 hex characters of plaintext");
                            keyString = userInput.nextLine();
                            key[1] = Integer.parseInt(keyString, 16);

                            Right = key[1] | (key[0] << 16);
                            System.out.printf("%x", Right);
                            System.out.println("\n");

                            output = decrypt.teaDecryption(key0, key1, key2, key3, Left, Right);
                            System.out.println(output);
                        }
                    }

                    break;


                case "teaencrypt":
                    for (int n = 0; n < 6; n++)
                    {
                        if (n < 4)
                        {
                            System.out.println("Enter 4 hex characters, left to right, pad left with 0's");
                            keyString = userInput.nextLine();
                            key[0] = Integer.parseInt(keyString, 16);
                            System.out.println("Enter next 4 hex characters, left to right");
                            keyString = userInput.nextLine();
                            key[1] = Integer.parseInt(keyString, 16);
                        }
                        if (n == 0) {
                            key0 = key[1] | (key[0] << 16);
                            System.out.printf("%x", key0);
                            System.out.println("\n");
                        }
                        if (n == 1) {
                            key1 = key[1] | (key[0] << 16);
                            System.out.printf("%x", key1);
                            System.out.println("\n");

                        }
                        if (n == 2) {
                            key2 = key[1] | (key[0] << 16);
                            System.out.printf("%x", key2);
                            System.out.println("\n");
                        }
                        if (n == 3) {
                            key3 = key[1] | (key[0] << 16);
                            System.out.printf("%x", key3);
                            System.out.println("\n");
                        }
                        if(n >= 4 )
                        {
                            for (int m = 0; L_[m] != 0 || n > 4; m++)
                            {
                                System.out.println("Enter first 4 hex characters of plaintext");
                                keyString = userInput.nextLine();
                                key[0] = Integer.parseInt(keyString, 16);
                                System.out.println("Enter second 4 hex characters of plaintext");
                                keyString = userInput.nextLine();
                                key[1] = Integer.parseInt(keyString, 16);

                                L_[m] = key[1] | (key[0] << 16);
                                System.out.printf("%x", L_[m]);
                                System.out.println("\n");

                                System.out.println("Enter 4 hex characters of plaintext");
                                keyString = userInput.nextLine();
                                key[0] = Integer.parseInt(keyString, 16);
                                System.out.println("Enter 4 hex characters of plaintext");
                                keyString = userInput.nextLine();
                                key[1] = Integer.parseInt(keyString, 16);

                                R_[m] = key[1] | (key[0] << 16);
                                System.out.printf("%x", R_[m]);

                                output = encrypt.teaEncryption(key0, key1, key2, key3, L_[m], R_[m]);
                                System.out.println("\n" + output);

                            }
                        }
                    }
                    break;
            }
        }
    }
}