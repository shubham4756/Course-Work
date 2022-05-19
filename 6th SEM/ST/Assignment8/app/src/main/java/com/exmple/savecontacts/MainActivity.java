package com.exmple.savecontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.exmple.savecontacts.fragments.ContactEntryFragment;
import com.exmple.savecontacts.fragments.DeleteFragment;
import com.exmple.savecontacts.fragments.SerachFragment;
import com.exmple.savecontacts.fragments.UpdateFragment;
import com.exmple.savecontacts.fragments.ViewContactFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawer,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ContactEntryFragment()).commit();
            navigationView.setCheckedItem(R.id.contactEntry);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contactEntry:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ContactEntryFragment()).commit();
                break;

            case R.id.viewEntries:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ViewContactFragment()).commit();
                break;

            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SerachFragment()).commit();
                break;

            case R.id.updateEntry:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UpdateFragment()).commit();
                break;

            case R.id.deleteEntry:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DeleteFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}