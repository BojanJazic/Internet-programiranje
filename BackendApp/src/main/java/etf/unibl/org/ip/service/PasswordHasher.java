package etf.unibl.org.ip.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordHasher {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256; // 256 bita = 32 bajta
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String SALT = "sigurnostinternetprogramiranje";

    // Generiše salt nasumičnim bajtovima
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hešira lozinku sa salt-om
    public static String hashPassword(String password) {
        try {
            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    Base64.getDecoder().decode(SALT),
                    ITERATIONS,
                    KEY_LENGTH
            );

            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Greška pri heširanju lozinke", e);
        }
    }

    public static boolean verifyPassword(String password, String storedHash) {
        String newHash = hashPassword(password);
        return newHash.equals(storedHash);
    }
}