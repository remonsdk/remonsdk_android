package kr.co.dnasoft.remontest;

import kr.co.dnasoft.remonsdk.AdFloatingView;
import kr.co.dnasoft.remonsdk.AdListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdClickClickedListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdClosedListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdFailedListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdLoadedListener;
import kr.co.dnasoft.remonsdk.AdView.OnAdWillLoadListener;
import kr.co.dnasoft.remonsdk.common.AdConstant.ADErrorCode;
import kr.co.dnasoft.remonsdk.common.AdInfo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class JavaTypeFloatingBanner extends Activity {

    private final static String 	TAG 		= JavaTypeInlineBanner.class.getName();
    private AdFloatingView 			adview 		= null;
    private FrameLayout             mainLayout  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.java_type_banner);
        mainLayout = new FrameLayout(this);
        mainLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        initRemon();

    }

    @Override
    public void finish() {

        //광고 제거
        if(adview != null) {
            adview.removeAd();
            adview = null;
        }

        super.finish();
    }

    private void initRemon() {

        if(adview != null) {
            adview.removeAd();
            adview = null;
        }

        AdListener listener = new AdListener();

        /**
         * Clicked Ad Listener
         */
        listener.OnAdClickClickedListener = new OnAdClickClickedListener() {
            @Override
            public void OnClickedAd() {
                Log.i("JavaTypeFloatingBanner", "JAVA Type Floating Banner Click ad");
            }
        };

        /**
         * Before loading the Ad Listener
         */
        listener.OnAdWillLoadListener = new OnAdWillLoadListener() {

            @Override
            public void OnAdWillLoad() {
                Log.i("JavaTypeFloatingBanner", "JAVA Type Floating Banner Will Load");
            }

        };

        /**
         * Closed the Ad Listener
         */
        listener.OnAdClickClosedListener = new OnAdClosedListener() {

            @Override
            public void OnAdClosed() {
                Log.i("JavaTypeFloatingBanner", "JAVA Type Floating Banner Closed");
            }
        };

        /**
         * Loaded the Ad Listener
         */
        listener.OnAdLoadedListener = new OnAdLoadedListener() {

            @Override
            public void OnAdLoaded() {
                Log.i("JavaTypeFloatingBanner", "JAVA Type Floating Banner Loaded");
            }
        };

        /**
         * Failed the Ad Listener
         */
        listener.OnAdFailedListener = new OnAdFailedListener() {

            @Override
            public void OnAdFailed(ADErrorCode arg0, String arg1) {
                Log.i("JavaTypeFloatingBanner", "JAVA Type Floating Banner Failed");
                Log.i("JavaTypeFloatingBanner", "Error Code : " + arg0 + ", Reason : " + arg1);
            }
        };

        adview = new AdFloatingView(this);      //광고 초기화
        adview.setcId("MTQyODkzNTI3NzcyNjE3");  //발급 받은 clinet Id를 정확하게 입력
        adview.setTestMode(true);               //테스트 시에 사용(앱스토에 업로드 시 false로 변경하거나 삭제
        adview.setLogMode(true);                //테스트 시에 사용(앱스토에 업로드 시 false로 변경하거나 삭제
        adview.setX(100.0f);                    //광고를 노출할 offst 중 x 좌표(노출할 View 내의 Offset임, default : 0)
        adview.setY(100.0f);                    //광고를 노출할 offst 중 y 좌표(노출할 View 내의 Offset임, default : 0)
        adview.setAdListener(listener);         //광고에 대한 상황등을 응답 받을 리스너

        AdInfo adInfo = new AdInfo();           //사용자 정보 설정
        adInfo.setGender("f");                  //사용자 성별
        adInfo.setAge(38);                      //사용자 연령대

        adview.setAdinfo(adInfo);               //사용자 정보 반영(Optional)
        adview.setTerms(true);                  //광고 약관 동의 여부 (Optional, default : false)
        adview.loadAd();                        //광고 요청

        mainLayout.addView(adview);             //광고 View 추가
        mainLayout.setBackgroundColor(Color.RED);
        setContentView(mainLayout);
    }

    @Override
    protected void onPause() {

        if(adview != null) {

            adview.pauseAd(); 					//pause시에 광고로 이벤트 전달
        }
        super.onPause();
    }

    @Override
    protected void onResume() {

        if(adview != null) {

            adview.resumeAd(); 					//resume시에 광고로 이벤트 전달
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}