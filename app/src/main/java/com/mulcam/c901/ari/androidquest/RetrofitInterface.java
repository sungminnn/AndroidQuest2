package com.mulcam.c901.ari.androidquest;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Jin on 2017-06-14.
 */

public interface RetrofitInterface {
        @PUT("m_getBoard.do")
        Call<HashMap<String, Object>> getBoard(@Query("boardNo") int boardNo);

        @PUT("m_android.do")
        Call<HashMap<String, Object>> repo();

        @PUT("m_applyPopupProc.do")
        Call<HashMap<String, Object>> applyProc();

        @PUT("m_login_id.do")
        Call<HashMap<String, Object>> login_id(@Query("id") String id);

        @PUT("m_login.do")
        Call<HashMap<String, Object>> login(@Query("id") String id, @Query("pw") String pw);
//        @PUT("m_session.do")
//        Call<HashMap<String, Object>> session();

}