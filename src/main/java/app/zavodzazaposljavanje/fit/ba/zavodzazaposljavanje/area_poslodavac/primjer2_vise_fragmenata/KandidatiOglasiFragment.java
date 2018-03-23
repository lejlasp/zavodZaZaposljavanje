package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F;

/**
 * Created by Acer on 29.1.2018.
 */

public class KandidatiOglasiFragment extends Fragment
{
    private static final String ARG_MODEL = "model";

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_kandidati, container, false);

        final PoslodavacPregledVM.ObjavljeniOglasiVM model = (PoslodavacPregledVM.ObjavljeniOglasiVM) getArguments().getSerializable(ARG_MODEL);

        F.findViewById(view, R.id.listViewKandidati, ListView.class).setAdapter(new BaseAdapter() {
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
                    //final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        return view;
    }

    public static Fragment newInstance(PoslodavacPregledVM.ObjavljeniOglasiVM oglasInfo)
    {
        Fragment fragment=new KandidatiOglasiFragment();
        Bundle arg=new Bundle();
        arg.putSerializable(ARG_MODEL,oglasInfo);
        fragment.setArguments(arg);

        return  fragment;
    }
}
