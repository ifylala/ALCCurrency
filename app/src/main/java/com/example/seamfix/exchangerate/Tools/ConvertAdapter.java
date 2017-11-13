package com.example.seamfix.exchangerate.Tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seamfix.exchangerate.R;
import com.example.seamfix.exchangerate.Rate;

import java.util.ArrayList;

/**
 * Created by Seamfix on 10/23/2017.
 */

public class ConvertAdapter extends RecyclerView.Adapter<ConvertAdapter.CurrencyViewHolder> {

    //ArrayList containing list of rates
    private ArrayList<Rate> mRates = new ArrayList<>();

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private final CurrencyAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface CurrencyAdapterOnClickHandler{
        void onClick(int position);
    }

    /**
     * Constructor of the class. Creates a new ConverterAdapter
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public ConvertAdapter(CurrencyAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    //
    class CurrencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mBitCoinTextView;

        TextView mExchangeRateTextView;

        public CurrencyViewHolder(View view){
            super(view);
            mBitCoinTextView = (TextView) view.findViewById(R.id.bitcoin_text);

            mExchangeRateTextView = (TextView) view.findViewById(R.id.other_currency_text);

            view.setOnClickListener(this);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex){
            Rate currentRate = getItem(listIndex);
            mBitCoinTextView.setText(currentRate.getBitcoinRate()+" Bitcoin equals");
            mExchangeRateTextView.setText(currentRate.getExchangeRate()+" "+currentRate.getCurrency());
        }


        /**
         * This gets called by the child view when clicked
         * @param v the view that was clicked
         */
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            mClickHandler.onClick(position);

        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ConverterViewHolder that holds the View for each list item
     */
    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForListItem = R.layout.currency_listview;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutForListItem, parent, shouldAttachToParentImmediately);
        return new CurrencyViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param currencyViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(CurrencyViewHolder currencyViewHolder, int position) {
        currencyViewHolder.bind(position);

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if(mRates == null)return 0;
        return mRates.size();
    }

    /**
     * This method is used to set the Conversion Rate on a ConverterAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new ConverterAdapter to display it.
     *
     * @param rates The new list of ConversionRates to be displayed.
     */
    public void setRatesDate(ArrayList<Rate> rates){
        mRates = rates;
        notifyDataSetChanged();
    }

    /**
     * *This method helps us get the values in a particular position from our list of Rate
     * Using the getMethods in Rate class
     * @param position position of item in the list
     * @return object of the rate class at a particular position
     */
    public Rate getItem(int position){
        return mRates.get(position);
    }



}

