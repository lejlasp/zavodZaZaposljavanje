package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.OglasApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.OglasiPregled;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Sesija;

import static app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F.findViewById;

/**
 * Created by Acer on 30.1.2018.
 */

public class OglasiDetaljiFragment extends DialogFragment
{
    private static final String ARG_MODEL = "model";
    int oglasId;
    OglasiPregled.OglasiDetails oglas;

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_oglasi_detalji, container, false);
        final PoslodavacPregledVM.ObjavljeniOglasiVM model = Sesija.getOdabraniOglas();

        if(getArguments().getInt("oglasId")!=0)
            oglasId=getArguments().getInt("oglasId");

        OglasApi.PregledDetaljaOglasa(getContext(), oglasId, new MyRunnable<JSONArray>() {
            @Override
            public void run(JSONArray result) {
                if(result==null)
                    Toast.makeText(getContext(),"Neuspjesno obavljeno",Toast.LENGTH_LONG).show();

                oglas=OglasApi.jsonToOglasiDetaljiList(result).get(0);

                findViewById(view,R.id.tvNazivOglasaValue,TextView.class).setText(oglas.nazivOglasa);
                findViewById(view,R.id.tvTekstOglasaValue,TextView.class).setText(oglas.tekst);
                findViewById(view,R.id.tvGradValue,TextView.class).setText(oglas.grad);
                findViewById(view,R.id.tvBrojPozicijaValue,TextView.class).setText(oglas.brojPozicija+"");
               findViewById(view,R.id.tvDatumObjaveValue,TextView.class).setText(F.date_ddMMyyyy(oglas.datumObjave));
                findViewById(view,R.id.tvDatumIstekaValue,TextView.class).setText(F.date_ddMMyyyy(oglas.datumIsteka));
                F.findViewById(view, R.id.brojPrijavljenihKand, TextView.class).setText(oglas.brojPrijavljenihKandidata + "");

                final TextView t = (TextView) F.findViewById(view, R.id.brojPrijavljenihKand, TextView.class);
                final String tekst = t.getText().toString();
                t.setMovementMethod(LinkMovementMethod.getInstance());
                t.setText(tekst, TextView.BufferType.SPANNABLE);
                Spannable mySpannable = (Spannable) t.getText();
                ClickableSpan myClickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                       /* do something */
                       /*final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        widget=inflater.inflate(R.layout.stavka_kandidati,parent,false);
                        startActivity(new Intent(OglasiActivity.this,KandidatiActivity.class));

                        */
                        //final Intent intent=new Intent(OglasiFragment.this,KandidatiOglasiActivity.class);
                        //intent.putExtra("model",x);
                        //startActivity(intent);

                        //Fragment fragment=KandidatiFragment.newInstance(oglas.Id);

                        //otvoriFragment(fragment);
                        getDialog().dismiss();

                        final Intent intent=new Intent(getActivity(),HomeActivity.class);
                        intent.putExtra("oglasId",oglas.Id);
                        startActivity(intent);

                        //    }
                        //};
                        //



                        //return view;
                    }
                };
                mySpannable.setSpan(myClickableSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        });




        //final PoslodavacPregledVM.ObjavljeniOglasiVM model = (PoslodavacPregledVM.ObjavljeniOglasiVM) getArguments().getSerializable(ARG_MODEL);

        return view;
    }

    public static DialogFragment newInstance(PoslodavacPregledVM.ObjavljeniOglasiVM oglasInfo)
    {
        DialogFragment fragment=new OglasiDetaljiFragment();
        Bundle arg=new Bundle();
        arg.putSerializable(ARG_MODEL,oglasInfo);
        fragment.setArguments(arg);

        return  fragment;
    }

    public static DialogFragment newInstance(int id)
    {
        DialogFragment fragment=new OglasiDetaljiFragment();
        Bundle arg=new Bundle();
        arg.putInt("oglasId",id);
        fragment.setArguments(arg);
        return fragment;
    }

    private void otvoriFragment(Fragment fragment) {
        final FragmentManager fm = getFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.mjestoFragment, fragment);
        transaction.commit();
    }
}
