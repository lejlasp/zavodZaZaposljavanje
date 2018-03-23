package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer1_vise_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F;

public class ClanciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clanci);


        final ListView listView =(ListView) findViewById(R.id.listViewClanci);
        final PoslodavacPregledVM model= (PoslodavacPregledVM) getIntent().getSerializableExtra("model");

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return model.PregledClanakaInfo.size();
            }

            @Override
            public Object getItem(int position) {
                return model.PregledClanakaInfo.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                final PoslodavacPregledVM.PregledClanakaVM x = model.PregledClanakaInfo.get(position);

                /*final LinearLayout linearLayout = new LinearLayout(context);

                final TextView textView = new TextView(context);
                linearLayout.addView(textView);
                textView.setText("Naziv djelatnosti: "+x.KategorijaNaziv);*/

                //omoucava da ucitamo xml layout file i da ga postavimo u jedan novi view


                if(view==null)
                {
                    final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.stavka_clanak,parent,false);
                }



                F.findViewById(view,R.id.tvNazivKompanijeValue,TextView.class).setText(x.NazivFirme);
                F.findViewById(view,R.id.tvNazivClankaValue,TextView.class).setText(x.Naslov);
                F.findViewById(view,R.id.tvTekstValue,TextView.class).setText(x.Tekst);
                F.findViewById(view,R.id.tvDatumValue,TextView.class).setText(F.date_ddMMyyyy(x.Datum));

                final TextView t=(TextView)F.findViewById(view,R.id.tvTekstValue,TextView.class);
                final String tekst=t.getText().toString();
                String tekst_dio=(tekst.substring(0,25))+"[Continue reading]";
                int i1 = tekst_dio.indexOf("[");
                int i2 = tekst_dio.indexOf("]");
                //String clickable="Continue reading";
                t.setMovementMethod(LinkMovementMethod.getInstance());
                t.setText(tekst_dio, TextView.BufferType.SPANNABLE);
                Spannable mySpannable = (Spannable)t.getText();
                ClickableSpan myClickableSpan = new ClickableSpan()
                {
                    @Override
                    public void onClick(View widget)
                    {
                        t.setText(tekst);/* do something */
                    }
                };
                mySpannable.setSpan(myClickableSpan, i1, i2 + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                //t.setText(tekst_dio);

                return view;
            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final AutentifikacijaProvjeraVM.DjelatnostInfoVM x = djelatnostInfo.get(position);
                Sesija.setOdabranaKategorija(x);
                startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHomeActivity.class));
            }
        });*/
    }

}
