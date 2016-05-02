package demo.com.countdown;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputTime;
    private Button startTime;
    private Button stopTime;
    private Timer timer = null;
    private TimerTask task = null;
    private int  i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTime = (EditText) findViewById(R.id.input_time);
        startTime = (Button) findViewById(R.id.start_time);
        stopTime = (Button) findViewById(R.id.stop_time);

        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
      switch (v.getId()){

          case R.id.start_time:
              inputTime.setText(inputTime.getText().toString());
              i=Integer.parseInt(inputTime.getText().toString());
              startTime();
              break;
          case R.id.stop_time:
             stopTime();
              break;
      }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
             inputTime.setText(msg.arg1+"s");
             startTime();
        };
    };

    public void startTime(){
        timer=new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                i--;
                Message message=handler.obtainMessage();
                message.arg1=i;
                handler.sendMessage(message);
            }
        };
       timer.schedule(task,1000);//启动timer，按照每秒的单位
    }


    public void stopTime(){
      timer.cancel();
    }

}