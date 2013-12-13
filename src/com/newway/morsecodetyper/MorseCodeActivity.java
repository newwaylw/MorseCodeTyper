package com.newway.morsecodetyper;

import com.newway.morsecodetyper.R;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

public class MorseCodeActivity extends Activity {
    public static final String TAG = MorseCodeActivity.class.getSimpleName();
    public static final int SHORT_FLASH=50; //milliseconds
    public static final int LONG_FLASH=200;  //milliseconds
    public static final int PAUSE=500;
    
    private Camera cam;
    private CheckBox mBeepCheckBox;
    private Button mOnOffButton;
    private EditText mEditText;
    private boolean mSound;
    private Timer mTimer;
    private Parameters p;
    private MorseCoder mMorseCoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mMorseCoder = new MorseCoder();
        mTimer = new Timer();
        mBeepCheckBox = (CheckBox) findViewById(R.id.checkbox_beep);
        mOnOffButton = (Button) findViewById(R.id.button);
        mEditText = (EditText) findViewById(R.id.edit_text);
        
        
        mOnOffButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {  
                String s = mEditText.getText().toString();
                Log.d(TAG, "button clicked, text="+s);
                runMorseCode(s);
                mOnOffButton.setText("STOP");
                
            }
        });

    }

    public void runMorseCode(String input){
       List<String> list = mMorseCoder.getMorseCode(input);
       for (String s : list){
           for (int i=0; i<s.length(); i++){
               char ch = s.charAt(i);
               Log.d(TAG, "current char="+ch);
               if ('.' == ch){
                  setFlashOnFor(MorseCodeActivity.SHORT_FLASH);
               }else if ('-' == ch){
                  setFlashOnFor(MorseCodeActivity.LONG_FLASH);
               }
               
           }
               
       }
       
    }
    
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        if (view.getId() == R.id.checkbox_beep) {
            mSound = ((CheckBox) view).isChecked();
        }
    }

    private void setFlashOnFor(long milliSecond) {
        Log.d(TAG,"opening camera flash...");
        cam = Camera.open();
        p = cam.getParameters();
        p.setFlashMode(Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();
        
        
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
             // off
                Log.d(TAG,"turning off flash...");
                cam.stopPreview();
                cam.release();
                
            }
        }, new Date(System.currentTimeMillis()+milliSecond) );

        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
