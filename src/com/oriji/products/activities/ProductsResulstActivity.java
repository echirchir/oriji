package com.oriji.products.activities;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import com.oriji.products.R;
import com.oriji.products.adapters.ProductFilterAdapter;
import com.oriji.products.adapters.ProductsAdapter;
import com.oriji.products.events.SyncCompletedEvent;
import com.oriji.products.models.ProductModel;
import com.oriji.products.models.ProductObject;
import com.oriji.products.utils.ActionbarHelper;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is the Results Page for all products acquired
 * from the server after a search.
 */

public class ProductsResulstActivity extends Activity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener
{
    protected ProductFilterAdapter productFilterAdapter;
    protected ArrayList<ProductObject> productList = new ArrayList<ProductObject>();
    protected List<ProductModel> models;
    protected ListView listView;
    private ProgressDialog progress;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        setContentView(R.layout.main_layout);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);

        ActionbarHelper.setupActionBar(this, "Products");

        EventBus.getDefault().register(this);

        progress = ProgressDialog.show(this, "",
                "Fetching products, please wait...", true);

    }

    /**
     * This is an EventBus method that receives events notifications
     *
     * @param event the event that fires once the respective
     *              action is completed; in this case,
     *              pulling down products from the server is completed
     */
    public void onEventMainThread(SyncCompletedEvent event){

        if( !event.isFinished() ){
            progress = ProgressDialog.show(this, "",
                    "Fetching products, please wait...", true);
        }else{
           progress.dismiss();
           initProductsList();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume(){
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPause(){super.onPause(); }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy(){
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.core_actions, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getString(R.string.search_hint));

        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                ProductsResulstActivity.this.finish();
                ProductsResulstActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        productFilterAdapter.getFilter().filter(newText);

        if (TextUtils.isEmpty(newText)) {
             listView.clearTextFilter();
         }
        else {
           listView.setFilterText(newText);
        }

        return true;
    }

    /**
     * Handles filtering of products in a list view
     * when a user enters keywords or letters to filter them
     *
     */
    private void initProductsList(){

        models = ProductModel.fetchAll();

        for (ProductModel model : models){

            ProductObject object = new ProductObject();
            object.setpName(model.getName());
            object.setId(model.getId());
            object.setpCategory(model.getCategory());
            object.setpYear(model.getManufacturedDate());

            productList.add(object);
        }

        productFilterAdapter = new ProductFilterAdapter(this, productList);

        listView = (ListView)findViewById(R.id.list);

        if (models != null && productList.size() > 0){
            listView.setAdapter(productFilterAdapter);
            listView.setTextFilterEnabled(false);
            listView.setOnItemClickListener(this);
            listView.setTextFilterEnabled(true);
            findViewById(R.id.empty).setVisibility(View.GONE);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("id", productFilterAdapter.getItem(position).getId());
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
