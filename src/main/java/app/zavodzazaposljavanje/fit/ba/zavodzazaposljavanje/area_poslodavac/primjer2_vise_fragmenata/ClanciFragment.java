package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.ClanciVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.ClanciApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;

/**
 * Created by Acer on 28.1.2018.
 */

public class ClanciFragment extends Fragment
{

    private static final String ARG_MODEL = "model";
    ArrayList<ClanciVM> clanci;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view=inflater.inflate(R.layout.activity_clanci,container,false);

        //final PoslodavacPregledVM model= (PoslodavacPregledVM)getArguments().getSerializable(ARG_MODEL);


        ClanciApi.PregledClanaka(getContext(), new MyRunnable<JSONArray>() {
            @Override
            public void run(JSONArray result) {
                if(result==null)
                    Toast.makeText(getContext(),"Neuspjesno",Toast.LENGTH_SHORT).show();

                clanci=ClanciApi.jsonToClanciList(result);

                F.findViewById(view,R.id.listViewClanci, ListView.class).setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return clanci.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return clanci.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View view, ViewGroup parent)
                    {
                        final ClanciVM x = clanci.get(position);

                /*final LinearLayout linearLayout = new LinearLayout(context);

                final TextView textView = new TextView(context);
                linearLayout.addView(textView);
                textView.setText("Naziv djelatnosti: "+x.KategorijaNaziv);*/

                        //omoucava da ucitamo xml layout file i da ga postavimo u jedan novi view


                        if(view==null)
                        {
                            //final LayoutInflater inflater= (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            view=inflater.inflate(R.layout.stavka_clanak,parent,false);
                        }



                        F.findViewById(view,R.id.tvNazivKompanijeValue,TextView.class).setText(x.Naziv);
                        F.findViewById(view,R.id.tvNazivClankaValue,TextView.class).setText(x.Naslov);
                        F.findViewById(view,R.id.tvTekstValue,TextView.class).setText(x.Tekst);
                        F.findViewById(view,R.id.tvDatumValue,TextView.class).setText(F.date_ddMMyyyy(x.datumObjave));

                        final TextView t=F.findViewById(view,R.id.tvTekstValue,TextView.class);
                        final String tekst=t.getText().toString();
                        String tekst_dio=(tekst.substring(0,5))+"[Continue reading]";
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
            }
        });

        F.findViewById(view,R.id.btnDodajClanak,Button.class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = DodajNoviClanakFragment.newInstance();
                FragmentManager fm = getFragmentManager();
                fragment.show(fm, "neki_tag");
            }
        });

    return  view;
}

    public static Fragment newInstance(PoslodavacPregledVM poslodavacInfo)
    {
        Fragment fragment=new ClanciFragment();
        Bundle arg=new Bundle();
        arg.putSerializable(ARG_MODEL,poslodavacInfo);
        fragment.setArguments(arg);

        return  fragment;
    }

    public static Fragment newInstance()
    {
        ClanciFragment f=new ClanciFragment();
        return f;
    }
}
