package org.unitedid.auth.client;

import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PasswordFactorTest {

    @Test
    public void testGenerateSalt() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        long startMilliSec = System.currentTimeMillis();
        PasswordFactor password = new PasswordFactor("123qwe", "1", 11);
        long stopMilliSec = System.currentTimeMillis();
        String passwordHash1 = password.getPasswordHash();
        String salt = password.getSalt();

        assertTrue(password.verify("123qwe", (salt + passwordHash1)));
    }

    @Test
    public void testRandomNonce() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        PasswordFactor password = new PasswordFactor("123qwe", "1");
        assertEquals(password.getRandomNonce().length(), 12);
    }
}
