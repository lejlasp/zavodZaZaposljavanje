package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.MyApp;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Config;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Sesija;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.volley.MyVolley;

/**
 * Created by Lejla on 17.3.2018..
 */

public class ClanciApi
{
    public static void PregledClanaka(final Context context, final MyRunnable<JSONArray> onSuccess) {
       int korisnikId= Sesija.getLogiraniKorisnik().Korisnik;

        final String url = Config.url + "/Clanaks/GetClanci/"+korisnikId;
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

    public static void DodavanjeClanka(final Context context,final Clanaks c, final MyRunnable<Clanaks> onSuccess) {
        int korisnikId= Sesija.getLogiraniKorisnik().Korisnik;


        final String url = Config.url + "/Clanaks";
        final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();



        MyVolley.set(url, Clanaks.class, new Response.Listener<Clanaks>() {
                    @Override
                    public void onResponse(Clanaks response) {
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
                },c
        );
    }

    public static ArrayList<ClanciVM> jsonToClanciList(JSONArray jsonArray)
    {
        ArrayList<ClanciVM> items=new ArrayList<ClanciVM>();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                try {
                    items.add(new ClanciVM(jsonArray.getJSONObject(i).getInt("Id"),
                            jsonArray.getJSONObject(i).getString("Naziv"),
                            jsonArray.getJSONObject(i).getString("Naslov"),
                            jsonArray.getJSONObject(i).getString("Tekst"),
                            sdf.parse(jsonArray.getJSONObject(i).getString("DatumObjave"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return  items;
    }
}
