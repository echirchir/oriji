package com.oriji.products.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.oriji.products.R;
import com.oriji.products.models.ProductModel;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is the Details Page for a single product.
 */
public class ProductDetailsActivity extends Activity {

    private TextView category, type, description, serial, year;

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        setContentView(R.layout.product_details);

        Intent intent = getIntent();

        long id = intent.getExtras().getLong("id");

        ProductModel model = ProductModel.fetchProduct(id);

        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        this.setTitle(model.getName());

        setCustomFont(R.id.descriptionTitle, "Quicksand-Regular.otf");
        setCustomFont(R.id.typeTitle, "Quicksand-Regular.otf");
        setCustomFont(R.id.serialTitle, "Quicksand-Regular.otf");
        setCustomFont(R.id.categoryTitle, "Quicksand-Regular.otf");
        setCustomFont(R.id.yearTitle, "Quicksand-Regular.otf");

        category = (TextView)findViewById(R.id.category);
        type = (TextView)findViewById(R.id.type);
        description = (TextView)findViewById(R.id.description);
        serial = (TextView)findViewById(R.id.serial);
        year = (TextView)findViewById(R.id.year);

        category.setText(model.getCategory());
        description.setText(model.getDescription());
        type.setText(model.getType());
        serial.setText(model.getSerialNumber());
        year.setText(model.getManufacturedDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * This method sets the custom font to the view
     *
     * @param id the element id to be applied the font
     * @param font the font to be applied to the element
     */

    private void setCustomFont(int id, String font){

        TextView text = (TextView) findViewById(id);
        Typeface tf = Typeface.createFromAsset(getAssets(), font);
        text.setTypeface(tf);
    }
}
