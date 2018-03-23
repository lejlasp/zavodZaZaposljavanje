package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model;

/**
 * Created by Lejla on 18.3.2018..
 */

public class KandidatiVM
{
    public int Id ;
    public String Ime ;
    public String Prezime ;
    public String StrucnaSprema ;
    public int RadnoIskustvo ;

    public KandidatiVM(int id, String ime, String prezime, String strucnaSprema, int radnoIskustvo)
    {
        this.Id=id;
        this.Ime=ime;
        this.Prezime=prezime;
        this.StrucnaSprema=strucnaSprema;
        this.RadnoIskustvo=radnoIskustvo;
    }
}
