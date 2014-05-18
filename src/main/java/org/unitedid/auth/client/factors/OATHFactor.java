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

package org.unitedid.auth.client.factors;

import com.google.gson.annotations.Expose;
import org.unitedid.auth.client.factors.impl.Factor;

public class OATHFactor extends Factor {
    @Expose
    private String aead;
    @Expose
    private String userCode;
    @Expose
    private int keyHandle;
    @Expose
    private String nonce;

    /***
     * Constructor used when adding an OATH credential.
     *
     * @param type "oathhotp" or "oathtotp"
     * @param keyHandle
     * @param aead
     * @param nonce
     * @param userCode
     * @param credentialId
     */
    public OATHFactor(String type, int keyHandle, String aead, String nonce, String userCode, String credentialId) {
        this.type = type;
        this.credentialId = credentialId;
        this.userCode = userCode;
        this.keyHandle = keyHandle;
        this.aead = aead;
        this.nonce = nonce;
    }

    /***
     * Constructor used when authenticating an OTP against an OATH credential.
     *
     * @param type
     * @param nonce
     * @param userCode
     * @param credentialId
     */
    public OATHFactor(String type, String nonce, String userCode, String credentialId) {
        this.type = type;
        this.nonce = nonce;
        this.userCode = userCode;
        this.credentialId = credentialId;
    }
}
