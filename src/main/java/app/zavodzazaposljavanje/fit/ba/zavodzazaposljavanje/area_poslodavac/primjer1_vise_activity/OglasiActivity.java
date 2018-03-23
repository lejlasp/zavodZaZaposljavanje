package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer1_vise_activity;

import android.content.Context;
import android.content.Intent;
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

public class OglasiActivity extends AppCompatActivity {
    private PoslodavacPregledVM poslodavacInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oglasi);

        final PoslodavacPregledVM model= (PoslodavacPregledVM) getIntent().getSerializableExtra("model");

        /*ExpandableListView listView= (ExpandableListView) findViewById(R.id.listView);
        listView.setAdapter(new ExpandableListAdapter() {
            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getGroupCount() {
                return model.ObjavljeniOglasInfo.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return model.ObjavljeniOglasInfo.get(groupPosition).PrijavljeniKandidati.size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return null;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return null;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return 0;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent)
            {
                if(view==null)
                {
                    final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.stavka_oglas,parent,false);
                }

                final PoslodavacPregledVM.ObjavljeniOglasiVM x = model.ObjavljeniOglasInfo.get(groupPosition);
                /*TextView tvRadnoMjesto=(TextView)view.findViewById(R.id.tvNazivRadnogMjestaValue);
                tvRadnoMjesto.setText(x.RadnoMjestoNaziv);
                TextView tvGrad=(TextView)view.findViewById(R.id.tvGradValue);
                tvGrad.setText(x.OpstinaNaziv);
                TextView tvKompanija=(TextView)view.findViewById(R.id.tvNazivKompanijeValue);
                tvKompanija.setText(x.KompanijaNaziv);
                TextView tvRokZaPrijavu=(TextView)view.findViewById(R.id.tvRokZaPrijavuValue);
                tvRokZaPrijavu.setText(x.RokZaPrijavu+"");*/

                /*F.findViewById(view,R.id.tvNazivOglasaValue,TextView.class).setText(x.NazivOglasa);
                F.findViewById(view,R.id.tvGradValue,TextView.class).setText(x.OpstinaNaziv);
                F.findViewById(view,R.id.tvNazivKompanijeValue,TextView.class).setText(x.KompanijaNaziv);
                F.findViewById(view,R.id.tvRokZaPrijavuValue,TextView.class).setText(F.date_ddMMyyyy(x.RokZaPrijavu));

                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent)
            {
                if(view==null)
                {
                    final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.stavka_kandidati,parent,false);
                }

                final PoslodavacPregledVM.PrijavljeniKandidatiVM x = model.ObjavljeniOglasInfo.get(groupPosition).PrijavljeniKandidati.get(childPosition);


                F.findViewById(view,R.id.tvImePrezimeValue,TextView.class).setText(x.Ime+" "+x.Prezime);
                F.findViewById(view,R.id.tvSSValue,TextView.class).setText(x.StrucnaSpremaKandidata);
                F.findViewById(view,R.id.tvRadnoIskustvoValue,TextView.class).setText(x.RadnoIskustvoKandidata+"");
                F.findViewById(view,R.id.tvDatumPrijaveValue,TextView.class).setText(F.date_ddMMyyyy(x.DatumPrijave));

                return view;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int groupPosition) {

            }

            @Override
            public void onGroupCollapsed(int groupPosition) {

            }

            @Override
            public long getCombinedChildId(long groupId, long childId) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long groupId) {
                return 0;
            }
        });*/

        final ListView listView= (ListView) findViewById(R.id.listView);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return model.ObjavljeniOglasInfo.size();
            }

            @Override
            public Object getItem(int position) {
                return model.ObjavljeniOglasInfo.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, final ViewGroup parent)
            {
                if(view==null)
                {
                    final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.stavka_oglas,parent,false);
                }



                final PoslodavacPregledVM.ObjavljeniOglasiVM x = model.ObjavljeniOglasInfo.get(position);

                F.findViewById(view,R.id.tvNazivOglasaValue,TextView.class).setText(x.NazivOglasa);
                F.findViewById(view,R.id.tvGradValue,TextView.class).setText(x.OpstinaNaziv);
                F.findViewById(view,R.id.tvNazivKompanijeValue,TextView.class).setText(x.KompanijaNaziv);
                F.findViewById(view,R.id.tvRokZaPrijavuValue,TextView.class).setText(F.date_ddMMyyyy(x.RokZaPrijavu));
                F.findViewById(view,R.id.tvBrojKandidataValue,TextView.class).setText(x.BrojPrijavljenihKandidata+"");

                final TextView t=(TextView)F.findViewById(view,R.id.tvBrojKandidataValue,TextView.class);
                final String tekst=t.getText().toString();
                t.setMovementMethod(LinkMovementMethod.getInstance());
                t.setText(tekst, TextView.BufferType.SPANNABLE);
                Spannable mySpannable = (Spannable)t.getText();
                ClickableSpan myClickableSpan = new ClickableSpan()
                {
                    @Override
                    public void onClick(View widget)
                    {
                       /* do something */
                       /*final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        widget=inflater.inflate(R.layout.stavka_kandidati,parent,false);
                        startActivity(new Intent(OglasiActivity.this,KandidatiActivity.class));

                        */final Intent intent=new Intent(OglasiActivity.this,KandidatiOglasiActivity.class);
                        intent.putExtra("model",x);
                        startActivity(intent);


                    }
                };
                mySpannable.setSpan(myClickableSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                return view;
            }
        });

    }
}
