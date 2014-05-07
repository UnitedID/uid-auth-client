package org.unitedid.auth.client;

import com.google.gson.annotations.Expose;
import org.unitedid.auth.client.impl.Factor;

public class YubiKeyFactor extends Factor {
    @Expose
    private String salt;
    @Expose
    private String userCode;

    public YubiKeyFactor(String type, String salt, String userCode, String credentialId) {
        this.type = type;
        this.salt = salt;
        this.userCode = userCode;
        this.credentialId = credentialId;
    }
}
