package kr.co.dnasoft.remontest;

import kr.co.dnasoft.remonsdk.AdInterstitial;
import kr.co.dnasoft.remonsdk.AdListener;
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

/**
 * Created by rabbit on 2015. 1. 26..
 */
public class JavaTypeInterstitialBanner extends Activity {

    AdInterstitial interstitial = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initRemon();
    }

    private void initRemon() {
        //Ad Listener
        AdListener listener = new AdListener();

        /**
         * Clicked Ad Listener
         */
        listener.OnAdClickClickedListener = new OnAdClickClickedListener() {
            @Override
            public void OnClickedAd() {
                Log.i("JavaTypeInterstitialBanner", "JAVA Type Interstitial Banner Click ad");
            }
        };

        /**
         * Before loading the Ad Listener
         */
        listener.OnAdWillLoadListener = new OnAdWillLoadListener() {

            @Override
            public void OnAdWillLoad() {
                Log.i("JavaTypeInterstitialBanner", "JAVA Type Interstitial Banner Will Load");
            }

        };

        /**
         * Closed the Ad Listener
         */
        listener.OnAdClickClosedListener = new OnAdClosedListener() {

            @Override
            public void OnAdClosed() {
                Log.i("JavaTypeInterstitialBanner", "JAVA Type Interstitial Banner Closed");
            }
        };

        /**
         * Loaded the Ad Listener
         */
        listener.OnAdLoadedListener = new OnAdLoadedListener() {

            @Override
            public void OnAdLoaded() {
                Log.i("JavaTypeInterstitialBanner", "JAVA Type Interstitial Banner Loaded");
            }
        };

        /**
         * Failed the Ad Listener
         */
        listener.OnAdFailedListener = new OnAdFailedListener() {

            @Override
            public void OnAdFailed(ADErrorCode arg0, String arg1) {
                Log.i("JavaTypeInterstitialBanner", "JAVA Type Interstitial Banner Failed");
                Log.i("JavaTypeInterstitialBanner", "Error Code : " + arg0 + ", Reason : " + arg1);
            }
        };


        interstitial = new AdInterstitial(this, listener);  //광고 초기화
        interstitial.setcId("MTQyODkzNTMxNTcyNjE4");        //발급 받은 clinet Id를 정확하게 입력
        interstitial.setTestMode(true);                     //테스트 시에 사용(앱스토에 업로드 시 false로 변경하거나 삭제
        interstitial.setLogMode(true);                      //테스트 시에 사용(앱스토에 업로드 시 false로 변경하거나 삭제
        interstitial.setUserBGColor("#cef279");             //광고 배경색 설정(서버에서 내려오는 배경색이 없는 경우 사용자 지정 색으로 설정)
        interstitial.setUseAutoClose(true);                 //자동 닫힘 기능 설정(default : false)
        interstitial.setAutoCloseTime(10);                  //자동 닫힘 시간 (default : 10, 0 초과 10 이하)

        AdInfo adInfo = new AdInfo();                       //사용자 정보 설정
        adInfo.setGender("f");                              //사용자 성별
        adInfo.setAge(38);                                  //사용자 연령대

        interstitial.setAdinfo(adInfo);                     //사용자 정보 반영(Optional)
        interstitial.setTerms(true);                        //광고 약관 동의 여부 (Optional, default : false)
        interstitial.requestAd();                           //광고 요청
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onDestroy() {

        //광고 제거
        if (interstitial != null) {
            interstitial.removeAd();
            interstitial = null;
        }

        super.onDestroy();
    }
}
