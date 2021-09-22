package com.exp.patterns.factory;

import java.util.Map;

/**
 * Created by Eduard Ivanov on 7/1/21
 */
public class FactoryCurrency {


    private static Map<String, Currency> CURRENCY_PER_NAME = Map.of(
            "USD", new USDCurrency(),
            "UAH", new UAHCurrency(),
            "PL", new PLCurrency()
    );

    public static Currency getCurrency(String name) {
        return CURRENCY_PER_NAME.get(name);
    }
}
