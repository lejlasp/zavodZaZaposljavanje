package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Acer on 13.6.2017.
 */

public class MyGson
{
    public static Gson build()
    {
        GsonBuilder builder=new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return builder.create();
    }
}
