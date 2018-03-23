package app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.primjer2_vise_fragmenata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.R;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacApi;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.area_poslodavac.model.PoslodavacPregledVM;
import app.zavodzazaposljavanje.fit.ba.zavodzazaposljavanje.helper.MyRunnable;

/**
 * Created by Lejla on 19.3.2018..
 */

public class HomeActivity extends AppCompatActivity
{
    private ViewPager mPager;
    static int oglasId=0;


    private DrawerLayout drawer_Layout;
    private ListView left_drawer;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence drawerNaslov;
    private CharSequence activityNaslov;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mPager=(ViewPager) findViewById(R.id.viewPager);

        oglasId=getIntent().getIntExtra("oglasId",0);


        activityNaslov=drawerNaslov=getTitle();
        drawer_Layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        left_drawer = (ListView) findViewById(R.id.left_drawer);

        drawer_Layout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        left_drawer.setAdapter(new ArrayAdapter<>(this, R.layout.stavka_drawer_list, getNaslovi()));
        left_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                selectItem(position);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawer_Layout,         /* DrawerLayout object */
                                      /* nav drawer image to replace 'Up' caret */
                R.string.drawer_otvoreno,  /* "open drawer" description for accessibility */
                R.string.drawer_zatvoreno  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(activityNaslov);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerNaslov);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawer_Layout.setDrawerListener(mDrawerToggle);



        PoslodavacApi.Pregled(this,new MyRunnable<PoslodavacPregledVM>()
        {
            @Override
            public void run(PoslodavacPregledVM response)
            {
                Toast.makeText(HomeActivity.this, "Podaci su učitani", Toast.LENGTH_SHORT).show();
                pripremi_viewPager();
                if(oglasId!=0)
                    mPager.setCurrentItem(2);
                if(getIntent().getBooleanExtra("refreshed",false))
                    mPager.setCurrentItem(2);
                if(getIntent().getBooleanExtra("clanakAdded",false))
                    mPager.setCurrentItem(1);
            }
        });


    }

    private void selectItem(int position)
    {
        //Fragment fragment = null;
        if (position == 0){
            mPager.setCurrentItem(0);}

        if (position == 1) {
            mPager.setCurrentItem(1);
        }
        if (position == 2)
            mPager.setCurrentItem(2);

        // update selected item and title, then close the drawer
        left_drawer.setItemChecked(position, true);
        setTitle(getNaslovi()[position]);
        drawer_Layout.closeDrawer(left_drawer);

    }

    private String[] getNaslovi() {
        return new String[]{
                "Oglasi",
                "Clanci",
                "Kandidati"
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }


    @Override
    public void setTitle(CharSequence title) {
        activityNaslov = title;
        getSupportActionBar().setTitle(activityNaslov);
    }

    private void pripremi_viewPager()
    {
        final FragmentManager fm=getSupportFragmentManager();
        mPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position)
            {
                Fragment fragment=null;
                if(position==0)
                    fragment= OglasiFragment.newInstance();
                if(position==1)
                    fragment=ClanciFragment.newInstance();
                if(position==2)
                    fragment=KandidatiFragment.newInstance(oglasId); //<-----------------------------------------------------)
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        final ActionBar  actionBar=getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.TabListener nesto=new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                int pozicija=tab.getPosition();
                mPager.setCurrentItem(pozicija);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
            {
                    if(tab.getPosition()==2)
                    {
                        oglasId=0;
                        final Intent intent=new Intent(getBaseContext(),HomeActivity.class);
                        intent.putExtra("refreshed",true);
                        startActivity(intent);
                    }

            }
        };

        ActionBar.Tab tab1=actionBar.newTab().setText("Oglasi").setTabListener(nesto);
        actionBar.addTab(tab1);

        ActionBar.Tab tab2=actionBar.newTab().setText("Clanci").setTabListener(nesto);
        actionBar.addTab(tab2);

        ActionBar.Tab tab3=actionBar.newTab().setText("Kandidati").setTabListener(nesto);
        actionBar.addTab(tab3);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //super.onPageSelected(position);
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
