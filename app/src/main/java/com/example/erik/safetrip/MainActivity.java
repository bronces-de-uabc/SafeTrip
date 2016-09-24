package com.example.erik.safetrip;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition = 0;
    private Menu menuAux;
    private int materiaId;

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity","onCreate");

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        titles = getResources().getStringArray(R.array.menu_list);
        drawerList = (ListView) findViewById(R.id.drawer);

        drawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position");
            materiaId = savedInstanceState.getInt("materiaId");
            setActionBarTitle(currentPosition);
        } else {
            Log.d("MainActivity","currentPosition: "+currentPosition);
            selectItem(0);
        }

        //Create the ActionBarDrawerToggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            //Called when a drawer has settled in a completely closed state
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            //Called when a drawer has settled in a completely open state.
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        FragmentManager fragMan = getFragmentManager();
                        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                        if (fragment instanceof InicioFragment) {
                            currentPosition = 0;
                        }
                        if (fragment instanceof PerfilFragment) {
                            currentPosition = 1;
                        }
                        if (fragment instanceof InformacionFragment) {
                            currentPosition = 2;
                        }
                        setActionBarTitle(currentPosition);
                        drawerList.setItemChecked(currentPosition, true);
                    }
                }
        );

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MainActivity","onSaveInstanceState");
        outState.putInt("position", currentPosition);
        outState.putInt("materiaId",materiaId);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        Log.d("MainActivity","onPostCreate");
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //Called whenever we call invalidateOptionsMenu()
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
// If the drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        if(currentPosition == 1){
            //menu.findItem(R.id.action_edit).setVisible(false);
//            if(drawerOpen){
//                menu.findItem(R.id.action_edit).setVisible(false);
//            }else{
//                menu.findItem(R.id.action_edit).setVisible(true);
//            }
        }else{
            //menu.findItem(R.id.action_edit).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //Cambiar de fragmento con la posicion de la opcion seleccionada
    private void selectItem(int position){
        Log.d("MainActivity","selectItem");
        Fragment fragment=null;
        currentPosition = position;
        setActionBarTitle(position);

        switch(position) {
            case 0:
                fragment = new InicioFragment();
                break;
            case 1:
                fragment = new PerfilFragment();
                break;
            case 2:
                fragment = new InformacionFragment();
                break;
            default:
//                fragment = new TopFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("materiaSeleccionada",materiaId);
        fragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_content, fragment,"visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        //Set the action bar title
        setActionBarTitle(position);
        //Close drawer
        drawerLayout.closeDrawer(drawerList);

    }
    //itemClicked para Informacion materia
    /*@Override
    public void itemClicked(long id) {

//        Toast.makeText(MainActivity.this, "BLAH id: "+id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,InformacionActivity.class);
        intent.putExtra("materiaId",id);
        startActivity(intent);

    }
    @Override
    public void itemClickedPresentaciones(long id) {
        Intent intent = new Intent(MainActivity.this,PresentacionActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("materiaId",materiaId);
        startActivity(intent);
    }*/

    /*@Override
    public void itemClickedLibros(long id) {
        Uri uri = null;
        if(id == 0){
            uri = Uri.parse("https://www.dropbox.com/s/bc2g6iiee2g7twu/Advances%20in%20Sintering%20Science%20and%20Technology.pdf?dl=0");
        }
        if(id == 1){
            uri = Uri.parse("https://www.dropbox.com/s/xgdiq06tq7vrq5z/AN%20INTRODUCTION%20TO%20MATERIALS%20ENGINEERING%20AND%20SCIENCE.pdf?dl=0");
        }
        if(id == 2){
            uri = Uri.parse("https://www.dropbox.com/s/5e66c3db79rq1c5/Callister-fundamentals_of_materials_science_and_engineering_callister_5th.pdf?dl=0");
        }
        if(id == 3){
            uri = Uri.parse("https://www.dropbox.com/s/006tlyxocwexzmg/Ceramic-Materials-Science-and-Engineering.pdf?dl=0");
        }
        if(id == 4) {
            uri = Uri.parse("https://www.dropbox.com/s/p5chdled6c5hnbx/Ciencia%20e%20Ingenieria%20de%20los%20Materiales%20-%20Donald%20Askeland%20-%203edicion.pdf?dl=0");
        }
        if(id == 5) {
            uri = Uri.parse("https://www.dropbox.com/s/utwlled7p2lkwmc/Classic%20and%20Advanced%20Ceramics.pdf?dl=0");
        }
        if(id == 6) {
            uri = Uri.parse("https://www.dropbox.com/s/jept6bj4hliatdd/Libro%20Materiales.pdf?dl=0");
        }

        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    // called when video category is choosen
    @Override
    /*public void itemClickedVideosCategory(long id) {
        Intent intent = new Intent(MainActivity.this,VideosActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("materiaId",materiaId);
        startActivity(intent);
    }*/

    //Called when Spinner in home Fragment changes
    /**@Override
    public void homeFragmentItemClicked(int id) {
        materiaId = id;
        Toolbar toolbar = null;
        if(materiaId == 0){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Green)));
        }
        if(materiaId == 1){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Blue)));
        }
        if(materiaId == 2){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Orange)));
        }
        Log.d("MainActivity","materiaId:"+materiaId);
    }*/
    //Cambair el titulo del menu
    private void setActionBarTitle(int position) {
        String title;
        if (position == 0){
            title = getResources().getString(R.string.app_name);
            //getSupportActionBar().setIcon(R.drawable.book);

        } else {
            title = titles[position];
        }
        changeActionBarIcons(position);
        getSupportActionBar().setTitle(title);

    }

    //Cambiar Icono de menu
    private void changeActionBarIcons(int position){
        switch(position) {
            case 1://PERFIL
              //  getSupportActionBar().setIcon(R.drawable.person);
                break;
            case 2://Informacion
                //getSupportActionBar().setIcon(R.drawable.school);
                break;
            default:
        }
    }

    //Al crear el menu:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicial,menu);
        menuAux = menu;
//        item1.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    //Al seleccionar una opcion del Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       /* if(item.getItemId() == R.id.action_edit){
            //Iniciar Actividad Editar Perfil
            Intent intent = new Intent(MainActivity.this,PerfilEditarActivity.class);
            startActivity(intent);
        }*/
        return drawerToggle.onOptionsItemSelected(item);
    }
}
