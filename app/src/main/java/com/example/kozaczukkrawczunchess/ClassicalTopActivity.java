package com.example.kozaczukkrawczunchess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassicalTopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classical_top);
        fetchClassicalTop();
    }

    private class ClassicalTopHolder extends RecyclerView.ViewHolder{
        private TextView usernameTextView;
        private TextView idTextView;
        private Profile classicalTopProfileObject;
        private TextView rating;
        public ClassicalTopHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.classical_top_recycler_view_item, parent, false));
            usernameTextView = itemView.findViewById(R.id.classical_top_username);
            idTextView = itemView.findViewById(R.id.classical_top_id);
            rating = itemView.findViewById(R.id.classical_top_rating);
        }

        public void bind(Profile profile) {
            usernameTextView.setText(profile.getUsername());
            idTextView.setText(profile.getUserID());
            classicalTopProfileObject = profile;
            rating.setText(profile.getPerfs().getClassical().getRating());
        }
    }
    private class ClassicalTopAdapter extends RecyclerView.Adapter<ClassicalTopActivity.ClassicalTopHolder>{
        private List<Profile> profiles;

        @NonNull
        @Override
        public ClassicalTopActivity.ClassicalTopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            return new ClassicalTopHolder(getLayoutInflater(),parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ClassicalTopActivity.ClassicalTopHolder holder, int position){
            if(profiles !=null){
                Profile profile = profiles.get(position);
                holder.bind(profile);
            } else {
                Log.d("ClassicalTopActivity","No one in top 10");
            }
        }

        @Override
        public int getItemCount(){
            if(profiles != null){
                return profiles.size();
            } else{
                return 0;
            }
        }
        void setClassicalTopList(List<Profile> profiles){
            this.profiles = profiles;
            notifyDataSetChanged();
        }
    }

    private void fetchClassicalTop(){
        ClassicalTopListAPI classicalTopListAPI = RetrofitInstance.getRetrofitInstance().create(ClassicalTopListAPI.class);
        TextView topUserTextView = (TextView)findViewById(R.id.classical_top_id_test);

        Call<ClassicalTopList> classicalTopListAPICall = classicalTopListAPI.getTopClassical();

        classicalTopListAPICall.enqueue(new Callback<ClassicalTopList>() {
            @Override
            public void onResponse(Call<ClassicalTopList> call, Response<ClassicalTopList> response) {
                //setupClassicalTopList(Arrays.asList(response.body()));
                topUserTextView.setText(response.body().profiles[0].getUserID());
            }

            @Override
            public void onFailure(Call<ClassicalTopList> call, Throwable t) {
                Snackbar.make(findViewById(R.id.classical_top_view),"Something went wrong!",Snackbar.LENGTH_LONG).show();
            }
        });

    }
    private void setupClassicalTopList(List<Profile> profiles){
        RecyclerView recyclerView = findViewById(R.id.classical_top_recycler_view);
        final ClassicalTopActivity.ClassicalTopAdapter adapter = new ClassicalTopActivity.ClassicalTopAdapter();
        adapter.setClassicalTopList(profiles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
