package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_autentifikacija;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_autentifikacija.model.AutentifikacijaApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_autentifikacija.model.AutentifikacijaProvjeraVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata.HomeActivity;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Sesija;


public class LoginActivity extends AppCompatActivity {

    private EditText txtPassword;
    private EditText txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

       // this.btnSignIn = (Button) findViewById(btnSignin);
        //btnSignIn.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                //Intent intent=new Intent(getApplicationContext(),ZZZHome.class);
                //startActivity(intent);
        //    }
        //});

        /*
        -da bi se pristupilo narednim varijablama potrebno ih je pretvoriti u "field", kao sto je prikazano ispd

        EditText txtUsername = (EditText)findViewById(R.id.txtUsername);
        EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
        Button btnSignIn = (Button)findViewById(R.id.btnSignin);
        */

        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        Button btnSignIn = (Button)findViewById(R.id.btnSignin);

        //implementiranje objekta anonimne klase koja moze naslijediti apstraktnu klasu i interfejs
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnSignIn_click();
            }
        });

    }

    private void do_btnSignIn_click()
    {
        //nje moguce direktno ovako pozvati web servis osim da se to dozvoli u thread policy-u
        //medjutim to nije ni preoruceno rjesenje jer se u tom slucaju bokira gl. thread dok se izvrsi poziv web servisa
        //a poziv web servisa moze da traje nekoliko sekundi ili cak duze
        //ukoliko web server nije dostupan onda bi u tom slucaju android apk zamrzla;por. Not responding dok se ne zavrsi poziv w.s.
        //da bi se to izbjeglo treba koristiti tredove

        //dakle zakljucak -naredna linija koda mora biti u thread-u:
        //AutentifikacijaProvjeraVM rezultat = AutentifikacijaApi.Provjera(txtUsername.getText().toString(), txtPassword.getText().toString());

        //V0.1- poziv web servisa je prebacen u kontroler "AutentifikacijaApi", kako bi se rasteretio LoginActiivity

        //new MyRunnable<AutentifikacijaProvjeraVM> jest paraetar koji se prosljedjuje f-ji Provjera,
        //ali posto je interfejs moguce ga je odmah definisati kao sto je navedeno u kodu ispod

        AutentifikacijaApi.Provjera(this,txtUsername.getText().toString(), txtPassword.getText().toString(),new MyRunnable<AutentifikacijaProvjeraVM>(){
            @Override
            public void run(AutentifikacijaProvjeraVM result)
            {
                //Sesija.setLogiraniKorisnik(result);
                if(result==null)
                    Toast.makeText(LoginActivity.this,"Pogresan username ili password!",Toast.LENGTH_LONG).show();
                else
                    {
                        Sesija.setLogiraniKorisnik(result);
                        Sesija.poslodavacId=result.PoslodavacId;
                         Toast.makeText(LoginActivity.this, result.Ime + " " + result.Prezime , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }
            }
        });


    }

}
