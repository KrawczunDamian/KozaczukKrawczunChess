package com.example.kozaczukkrawczunchess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText profile_username_input;
    EditText rating_username_input;
    /*EditText user1_input;
    EditText user2_input;*/

    SensorManager sensorManager;
    Sensor lightSensor;
    SensorEventListener lightEventListener;
    float maxValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        maxValue = lightSensor.getMaximumRange();
        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float value = event.values[0];
                int newValue = (int) (255f * value / maxValue);
                if(newValue<128) newValue=128;
                findViewById(R.id.main_activity_linear_layout).setBackgroundColor(Color.rgb(newValue,newValue,newValue));
                findViewById(R.id.main_view).setBackgroundColor(Color.rgb(newValue,newValue,newValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


        profile_username_input = (EditText) findViewById(R.id.profile_username_input);
        rating_username_input = (EditText) findViewById(R.id.rating_username_input);
        /*user1_input = (EditText) findViewById(R.id.user1_matchup_input);
        user2_input = (EditText) findViewById(R.id.user2_matchup_input);
        */

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

        /*final Button classicalLeaderboardButton = (Button)findViewById(R.id.go_to_classical_leaderboard);
        classicalLeaderboardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClassicalTopActivity.class);
                startActivity(intent);
            }
        });*/
        /*final Button matchupButton = (Button)findViewById(R.id.go_to_matchup);
        matchupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user1 =user1_input.getText().toString();
                String user2 =user2_input.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MatchupActivity.class);
                intent.putExtra("user1",user1);
                intent.putExtra("user2",user2);
                startActivity(intent);
            }
        });*/
        final Button ratingHistoryButton = (Button)findViewById(R.id.go_to_rating_history);
        ratingHistoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username =rating_username_input.getText().toString();
                Intent intent = new Intent(getApplicationContext(), RatingHistoryActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }
    public void goToYoutube (View view) {
        Uri uriUrl = Uri.parse(getString(R.string.lichess_youtube_channel));
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW,uriUrl);
        startActivity(launchBrowser);
    }
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
    }
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
}