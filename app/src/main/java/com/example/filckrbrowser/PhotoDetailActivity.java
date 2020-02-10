package com.example.filckrbrowser;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        activateToolbar(true);
        photoDetails();
    }

    // Full details in image
    public void photoDetails(){
        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        if(photo != null){
            TextView phototitle = (TextView) findViewById(R.id.photo_title);
            Resources resources = getResources();
            String text = resources.getString(R.string.photo_title_text, photo.getTitle());
            phototitle.setText(text);

            TextView phototag = (TextView) findViewById(R.id.photo_tags);
            phototag.setText(resources.getString(R.string.photo_tags_text, photo.getTags()));

            TextView photoauthor = (TextView) findViewById(R.id.photo_author);
            photoauthor.setText(photo.getAuthor());

            ImageView photoimage = (ImageView) findViewById(R.id.photo_image);
            Picasso.with(this).load(photo.getLink())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(photoimage);
        }
    }
}
