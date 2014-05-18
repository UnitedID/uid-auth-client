package org.unitedid.auth.client.factors;

import org.unitedid.auth.client.factors.impl.Factor;

public class RevokeFactor extends Factor {
    public RevokeFactor(String type, String credentialId) {
        this.type = type;
        this.credentialId = credentialId;
    }
}
