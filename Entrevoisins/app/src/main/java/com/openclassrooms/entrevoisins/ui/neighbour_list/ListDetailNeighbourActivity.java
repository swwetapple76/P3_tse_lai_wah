package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;

public class ListDetailNeighbourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_neighbour);
        // 4.	We modify the List DetailNeighbourActivity
        Intent intent = getIntent();
        // 4.a  crate String name for Intent
        String name = intent.getStringExtra("name");
        TextView personName = findViewById(R.id.person_name);
        personName.setText(name);

        String avatarImage = intent.getStringExtra("avatar");
        ImageView imageView = findViewById(R.id.detail_avatar);
        Glide.with(this)
                .load(avatarImage)
                .apply(RequestOptions.noTransformation())
                .into(imageView);


        String address = intent.getStringExtra("address");
        TextView addressNeighbour = findViewById(R.id.address);
        addressNeighbour.setText(address);

        String phone = intent.getStringExtra("phone");
        TextView phoneNumber = findViewById(R.id.phone);
        phoneNumber.setText(phone);

        String webLink = intent.getStringExtra("webLink");
        TextView faceBook = findViewById(R.id.webLink);
        faceBook.setText(webLink);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(name);


    }
}
