package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer1_vise_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F;

public class KandidatiOglasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kandidati);

        final PoslodavacPregledVM.ObjavljeniOglasiVM model= (PoslodavacPregledVM.ObjavljeniOglasiVM) getIntent().getSerializableExtra("model");

        final ListView listView= (ListView) findViewById(R.id.listViewKandidati);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return model.PrijavljeniKandidati.size();
            }

            @Override
            public Object getItem(int position) {
                return model.PrijavljeniKandidati.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                if(view==null)
                {
                    final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.stavka_kandidati,parent,false);
                }

                final PoslodavacPregledVM.PrijavljeniKandidatiVM x = model.PrijavljeniKandidati.get(position);


                F.findViewById(view,R.id.tvImePrezimeValue,TextView.class).setText(x.Ime+" "+x.Prezime);
                F.findViewById(view,R.id.tvSSValue,TextView.class).setText(x.StrucnaSpremaKandidata);
                F.findViewById(view,R.id.tvRadnoIskustvoValue,TextView.class).setText(x.RadnoIskustvoKandidata+"");
                //F.findViewById(view,R.id.tvDatumPrijaveValue,TextView.class).setText(F.date_ddMMyyyy(x.DatumPrijave));

                return view;
            }
        });
    }
}
