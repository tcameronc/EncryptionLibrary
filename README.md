
Demonstration at https://docs.google.com/document/d/1-3Xcz3SKnGgVh7ahJT6ffLQHiqJeKugHFTtR69UalC0/edit

The Encryption Library is a java application that lets users enter plaintext of any length and encrypt that into a secret message. Depending on the method used, the user may need to supply a key to get their ciphertext (encrypted message). 

The program also lets users decrypt the ciphertext from above to get their original plaintext back. Depending on the method used, the user will need to supply the *same* key and ciphertext the used to get the correct message.



Caesar encryption takes a user’s offset and encrypts their plaintext based on that. 
It builds a caesar cipher, or shifted alphabet that is compared and substituted into the ciphertext string.
If the user supplies the appropriate offset, the original message is revealed.




Simple encryption is an example of ECB (electronic code book mode ) encryption. It xors the plaintext with a user supplied key. It decrypts the ciphertext by doing the same again. Each letter of plaintext is treated as one of the encryption “blocks”.





Cbc (cipher block chaining) encryption is similar to the simple encryption. However, instead of XORing the keystring and the plaintext, it XORs the first unit of plaintext with an initialization vector and then XORs the result with the key. The resulting ciphertext is then used in the encryption operations of the future letters, hence the chain. 

The ciphertext is given as a series of numbers separated by forward slashes. To decrypt, follow the prompts and enter the information that was given by the application upon encryption.


Coming Soon…


TEA Encryption
Hash Algorithm

