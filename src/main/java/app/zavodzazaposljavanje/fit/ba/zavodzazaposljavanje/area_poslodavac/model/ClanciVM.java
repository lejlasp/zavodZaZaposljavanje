package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

import java.util.Date;

/**
 * Created by Lejla on 17.3.2018..
 */

public class ClanciVM
{
        public int Id;
        public String Naziv;
        public String Naslov;
        public String Tekst;
        public Date datumObjave;

    public ClanciVM(int id, String naziv, String naslov, String tekst, Date datumObjave)
    {
        this.Id=id;
        this.Naziv=naziv;
        this.Naslov=naslov;
        this.Tekst=tekst;
        this.datumObjave=datumObjave;
    }

    public ClanciVM() {

    }
}
