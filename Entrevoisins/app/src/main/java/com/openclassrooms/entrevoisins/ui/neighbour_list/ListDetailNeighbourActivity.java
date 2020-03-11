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
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

public class ListDetailNeighbourActivity extends AppCompatActivity {
    // FAV 1. Define the variable
    boolean favoriteNeighbour;
    private NeighbourApiService mApiServices;
    Neighbour neighbour;
    FloatingActionButton mFavoritesButton;
    int idNeighbour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_neighbour);
        //set toolbar
        setSupportActionBar( findViewById(R.id.widget_toolbar));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //FAV 2. apply the ApiServices
        mApiServices = DI.getNeighbourApiService();

        // 4. modify the List DetailNeighbourActivity
        /*Intent intent = getIntent();
        // 4.a  crate String name for Intent
        String name = intent.getStringExtra("name");
        TextView personName = findViewById(R.id.person_name);
        personName.setText(name);*/

        //use Gson to replace Intent
        // Gson (by Google) is a Java library that can be used to convert a Java object into JSON string. Also, it can used to convert the JSON string into equivalent java object.
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("Neighbour");
        neighbour = gson.fromJson(strObj, Neighbour.class);


        String name = neighbour.getName();
        String neighbourAvatar = neighbour.getAvatarUrl();
        favoriteNeighbour=neighbour.isFavorites();

          //Name Neighbour
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(name);
        // Name Description
        TextView personName = findViewById(R.id.person_name);
        personName.setText(name);

        // Avatar
        ImageView detailAvatar = findViewById(R.id.detail_avatar);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground);
        Glide.with(this)
                .load(neighbourAvatar)
                .into(detailAvatar);

        // FAV 3.
       mFavoritesButton = findViewById(R.id.fab_favorites);
        if (favoriteNeighbour) {
            mFavoritesButton.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
        }else
            mFavoritesButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp));

        //15.FAV Modify ListDetaiActivity pour le click sur le button favori
        mFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!favoriteNeighbour){
                    mFavoritesButton.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
                    mApiServices.getNeighbourById(neighbour.getId()).setFavorites(true);
                    favoriteNeighbour=true;
                }else{
                    mFavoritesButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp));
                    mApiServices.getNeighbourById(neighbour.getId()).setFavorites(false);
                    favoriteNeighbour=false;
                }

            }
        });

    }
}
