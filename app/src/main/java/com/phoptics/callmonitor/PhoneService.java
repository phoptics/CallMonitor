package com.phoptics.callmonitor;

import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);  
        telephonyManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE); 
	}
	 private final class PhoneListener extends PhoneStateListener  
	    {  
	        private String incomeNumber;
	        private MediaRecorder mediaRecorder;  
	        private File file; 
	        private MediaPlayer mPlayer;
	        @Override  
	        public void onCallStateChanged(int state, String incomingNumber)  
	        {  
	            try  
	            {  
	                switch(state)  
	                {  
	                case TelephonyManager.CALL_STATE_RINGING:
	                    this.incomeNumber = incomingNumber;  
	                    break;  
	                case TelephonyManager.CALL_STATE_OFFHOOK:
	                    file = new File(Environment.getExternalStorageDirectory(), this.incomeNumber + System.currentTimeMillis() + ".3gp");  
	                    mediaRecorder = new MediaRecorder();  
	                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);     
	                    mediaRecorder.setOutputFile(file.getAbsolutePath());
	                    mediaRecorder.prepare();
	                    mediaRecorder.start();  
	                    break;  
	                  
	                case TelephonyManager.CALL_STATE_IDLE:
	                    if(mediaRecorder != null)  
	                    {  
	                        mediaRecorder.stop();  
	                        mediaRecorder.release();  
	                        mediaRecorder = null;  
	                        mPlayer=new MediaPlayer();
	                        mPlayer.setDataSource(file.getAbsolutePath());
	                        mPlayer.prepare();
	                        mPlayer.start();
	                    }   
	                    break;  
	                  
	                }  
	            }  
	            catch (IllegalStateException e)  
	            {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	            catch (IOException e)  
	            {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        }  
	          
	          
	    }  

}
