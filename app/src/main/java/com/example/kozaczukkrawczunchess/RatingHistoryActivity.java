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

public class RatingHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_history);
        String username = getIntent().getStringExtra("username");
        this.setTitle(username+getString(R.string.title_ranking_history));
        fetchRatingHistory(username);
    }

    private class RatingHolder extends RecyclerView.ViewHolder{
        private TextView ratingNameTextView;
        private TextView ratingNumberTextView;


        public RatingHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.rating_history_recycler_view_item, parent, false));
            ratingNameTextView = itemView.findViewById(R.id.rating_name);
            ratingNumberTextView = itemView.findViewById(R.id.rating_number);
        }

        public void bind(RatingHistory ratingHistory) {
            ratingNameTextView.setText(ratingHistory.getName());

            String pointsString = "";
            List<List<Integer>> points = ratingHistory.getPoints();
            if(points.size()>0) {
                int counter = 0;
                for (List<Integer> l : points) {
                    for (Integer p : l) {
                        if (counter == 1) p++;
                        pointsString += p.toString();
                        if (counter == 2) {
                            pointsString += ": ";
                            counter -= 4;
                        } else pointsString += ",";
                        counter++;
                    }
                    pointsString += "\n";
                }
            }
            else pointsString = getString(R.string.points_no_history);
            ratingNumberTextView.setText(pointsString);
        }
    }
    private class RatingAdapter extends RecyclerView.Adapter<RatingHistoryActivity.RatingHolder>{
        private List<RatingHistory> ratings;

        @NonNull
        @Override
        public RatingHistoryActivity.RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            return new RatingHistoryActivity.RatingHolder(getLayoutInflater(),parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RatingHistoryActivity.RatingHolder holder, int position){
            if(ratings !=null){
                RatingHistory rating = ratings.get(position);
                holder.bind(rating);
            } else {
                Log.d("RatingHistoryActivity","No rating history");
            }
        }

        @Override
        public int getItemCount(){
            if(ratings != null){
                return ratings.size();
            } else{
                return 0;
            }
        }
        void setRatings(List<RatingHistory> ratings){
            this.ratings = ratings;
            notifyDataSetChanged();
        }
    }

    private void fetchRatingHistory(String username){
        RatingHistoryAPI ratingAPI = RetrofitInstance.getRetrofitInstance().create(RatingHistoryAPI.class);


        Call<RatingHistory[]> ratingAPICall = ratingAPI.getRatingHistory(username);

        ratingAPICall.enqueue(new Callback<RatingHistory[]>() {
            @Override
            public void onResponse(Call<RatingHistory[]> call, Response<RatingHistory[]> response) {
                setupRatingsListView(Arrays.asList(response.body()));
            }

            @Override
            public void onFailure(Call<RatingHistory[]> call, Throwable t) {
                Snackbar.make(findViewById(R.id.streamers_view),"Something went wrong!",Snackbar.LENGTH_LONG).show();
            }
        });

    }
    private void setupRatingsListView(List<RatingHistory> ratings){
        RecyclerView recyclerView = findViewById(R.id.rating_history_recycler_view);
        final RatingHistoryActivity.RatingAdapter adapter = new RatingHistoryActivity.RatingAdapter();
        adapter.setRatings(ratings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
