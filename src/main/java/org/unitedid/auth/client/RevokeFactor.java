package org.unitedid.auth.client;

import org.unitedid.auth.client.impl.Factor;

public class RevokeFactor extends Factor {
    public RevokeFactor(String type, String credentialId) {
        this.type = type;
        this.credentialId = credentialId;
    }
}
