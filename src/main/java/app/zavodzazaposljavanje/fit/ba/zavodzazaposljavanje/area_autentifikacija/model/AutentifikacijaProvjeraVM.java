package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_autentifikacija.model;

import java.util.List;

/**
 * Created by Acer on 11.6.2017.
 */

public class AutentifikacijaProvjeraVM
{
    public String Ime ;
    public String KorisnickoIme ;
    public int Korisnik ;
    public int PoslodavacId;
    public String Prezime ;

    public List<ZaposlenjaInfoVM>ZaposlenjaInfo;
    public List<DjelatnostInfoVM> DjelatnostInfo;

    public class ZaposlenjaInfoVM
    {
        public int KorisnickaUloga;
        public String ZaposlenjeMjestoNaziv;
    }
    public class DjelatnostInfoVM
    {
        public int DjelatnostId;
        public String KategorijaNaziv;
    }

}
