package id.recyclerview.controller;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.recyclerview.ApplicationMain;
import id.recyclerview.model.UGCHomeResponseModel;

/**
 * Created by Andini Rachmah on 1/20/2017.
 */

public class UGCController extends GeneralController {
    public static void getLatestUGC(final int start, final int end, final GeneralController.ConsumeApiListenerArr<UGCHomeResponseModel> listener) {
        final List<UGCHomeResponseModel> list = new ArrayList<>();
        final String url = "http://gusman.api.netjj.dev.codigo.id/v2/ugc";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        int status = obj.getInt("status");
                        String message = obj.getString("message");
                        JSONObject objItems = obj.getJSONObject("items");
                        if (objItems.has("listUGC")) {
                            JSONArray arrListUgc = objItems.getJSONArray("listUGC");

                            Gson gson = new Gson();
                            if (arrListUgc != null) {
                                UGCHomeResponseModel[] ugcProfileResponseModel = gson.fromJson(arrListUgc.toString(), UGCHomeResponseModel[].class);
                                if (ugcProfileResponseModel != null) {
                                    for (UGCHomeResponseModel ugc : ugcProfileResponseModel) {
                                        System.out.println(ugc.getTitle());
                                        list.add(ugc);
                                    }
                                    listener.onReceive(true, message, list);
                                }
                            } else {
                                listener.onReceive(true, "data null", null);
                            }
                        }
                    } catch (Exception e) {
                        listener.onReceive(false, e.getMessage(), null);
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onReceive(false, error.getMessage(), null);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("limit_start", String.valueOf(start));
                params.put("limit_end", String.valueOf(end));
                params.put("username", "");
                return params;
            }
        };
        int socketTimeout = 6000000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        ApplicationMain.getInstance().addToRequestQueue(request, "NETJalanJalan");
    }


}
