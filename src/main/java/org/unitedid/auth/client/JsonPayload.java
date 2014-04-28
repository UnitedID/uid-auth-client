package org.unitedid.auth.client;

import com.google.gson.annotations.Expose;
import org.unitedid.auth.client.impl.Factor;

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
