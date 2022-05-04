package com.example.projectv4.ui.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.projectv4.R;

import java.util.List;

public class AthletesAdapter extends ArrayAdapter<String> {
    public AthletesAdapter(@NonNull Context context , @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.athlete_item, parent, false);
        }

        String currentAthlete = getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        nameTextView.setText(currentAthlete);

        return listItemView;
    }
}
