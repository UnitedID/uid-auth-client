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

import com.google.gson.annotations.Expose;
import org.unitedid.auth.client.factors.impl.Factor;

import java.util.ArrayList;
import java.util.List;

public class JsonPayload {
    @Expose
    private String userId;
    @Expose
    private int version = 1;
    @Expose
    List<Factor> factors = new ArrayList<>();

    public JsonPayload(String userId) {
        this.userId = userId;
    }

    public JsonPayload(String userId, List<Factor> factors) {
        this.userId = userId;
        addFactors(factors);
    }

    public List<Factor> getFactors() {
        return factors;
    }

    public void addFactor(Factor factor) {
        factors.add(factor);
    }

    public void addFactors(List<Factor> factors) {
        this.factors.addAll(factors);
    }
}
