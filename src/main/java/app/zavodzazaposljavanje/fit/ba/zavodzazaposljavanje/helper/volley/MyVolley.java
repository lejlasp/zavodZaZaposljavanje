package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.Arrays;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.MyApp;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyGson;

/**
 * Created by Acer on 16.6.2017.
 */

public class MyVolley
{
    public static <T> void get(String urlStr, Class<T> responseType, Response.Listener <T> listener, Response.ErrorListener errorListener, BasicNameValuePair... inputParams)
    {
        String urlParam= URLEncodedUtils.format(Arrays.asList(inputParams),"utf-8");
        String url=urlStr+"?"+urlParam;
        GsonRequest<T> gsonRequest=new GsonRequest<>(url,responseType,null,null,listener,errorListener, Request.Method.GET);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static <T> void set(String urlStr, Class<T> responseType, final Response.Listener <T> listener, Response.ErrorListener errorListener, Object postObject )
    {
        String jsonStr= MyGson.build().toJson(postObject);
        GsonRequest<T> gsonRequest=new GsonRequest<>(urlStr,responseType,null,jsonStr,listener,errorListener, Request.Method.POST);
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static void get2(String url, JSONArray jsonArray, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener)
    {
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET,url,jsonArray,listener, (Response.ErrorListener) errorListener);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(arrayRequest);
    }
}
