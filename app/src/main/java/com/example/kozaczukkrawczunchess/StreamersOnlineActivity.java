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

public class StreamersOnlineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamers_online);
        fetchStreamers();
    }

    private class StreamerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView usernameTextView;
        private TextView idTextView;
        private Streamer streamerObject;

        public StreamerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.streamers_online_recycler_view_item, parent, false));
            itemView.setOnClickListener(this);
            usernameTextView = itemView.findViewById(R.id.streamer_username);
            idTextView = itemView.findViewById(R.id.streamer_id);
        }

        public void bind(Streamer streamer) {
            usernameTextView.setText(streamer.getUsername());
            idTextView.setText(streamer.getUserID());
            streamerObject = streamer;
        }
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(), ProfileInfoActivity.class);
            intent.putExtra("username", streamerObject.getUsername());
            startActivity(intent);
        }
    }
    private class StreamerAdapter extends RecyclerView.Adapter<StreamersOnlineActivity.StreamerHolder>{
        private List<Streamer> streamers;

        @NonNull
        @Override
        public StreamersOnlineActivity.StreamerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            return new StreamerHolder(getLayoutInflater(),parent);
        }

        @Override
        public void onBindViewHolder(@NonNull StreamersOnlineActivity.StreamerHolder holder, int position){
            if(streamers !=null){
                Streamer streamer = streamers.get(position);
                holder.bind(streamer);
            } else {
                Log.d("StreamersOnline","No one online");
            }
        }

        @Override
        public int getItemCount(){
            if(streamers != null){
                return streamers.size();
            } else{
                return 0;
            }
        }
        void setStreamers(List<Streamer> streamers){
            this.streamers = streamers;
            notifyDataSetChanged();
        }
    }

    private void fetchStreamers(){
        StreamerAPI streamerAPI = RetrofitInstance.getRetrofitInstance().create(StreamerAPI.class);


        Call<Streamer[]> streamerApiCall = streamerAPI.getStreamers();

        streamerApiCall.enqueue(new Callback<Streamer[]>() {
            @Override
            public void onResponse(Call<Streamer[]> call, Response<Streamer[]> response) {
                setupStreamersListView(Arrays.asList(response.body()));
            }

            @Override
            public void onFailure(Call<Streamer[]> call, Throwable t) {
                Snackbar.make(findViewById(R.id.streamers_view),"Something went wrong!",Snackbar.LENGTH_LONG).show();
            }
        });

    }
    private void setupStreamersListView(List<Streamer> streamers){
        RecyclerView recyclerView = findViewById(R.id.streamers_online_recycler_view);
        final StreamersOnlineActivity.StreamerAdapter adapter = new StreamersOnlineActivity.StreamerAdapter();
        adapter.setStreamers(streamers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
