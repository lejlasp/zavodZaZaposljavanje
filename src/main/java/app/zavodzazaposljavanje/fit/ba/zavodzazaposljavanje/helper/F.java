package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper;

import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Acer on 14.12.2017.
 */

public class F {
    public static <T> T findViewById(View view, int id, Class<T> tClass)
    {
        final View viewById = view.findViewById(id);
        return (T) viewById;
    }

    public static String date_ddMMyyyy(Date datum)
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return  sdf.format(datum);
    }
}
