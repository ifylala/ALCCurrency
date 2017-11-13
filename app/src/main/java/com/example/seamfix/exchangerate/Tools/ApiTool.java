package com.example.seamfix.exchangerate.Tools;

import android.text.TextUtils;
import android.util.Log;

import com.example.seamfix.exchangerate.Rate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Seamfix on 10/23/2017.
 */

public class ApiTool {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = ApiTool.class.getSimpleName();

    //List of Currencies to get their rates in Bitcoin
    public static final String[] CURRENCIES_SHORT_FORM = {"ETH", "USD", "EUR", "GBP", "IND", "AUD", "CAD", "SGD", "ARS", "BAM",
            "BRL", "CHF", "CLP", "CNY", "DKK", "DZD", "EGP", "GHS", "JPY", "NGN", "RUB"};

    private static final String[] CURRENCIES = {"Ethereum", "US Dollar", "Euro", "British Pound", "Indian Rupee", "Australian Dollar",
            "Canadian Dollar", "Indian Rupee", "Singapore Dolar", "Argentine Peso", "Bosnian Convertible Marka", "Brazilian Real", "Swiss Franc",
            "Chilean Peso", "Chinese Yuan Renminbi", "Danish Krone", "Algerian Dinar", "Egyptian Pound", "South Korean Won",
            "Turkish Lira", "United Arab Emirates Dirham"};

    /**
     * Create a private constructor because no one should ever create a {@link ApiTool} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private ApiTool() {
    }

    /**
     * Method to process the api url and return an arraylist of Developers
     * using other utility method
     *
     * @param APIurl
     * @return
     */
    public static Rate[] extractCryptoCards(String APIurl) {


        String jsonResponse = null;
        Rate[] rates = null;

        try {
            URL url = makeURL(APIurl);
            jsonResponse = makeHttpRequest(url);
            if (jsonResponse != null) {
                rates = extractFromJSON(jsonResponse);
            } else {
                Log.e(LOG_TAG, "the http request returns a null string");
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "error while making http request", e);
        }
        return rates;
    }


    private static Rate[] extractFromJSON(String jsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        Rate[] rates = new Rate[CURRENCIES_SHORT_FORM.length];

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // build up a list of Developer objects with the corresponding data.
            JSONObject root = new JSONObject(jsonResponse);
            JSONObject bit = root.getJSONObject("BTC");
            JSONObject eth = root.getJSONObject("ETH");

            for(int i = 0; i< CURRENCIES_SHORT_FORM.length; i++ ){
                if(root.has(CURRENCIES_SHORT_FORM[i])){
                    double value = root.getDouble(CURRENCIES_SHORT_FORM[i]);
                    rates [i] = new Rate(1, value, CURRENCIES[i], CURRENCIES_SHORT_FORM[i]);

                }
            }
            return rates;

        } catch (JSONException e) {
            // catch any error that might occur while analysing the JSON response and log it
            Log.e(LOG_TAG, "Problem parsing the cards JSON results", e);
        }

        return rates;

    }

    /**
     * method that create a URL object form string objects
     * @param url
     * @return makeURL
     */
    public static URL makeURL(String url) {

        URL urlString = null;
        try {
            urlString = new URL(url);
        } catch (MalformedURLException e) {

            Log.e(LOG_TAG, "error while forming url", e);

        }
        return urlString;

    }

    /**
     * method that helps in making a connection to the internet using a URL object
     * @param url
     * @return String jsonResponse
     * @throws IOException
     */
    public static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {

            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /*milliseconds*/);
            connection.setConnectTimeout(50000 /*milliseconds*/);
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Unhandled response code from the connection:" + connection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "error while making Http connection", e);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.v(LOG_TAG, "error while closing connection", e);
                }
            }
        }


        return jsonResponse;

    }

    /**
     * method that accepts an InputStream object analyses it and return
     * coressponding String object
     * @param input
     * @return String jsonResponse
     * @throws IOException
     */
    public static String readInputStream(InputStream input)  {

        // a string builder object to hold the output from analysed input stream
        StringBuilder readJsonResponse = new StringBuilder();
        try {
            if (input != null) {
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String readLine = bufferedReader.readLine();

                while (readLine != null) {
                    readJsonResponse.append(readLine);
                    readLine = bufferedReader.readLine();
                }
            }
        }catch (IOException ex){
            Log.e(LOG_TAG,"error while reading input stream",ex);
        }

        return readJsonResponse.toString();
    }

}