package com.example.seamfix.exchangerate;

/**
 * Created by Seamfix on 10/23/2017.
 */

public class Rate {

    private double mBitcoinRate; //the current value of bitcoin in currency type
    private double mExchangeRate; //the current value of bitcoin in currency type
    private String mCurrency;
    private String mCurrencyShortForm;

    public Rate(double bitcoinRate, double exchangeRate, String currency, String currencyShortForm){
        mBitcoinRate = bitcoinRate;
        mExchangeRate = exchangeRate;
        mCurrency = currency;
        mCurrencyShortForm = currencyShortForm;
    }

    public double getBitcoinRate(){return  mBitcoinRate;}

    public double getExchangeRate(){return mExchangeRate;}

    public String getCurrency(){return mCurrency;}

    public String getCurrencyShortForm(){return mCurrencyShortForm;}
}