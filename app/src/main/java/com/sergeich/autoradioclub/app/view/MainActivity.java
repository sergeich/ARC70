package com.sergeich.autoradioclub.app.view;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sergeich.autoradioclub.R;
import com.sergeich.autoradioclub.messages.view.MessageListFragment;

import io.reist.visum.view.VisumFragment;
import io.reist.visum.view.VisumFragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FragmentManager.OnBackStackChangedListener,
        BaseFragment.FragmentController {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getFragmentManager();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        drawerToggle.setToolbarNavigationClickListener(v -> fragmentManager.popBackStackImmediate());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_journal);

        fragmentManager.addOnBackStackChangedListener(this);

        showFragment(new MessageListFragment(), false);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragmentManager.getBackStackEntryCount() > 1) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_journal) {
            showFragment(new MessageListFragment(), true, true);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void showFragment(VisumFragment fragment, boolean remove) {
        VisumFragmentManager.showFragment(getFragmentManager(),
                fragment,
                R.id.fragment_container,
                remove,
                false);
    }

    private void showFragment(VisumFragment fragment, boolean remove, boolean popBackStack) {
        VisumFragmentManager.showFragment(getFragmentManager(),
                fragment,
                R.id.fragment_container,
                remove,
                popBackStack);
    }

    @Override
    public void onBackStackChanged() {
        boolean showBurger = fragmentManager.getBackStackEntryCount() == 1;
        drawerToggle.setDrawerIndicatorEnabled(showBurger);
        getSupportActionBar().setDisplayHomeAsUpEnabled(!showBurger);
        drawerToggle.syncState();
    }
}
