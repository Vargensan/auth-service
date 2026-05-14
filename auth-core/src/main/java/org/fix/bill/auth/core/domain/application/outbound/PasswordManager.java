package org.fix.bill.auth.core.domain.application.outbound;

public interface PasswordManager {

    String hash(String rawPassword);

    boolean verify(String rawPassword, String hashedPassword);

}
