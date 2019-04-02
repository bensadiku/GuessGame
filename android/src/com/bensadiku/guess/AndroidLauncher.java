package com.bensadiku.guess;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.bensadiku.guess.GuessGame;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//verifyPermissions();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GuessGame(), config);

	}

	public static void Save (){

	}

	/*	private  void  verifyPermissions(){
			final int RequestPermissionID = 1001;
			final String TAG ="SearchActivity";

			Log.d(TAG,"Verify Permissions: asking users for persmissions");
			String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE,
					};


			if(ContextCompat.checkSelfPermission(this.getApplicationContext(),permissions[0]) ==PackageManager.PERMISSION_GRANTED
					&& ContextCompat.checkSelfPermission(this.getApplicationContext(),permissions[1]) ==PackageManager.PERMISSION_GRANTED)
			{
				try {
					AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
					initialize(new GuessGame(), config);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}else{
				ActivityCompat.requestPermissions(AndroidLauncher.this, permissions,RequestPermissionID);
			}
		}

		@Override
		public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
			verifyPermissions();
		}



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case RequestCameraPermissionID:
            {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        return;
                }
            }
            try {
                cameraSource.start(cameraView.getHolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

	}


