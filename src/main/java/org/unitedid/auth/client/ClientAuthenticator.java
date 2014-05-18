package org.unitedid.auth.client;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ClientAuthenticator implements ClientRequestFilter {
    private final String username;
    private final String password;

    public ClientAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        final String basicAuthentication = getBasicAuthentication();
        headers.add("Authorization", basicAuthentication);
    }

    private String getBasicAuthentication() {
        String token = username + ":" + password;
        try {
            return "Basic " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException("Failed to encode using UTF-8");
        }
    }
}
