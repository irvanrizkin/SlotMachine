package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView pesan;
    private ImageView img1,img2,img3;
    private Button Buttonmulai;
    private Wheel wheel1,wheel2,wheel3;
    private boolean isStarted;

    public static final Random random = new Random();
    public static long randomLong(Long lower, long upper) {
        return lower + (long) (random.nextDouble() * (upper-lower));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.gambar1);
        img2 = findViewById(R.id.gambar2);
        img3 = findViewById(R.id.gambar3);
        Buttonmulai = findViewById(R.id.buttonmulai);
        pesan = findViewById(R.id.pesan);
        Buttonmulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted){
                    wheel1.stopwheel();
                    wheel2.stopwheel();
                    wheel3.stopwheel();
                    if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                        pesan.setText("Menang Telak");
                    }else if (wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == wheel3.currentIndex) {
                        pesan.setText("Menang");
                    }else {
                        pesan.setText("Kalah");
                    }
                    Buttonmulai.setText("Start");
                    isStarted = false;
                }else{
                    wheel1 = new Wheel(new Wheel.wheellistener(){
                        @Override
                        public void newImage(int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img1.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(0, 200));

                    wheel1.start();

                    wheel2 = new Wheel(new Wheel.wheellistener(){
                        @Override
                        public void newImage(int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img2.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(0, 200));

                    wheel2.start();

                    wheel3 = new Wheel(new Wheel.wheellistener(){
                        @Override
                        public void newImage(int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img3.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(0, 200));

                    wheel3.start();

                    Buttonmulai.setText("Stop");
                    pesan.setText("");
                    isStarted=true;
                }
            }
        });
    }
}