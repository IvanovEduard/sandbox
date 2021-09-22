package com.exp.patterns.factory;

/**
 * Created by Eduard Ivanov on 7/1/21
 */
public class USDCurrency implements Currency{
    @Override
    public String getSymbol() {
        return "USD";
    }
}
