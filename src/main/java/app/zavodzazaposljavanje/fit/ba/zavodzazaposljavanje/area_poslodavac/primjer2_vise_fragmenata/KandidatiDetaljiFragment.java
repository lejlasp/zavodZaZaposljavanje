package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.KandidatiDetaljiVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.F;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by Lejla on 18.3.2018..
 */

public class KandidatiDetaljiFragment extends DialogFragment
{
    KandidatiDetaljiVM kandidatDetalji;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_kandidati_detalji, container, false);



        if(getArguments().getSerializable("model")!=null)
        {
            kandidatDetalji=(KandidatiDetaljiVM)getArguments().getSerializable("model");
            /*if(kandidatDetalji!=null)
               // Toast.makeText(getContext(),"Nije null"+kandidatDetalji.Id,Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getContext(),"Null",Toast.LENGTH_LONG).show();*/
        }
        F.findViewById(view,R.id.imePrezime,TextView.class).setText(kandidatDetalji.Ime+" "+kandidatDetalji.Prezime);

        F.findViewById(view,R.id.listaIskustava, ListView.class).setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return kandidatDetalji.IskustvaInfo.size();
            }

            @Override
            public Object getItem(int position) {
                return kandidatDetalji.IskustvaInfo.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                KandidatiDetaljiVM.IskustvaVM x=kandidatDetalji.IskustvaInfo.get(position);

                if(view==null)
                    view=inflater.inflate(R.layout.stavka_iskustvo,parent,false);

                F.findViewById(view,R.id.radnoMjesto, TextView.class).setText(x.RadnoMjesto);
                F.findViewById(view,R.id.datumOd,TextView.class).setText(F.date_ddMMyyyy(x.DatumPocetka));
                F.findViewById(view,R.id.datumDo,TextView.class).setText(F.date_ddMMyyyy(x.DatumZavrsetka));


                return view;
            }
        });

        F.findViewById(view,R.id.listaJezika, ListView.class).setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return kandidatDetalji.JeziciInfo.size();
            }

            @Override
            public Object getItem(int position) {
                return kandidatDetalji.JeziciInfo.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                KandidatiDetaljiVM.JeziciVM x=kandidatDetalji.JeziciInfo.get(position);
                if(view==null)
                    view=inflater.inflate(R.layout.stavka_jezik,parent,false);


                F.findViewById(view,R.id.nazivJezika,TextView.class).setText(x.Naziv);
                F.findViewById(view,R.id.citanjeValue,TextView.class).setText(x.Citanje);
                F.findViewById(view,R.id.pisanjeValue,TextView.class).setText(x.Pisanje);
                F.findViewById(view,R.id.govorValue,TextView.class).setText(x.Govor);


                return view;
            }
        });

        F.findViewById(view,R.id.listaVozackih,ListView.class).setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return kandidatDetalji.VozackeInfo.size();
            }

            @Override
            public Object getItem(int position) {
                return kandidatDetalji.VozackeInfo.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent)
            {
                KandidatiDetaljiVM.VozackeVM x=kandidatDetalji.VozackeInfo.get(position);

                if(view==null)
                    view=inflater.inflate(R.layout.stavka_vozacka,parent,false);

                F.findViewById(view,R.id.kategorijaVozacke,TextView.class).setText(x.Kategorija);

                return view;
            }
        });

        F.findViewById(view,R.id.btnDownload, Button.class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bd=new AlertDialog.Builder(getContext());
                bd.setTitle("Potvrda preuzimanja");
                bd.setMessage("Da li ste sigurni da želite preuzeti ovaj fajl?");
                bd.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                bd.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Download u toku.", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        //if(kandidatDetalji.VrstaDokumenta.toUpperCase()=="CV")
                        //{

                         String putanja = kandidatDetalji.PutanjaDokumenta;


                                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(putanja));
                                    request.setDescription("Otvorite ovaj fajl da biste instalirali");
                                    request.setTitle("CV: "+kandidatDetalji.Ime);
                                    // in order for this if to run, you must use the android 3.2 to compile your app
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                                        if (checkPermission())
                                        {
                                            // Code for above or equal 23 API Oriented Device
                                            // Your Permission granted already .Do next code
                                            request.allowScanningByMediaScanner();
                                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "CV.pdf");

                                            // get download service and enqueue file
                                            DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                                            manager.enqueue(request);

                                            //getActivity().moveTaskToBack(true);
                                            getDialog().dismiss();
                                        } else {
                                            requestPermission(); // Code for permission
                                        }
                                        //request.allowScanningByMediaScanner();
                                        //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


                                    }
                                    /*request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "uputsvo.pdf");

                                    // get download service and enqueue file
                                    DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                                    manager.enqueue(request);

                                    getActivity().moveTaskToBack(true);
                                    getDialog().dismiss();*/
                                }

                    });
                    //}


                bd.setCancelable(false);
                bd.show();
            }
        });




        return view;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getActivity(), "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 26) {
            if (checkSelfPermission(getActivity(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error", "You already have the permission");
            return true;
        }}




    public static DialogFragment newInstance(KandidatiDetaljiVM result)
    {
        DialogFragment fragment=new KandidatiDetaljiFragment();
        Bundle arg=new Bundle();
        arg.putSerializable("model",result);
        fragment.setArguments(arg);

        return  fragment;
    }
}
