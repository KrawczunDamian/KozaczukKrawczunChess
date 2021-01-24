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

    private class ProfileHolder extends RecyclerView.ViewHolder{
        private TextView usernameTextView;
        private TextView idTextView;
        private Profile profileObject;

        public ProfileHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.profile_info_recycler_view_item, parent, false));
            usernameTextView = itemView.findViewById(R.id.profile_username);
            idTextView = itemView.findViewById(R.id.profile_id);
        }

        public void bind(Profile profile) {
            usernameTextView.setText(profile.getUsername());
            idTextView.setText(profile.getUserID());
            profileObject = profile;
        }
    }
    private class ProfileAdapter extends RecyclerView.Adapter<ProfileInfoActivity.ProfileHolder>{
        private List<Profile> profiles;

        @NonNull
        @Override
        public ProfileInfoActivity.ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            return new ProfileHolder(getLayoutInflater(),parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProfileInfoActivity.ProfileHolder holder, int position){
            if(profiles !=null){
                Profile profile = profiles.get(position);
                holder.bind(profile);
            } else {
                Log.d("MainActivity","No profiles");
            }
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        void setProfiles(List<Profile> profiles){
            this.profiles = profiles;
            notifyDataSetChanged();
        }
    }

    private void fetchProfileData(String query){
        ProfileAPI profileAPI = RetrofitInstance.getRetrofitInstance().create(ProfileAPI.class);
        TextView usernameTextView = (TextView)findViewById(R.id.profile_username);
        TextView idTextView = (TextView)findViewById(R.id.profile_id);
        Call<Profile> profileApiCall = profileAPI.findProfile(query);

        profileApiCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                usernameTextView.setText(response.body().getUsername());
                idTextView.setText(response.body().getUserID());
                /*response.body();
                List<Profile> profileList = null;
                profileList.add(response.body());
                setupProfileListView(profileList);*/
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Snackbar.make(findViewById(R.id.profile_view),"Something went wrong!",Snackbar.LENGTH_LONG).show();
            }
        });

    }
    private void setupProfileListView(List<Profile> profiles){
        RecyclerView recyclerView = findViewById(R.id.profile_info_recycler_view);
        final ProfileInfoActivity.ProfileAdapter adapter = new ProfileInfoActivity.ProfileAdapter();
        adapter.setProfiles(profiles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public boolean checkNullOrEmpty(String text){
        return text != null && !TextUtils.isEmpty(text);
    }
}
