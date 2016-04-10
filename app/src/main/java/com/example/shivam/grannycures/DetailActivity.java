package com.example.shivam.grannycures;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    String mTitle;
    String mRemedy;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = this.getIntent();
        int position = intent.getIntExtra("position", 0);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

//        scrollView.setBackgroundResource(Utility.bgID[position]);

        Drawable backGround = getResources().getDrawable(Utility.bgID[position]);

// setting the opacity (alpha)
        backGround.setAlpha(100);

        scrollView.setBackground(backGround);


        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        TextView textView = (TextView) findViewById(R.id.detail_text);

        DatabaseHelper myDB = new DatabaseHelper(this);
        mRemedy = myDB.getRemedyFromID(position + 1);
        mTitle = myDB.getTitleFromID(position + 1);

        imageView.setImageResource(Utility.iconId[position]);
        textView.setText(mRemedy);


        getSupportActionBar().setTitle(mTitle);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailactivity, menu);

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
        android.support.v7.widget.ShareActionProvider shareActionProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // If onLoadFinished happens before this, we can go ahead and set the share intent now.
        if (mRemedy != null) {
            shareActionProvider.setShareIntent(createShareForecastIntent());
        }
        return super.onCreateOptionsMenu(menu);
    }

    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Granny at help!\n\n" + mTitle + "\n\n" + mRemedy);
        return shareIntent;
    }
}
