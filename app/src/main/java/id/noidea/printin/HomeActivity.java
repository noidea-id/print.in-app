package id.noidea.printin;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import id.noidea.printin.Session.SessionManager;

import static java.lang.Boolean.TRUE;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SessionManager session;

    SliderLayout mDemoSlider;

    RelativeLayout btnBanner;
    RelativeLayout btnPoster;
    RelativeLayout btnBrosur;
    RelativeLayout btnStiker;
    RelativeLayout btnBulletin;
    RelativeLayout btnKalender;

    TextView tvBanner;
    TextView tvPoster;
    TextView tvBrosur;
    TextView tvKalender;
    TextView tvBulletin;
    TextView tvStiker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn() == TRUE) {
            setContentView(R.layout.activity_home);



            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            CircleImageView profilePictures =  navigationView.getHeaderView(0).findViewById(R.id.profile_picture_user);
            TextView name = navigationView.getHeaderView(0).findViewById(R.id.nameView);

//        String imageUrl = session.getAvatar();
            name.setText(session.getName());
//        Picasso.with(getApplicationContext()).load(imageUrl).into(profilePictures);



        } else {
            setContentView(R.layout.activity_home_guest);

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }

        /* SET FONT HERE */
        Typeface lato_bold = Typeface.createFromAsset(getAssets(), "font/lato_bold.ttf");

        tvBanner = (TextView)findViewById(R.id.tvBanner);
        tvBanner.setTypeface(lato_bold);
        tvPoster = (TextView)findViewById(R.id.tvPoster);
        tvPoster.setTypeface(lato_bold);
        tvBrosur = (TextView)findViewById(R.id.tvBrosur);
        tvBrosur.setTypeface(lato_bold);
        tvKalender = (TextView)findViewById(R.id.tvKalendar);
        tvKalender.setTypeface(lato_bold);
        tvBulletin = (TextView)findViewById(R.id.tvBulletin);
        tvBulletin.setTypeface(lato_bold);
        tvStiker = (TextView)findViewById(R.id.tvStiker);
        tvStiker.setTypeface(lato_bold);

        /* HERE LIES SLIDER FUNCTION */
        mDemoSlider = findViewById(R.id.slider);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1",R.drawable.home_slider1);
        file_maps.put("2",R.drawable.home_slider2);
        file_maps.put("3",R.drawable.home_slider3);

        for(String name : file_maps.keySet()){
            DefaultSliderView sliderView = new DefaultSliderView(getBaseContext());
            // initialize a SliderLayout
            sliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            mDemoSlider.addSlider(sliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setDuration(3000);
        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        mDemoSlider.addOnPageChangeListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        btnBanner = findViewById(R.id.menu_banner);
        btnBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PreviewJenisActivity.class);
                startActivity(intent);
            }
        });
        btnPoster = findViewById(R.id.menu_poster);
        btnPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PreviewJenisActivity.class);
                startActivity(intent);
            }
        });
        btnBrosur = findViewById(R.id.menu_brosur);
        btnBrosur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PreviewJenisActivity.class);
                startActivity(intent);
            }
        });
        btnStiker = findViewById(R.id.menu_stiker);
        btnStiker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PreviewJenisActivity.class);
                startActivity(intent);
            }
        });
        btnBulletin = findViewById(R.id.menu_bulletin);
        btnBulletin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PreviewJenisActivity.class);
                startActivity(intent);
            }
        });
        btnKalender = findViewById(R.id.menu_kalender);
        btnKalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PreviewJenisActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notif) {
            Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (session.isLoggedIn() == TRUE) {
            if (id == R.id.nav_product) {
                // Handle the camera action
            } else if (id == R.id.nav_search) {

            } else if (id == R.id.nav_order) {

            } else if (id == R.id.nav_setting) {

            } else if (id == R.id.nav_faq) {

            } else if (id == R.id.nav_contact) {

            } else if (id == R.id.nav_exit) {
                session.logoutUser();
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        } else {
            if (id == R.id.nav_product) {
                // Handle the camera action
            } else if (id == R.id.nav_search) {

            } else if (id == R.id.nav_order) {

            } else if (id == R.id.nav_setting) {

            } else if (id == R.id.nav_faq) {

            } else if (id == R.id.nav_contact) {

            } else if (id == R.id.nav_exit) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* FUNC FOR SLIDER */
    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getBaseContext(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
