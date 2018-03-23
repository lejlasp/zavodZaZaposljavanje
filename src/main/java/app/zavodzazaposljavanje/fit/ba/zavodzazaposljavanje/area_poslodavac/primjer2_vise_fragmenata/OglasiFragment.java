package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.OglasApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.OglasiPregled;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.OpstineVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.OpstineApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;


/**
 * Created by Acer on 26.1.2018.
 */

public class OglasiFragment extends Fragment
{

    private static final String ARG_MODEL = "model";
    ListView listaOglasa;
    ArrayList<OglasiPregled.Oglasi> oglasi;
    ArrayList<OpstineVM>opstine;

    Spinner spinnerOpstine;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_oglasi, container, false);

        listaOglasa = F.findViewById(view, R.id.listView, ListView.class);
        spinnerOpstine=F.findViewById(view,R.id.spinner,Spinner.class);

        do_populate_spinner();

        spinnerOpstine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                do_populate_list(position,inflater);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                do_populate_list(0,inflater);
            }
        });



        listaOglasa.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final OglasiPregled.Oglasi x =oglasi.get(position);
                //Sesija.setOdabraniOglas(x);
                //za pokretanje activity-a
                //startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHomeActivity.class));

                //za pokretanje fragmenata
                // startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHome2Activity.class));


                DialogFragment fragment = OglasiDetaljiFragment.newInstance(x.Id);
                FragmentManager fm = getFragmentManager();
                fragment.show(fm, "neki_tag");
            }
        });

        return view;
    }

    private void do_populate_spinner()
    {
        OpstineApi.GetAllOpstine(getContext(), new MyRunnable<JSONArray>() {
            @Override
            public void run(JSONArray result) {
                opstine=OpstineApi.jsonToOpstineList(result);
                opstine.add(0,new OpstineVM(0,"Odaberite opštinu"));
                List<String> labels=new ArrayList<String>();

                for(int i=0;i<opstine.size();i++)
                    labels.add(opstine.get(i).Opis);

                ArrayAdapter<String> opstine_adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,labels);
                opstine_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerOpstine.setAdapter(opstine_adapter);
                spinnerOpstine.setSelection(0);


            }
        });
    }

    private void do_populate_list(int opstinaId, final LayoutInflater inflater)
    {
        OglasApi.PregledOglasa(getActivity(), opstinaId, new MyRunnable<JSONArray>() {
            @Override
            public void run(JSONArray result) {
                if (result == null)
                    Toast.makeText(getContext(), "Neuspjesno", Toast.LENGTH_LONG).show();
                oglasi = OglasApi.jsonToOglasiList(result);

                listaOglasa.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return oglasi.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return oglasi.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View view, ViewGroup parent) {
                        if (view == null)
                            view = inflater.inflate(R.layout.stavka_oglas, parent, false);

                        OglasiPregled.Oglasi x = oglasi.get(position);

                        F.findViewById(view, R.id.tvNazivOglasaValue, TextView.class).setText(x.nazivOglasa);
                        F.findViewById(view, R.id.tvGradValue, TextView.class).setText(x.grad);
                        F.findViewById(view, R.id.tvNazivKompanijeValue, TextView.class).setText(x.nazivKompanije);
                        F.findViewById(view, R.id.tvRokZaPrijavuValue, TextView.class).setText(F.date_ddMMyyyy(x.datumIsteka));
                        F.findViewById(view, R.id.tvBrojKandidataValue, TextView.class).setText(x.brojPrijavljenihKandidata + "");


                        return view;
                    }

                    ;

                });
            }
        });
    }

       /* final PoslodavacPregledVM model= (PoslodavacPregledVM)getArguments().getSerializable(ARG_MODEL);

        findViewById(view,R.id.listView, ListView.class).setAdapter(new BaseAdapter() {
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
                    //final LayoutInflater inflater= (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.stavka_oglas,parent,false);
                }



                final PoslodavacPregledVM.ObjavljeniOglasiVM x = model.ObjavljeniOglasInfo.get(position);

                findViewById(view,R.id.tvNazivOglasaValue,TextView.class).setText(x.NazivOglasa);
                findViewById(view,R.id.tvGradValue,TextView.class).setText(x.OpstinaNaziv);
                findViewById(view,R.id.tvNazivKompanijeValue,TextView.class).setText(x.KompanijaNaziv);
                findViewById(view,R.id.tvRokZaPrijavuValue,TextView.class).setText(F.date_ddMMyyyy(x.RokZaPrijavu));
                findViewById(view,R.id.tvBrojKandidataValue,TextView.class).setText(x.BrojPrijavljenihKandidata+"");

                final TextView t=(TextView) findViewById(view,R.id.tvBrojKandidataValue,TextView.class);
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

                        */
            //final Intent intent=new Intent(OglasiFragment.this,KandidatiOglasiActivity.class);
            //intent.putExtra("model",x);
            //startActivity(intent);

            //Fragment fragment=KandidatiOglasiFragment.newInstance(x);

            //otvoriFragment(fragment);

            //    }
            //};
            // mySpannable.setSpan(myClickableSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            //return view;
            //}
            //});


        /*F.findViewById(view,R.id.listView,ListView.class).setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final PoslodavacPregledVM.ObjavljeniOglasiVM x = model.ObjavljeniOglasInfo.get(position);
                Sesija.setOdabraniOglas(x);
                //za pokretanje activity-a
                //startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHomeActivity.class));

                //za pokretanje fragmenata
               // startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHome2Activity.class));

                DialogFragment fragment = OglasiDetaljiFragment.newInstance(Sesija.getOdabraniOglas());
                FragmentManager fm = getFragmentManager();
                fragment.show(fm, "neki_tag");
            }
        });*/
            //return view;
//}

            public Fragment newInstance(PoslodavacPregledVM poslodavacInfo) {
                Fragment fragment = new OglasiFragment();
                Bundle arg = new Bundle();
                arg.putSerializable(ARG_MODEL, poslodavacInfo);
                fragment.setArguments(arg);

                return fragment;
            }

            private void otvoriFragment(Fragment fragment) {
                final FragmentManager fm = getFragmentManager();
                final FragmentTransaction transaction = fm.beginTransaction();

                transaction.replace(R.id.mjestoFragment, fragment);
                transaction.commit();
            }

            public static Fragment newInstance() {
                OglasiFragment f = new OglasiFragment();
                return f;
            }
        }
