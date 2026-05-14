package org.fix.bill.auth.infrastructure.security.encryption;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.fix.bill.auth.core.domain.application.outbound.PasswordManager;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class BountyCastlePasswordManager implements PasswordManager {

    private static final DigestRandomGenerator generator = new DigestRandomGenerator(new SHA3Digest(512));

    @Override
    public String hash(String plainPassword) {
        return hash(plainPassword, salt(128), 512, 101501);
    }

    public static String hash(String plainPassword, byte[] salt) {
        return hash(plainPassword, salt, 512, 101501);
    }

    public static String hash(String plainPassword, byte[] salt, int keyLength, int iterations) {
        if (isNullOrEmpty(plainPassword)) {
            throw new IllegalArgumentException("Password is no specified.");
        }
        if (keyLength <= 0) {
            throw new IllegalArgumentException("The key length must be greater than 0");
        }
        if (iterations < 0) {
            throw new IllegalArgumentException("The number of iterations must be positive");
        }

        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator();
        char[] plainPasswordCharArray = plainPassword.toCharArray();
        byte[] passwordBytes = PBEParametersGenerator.PKCS5PasswordToBytes(plainPasswordCharArray);
        generator.init(passwordBytes, salt, iterations);

        KeyParameter keyParameter = (KeyParameter) generator.generateDerivedParameters(keyLength);
        byte[] key = keyParameter.getKey();
        return format("%s|%s", encode(salt), encode(key));
    }

    @Override
    public boolean verify(String plainPassword, String hash) {
        if (isNullOrEmpty(plainPassword)) {
            throw new IllegalArgumentException("Password is not specified.");
        }
        if (isNullOrEmpty(hash)) {
            throw new IllegalArgumentException("Hash is not specified.");
        }
        return hash(plainPassword, decode(extractSalt(hash))).equals(hash);
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private static byte[] salt(int count) {
        byte[] salt = new byte[count];
        generator.nextBytes(salt);
        return salt;
    }

    private static String encode(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    private static byte[] decode(String input) {
        return Base64.getDecoder().decode(input.getBytes(UTF_8));
    }

    private static String extractSalt(String input) {
        return input.substring(0, input.indexOf("|"));
    }

}
