package kr.co.dnasoft.remontest;


import kr.co.dnasoft.remonsdk.AgreeActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AgreeSampleActivity extends Activity {

	private final static String 	TAG 			= AgreeSampleActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_type_banner);

		Agreement();
	}

	private void Agreement() {
		
		//Remonsdk 약관 동의 호출 
		Intent intent = new Intent(AgreeSampleActivity.this, AgreeActivity.class);
		startActivityForResult(intent, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode== 1 || resultCode==RESULT_OK){

			if(data.getStringExtra("AgreeFlag").equals("Y")){//use data
				
				//동의
                Log.i(TAG, "동의");

				
			} else {
				//미동의
                Log.i(TAG, "미동의");
			}
		}
	}
}