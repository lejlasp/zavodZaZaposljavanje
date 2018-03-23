package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer1_vise_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;

public class KategorijaHomeActivity extends AppCompatActivity {
    private PoslodavacPregledVM poslodavacInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategorija_home);

        //TextView tvHello=(TextView) findViewById(R.id.tvHello);
        //final AutentifikacijaProvjeraVM.DjelatnostInfoVM model= Sesija.getOdabranaKategorija();
        //tvHello.setText("Informacije za kategoriju "+model.KategorijaNaziv);

        Button btnOglasi =(Button) findViewById(R.id.btnOglasi);
        btnOglasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final Intent intent=new Intent(KategorijaHomeActivity.this,OglasiActivity.class);
                intent.putExtra("model",poslodavacInfo);
                startActivity(intent);
            }
        });

        Button btnClanci =(Button) findViewById(R.id.btnClanci);
        btnClanci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final Intent intent=new Intent(KategorijaHomeActivity.this,ClanciActivity.class);
                intent.putExtra("model",poslodavacInfo);
                startActivity(intent);
            }
        });

        Button btnKandidati =(Button) findViewById(R.id.btnKandidati);
        btnKandidati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final Intent intent=new Intent(KategorijaHomeActivity.this,KandidatiActivity.class);
                intent.putExtra("model",poslodavacInfo);
                startActivity(intent);
            }
        });

        /*F.findViewById(this,R.id.btnOglasi,Button.class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final Intent intent=new Intent(KategorijaHomeActivity.this,OglasiActivity.class);
                intent.putExtra("model",poslodavacInfo);
                startActivity(intent);
            }
        });*/

        PoslodavacApi.Pregled(this, new MyRunnable<PoslodavacPregledVM>() {

            @Override
            public void run(PoslodavacPregledVM result) {
                Toast.makeText(getApplicationContext(),"Podaci su ucitani",Toast.LENGTH_LONG).show();
                poslodavacInfo = result;
            }
        });
    }
}
