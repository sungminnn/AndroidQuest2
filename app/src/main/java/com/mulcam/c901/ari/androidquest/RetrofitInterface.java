package com.mulcam.c901.ari.androidquest;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Created by Jin on 2017-06-13.
 */

public interface RetrofitInterface {
        @PUT("android.do")
        Call<HashMap<String, Object>> repo();
}
