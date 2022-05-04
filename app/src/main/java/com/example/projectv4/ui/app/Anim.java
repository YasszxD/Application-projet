package com.example.projectv4.ui.app;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class Anim extends Thread {
    ScaleAnimation fade_in = new ScaleAnimation(1.2f, 1f, 1.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    ScaleAnimation fade_out = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    View v;
    int progress;
    int time=800;

    public void setVal(int p){
        this.progress=p;
    }
    public Anim(View v, int progress){
        this.v=v;
        this.progress=progress;
    }
    @Override
    public void run() {
        super.run();
        fade_in.setDuration(300);
        fade_in.setFillAfter(true);
        fade_out.setDuration(300);
        fade_out.setFillAfter(true);
        while (true) {
            v.startAnimation(fade_in);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            v.startAnimation(fade_out);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (progress<20)
                time=800;
            else if (progress<40)
                time=700;
            else if (progress<60)
                time=500;
            else if (progress<80)
                time=400;
            else
                time=300;
        }
    }
}
