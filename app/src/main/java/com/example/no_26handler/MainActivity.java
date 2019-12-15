package com.example.no_26handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=(TextView) findViewById(R.id.text1);
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                textView.setText(msg.arg1+"");
            }
        };
        final  Runnable mywork=new Runnable() {
            @Override
            public void run() {
                int progress=0;
                while(progress<=100){
                    Message message=new Message();
                    message.arg1=progress;
                    handler.sendMessage(message);
                    progress+=10;
                    try{
                        Thread.sleep(1000);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                Message msg=handler.obtainMessage();
                msg.arg1=-1;
                handler.sendMessage(msg);
            }
        };
        Button button1=(Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread=new Thread(null,mywork,"WorkThread");
                workThread.start();
            }
        });
    }
}
