package com.example.kozaczukkrawczunchess;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchup);
        String user1 = getIntent().getStringExtra("user1");
        String user2 = getIntent().getStringExtra("user1");
        fetchMatchup(user1, user2);
    }


    private void fetchMatchup(String user1, String user2) {
        MatchupAPI matchupAPI = RetrofitInstance.getRetrofitInstance().create(MatchupAPI.class);

        TextView user1Username = (TextView) findViewById(R.id.matchup_user2_username);
        TextView user2Username = (TextView) findViewById(R.id.matchup_user2_username);
        TextView gamesPlayed = (TextView)findViewById(R.id.matchup_games_played);
        TextView user1Wins= (TextView) findViewById(R.id.matchup_user1_wins);
        TextView user2Wins= (TextView) findViewById(R.id.matchup_user2_wins);

        Call<ProfilesList> matchupAPICall = matchupAPI.getMatchup(user1,user2);

        matchupAPICall.enqueue(new Callback<ProfilesList>() {
            @Override
            public void onResponse(Call<ProfilesList> call, Response<ProfilesList> response) {
                //usernameTextView.setText(response.body().getUsername());
                Profile user1Profile;
                Profile user2Profile;
                user1Profile = response.body().getProfiles().get(0);
                user2Profile = response.body().getProfiles().get(1);
                user1Username.setText(user1Profile.getUsername());
                user2Username.setText(user2Profile.getUsername());
                user1Username.setText(user1Profile.getUsername());
                /*response.body();
                List<Profile> profileList = null;
                profileList.add(response.body());
                setupProfileListView(profileList);*/
            }

            @Override
            public void onFailure(Call<ProfilesList> call, Throwable t) {
                Snackbar.make(findViewById(R.id.matchup_view), "Something went wrong!", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
