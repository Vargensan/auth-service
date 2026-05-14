package org.fix.bill.auth.infrastructure.security.jwt;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {

    public static PrivateKey getPrivateKey(String filename) throws Exception {
        try (InputStream inputStream = new ClassPathResource(filename).getInputStream()) {
            String key = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            String privateKeyPEM = key
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(keySpec);
        }
    }

    public static PublicKey getPublicKey(String filename) throws Exception {
        try (InputStream inputStream = new ClassPathResource(filename).getInputStream()) {
            String key = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            String publicKeyPEM = key
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(keySpec);
        }
    }

}
