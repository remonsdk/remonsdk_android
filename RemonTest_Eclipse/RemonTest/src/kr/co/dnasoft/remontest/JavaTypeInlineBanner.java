package kr.co.dnasoft.remontest;

import kr.co.dnasoft.remonsdk.AdView;
import kr.co.dnasoft.remonsdk.AdView.OnAdClickClickedListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdClosedListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdFailedListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdLoadedListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdWillLoadListener;
import kr.co.dnasoft.remonsdk.common.AdConstant.ADErrorCode;
import kr.co.dnasoft.remonsdk.common.AdInfo;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class JavaTypeInlineBanner extends Activity {

	private final   static  String 	        TAG 		= JavaTypeInlineBanner.class.getName();
	private                 AdView 			adView 		= null;
	private                 LinearLayout layout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_type_banner);
		layout = (LinearLayout) findViewById(R.id.ad_layout);
		initRemon();
	}
	 
	private void initRemon() {
		
		//RemoN 광고 View 생성 및 설정
		if(adView != null) {
			
			adView.stopAd();
			
			adView.setOnAdClickedListener(null);
			adView.setOnAdClosedListener(null);
			adView.setOnAdFailedListener(null);
			adView.setOnAdLoadedListener(null);
			adView.setOnAdWillLoadListener(null);
			layout.removeView(adView);
			adView = null;
		}
		
		//Remon 광고 뷰 생성 및 설정
		adView = new AdView(this);
		
		/*====================================================
		 * 									Listener
		 ====================================================*/
		//광고 클릭시 실행될 Listener
		adView.setOnAdClickedListener(new OnAdClickClickedListener() {
			@Override
			public void OnClickedAd() {
				Log.i(TAG, "JAVA Type Inline Banner Click ad");
			}
		});
		
		//광고 실패시 실행될 Listener
		adView.setOnAdFailedListener(new OnAdFailedListener() {
			
			@Override
			public void OnAdFailed(ADErrorCode arg0, String arg1) {
				Log.w(TAG, "JAVA Type Inline Banner Ad failed : " + arg1);
			}
		});
		
		//광고가 정상적으로 Load 될때 실행될 Listener
		adView.setOnAdLoadedListener(new OnAdLoadedListener() {
			
			@Override
			public void OnAdLoaded() {
				Log.i(TAG, "JAVA Type Inline Banner Ad loaded");
			}
		});
		
		//광고를 호출하는 시점에 실행될 Listener
		adView.setOnAdWillLoadListener(new OnAdWillLoadListener() {
			
			@Override
			public void OnAdWillLoad() {
				Log.i(TAG, "JAVA Type Inline Banner Ad will load");
			}
		});
		
		//광고를 종료할때 실행될 Listener
		adView.setOnAdClosedListener(new OnAdClosedListener() {
			
			@Override
			public void OnAdClosed() {
				Log.i(TAG, "JAVA Type Inline Banner Ad closed");
			}
		});
		
		//할당 받은client Id 설정(필수)MTQxOTI1NTg3MTcwMDA2
		//adView.setcId("MTQxMDg3NDQ1MzY3OTc4");
		adView.setcId("MTQxOTI1NTg3MTcwMDA2");
		
		//광고 배경색 설정(서버에서 내려오는 배경색이 없는 경우 사용자 지정 색으로 설정)
		adView.setBackgroundColor("#FFEAEAEA");
		//광고 갱신 주기 설정(default : 30초, max : 300초)
		adView.setRefreshInterval(30);
		
		//SDK 광고 Test 설정(default : false)
		adView.setTestMode(true);
		
		//SDK 광고 요청 상태 Log 설정(default : false)
		adView.setSDKLog(true, "JavaTypeInlineBanner");
		
		//광고 활성화 설정 : INVISIBLE, GONE로 설정시 광고 중단(default : VISIBLE)
		adView.setVisibility(View.VISIBLE);
		layout.addView(adView);
		
		//약관동의 여부 설정
		adView.setTerms(true);
		
		//사용자 정보 설정
		AdInfo adInfo = new AdInfo();
		
		//사용자 성별
		adInfo.setGender("f");
		
		//사용자 연령대
		adInfo.setAge(38);
		adView.setAdInfo(adInfo);
		
		//광고 요청
		adView.requestAd();
		
	}
	
	@Override
	protected void onPause() {
		
		if(adView != null) {
			adView.onPause();					//pause시에 광고로 이벤트 전달
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		
		if(adView != null) {
			adView.onResume();					//resume시에 광고로 이벤트 전달
		}
		super.onResume();
	}

    @Override
    public void finish() {

    	//광고 제거 
        if(adView != null) {
            adView.stopAd();
            layout.removeView(adView);
            layout = null;
            adView = null;
        }

        super.finish();
    }

}
