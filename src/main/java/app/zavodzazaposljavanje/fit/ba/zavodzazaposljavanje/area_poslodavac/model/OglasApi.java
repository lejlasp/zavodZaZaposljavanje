package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
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
 * Created by Lejla on 16.3.2018..
 */

public class OglasApi
{
    public static void PregledOglasa(final Context context, final int opstinaId, final MyRunnable<JSONArray> onSuccess) {
        int korisnikId= Sesija.getLogiraniKorisnik().Korisnik;

        final String url = Config.url + "/Oglasis/GetOglasiByOpstina/"+korisnikId+"/"+opstinaId;
        final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();



        MyVolley.get2(url, new JSONArray(), new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dialog.dismiss();
                        onSuccess.run(response);
                    }
                },  new ErrorListener()
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

    public static void PregledDetaljaOglasa(final Context context, final int oglasId, final MyRunnable<JSONArray> onSuccess) {
        int korisnikId= Sesija.getLogiraniKorisnik().Korisnik;

        final String url = Config.url + "/Oglasis/GetOglasDetaliByOglasId/"+oglasId;
        final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();



        MyVolley.get2(url, new JSONArray(), new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dialog.dismiss();
                        onSuccess.run(response);
                    }
                },  new ErrorListener()
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
        public static ArrayList<OglasiPregled.Oglasi> jsonToOglasiList(JSONArray jsonArray)
        {
            ArrayList<OglasiPregled.Oglasi> items=new ArrayList<OglasiPregled.Oglasi>();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

            for(int i=0;i<jsonArray.length();i++)
            {
                try {
                    try {
                        items.add(new OglasiPregled.Oglasi(jsonArray.getJSONObject(i).getInt("Id"),
                                jsonArray.getJSONObject(i).getString("Naziv"),
                                jsonArray.getJSONObject(i).getString("Opis"),
                                jsonArray.getJSONObject(i).getString("NazivKompanije"),
                                sdf.parse(jsonArray.getJSONObject(i).getString("DatumIsteka")),
                                jsonArray.getJSONObject(i).getInt("BrojPrijavljenihKandidata")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return  items;
        }

    public static ArrayList<OglasiPregled.OglasiDetails> jsonToOglasiDetaljiList(JSONArray jsonArray)
    {
        ArrayList<OglasiPregled.OglasiDetails> items=new ArrayList<OglasiPregled.OglasiDetails>();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                try {
                    items.add(new OglasiPregled.OglasiDetails(jsonArray.getJSONObject(i).getInt("Id"),
                            jsonArray.getJSONObject(i).getString("Naziv"),
                            jsonArray.getJSONObject(i).getString("Opis"),
                            jsonArray.getJSONObject(i).getString("Opstina"),
                            jsonArray.getJSONObject(i).getInt("BrojPozicija"),
                            jsonArray.getJSONObject(i).getString("NazivKompanije"),
                            sdf.parse(jsonArray.getJSONObject(i).getString("DatumObjave")),
                            sdf.parse(jsonArray.getJSONObject(i).getString("DatumIsteka")),
                            jsonArray.getJSONObject(i).getInt("BrojPrijavljenihKandidata")));
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







