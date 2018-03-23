package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Lejla on 18.3.2018..
 */

public class KandidatiDetaljiVM implements Serializable
{
    public int Id ;
    public String VrstaDokumenta;
    public String PutanjaDokumenta;
    public String Ime;
    public String Prezime;

    public class IskustvaVM implements Serializable
    {
        public String RadnoMjesto ;
        public Date DatumPocetka ;
        public Date DatumZavrsetka ;
    }

    public class JeziciVM implements  Serializable
    {
        public String Naziv ;
        public String Citanje ;
        public String Pisanje ;
        public String Govor ;
    }

    public class VozackeVM implements Serializable
    {
        public String Kategorija ;
    }

    public List<IskustvaVM> IskustvaInfo ;
    public List<JeziciVM> JeziciInfo ;
    public List<VozackeVM> VozackeInfo ;
}
