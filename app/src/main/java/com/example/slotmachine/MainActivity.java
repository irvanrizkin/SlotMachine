package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView pesan;
    private ImageView img1,img2,img3;
    private Button Buttonmulai;
    private boolean isPlay;
    AsyncSlotTask img11, img21, img31;
    private static int[] img={R.drawable.slot1, R.drawable.slot2, R.drawable.slot3, R.drawable.slot4, R.drawable.slot5, R.drawable.slotbar};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.gambar1);
        img2 = findViewById(R.id.gambar2);
        img3 = findViewById(R.id.gambar3);
        img1.setImageResource(R.drawable.slotbar);
        img2.setImageResource(R.drawable.slotbar);
        img3.setImageResource(R.drawable.slotbar);
        Buttonmulai = findViewById(R.id.buttonmulai);
        Buttonmulai.setOnClickListener(this);
        pesan = findViewById(R.id.pesan);

    }
    @Override
    public void onClick(View v) {
        if (v.getId()==Buttonmulai.getId()){
            if(!isPlay){
                img11 = new AsyncSlotTask();
                img21 = new AsyncSlotTask();
                img31 = new AsyncSlotTask();

                img11.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, img1 );
                img21.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, img2 );
                img31.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, img3 );

                Buttonmulai.setText("STOP");
                isPlay = !isPlay;
            }else {
                img11._Play = false;
                img21._Play = false;
                img31._Play = false;

            if (img11.getImageId() == img21.getImageId() && img21.getImageId() == img31.getImageId()) {
                Toast.makeText(getApplicationContext(), "Anda Menang Telak", Toast.LENGTH_SHORT).show();
            }else if (img11.getImageId() == img21.getImageId() || img21.getImageId() == img31.getImageId() || img11.getImageId() == img31.getImageId()) {
                Toast.makeText(getApplicationContext(), "Anda Menang", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Anda Kalah", Toast.LENGTH_SHORT).show();
            }

                Buttonmulai.setText("PLAY");
                isPlay = !isPlay;
            }
        }

    }

    private class AsyncSlotTask extends AsyncTask<ImageView, Integer, Boolean> {
        ImageView _Slotimg;
        Random random = new Random();
        public boolean _Play =true;

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        public AsyncSlotTask(){
            _Play = true;
        }

        public Object getImageId() {
            return _Slotimg.getDrawable().getConstantState();
        }

        @Override
        protected Boolean doInBackground(ImageView... imgs) {
            _Slotimg = imgs[0];
            int a =0;
            while (_Play){
                int i = random.nextInt(6);
                publishProgress(i);

                try {
                    Thread.sleep(random.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return !_Play;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            _Slotimg.setImageResource(img[values[0]]);
        }
    }

}