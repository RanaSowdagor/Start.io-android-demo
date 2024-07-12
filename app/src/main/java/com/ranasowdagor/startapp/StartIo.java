package com.ranasowdagor.startapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.ads.banner.Cover;
import com.startapp.sdk.ads.banner.Mrec;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.StartAppAd.AdMode;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

public class StartIo {
    private final StartAppAd startAppAd;
    public static AdMode AUTOMATIC = AdMode.AUTOMATIC;
    public static AdMode FULL_PAGE = AdMode.FULLPAGE;
    public static AdMode OFFER_WALL = AdMode.OFFERWALL;
    public static AdMode REWARDED = AdMode.REWARDED_VIDEO;
    public interface GlobalAdListener {
        void onClick();
        void onComplete();
        void onError(String reason);
    }

    public StartIo(Activity act,String appId,boolean returnAd) {
        StartAppSDK.init(act,appId,returnAd);
        startAppAd = new StartAppAd(act);
    }

    public StartIo(Activity act) {
        StartAppSDK.init(act,"202651970",false);
        StartAppSDK.setTestAdsEnabled(true);
        startAppAd = new StartAppAd(act);
    }

    public void loadBannerAd(Activity act,RelativeLayout rootLayout) {
        Banner banner = new Banner(act);
        RelativeLayout.LayoutParams coverParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        coverParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        coverParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rootLayout.addView(banner, coverParameters);
        banner.loadAd();
    }

    public void loadBannerAd(Activity act,RelativeLayout rootLayout,GlobalAdListener globalAdListener) {
        Banner banner = new Banner(act, new BannerListener() {
            @Override
            public void onReceiveAd(View view) {}
            @Override
            public void onFailedToReceiveAd(View view) {
                globalAdListener.onError("not load");
            }
            @Override
            public void onImpression(View view) {
                globalAdListener.onComplete();
            }
            @Override
            public void onClick(View view) {
                globalAdListener.onClick();
            }
        });
        RelativeLayout.LayoutParams coverParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        coverParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        coverParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rootLayout.addView(banner, coverParameters);
        banner.loadAd();
    }

    public void loadCoverAd(Activity act,RelativeLayout rootLayout) {
        Cover startAppCover = new Cover(act);
        RelativeLayout.LayoutParams coverParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        coverParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        coverParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rootLayout.addView(startAppCover, coverParameters);
        startAppCover.loadAd();
    }

    public void loadCoverAd(Activity act,RelativeLayout rootLayout,GlobalAdListener globalAdListener) {
        Cover startAppCover = new Cover(act, new BannerListener() {
            @Override
            public void onReceiveAd(View view) {}
            @Override
            public void onFailedToReceiveAd(View view) {
                globalAdListener.onError("not load");
            }
            @Override
            public void onImpression(View view) {
                globalAdListener.onComplete();
            }
            @Override
            public void onClick(View view) {
                globalAdListener.onClick();
            }
        });
        RelativeLayout.LayoutParams coverParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        coverParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        coverParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rootLayout.addView(startAppCover, coverParameters);
        startAppCover.loadAd();
    }

    public void loadNativeAd(Activity act,RelativeLayout rootLayout) {
        Mrec startAppMrec = new Mrec(act);
        RelativeLayout.LayoutParams mrecParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        mrecParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mrecParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rootLayout.addView(startAppMrec, mrecParameters);
        startAppMrec.loadAd();
    }

    public void loadNativeAd(Activity act,RelativeLayout rootLayout,GlobalAdListener globalAdListener) {
        Mrec startAppMrec = new Mrec(act, new BannerListener() {
            @Override
            public void onReceiveAd(View view) {}
            @Override
            public void onFailedToReceiveAd(View view) {
                globalAdListener.onError("not load");
            }
            @Override
            public void onImpression(View view) {
                globalAdListener.onComplete();
            }
            @Override
            public void onClick(View view) {
                globalAdListener.onClick();
            }
        });
        RelativeLayout.LayoutParams mrecParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        mrecParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mrecParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rootLayout.addView(startAppMrec, mrecParameters);
        startAppMrec.loadAd();
    }

    public void showBackPressAd(Context ctx) {
        StartAppAd.onBackPressed(ctx);
    }

    public void showAd(Activity act,AdMode type,GlobalAdListener globalAdListener) {
        ProgressDialog pd = new ProgressDialog(act);
        pd.setMessage("Ad Loading...");pd.setCancelable(false);pd.show();
        startAppAd.loadAd(type, new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull Ad ad) {
                pd.dismiss();
                startAppAd.showAd(new AdDisplayListener() {
                    @Override
                    public void adHidden(Ad ad) {
                        globalAdListener.onComplete();
                    }
                    @Override
                    public void adDisplayed(Ad ad) {}
                    @Override
                    public void adClicked(Ad ad) {
                        globalAdListener.onClick();
                    }
                    @Override
                    public void adNotDisplayed(Ad ad) {
                        globalAdListener.onError(ad.getErrorMessage());
                    }
                });
            }
            @Override
            public void onFailedToReceiveAd(@Nullable Ad ad) {
                assert ad != null;pd.dismiss();
                globalAdListener.onError(ad.getErrorMessage());
            }
        });
    }

    public void showAd(Activity act,AdMode type) {
        ProgressDialog pd = new ProgressDialog(act);
        pd.setMessage("Ad Loading...");pd.setCancelable(false);pd.show();
        startAppAd.loadAd(type, new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull Ad ad) {
                pd.dismiss();
                startAppAd.showAd();
            }
            @Override
            public void onFailedToReceiveAd(@Nullable Ad ad) {
                pd.dismiss();
            }
        });
    }
}
