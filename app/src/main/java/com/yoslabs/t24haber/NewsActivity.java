package com.yoslabs.t24haber;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yoslabs.t24haber.adapter.DrawerAdapter;
import com.yoslabs.t24haber.fragments.MainNewsFragment;
import com.yoslabs.t24haber.fragments.NewsFragment;
import com.yoslabs.t24haber.models.NewsCats;
import com.yoslabs.t24haber.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView dataListView;
    private DrawerLayout drawerLayout = null;
    private ArrayList<NewsCats.NewsCat> mNewsCats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        dataListView = (ListView) findViewById(R.id.dataListView);

        /** start transaction for main news fragment **/
        startTransactionFromActivity(new MainNewsFragment(), false, true);

    }


    public void startTransactionFromActivity(Fragment fragment, boolean addToBackStack, boolean isReplaceOrAdd) {
        /** start some transaction **/
        Navigator navigator = new Navigator(getSupportFragmentManager(), this);
        navigator.startFragmentTransaction(fragment, addToBackStack, isReplaceOrAdd);
    }

    /** set drawer layout **/
    public void setDrawer(Toolbar toolbar) {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    /** set drawer layouts data **/
    public void setDrawerData(ArrayList<NewsCats.NewsCat> newsCats) {
        mNewsCats = newsCats;
        dataListView.setAdapter(new DrawerAdapter(this, R.layout.drawer_item, newsCats));
        dataListView.setOnItemClickListener(this);
    }

    /** up touched **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** when pressed back **/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    /** item click on drawers layout **/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        openNewsPage(mNewsCats.get(position).getId(), position, mNewsCats.get(position).getName());

        if(drawerLayout != null) drawerLayout.closeDrawer(dataListView);

    }

    /** check item in drawer layyout **/
    public void setItemChecked(int position) {
        dataListView.setItemChecked(position, true);
    }

    /** open news page **/
    private void openNewsPage(String catId, int position, String name) {
        Fragment newsFragment = new MainNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("catId", catId);
        bundle.putInt("position", position);
        bundle.putString("name", name);
        newsFragment.setArguments(bundle);
        startTransactionFromActivity(newsFragment, false, true);
    }

}
