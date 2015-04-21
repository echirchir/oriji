package com.oriji.products.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.oriji.products.R;
import com.oriji.products.activities.ProductsResulstActivity;
import com.oriji.products.models.ProductModel;
import com.oriji.products.models.ProductObject;

import java.util.ArrayList;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is the Adapter for filtering the products displayed in
 * a list view by keywords
 */
public class ProductFilterAdapter extends BaseAdapter implements Filterable {

    private ProductsResulstActivity activity;
    private ProductFilter productFilter;
    protected Typeface typeface;
    private ArrayList<ProductObject> productList;
    private ArrayList<ProductObject> filteredList;

    /**
     * Initialize context variables
     * @param activity friend list activity
     * @param friendList friend list
     */
    public ProductFilterAdapter(ProductsResulstActivity activity, ArrayList<ProductObject> friendList) {
        this.activity = activity;
        this.productList = friendList;
        this.filteredList = friendList;
        typeface = Typeface.createFromAsset(activity.getAssets(), "Vegur.otf");

        getFilter();
    }

    /**
     * Get size of user list
     * @return userList size
     */
    @Override
    public int getCount() {
        return filteredList.size();
    }

    /**
     * Get specific item from product list
     * @param i item index
     * @return list item
     */
    @Override
    public ProductObject getItem(int i) {
        return filteredList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Create list row view
     * @param position index
     * @param view current list item view
     * @param parent parent
     * @return view
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        final ProductObject product = (ProductObject) getItem(position);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.single_product_row, parent, false);
            holder = new ViewHolder();

            holder.icon = (ImageView)view.findViewById(R.id.icon);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.category = (TextView)view.findViewById(R.id.category);
            holder.year = (TextView)view.findViewById(R.id.year);

            view.setTag(holder);
        } else {
            // get view holder back
            holder = (ViewHolder) view.getTag();
        }

        // bind text with view holder content view for efficient use
        holder.icon.setImageResource(R.drawable.star);
        holder.name.setText(product.getpName());
        holder.category.setText(product.getpCategory());
        holder.year.setText(product.getpYear());
        view.setBackgroundResource(R.drawable.list_row_bg_selector);

        return view;
    }

    /**
     * Get custom filter
     * @return filter
     */
    @Override
    public Filter getFilter() {
        if (productFilter == null) {
            productFilter = new ProductFilter();
        }

        return productFilter;
    }

    /**
     * Keep reference to children view to avoid unnecessary calls
     */
    static class ViewHolder {
        TextView name;
        TextView category;
        TextView year;
        ImageView icon;
    }

    /**
     * Custom filter for friend list
     * Filter content in friend list according to the search text
     */
    private class ProductFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<ProductObject> tempList = new ArrayList<ProductObject>();

                // search content in product list
                for (ProductObject product : productList) {
                    if (product.getpName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(product);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = productList.size();
                filterResults.values = productList;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<ProductObject>) results.values;
            notifyDataSetChanged();
        }
    }

}
