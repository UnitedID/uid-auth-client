package org.unitedid.auth.client;

import org.testng.annotations.Test;
import org.unitedid.auth.client.impl.Factor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.assertTrue;

public class AuthClientTest {
    @Test
    public void testAddCred() throws Exception {
        String userId = UUID.randomUUID().toString();
        String credentialId = UUID.randomUUID().toString();
        AuthClient auth = new AuthClient("http://localhost:8080/uid-auth-backend");
        PasswordFactor password = new PasswordFactor("plaintext", credentialId);
        String salt = password.getSalt();
        List<Factor> factors = new ArrayList<>();
        factors.add(password);
        assertTrue(auth.addCredentials(userId, factors));

        PasswordFactor password2 = new PasswordFactor("plaintext", credentialId, salt);
        factors.clear();
        factors.add(password2);
        assertTrue(auth.authenticate(userId, factors));
    }
}
