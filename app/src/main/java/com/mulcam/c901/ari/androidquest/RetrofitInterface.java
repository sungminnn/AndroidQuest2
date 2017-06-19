package com.mulcam.c901.ari.androidquest;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.PUT;

/**
 * Created by Jin on 2017-06-14.
 */

public interface RetrofitInterface {
        @PUT("m_android.do")
        Call<HashMap<String, Object>> repo();
//        @PUT("m_session.do")
//        Call<HashMap<String, Object>> session();
//        @PUT("m_getBoard.do")
//        Call<HashMap<String, Object>> getBoard(int boardNo);
}