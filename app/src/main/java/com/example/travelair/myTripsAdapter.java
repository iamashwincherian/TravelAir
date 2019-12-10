package com.example.travelair;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class myTripsAdapter extends RecyclerView.Adapter<myTripsAdapter.RecyclerViewHolder> {

    private ArrayList name,from,to,date,time,cclass;

    public myTripsAdapter(ArrayList name,ArrayList from,ArrayList to,ArrayList date,ArrayList time,ArrayList cclass){
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.cclass = cclass;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.my_trip_item, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

        String FlightName = (String) name.get(i);
        String FlightFrom = (String) from.get(i);
        String FlightTo = (String) to.get(i);
        String FlightDate = (String) date.get(i);
        String FlightTime = (String) time.get(i);
        String FlightCclass = (String) cclass.get(i);

        recyclerViewHolder.flightNameText.setText(FlightName);
        recyclerViewHolder.fromText.setText(FlightFrom);
        recyclerViewHolder.toText.setText(FlightTo);
        recyclerViewHolder.dateText.setText(FlightDate);
        recyclerViewHolder.timeText.setText(FlightTime);
        recyclerViewHolder.cclassText.setText(FlightCclass);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView flightNameText, fromText, toText, dateText, timeText, cclassText;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            flightNameText = (TextView) itemView.findViewById(R.id.flightNameText1);
            fromText = (TextView) itemView.findViewById(R.id.fromText);
            toText = (TextView) itemView.findViewById(R.id.toText);
            dateText = (TextView) itemView.findViewById(R.id.dateText);
            timeText = (TextView) itemView.findViewById(R.id.timeText);
            cclassText = (TextView) itemView.findViewById(R.id.cclassText);

        }
    }
}
