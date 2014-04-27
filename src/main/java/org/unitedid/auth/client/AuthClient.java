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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.unitedid.auth.client.impl.Factor;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class AuthClient {
    private String baseURL;
    private Client client;
    private WebTarget authenticationTarget;
    private WebTarget credentialTarget;

    public AuthClient(String baseURL) {
        this.baseURL = baseURL;
        client = ClientBuilder.newClient();
        authenticationTarget = client.target(baseURL + "/authenticate/{userId}");
        credentialTarget = client.target(baseURL + "/credential/{userId}");
    }

    public Boolean authenticate(String userId, List<Factor> factors) {
        JSONPayLoad payload = new JSONPayLoad(userId);

        for (Factor factor : factors) {
            payload.addFactor(factor);
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Response res = authenticationTarget
                .resolveTemplate("userId", userId)
                .request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(
                        "{\"auth\":" + gson.toJson(payload) + "}", MediaType.APPLICATION_JSON_TYPE));
        AuthClientResponse authResponse = new Gson().fromJson(res.readEntity(String.class), AuthClientResponse.class);

        if (authResponse.getStatus() && authResponse.getAction().equals("auth")) {
            return true;
        }
        return false;
    }

    public Boolean addCredentials(String userId, List<Factor> factors) {
        JSONPayLoad payLoad = new JSONPayLoad(userId);

        for (Factor factor : factors) {
            payLoad.addFactor(factor);
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Response res = credentialTarget
                .resolveTemplate("userId", userId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity("{\"addCreds\":" + gson.toJson(payLoad) + "}", MediaType.APPLICATION_JSON_TYPE));
        AuthClientResponse authResponse = new Gson().fromJson(res.readEntity(String.class), AuthClientResponse.class);

        if (authResponse.getStatus() && authResponse.getAction().equals("addCred")) {
            return true;
        }
        return false;
    }

    public Boolean revokeCredentials(String userId, List<Factor> factors) {
        return false;
    }
}
