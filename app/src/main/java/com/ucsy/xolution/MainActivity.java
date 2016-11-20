package com.ucsy.xolution;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import adapter.HomeAdapter;
import model.Lawyer;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    RecyclerView recyclerView;
    HomeAdapter adapter;
    List<Lawyer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = getData();
        adapter = new HomeAdapter(this, list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    public List<Lawyer> getData() {

        List<Lawyer> data = new ArrayList<>();
        String name[] = {"Ye Wint Naing", "Myo Thu Ko", "Lwin Moe Thu", "Hein Min Htet", "Ye Wint Naing", "Myo Thu Ko",
                "Lwin Moe Thu", "Hein Min Htet", "Ye Wint Naing", "Myo Thu Ko"};

        int[] image = {R.drawable.l3, R.drawable.l2, R.drawable.l3,
                R.drawable.l3, R.drawable.l2, R.drawable.l3, R.drawable.l3, R.drawable.l2, R.drawable.l3, R.drawable.l3};

        String[] area_law = {"Business Transactions", "Criminal Law", "Management Labor Law", "Minimun Wage Law", "Business Law"};

        for (int i = 0; i < name.length; i++) {
            Lawyer l = new Lawyer();
            l.image = image[i];
            l.name = name[i];
            l.area_law = area_law;
            l.about = "Notepad is a simple text editor for Microsoft Windows and a basic text-editing program which enables computer users to create documents. It was first released as a mouse-based DOS program in 1983," +
                    " and has been included in all versions of Microsoft Windows since Windows 1.0 in 1985.";
            data.add(l);
        }

        return data;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);



        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
