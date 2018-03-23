package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 13.12.2017.
 */


public class PoslodavacPregledVM implements Serializable
{
    public static class ObjavljeniOglasiVM implements Serializable
    {
        public int OglasId ;
        public String NazivOglasa ;
        public String Tekst ;
        public String OpstinaNaziv ;
        public int BrojPozicija;
        public String KompanijaNaziv ;
        public Date DatumObjave;
        public Date RokZaPrijavu ;
        public List<PrijavljeniKandidatiVM> PrijavljeniKandidati ;
        public int BrojPrijavljenihKandidata;
    }

    public static class PrijavljeniKandidatiVM implements Serializable
    {
        public int KandidatId ;
        public String Ime ;
        public String Prezime ;
        public String StrucnaSpremaKandidata ;
        public int RadnoIskustvoKandidata ;
        public Date DatumPrijave ;
    }

    public static class PregledClanakaVM implements Serializable
    {
        public int ClanakId ;
        public String Naslov ;
        public String Tekst ;
        public String NazivFirme ;
        public Date Datum;
    }
    //public String Kategorija ;

    public List<ObjavljeniOglasiVM> ObjavljeniOglasInfo ;
    public List<PrijavljeniKandidatiVM> PrijavljeniKandidatiInfo ;
    public List<PregledClanakaVM> PregledClanakaInfo ;

}
