package com.exp.patterns.factory;

/**
 * Created by Eduard Ivanov on 7/1/21
 */
public class PLCurrency implements Currency {
    @Override
    public String getSymbol() {
        return "PL";
    }
}
