package org.unitedid.auth.client;

public class AuthClientResponse {
    private String action;
    private Boolean status;


    AuthClientResponse() {}

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
