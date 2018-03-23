package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_autentifikacija.LoginActivity;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.OdabirKategorijeActivity;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Sesija;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(Sesija.getLogiraniKorisnik() ==null)
        {
            final Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else
        {
            final Intent intent=new Intent(this,OdabirKategorijeActivity.class);
            startActivity(intent);
        }

        }
    }


