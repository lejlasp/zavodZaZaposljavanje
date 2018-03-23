package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lejla on 16.3.2018..
 */

public class OglasiPregled implements  Serializable
{
    public static class Oglasi implements Serializable
    {
        public int Id;
        public String nazivOglasa;
        public String grad;
        public String nazivKompanije;
        public Date datumIsteka;
        public int brojPrijavljenihKandidata;

        public Oglasi(int id, String naziv, String opis, String nazivKompanije, Date datumIsteka, int brojPrijavljenihKandidata)
        {
            this.Id=id;
            this.nazivOglasa=naziv;
            this.grad=opis;
            this.nazivKompanije=nazivKompanije;
            this.datumIsteka=datumIsteka;
            this.brojPrijavljenihKandidata=brojPrijavljenihKandidata;
        }
    }

    public static class OglasiDetails implements Serializable
    {
        public int Id;
        public String nazivOglasa;
        public String tekst;
        public String grad;
        public int brojPozicija;
        public String nazivKompanije;
        public Date datumObjave;
        public Date datumIsteka;
        public int brojPrijavljenihKandidata;

        public OglasiDetails(int id, String naziv, String opis, String opstina, int brojPozicija, String nazivKompanije, Date datumObjave, Date datumIsteka, int brojPrijavljenihKandidata)
        {
            this.Id=id;
            this.nazivOglasa=naziv;
            this.tekst=opis;
            this.grad=opstina;
            this.brojPozicija=brojPozicija;
            this.nazivKompanije=nazivKompanije;
            this.datumObjave=datumObjave;
            this.datumIsteka=datumIsteka;
            this.brojPrijavljenihKandidata=brojPrijavljenihKandidata;
        }
    }
}
