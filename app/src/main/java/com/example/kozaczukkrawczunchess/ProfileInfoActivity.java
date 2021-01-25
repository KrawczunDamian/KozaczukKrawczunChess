package com.example.kozaczukkrawczunchess;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        String username = getIntent().getStringExtra("username");
        fetchProfileData(username);
    }


    private void fetchProfileData(String query) {
        ProfileAPI profileAPI = RetrofitInstance.getRetrofitInstance().create(ProfileAPI.class);
        TextView usernameTextView = (TextView) findViewById(R.id.profile_username);
        TextView idTextView = (TextView) findViewById(R.id.profile_id);
        TextView classicalRatingTextView = (TextView)findViewById(R.id.profile_classical_rating);
        TextView isOnlineView = (TextView) findViewById(R.id.profile_online);
        Call<Profile> profileApiCall = profileAPI.findProfile(query);

        profileApiCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                usernameTextView.setText(response.body().getUsername());
                idTextView.setText(response.body().getUserID());
                classicalRatingTextView.setText(response.body().getPerfs().getClassical().getRating().toString());
                isOnlineView.setText(response.body().getOnline());
                /*response.body();
                List<Profile> profileList = null;
                profileList.add(response.body());
                setupProfileListView(profileList);*/
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Snackbar.make(findViewById(R.id.profile_view), "Something went wrong!", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
