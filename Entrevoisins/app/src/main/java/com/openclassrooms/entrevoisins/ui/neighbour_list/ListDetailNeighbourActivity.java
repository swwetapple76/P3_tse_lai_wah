package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;

import java.util.Objects;

public class ListDetailNeighbourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_neighbour);
        //set toolbar
        setSupportActionBar( findViewById(R.id.widget_toolbar));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // 4.	We modify the List DetailNeighbourActivity
        Intent intent = getIntent();
        // 4.a  crate String name for Intent
        String name = intent.getStringExtra("name");
        TextView personName = findViewById(R.id.person_name);
        personName.setText(name);

        //Staring avatarImage
        String avatarImage = intent.getStringExtra("avatar");
        ImageView imageView = findViewById(R.id.detail_avatar);

               // loading the image avatar
        Glide.with(this)
                .load(avatarImage)
                .into(imageView);
        FloatingActionButton fab = findViewById(R.id.fab_favorites);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(name);


    }
}
