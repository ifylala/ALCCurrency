package com.example.seamfix.exchangerate.Tools;

/**
 * Created by USER on 11/5/2017.
 */
import android.content.Context;
import android.content.AsyncTaskLoader;
import com.example.seamfix.exchangerate.Rate;

import org.json.JSONObject;

/**
 * Loads a JSONObject that contains currency conversion rates  by using an AsyncTask to perform the
 * network request to the given URL from Cryptocompare API.
 */
public class CurrencyLoader extends AsyncTaskLoader<Rate[]> {
     private static final String CRYPTOCOMPARE_URL
     = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR,GBP,IND,AUD,CAD,SGD,ARS,BAM,BRL,CHF,CLP,CNY,DKK,DZD,EGP,GHS,JPY,NGN,RUB";



    public CurrencyLoader(Context context){
        super(context);
    }

    @Override
    public Rate[] loadInBackground() {
        return ApiTool.extractCryptoCards(CRYPTOCOMPARE_URL);
    }
}