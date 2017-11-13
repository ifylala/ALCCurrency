package com.example.seamfix.exchangerate;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.seamfix.exchangerate.Tools.ApiTool;
import com.example.seamfix.exchangerate.Tools.ConvertAdapter;
import com.example.seamfix.exchangerate.Tools.CurrencyLoader;

/**
 * Created by Seamfix on 10/31/2017.
 */

public class CurrencyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Rate[]> {


    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    public static final int RATE_LOADER_ID = 0;
    //Url to query Cryptocompare database
    private static final String

            CRYPTOCOMPARE_URL
            = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR,GBP,IND,AUD,CAD,SGD,ARS,BAM,BRL,CHF,CLP,CNY,DKK,DZD,EGP,GHS,JPY,NGN,RUB";

    //spinner containing the list of all currencies
    Spinner spinner;
    ArrayAdapter<Rate> spinnerAdapter;

    //Object of SwipeRefreshLayout for the SwipeRefreshLayout
    SwipeRefreshLayout mSwipeRefreshLayout;
    //Object of TextView for the empty state text view
    TextView mEmptyStatTextView;
    //Object of ProgressBar
    ProgressBar mProgressBar;
    //Instance of the ConvertAdapter class
    ConvertAdapter mConvertAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);


        //Reference to the error_msg_text_view from our layout
        mEmptyStatTextView = (TextView) findViewById(R.id.error_msg_tex_view);

        //Reference to the progressBar from our Layout
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            getLoaderManager().initLoader(0, null, this);
        } else {
            // Otherwise, display error
            // Update empty state with no connection error message
            mEmptyStatTextView.setText(R.string.no_internet_connection);

            //Call  displayError method to hid progress bar and display error
            displayError();
        }


        //Listener for the SwipeRefreshLayout
        //Refreshes the list and updates the rates if there is an update
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refresh items
                refreshItems();
            }
        });
    }

    void displayError() {
        //Hide progress bar
        mProgressBar.setVisibility(View.GONE);
        //Make Error Message Text View visible
        mEmptyStatTextView.setVisibility(View.VISIBLE);
    }

    void displayData() {
        //Hide ProgressBar
        mProgressBar.setVisibility(View.GONE);
        //Hide ErrorMessage View
        mEmptyStatTextView.setVisibility(View.GONE);
    }

    void refreshItems() {
        //Restart our loader manager Already disposed: Module: 'app'
        getLoaderManager().restartLoader(RATE_LOADER_ID, null, CurrencyActivity.this);

        //Load Complete
        onItemLoadComplete();
    }

    void onItemLoadComplete() {


        //create spinner - set adapter and set the item click listener
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ApiTool.CURRENCIES_SHORT_FORM);
        spinner.setAdapter(spinnerAdapter);

        //Stop refreshing animation
        mSwipeRefreshLayout.setRefreshing(false);


    }


    @Override
    public Loader<Rate[]> onCreateLoader(int id, Bundle args) {
        //Show the progress bar
        mProgressBar.setVisibility(View.VISIBLE);

        //Hide the error message
        mEmptyStatTextView.setVisibility(View.GONE);

        return new CurrencyLoader(CurrencyActivity.this);
    }


    @Override
    public void onLoadFinished(Loader<Rate[]> loader, Rate[] data) {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent;
                switch (position) {
                    case 1:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 9:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 10:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 11:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 12:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 13:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 14:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 15:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 16:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 17:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 18:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 19:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 20:
                        intent = new Intent(CurrencyActivity.this, ConvertActivity.class);
                        startActivity(intent);
                        break;


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });


    }


    @Override
    public void onLoaderReset(Loader<Rate[]> loader) {

        //Clear Data in the Adapter
        mConvertAdapter.setRatesDate(null);
    }

    public void onClick(int position) {

        //Find the current Item that was clicked on
        Rate currentRate = spinnerAdapter.getItem(position);

        //Create a new Intent for the ConverterActivity class
        Intent intent = new Intent(CurrencyActivity.this, ConvertActivity.class);

        /**
         * put details of currency to be received by the intent
         */
        intent.putExtra("CURRENCY_RATE", currentRate.getExchangeRate());
        intent.putExtra("CURRENCY", currentRate.getCurrency());
        intent.putExtra("CURRENCY_SHORT_FORM", currentRate.getCurrencyShortForm());
        intent.putExtra("BTC_VALUE", currentRate.getBitcoinRate());

        //Launch the activity
        startActivity(intent);
    }
}
