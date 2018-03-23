package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.MyApp;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Config;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Sesija;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.url_connection.HttpManager;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.url_connection.MojApiRezultat;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.volley.MyVolley;

/**
 * Created by Lejla on 18.3.2018..
 */

public class KandidatApi
{
    public static void PregledKandidata(final Context context,final int strucnaSpremaId, final int oglasId, final MyRunnable<JSONArray> onSuccess) {
        int korisnikId= Sesija.getLogiraniKorisnik().Korisnik;
        //{korisnikId}/{strucnaSpremaId}/{oglasId}
        final String url = Config.url + "/Kandidats/GetandidatsByStrucnaSprema/"+korisnikId+"/"+strucnaSpremaId+"/"+oglasId;
        final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();



        MyVolley.get2(url, new JSONArray(), new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dialog.dismiss();
                        onSuccess.run(response);
                    }
                },  new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        dialog.dismiss();
                        Toast.makeText(MyApp.getContext(),"Greska u komunikaciji sa serverom : "+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public static ArrayList<KandidatiVM> jsonToKandidatiList(JSONArray jsonArray)
    {
        ArrayList<KandidatiVM> items=new ArrayList<KandidatiVM>();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                items.add(new KandidatiVM(jsonArray.getJSONObject(i).getInt("Id"),
                        jsonArray.getJSONObject(i).getString("Ime"),
                        jsonArray.getJSONObject(i).getString("Prezime"),
                        jsonArray.getJSONObject(i).getString("StrucnaSprema"),
                        jsonArray.getJSONObject(i).getInt("RadnoIskustvo")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return  items;
    }

    public static void PregledDetaljaKorisnika(final Context context,final int kandidatId, final MyRunnable<KandidatiDetaljiVM> onSuccess)
    {
        final String url= Config.url + "/Kandidats/Pregled/"+kandidatId;
        final ProgressDialog dialog= ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();

        new  AsyncTask<Void, Void, MojApiRezultat<KandidatiDetaljiVM>>()
        {

            protected MojApiRezultat<KandidatiDetaljiVM> doInBackground(Void... params)
            {

                return HttpManager.get2(url,KandidatiDetaljiVM.class
                );

            }

            @Override
            protected void onPostExecute(MojApiRezultat<KandidatiDetaljiVM> rezultat)
            {
                if(rezultat.isError)
                {
                    Toast.makeText(MyApp.getContext(),"Greska u komunikaciji sa serverom : "+rezultat.errorMessage,Toast.LENGTH_LONG).show();
                }
                else
                {
                    onSuccess.run(rezultat.value);
                }
                dialog.dismiss();
            }
        }.execute();
    }
}
