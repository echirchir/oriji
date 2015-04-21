package com.oriji.products.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.oriji.products.R;
import com.oriji.products.models.ProductObject;

import java.util.List;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is the default products adapter without filtering
 * for all products acquired from the server after a search.
 */
public class ProductsAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ProductObject> listModels;

    public ProductsAdapter(Activity act, List<ProductObject> models) {
        this.activity = act;
        this.listModels = models;
    }

    public List<ProductObject> getData(){
        return listModels;
    }

    @Override
    public int getCount() {
        return listModels.size();
    }

    @Override
    public Object getItem(int position) {
        return listModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.single_product_row, null);

        ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView category = (TextView)convertView.findViewById(R.id.category);
        TextView year = (TextView)convertView.findViewById(R.id.year);

        ProductObject model = listModels.get(position);

        icon.setImageResource(R.drawable.star);
        name.setText(model.getpName());
        category.setText("CATEGORY : "+model.getpCategory());
        year.setText("MADE IN : "+model.getpYear());

        return convertView;
    }
}

