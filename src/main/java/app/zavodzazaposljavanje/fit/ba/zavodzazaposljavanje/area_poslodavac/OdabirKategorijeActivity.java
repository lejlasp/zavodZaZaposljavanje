package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_autentifikacija.model.AutentifikacijaProvjeraVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata.KategorijaHome2Activity;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Sesija;

public class OdabirKategorijeActivity extends AppCompatActivity {

    final OdabirKategorijeActivity context=OdabirKategorijeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_kategorije);

        final ListView listView =(ListView) findViewById(R.id.listView);
        final List<AutentifikacijaProvjeraVM.DjelatnostInfoVM> djelatnostInfo = Sesija.getLogiraniKorisnik().DjelatnostInfo;

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return djelatnostInfo.size();
            }

            @Override
            public Object getItem(int position) {
                return djelatnostInfo.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                final AutentifikacijaProvjeraVM.DjelatnostInfoVM x = djelatnostInfo.get(position);

                /*final LinearLayout linearLayout = new LinearLayout(context);

                final TextView textView = new TextView(context);
                linearLayout.addView(textView);
                textView.setText("Naziv djelatnosti: "+x.KategorijaNaziv);*/

                //omoucava da ucitamo xml layout file i da ga postavimo u jedan novi view


                if(view==null)
                {
                    final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.stavka_kategorije,parent,false);
                }
                TextView tvKategorija=(TextView)view.findViewById(R.id.tvKategorija);
                tvKategorija.setText(x.KategorijaNaziv);

                return view;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final AutentifikacijaProvjeraVM.DjelatnostInfoVM x = djelatnostInfo.get(position);
                Sesija.setOdabranaKategorija(x);
                //za pokretanje activity-a
                //startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHomeActivity.class));

                //za pokretanje fragmenata
                startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHome2Activity.class));
            }
        });
    }
}
