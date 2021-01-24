package com.example.kozaczukkrawczunchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText profile_username_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile_username_input = (EditText) findViewById(R.id.profile_username_input);

        final Button profileInfoButton = (Button)findViewById(R.id.go_to_profile);
        profileInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = profile_username_input.getText().toString();
                Intent intent = new Intent(getApplicationContext(), ProfileInfoActivity.class);
                intent.putExtra("username",str);//profile_username_input.getText().toString()
                startActivity(intent);
            }
        });

        final Button streamersOnlineButton = (Button)findViewById(R.id.go_to_streamers);
        streamersOnlineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StreamersOnlineActivity.class);
                startActivity(intent);
            }
        });

        final Button classicalLeaderboardButton = (Button)findViewById(R.id.go_to_classical_leaderboard);
        classicalLeaderboardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClassicalTopActivity.class);
                startActivity(intent);
            }
        });

    }
    public void goToYoutube (View view) {
        Uri uriUrl = Uri.parse(getString(R.string.lichess_youtube_channel));
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW,uriUrl);
        startActivity(launchBrowser);
    }

}