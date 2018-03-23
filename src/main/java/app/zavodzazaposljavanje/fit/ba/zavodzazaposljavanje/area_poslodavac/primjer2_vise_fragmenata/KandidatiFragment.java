package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.KandidatApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.KandidatiDetaljiVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.KandidatiVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.StrucnaSpremaApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.StrucneSpremeVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;

/**
 * Created by Acer on 28.1.2018.
 */

public class KandidatiFragment extends Fragment
{
    private static final String ARG_MODEL = "model";
    ListView listaKandidata;
    Spinner strucneSpremeSpinner;

    ArrayList<StrucneSpremeVM> spreme;
    ArrayList<KandidatiVM> kandidati;
    int oglasId=0;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view=inflater.inflate(R.layout.activity_kandidati,container,false);

        listaKandidata= F.findViewById(view,R.id.listViewKandidati,ListView.class);
        strucneSpremeSpinner=F.findViewById(view,R.id.spinnerStrucnaSprema,Spinner.class);

        if(getArguments().getInt("oglasId")!=0)
            oglasId=getArguments().getInt("oglasId");

        do_populate_spinner();

        strucneSpremeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        listaKandidata.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final KandidatiVM x =kandidati.get(position);
                //Sesija.setOdabraniOglas(x);
                //za pokretanje activity-a
                //startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHomeActivity.class));

                //za pokretanje fragmenata
                // startActivity(new Intent(OdabirKategorijeActivity.this,KategorijaHome2Activity.class));

                //Toast.makeText(getContext(),"Id "+kandidati.get(position).Id,Toast.LENGTH_LONG).show();
                KandidatApi.PregledDetaljaKorisnika(getContext(), kandidati.get(position).Id, new MyRunnable<KandidatiDetaljiVM>() {
                    @Override
                    public void run(KandidatiDetaljiVM result) {
                        Toast.makeText(getContext(),"Id "+result.Id,Toast.LENGTH_LONG).show();
                        DialogFragment fragment = KandidatiDetaljiFragment.newInstance(result);
                        FragmentManager fm = getFragmentManager();
                        fragment.show(fm, "neki_tag");
                    }
                });

            }
        });


        /*final PoslodavacPregledVM model= (PoslodavacPregledVM)getArguments().getSerializable(ARG_MODEL);

        F.findViewById(view,R.id.listViewKandidati, ListView.class).setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return model.PrijavljeniKandidatiInfo.size();
            }

            @Override
            public Object getItem(int position) {
                return model.PrijavljeniKandidatiInfo.get(position);
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
                    //final LayoutInflater inflater= (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.stavka_kandidati,parent,false);
                }

                final PoslodavacPregledVM.PrijavljeniKandidatiVM x = model.PrijavljeniKandidatiInfo.get(position);


                F.findViewById(view,R.id.tvImePrezimeValue,TextView.class).setText(x.Ime+" "+x.Prezime);
                F.findViewById(view,R.id.tvSSValue,TextView.class).setText(x.StrucnaSpremaKandidata);
                F.findViewById(view,R.id.tvRadnoIskustvoValue,TextView.class).setText(x.RadnoIskustvoKandidata+"");
                F.findViewById(view,R.id.tvDatumPrijaveValue,TextView.class).setText(F.date_ddMMyyyy(x.DatumPrijave));

                return view;
            }
        });*/

        return view;
    }

    private void do_populate_list(int strucnaSpremaId, final LayoutInflater inflater)
    {
        KandidatApi.PregledKandidata(getActivity(), strucnaSpremaId,oglasId, new MyRunnable<JSONArray>() {
            @Override
            public void run(JSONArray result) {
                if (result == null)
                    Toast.makeText(getContext(), "Neuspjesno", Toast.LENGTH_LONG).show();
                kandidati = KandidatApi.jsonToKandidatiList(result);

                listaKandidata.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return kandidati.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return kandidati.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View view, ViewGroup parent) {
                        if (view == null)
                            view = inflater.inflate(R.layout.stavka_kandidati, parent, false);

                        KandidatiVM x = kandidati.get(position);

                        F.findViewById(view,R.id.tvImePrezimeValue,TextView.class).setText(x.Ime+" "+x.Prezime);
                        F.findViewById(view,R.id.tvSSValue,TextView.class).setText(x.StrucnaSprema);
                        F.findViewById(view,R.id.tvRadnoIskustvoValue,TextView.class).setText(x.RadnoIskustvo+"");
                        //F.findViewById(view,R.id.tvDatumPrijaveValue,TextView.class).setText(F.date_ddMMyyyy(x.));


                        return view;
                    }

                    ;

                });
            }
        });
    }

    private void do_populate_spinner()
    {
        StrucnaSpremaApi.GetAllStrucneSpreme(getContext(), new MyRunnable<JSONArray>() {
            @Override
            public void run(JSONArray result) {
                spreme=StrucnaSpremaApi.jsonToStrucneSpremeList(result);
                spreme.add(0,new StrucneSpremeVM(0,"Odaberite spremu"));
                List<String> labels=new ArrayList<String>();

                for(int i=0;i<spreme.size();i++)
                    labels.add(spreme.get(i).Opis);

                ArrayAdapter<String> spreme_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,labels);
                spreme_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                strucneSpremeSpinner.setAdapter(spreme_adapter);
                strucneSpremeSpinner.setSelection(0);


            }
        });
    }

    public static Fragment newInstance(PoslodavacPregledVM poslodavacInfo)
    {
        Fragment fragment=new KandidatiFragment();
        Bundle arg=new Bundle();
        arg.putSerializable(ARG_MODEL,poslodavacInfo);
        fragment.setArguments(arg);

        return  fragment;
    }

    public static Fragment newInstance(int id)
    {
        Fragment fragment=new KandidatiFragment();
        Bundle arg=new Bundle();
        arg.putInt("oglasId",id);
        fragment.setArguments(arg);

        return  fragment;
    }
}
