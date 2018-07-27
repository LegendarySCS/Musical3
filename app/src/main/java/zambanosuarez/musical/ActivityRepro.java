package zambanosuarez.musical;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;

import zambanosuarez.musical.Fragment.PagerFragmento;
import zambanosuarez.musical.Fragment.Artistas;
import zambanosuarez.musical.Fragment.Canciones;
import zambanosuarez.musical.Fragment.Generos;
import zambanosuarez.musical.Fragment.Perfil;

public class ActivityRepro extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    PagerFragmento adapter ;
    DrawerLayout drawer;

    private static final int MULTIPLE_PERMISSIONS_REQUEST_CODE = 3;

    private FABRevealLayout fabRevealLayout;
    private TextView albumTitleText;
    private TextView artistNameText;
    private SeekBar songProgress;
    private TextView songTitleText;
    private ImageView prev;
    private ImageView stop;
    private ImageView next;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
  //      fab.setOnClickListener(new View.OnClickListener() {
    //        @Override
      //      public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          //              .setAction("Action", null).show();
            //}
        //});

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupDrawerContent(navigationView);

        //viewPager = findViewById(R.id.pagermain);
      //  adapter = new PagerFragmento(getSupportFragmentManager(), 0);
        //viewPager.setAdapter(adapter);
        //instancia para el fragment
        Fragment fragment;
        FragmentManager fragmentManager;
        fragment = Perfil.newInstance("aaa");

        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();

            }





    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Fragment fragment;
                        FragmentManager fragmentManager;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_gallery:
                                // Enviar título como arguemento del fragmento
                                //Bundle args = new Bundle();
                                //args.putString(ViewFragment.Seccion_Titulo, title);
                                Log.e("MEni",menuItem.getItemId()+"");
                                 fragment = Canciones.newInstance("aaa");

                                 fragmentManager = getSupportFragmentManager();
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.flContent, fragment)
                                        .commit();

                                drawer.closeDrawers(); // Cerrar drawer

                                ///setTitle(title); // Setear título actual
                                break;
                            case R.id.nav_artista:
                                Log.e("MEni",menuItem.getItemId()+"");
                                fragment = Artistas.newInstance("aaa");

                                fragmentManager = getSupportFragmentManager();
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.flContent, fragment)
                                        .commit();
                                drawer.closeDrawers();
                                break;
                            case R.id.nav_genero:
                                Log.e("MEni",menuItem.getItemId()+"");
                                fragment = Generos.newInstance("aaa");

                                fragmentManager = getSupportFragmentManager();
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.flContent, fragment)
                                        .commit();
                                drawer.closeDrawers();
                                break;
                            case R.id.nav_lista:
                                Log.e("MEni",menuItem.getItemId()+"");
                                fragment = Perfil.newInstance("aaa");

                                fragmentManager = getSupportFragmentManager();
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.flContent, fragment)
                                        .commit();
                                drawer.closeDrawers();
                                break;
                                default:

                                    Log.e("MEni",menuItem.getItemId()+"");
                                    fragment = Artistas.newInstance("aaa");

                                    fragmentManager = getSupportFragmentManager();
                                    fragmentManager
                                            .beginTransaction()
                                            .replace(R.id.flContent, fragment)
                                            .commit();
                                    drawer.closeDrawers();
                                    break;

                        }
                        return true;
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
        getMenuInflater().inflate(R.menu.activity_repro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lista) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_artista) {

        }else if(id == R.id.nav_genero){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void permisos() {
        String[] permissions = new String[]{
                Manifest.permission.INTERNET
                ,Manifest.permission.READ_EXTERNAL_STORAGE
                ,Manifest.permission.WRITE_EXTERNAL_STORAGE};
//
        if (ActivityCompat.checkSelfPermission(ActivityRepro.this, permissions[0]) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(ActivityRepro.this, permissions[1]) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(ActivityRepro.this, permissions[2]) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(ActivityRepro.this, permissions[3]) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(ActivityRepro.this, permissions[4]) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(ActivityRepro.this, permissions[5]) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(ActivityRepro.this, permissions[6]) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(ActivityRepro.this, permissions[7]) != PackageManager.PERMISSION_GRANTED) {
            //Si alguno de los permisos no esta concedido lo solicita
            Toast.makeText(this, "Permisos no activados! " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();

            ActivityCompat.requestPermissions(ActivityRepro.this, permissions, MULTIPLE_PERMISSIONS_REQUEST_CODE);

        } else {
            //Si todos los permisos estan concedidos prosigue con el flujo normal
            //Toast.makeText(this, "The permissions are already granted ", Toast.LENGTH_LONG).show();

        }
        //
    }
}
