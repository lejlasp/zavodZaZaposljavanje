package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.MyApp;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Config;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Sesija;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.url_connection.HttpManager;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.url_connection.MojApiRezultat;


/**
 * Created by Acer on 11.6.2017.
 */

public class PoslodavacApi
{

    public static void Pregled(final Context context, final MyRunnable<PoslodavacPregledVM> onSuccess)
    {
        final String url= Config.url + "/Poslodavac/Pregled";
        final ProgressDialog dialog= ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();

        final int korisnikId= Sesija.getLogiraniKorisnik().Korisnik;

        new  AsyncTask<Void, Void, MojApiRezultat<PoslodavacPregledVM>>()
        {

            protected MojApiRezultat<PoslodavacPregledVM> doInBackground(Void... params)
            {

                return HttpManager.get(url,PoslodavacPregledVM.class,
                        new BasicNameValuePair("korisnikId",korisnikId+"")
                );

            }

            @Override
            protected void onPostExecute(MojApiRezultat<PoslodavacPregledVM> rezultat)
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


       /* MyVolley.get(url, PoslodavacPregledVM.class, new Response.Listener<PoslodavacPregledVM>() {
            @Override
            public void onResponse(PoslodavacPregledVM response)
            {
                dialog.dismiss();
                onSuccess.run(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                dialog.dismiss();
                Toast.makeText(MyApp.getContext(),"Greska u komunikaciji sa serverom : "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        },
                new BasicNameValuePair("djelatnostId",djelatnostId+"")
        );*/
    }
}
