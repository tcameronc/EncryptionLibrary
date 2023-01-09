import java.lang.*;
import java.util.Random;

public class DecryptionLibrary
{
    public String teaDecryption(int key0, int key1, int key2, int key3, int L, int R )
    {
        int delta = 0x9e3779b9;
        int sum = 0;

        for(int i = 0; i < 32 ; i++ )
        {// weird plus is xor
            R -= ((L << 4) +key2) ^ (L+sum) ^ ((L>>5) + key3); //keys 2, 3
            L -= ((R << 4) +key0) ^ (R+sum) ^ ((R>>5) + key1); //keys 0, 1
            sum -= delta;
        }
        return (String.format("%x", L) + " " + String.format("%x", R));
    }

    public String caesarDecryption(int offset, String ciphertext)
    {
        String output = "";
        char[] shiftobet = new char[81];

        char alphabet[] = {                                             //list of all accepted characters
                'a','b','c','d','e','f','g','h','i','j','k','l','m',
                'n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J','K','L','M',
                'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                '1','2','3','4','5','6','7','8','9','0','-','=',
                '!','@','#','$','%','^','&','*','(',')','_','+',
                '[',']',',','.','/',
        };

        for(int n = 0; n < 81 ;n++) //builds the shifted alphabet based on offset
        {
            shiftobet[(n + offset)% 81] = alphabet[n];
        }

        for(int n = 0; n < ciphertext.length(); n++) //replaces the appropriate characters, traverses the plaintext
        {
            for(int m = 0, o = 0; m < 81 ; m++) {
                if (ciphertext.charAt(n) == shiftobet[m]) {
                    output += alphabet[m];
                }
            }
        }

        return output;
    }
    public String simpleDecryption(int[] ciphertxt, int limit, String keystring){
        int i;
        int[] plaintext = new int[limit];

        while(keystring.length() < limit){
            keystring+='0';
        }
        for(int n = 0, m = 0; n < limit; n++)
        {

            m = ciphertxt[n];
            i = ( m ^ (keystring.charAt(n)));
            plaintext[n] = i;

        }
        i = 0;
        System.out.println("Decrypted text is:");
        while(i < limit )
        {
            System.out.print(" " + (char) plaintext[i]);
            i++;
        }

        return "";
    }

    public String cbcDecryption (int[] ciphertext, int limit, String keystring, int IV) {
        int i = 0, block1;
        int[] plaintext = new int[limit];

        while (keystring.length() < limit) {
            keystring += '0';
        }

        block1 = IV ^ keystring.charAt(0);                                  //xor first block with IV
        plaintext[0] = block1 ^ ciphertext[0];                              //xor already XOR'ed block with key

        for (int n = 1; n < limit; n++) {
            plaintext[n] = ciphertext[n-1] ^ keystring.charAt(n);          //xor plaintext with previous ciphertext block
            i = (plaintext[n] ^ ciphertext[n]);                            //xor the result with the key
            plaintext[n] = i;                                              //result

        }
        i = 0;
        System.out.println("Plaintext is: ");
        while (i < limit) {
            System.out.print((char) plaintext[i]);
            i++;
        }

        return "";

    }
}
