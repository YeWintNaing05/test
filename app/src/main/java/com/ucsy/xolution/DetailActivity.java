package com.ucsy.xolution;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class DetailActivity extends AppCompatActivity {


    TextView name, about;
    ListView list;
    ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getResources().getString(R.string.app_name));


        Bundle b = getIntent().getExtras();

        String n = b.getString("name");
        String[] area = b.getStringArray("area");
        String a = b.getString("about");
        int image = getIntent().getExtras().getInt("image");


        ExpandableTextView expTv1 = (ExpandableTextView) findViewById(R.id.expand_root)
                .findViewById(R.id.expand_description);

        expTv1.setText(a);

        name = (TextView) findViewById(R.id.name_l);
        //   about = (TextView) findViewById(R.id.about);

        list = (ListView) findViewById(R.id.area_law);


        i = (ImageView) findViewById(R.id.item_image);

        i.setImageResource(image);

        name.setText(n);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, area);

        list.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    supportFinishAfterTransition();
                } else {
                    onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
