package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.Clanaks;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.ClanciApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.Sesija;

import static app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F.findViewById;

/**
 * Created by Lejla on 17.3.2018..
 */

public class DodajNoviClanakFragment extends DialogFragment
{

    EditText naslov;
    EditText tekst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view=inflater.inflate(R.layout.fragment_novi_clanak,container,false);

        final Clanaks c=new Clanaks();




        //c.Naslov= F.findViewById(view,R.id.tvNazivClankaValue, EditText.class).getText().toString();
        //c.Tekst=F.findViewById(view,R.id.tvTekstOglasaValue,EditText.class).getText().toString();


        findViewById(view,R.id.btnSpremi, Button.class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bd=new AlertDialog.Builder(getContext());
                bd.setTitle("Potvrda");
                bd.setMessage("Jeste li sigurni da želite dodati ovj članak?");
                bd.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                });
                bd.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        naslov= findViewById(view, R.id.editTextNaslov, EditText.class);
                        tekst= findViewById(view, R.id.editTextTekst, EditText.class);

                        if (!naslov.getText().toString().isEmpty() && !tekst.getText().toString().isEmpty())
                        {
                            c.Naslov=naslov.getText().toString();
                            c.Tekst=tekst.getText().toString();
                            c.PoslodavacId= Sesija.poslodavacId;
                            ClanciApi.DodavanjeClanka(getContext(), c, new MyRunnable<Clanaks>() {
                                @Override
                                public void run(Clanaks result) {
                                    if(result==null)
                                    {
                                        Toast.makeText(getContext(),"Neuspjesno! Niste dodali članak!",Toast.LENGTH_SHORT).show();
                                        getDialog().dismiss();
                                    }
                                    else {
                                        final Intent intent=new Intent(getActivity(),HomeActivity.class);
                                        intent.putExtra("clanakAdded",true);
                                        startActivity(intent);
                                        getDialog().dismiss();
                                        Toast.makeText(getContext(), "Uspješno ste dodali članak", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });

                Dialog dialog=bd.create();
                dialog.show();
            }
        });


        return view;
    }

    public static DialogFragment newInstance()
    {
        DialogFragment f=new DodajNoviClanakFragment();
        return f;
    }
}
