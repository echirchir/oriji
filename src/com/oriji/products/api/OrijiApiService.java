package com.oriji.products.api;

import com.oriji.products.models.ProductItem;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is the Retrofit required interface that defines the
 * Http request methods for the API
 *
 * @see "http://square.github.io/retrofit/"
 */

public interface OrijiApiService {

    /**
     *
     * @param keyword the user-entered keyword to be sent to the server
     * @param cb the Retrofit Callback function that handles the results
     *           from the server.
     */
    @GET("/api/v1/products/{keyword}")
    void fetchProducts(@Path("keyword") String keyword, Callback<List<ProductItem>> cb);
}
