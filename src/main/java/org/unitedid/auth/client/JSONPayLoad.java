package org.unitedid.auth.client;

import com.google.gson.annotations.Expose;
import org.unitedid.auth.client.impl.Factor;

import java.util.ArrayList;
import java.util.List;

public class JSONPayLoad {
    @Expose
    private String userId;
    @Expose
    private int version = 1;
    @Expose
    List<Factor> factors = new ArrayList<Factor>();

    public JSONPayLoad(String userId) {
        this.userId = userId;
    }

    public List<Factor> getFactors() {
        return factors;
    }

    public void addFactor(Factor factor) {
        factors.add(factor);
    }
}
