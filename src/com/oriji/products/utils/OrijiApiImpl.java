package com.oriji.products.utils;

import com.oriji.products.api.OrijiApiService;
import com.oriji.products.events.SyncCompletedEvent;
import com.oriji.products.models.ProductItem;
import com.oriji.products.models.ProductModel;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is a utility that handles the GET request to the server
 * using Retrofit and RestAdapter as well as the OrijiApiService interface
 */

public class OrijiApiImpl {

    private static final String BASE_URL = "http://oriji.co.ke";
    protected RestAdapter adapter;
    protected OrijiApiService apiService;

    public OrijiApiImpl(){

        adapter = new RestAdapter
                .Builder()
                .setEndpoint(BASE_URL)
                .build();

        apiService = adapter.create(OrijiApiService.class);
    }

    /**
     *
     * @param keyword the keyword entered by the user to the search box
     *                Uses the EventBus library to report back to the activity
     *                as soon as the GET request has completed successfully or not
     */
    public void getProductsFromServer(String keyword){

        apiService.fetchProducts(keyword, new Callback<List<ProductItem>>() {
            @Override
            public void success(List<ProductItem> productItems, Response response) {

                ProductModel.truncate(ProductModel.class);

                ProductModel product;

                for (ProductItem item : productItems){

                    product = new ProductModel();
                    product.setName(item.getName());
                    product.setDescription(item.getDescription());
                    product.setType(item.getType());
                    product.setCategory(item.getCategory());
                    product.setSerialNumber(item.getSerialNumber());
                    product.setManufacturedDate(item.getManufacturedDate());

                    product.save();
                }

                EventBus.getDefault().post( new SyncCompletedEvent(true) );
            }

            @Override
            public void failure(RetrofitError retrofitError) {

                EventBus.getDefault().post( new SyncCompletedEvent(false) );
            }
        });
    }


}
