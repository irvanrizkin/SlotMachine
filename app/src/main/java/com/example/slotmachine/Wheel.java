package com.example.slotmachine;

import android.widget.LinearLayout;

public class Wheel extends Thread{
    public LinearLayout fotoslot;

    interface wheellistener{
        void newImage(int img);
    }
    private static int[] img={R.drawable.slot1, R.drawable.slot2, R.drawable.slot3, R.drawable.slot4, R.drawable.slot5};
    public int currentIndex;
    private wheellistener wheellistener;
    private long frameDuration;
    private long startIn;
    private boolean isStarted;


    public void wheel (wheellistener wheellistener, long frameDuration, long startIn) {
        this.wheellistener = wheellistener;
        this.frameDuration = frameDuration;
        this.startIn = startIn;
        currentIndex = 0;
        isStarted = true;
    }
    public void nextImg(){
        currentIndex++;
        if (currentIndex==fotoslot.length) {
            currentIndex = 0;
        }
    }

    public void run() {
        try{
            Thread.sleep(startIn);
            }catch (InterruptedException e){

        }
        while(isStarted){
            try{
                Thread.sleep(frameDuration);

            }catch (InterruptedException e){
            }

            nextImg();

            if (wheellistener != null) {
                wheellistener.newImage(fotoslot[currentIndex]);
            }
        }
    }
    public void stopwheel(){
        isStarted=false;
    }
}

