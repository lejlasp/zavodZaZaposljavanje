package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.url_connection;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyGson;

/**
 * Created by Acer on 11.6.2017.
 */

public class HttpManager
{
    public static <T> MojApiRezultat<T> get(String url,Class<T> outputType, NameValuePair... inputParams)
    {

        //konverzija parametara u string
        String urlParam =URLEncodedUtils.format( Arrays.asList(inputParams),"utf-8");

        HttpGet httpGet=new HttpGet(url+"?"+urlParam);

        //izvrsavanje httpget pomocu klijenta
        DefaultHttpClient client=new DefaultHttpClient();

        //HttpClient client = HttpClientBuilder.create().build();

        final MojApiRezultat<T> rezultat=new MojApiRezultat<>();

        try
        {
            //u response se smjesta rezultat poziva web servisa
            HttpResponse response=client.execute(httpGet);

            //preuzimanje sadrzaja iz prethodnog rezultata
            InputStream stream=response.getEntity().getContent();

            String strJson= convertStreamToString(stream);

            //konverzija iz json u AutentifikcijaProvjerVM

            T x = MyGson.build().fromJson(strJson, outputType);

            rezultat.isError=false;
            rezultat.value=x;

            return rezultat;
        }
        catch (IOException e)
        {
            Log.e("HttpManager",e.getMessage());
            rezultat.isError=false;
            rezultat.value=null;
            rezultat.errorMessage=e.getMessage();
        }
        return rezultat;
    }

    public static String convertStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder=new StringBuilder();
        String line = null;

        //uzima se red po red i dodaje u stringBuilder
        while((line=bufferedReader.readLine())!=null)
        {
            stringBuilder.append(line+'\n');
        }

        return stringBuilder.toString();
    }

    public static <T>MojApiRezultat<T> get2(String url, Class<T> kandidatiDetaljiVMClass)
    {
        //String urlParam =URLEncodedUtils.format( Arrays.asList(inputParams),"utf-8");

        HttpGet httpGet=new HttpGet(url);

        //izvrsavanje httpget pomocu klijenta
        DefaultHttpClient client=new DefaultHttpClient();

        //HttpClient client = HttpClientBuilder.create().build();

        final MojApiRezultat<T> rezultat=new MojApiRezultat<>();

        try
        {
            //u response se smjesta rezultat poziva web servisa
            HttpResponse response=client.execute(httpGet);

            //preuzimanje sadrzaja iz prethodnog rezultata
            InputStream stream=response.getEntity().getContent();

            String strJson= convertStreamToString(stream);

            //konverzija iz json u AutentifikcijaProvjerVM

            T x = MyGson.build().fromJson(strJson, kandidatiDetaljiVMClass);

            rezultat.isError=false;
            rezultat.value=x;

            return rezultat;
        }
        catch (IOException e)
        {
            Log.e("HttpManager",e.getMessage());
            rezultat.isError=false;
            rezultat.value=null;
            rezultat.errorMessage=e.getMessage();
        }
        return rezultat;

    }
}
