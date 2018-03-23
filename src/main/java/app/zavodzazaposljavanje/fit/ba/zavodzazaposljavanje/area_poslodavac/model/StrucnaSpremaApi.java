package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.MyApp;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Config;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.volley.MyVolley;

/**
 * Created by Lejla on 18.3.2018..
 */

public class StrucnaSpremaApi
{
    public static void GetAllStrucneSpreme(final Context context, final MyRunnable<JSONArray> onSuccess) {
        //int korisnikId= Sesija.getLogiraniKorisnik().Korisnik;

        final String url = Config.url + "/StrucnaSpremas/GetStrucneSpreme";
        //final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        //dialog.show();



        MyVolley.get2(url, new JSONArray(), new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // dialog.dismiss();
                        onSuccess.run(response);
                    }
                },  new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //dialog.dismiss();
                        Toast.makeText(MyApp.getContext(),"Greska u komunikaciji sa serverom : "+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public static ArrayList<StrucneSpremeVM> jsonToStrucneSpremeList(JSONArray jsonArray)
    {
        ArrayList<StrucneSpremeVM> items=new ArrayList<StrucneSpremeVM>();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                items.add(new StrucneSpremeVM(jsonArray.getJSONObject(i).getInt("Id"),
                        jsonArray.getJSONObject(i).getString("Opis")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return  items;
    }
}
