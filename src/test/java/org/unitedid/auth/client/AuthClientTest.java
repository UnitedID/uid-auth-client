/*
 * Copyright (c) 2014 United ID.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.unitedid.auth.client;

import org.testng.annotations.Test;
import org.unitedid.auth.client.factors.PasswordFactor;
import org.unitedid.auth.client.factors.impl.Factor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.assertTrue;

public class AuthClientTest {
    @Test
    public void testAddCred() throws Exception {
        String userId = UUID.randomUUID().toString();
        String credentialId = UUID.randomUUID().toString();
        AuthClient auth = new AuthClient("http://localhost:8081/uid-auth-backend", "admin", "password");
        PasswordFactor password = new PasswordFactor("plaintext", credentialId);
        String salt = password.getSalt();
        List<Factor> factors = new ArrayList<>();
        factors.add(password);
        assertTrue(auth.addCredentials(userId, factors));

        PasswordFactor password2 = new PasswordFactor("plaintext", credentialId, salt);
        factors.clear();
        factors.add(password2);
        assertTrue(auth.authenticate(userId, factors));

        assertTrue(auth.revokeCredential(userId, password2));
    }

    @Test
    public void testAddOathCred() throws Exception {
        /*String userId = UUID.randomUUID().toString();
        String seed = "3132333435363738393031323334353637383930";
        String credentialId = UUID.randomUUID().toString();
        AuthClient auth = new AuthClient("http://localhost:8081/uid-auth-backend");
        OATHFactor oathHOTP = new OATHFactor("oathhotp", 8192, seed, "755224", credentialId);
        String nonce = oathHOTP.getNonce();
        assertTrue(auth.addCredential(userId, oathHOTP));

        OATHFactor oathValidate = new OATHFactor("oathhotp", nonce, "969429", credentialId);
        assertTrue(auth.authenticate(userId, oathValidate));

        OATHFactor oathFail = new OATHFactor("oathhotp", nonce, "111111", credentialId);
        assertFalse(auth.authenticate(userId, oathFail));*/
    }
}
