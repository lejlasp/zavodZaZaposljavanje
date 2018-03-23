package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper;

import android.content.SharedPreferences;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.MyApp;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_autentifikacija.model.AutentifikacijaProvjeraVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;

/**
 * Created by Acer on 14.6.2017.
 */

public class Sesija
{
    //rezultat poziva logiranog korisnika
    private static final String PREFS_NAME="DatotekaZaSharedPreferences1";
    private  static  AutentifikacijaProvjeraVM.DjelatnostInfoVM odabranaKategorija;
    private static AutentifikacijaProvjeraVM logiraniKorisnik;
    private  static PoslodavacPregledVM.ObjavljeniOglasiVM odabraniOglas;
    public static int poslodavacId=0;

    public static AutentifikacijaProvjeraVM getLogiraniKorisnik()
{
    if(logiraniKorisnik!=null)
        return logiraniKorisnik;
    SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
    String str=settings.getString("logiraniKorisnikJson","");
    if(str.length()==0)
        return null;

    logiraniKorisnik = MyGson.build().fromJson(str, AutentifikacijaProvjeraVM.class);

    return logiraniKorisnik;

}
public static void setLogiraniKorisnik(AutentifikacijaProvjeraVM logiraniKorisnik)
{
    Sesija.logiraniKorisnik=logiraniKorisnik;
    final String str = MyGson.build().toJson(logiraniKorisnik);
    SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
    SharedPreferences.Editor editor=settings.edit();
    editor.putString("logiraniKorisnikJson",str);

    editor.commit();
}

    public static AutentifikacijaProvjeraVM.DjelatnostInfoVM getOdabranaKategorija()
    {

        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        String str=settings.getString("odabranaKategorijaJson","");
        if(str.length()==0)
            return null;

        final AutentifikacijaProvjeraVM.DjelatnostInfoVM vm = MyGson.build().fromJson(str, AutentifikacijaProvjeraVM.DjelatnostInfoVM.class);

        return vm;

    }

    public static void setOdabranaKategorija(AutentifikacijaProvjeraVM.DjelatnostInfoVM odabranaKategorija)
    {
        final String str = MyGson.build().toJson(odabranaKategorija);
        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("odabranaKategorijaJson",str);

        editor.commit();
    }

    public static PoslodavacPregledVM.ObjavljeniOglasiVM getOdabraniOglas()
    {
        if(odabraniOglas!=null)
            return odabraniOglas;
        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        String str=settings.getString("odabraniOglasJson","");
        if(str.length()==0)
            return null;

        odabraniOglas = MyGson.build().fromJson(str, PoslodavacPregledVM.ObjavljeniOglasiVM.class);

        return odabraniOglas;

    }
    public static void setOdabraniOglas(PoslodavacPregledVM.ObjavljeniOglasiVM odabraniOglas)
    {
        Sesija.odabraniOglas=odabraniOglas;
        final String str = MyGson.build().toJson(odabraniOglas);
        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("odabraniOglasJson",str);

        editor.commit();
    }
}
