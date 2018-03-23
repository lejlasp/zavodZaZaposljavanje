package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper;

/**
 * Created by Acer on 13.6.2017.
 */

public interface MyRunnable<T>
{
    //funkcija koja se izvrsava ukoliko je ispravno pozvan web servis

    void run(T result);
}
