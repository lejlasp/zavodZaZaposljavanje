package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_autentifikacija.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.MyApp;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Config;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.url_connection.HttpManager;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.url_connection.MojApiRezultat;


/**
 * Created by Acer on 11.6.2017.
 */

public class AutentifikacijaApi {

    public static void Provjera(final Context context, final String username, final String password, final MyRunnable<AutentifikacijaProvjeraVM> onSuccess) {
        final String url = Config.url + "/autentifikacija/provjera/"+username+"/"+password;
        final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();


        new AsyncTask<Void, Void, MojApiRezultat<AutentifikacijaProvjeraVM>>() {


            protected MojApiRezultat<AutentifikacijaProvjeraVM> doInBackground(Void... params) {

                return HttpManager.get2(url, AutentifikacijaProvjeraVM.class
                );

            }

            @Override
            protected void onPostExecute(MojApiRezultat<AutentifikacijaProvjeraVM> rezultat) {

                if (rezultat.isError) {
                    Toast.makeText(MyApp.getContext(), "Greska u komunikaciji sa serverom : " + rezultat.errorMessage, Toast.LENGTH_LONG).show();
                } else {
                    onSuccess.run(rezultat.value);
                }
                dialog.dismiss();
            }


        }.execute();


       /* MyVolley.get(url, AutentifikacijaProvjeraVM.class, new Response.Listener<AutentifikacijaProvjeraVM>() {
            @Override
            public void onResponse(AutentifikacijaProvjeraVM response)
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
                new BasicNameValuePair("username",username),
                new BasicNameValuePair("password",password)
        );*/
    }
}