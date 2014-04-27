package org.unitedid.auth.client.impl;


import com.google.gson.annotations.Expose;

public abstract class Factor {
    @Expose
    public String type;
    @Expose
    public String credentialId;


    public String getType() {
        return type;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public abstract String toString();
}
