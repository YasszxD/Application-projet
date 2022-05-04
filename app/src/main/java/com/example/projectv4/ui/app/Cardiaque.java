package com.example.projectv4.ui.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectv4.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Cardiaque extends androidx.fragment.app.Fragment {
        int d;
        private int progress = 0;
        ImageView img;
        Button btn = null;
        Button btn1 = null;
        TextView Txt = null;


        public Cardiaque(int contentLayoutId) {
            super(contentLayoutId);
            this.d = contentLayoutId;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(d, container, false);
            btn1 = v.findViewById(R.id.button_decr);
            btn = v.findViewById(R.id.button_incr);
            Txt = v.findViewById(R.id.text_view_progress);
            img = v.findViewById(R.id.image);
            Anim a = new Anim(img,progress);
            a.start();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (progress < 100) {
                        progress += 1;
                        updateProgressBar();
                        btn1.setEnabled(true);
                    }
                    if (progress == 100)
                        btn.setEnabled(false);
                    a.setVal(progress);

                }
            });
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (progress > 0) {
                        progress -= 1;
                        updateProgressBar();
                        btn.setEnabled(true);
                    }
                    if (progress == 0)
                        btn1.setEnabled(false);
                }
            });
            GraphView graph = v.findViewById(R.id.graph);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(13, 100),
                    new DataPoint(14, 80),
                    new DataPoint(15, 70),
                    new DataPoint(16, 90),
                    new DataPoint(17, 60)
            });
            graph.addSeries(series);
            return v;
        }

        private void updateProgressBar() {
            Txt.setText(Integer.toString(progress));
        }
}
