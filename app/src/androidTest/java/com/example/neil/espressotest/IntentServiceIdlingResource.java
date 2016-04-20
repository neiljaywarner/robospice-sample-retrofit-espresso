package com.example.neil.espressotest;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.support.test.espresso.IdlingResource;
import android.util.Log;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.sample.retrofit.BaseSampleSpiceActivity;
import com.octo.android.robospice.sample.retrofit.network.SampleRetrofitSpiceService;

public class IntentServiceIdlingResource implements IdlingResource {
  private final BaseSampleSpiceActivity activity;
  private ResourceCallback resourceCallback;

  public IntentServiceIdlingResource(BaseSampleSpiceActivity activity) {
    this.activity = activity;
  }

  @Override
  public String getName() {
    return IntentServiceIdlingResource.class.getName();
  }

  @Override
  public boolean isIdleNow() {
    boolean idle = !isIntentServiceRunning();
    if (idle && resourceCallback != null) {
      resourceCallback.onTransitionToIdle();
    }
    return idle;
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
    this.resourceCallback = resourceCallback;
  }

  private boolean isIntentServiceRunning() {
    if (activity.getSpiceManager().getPendingRequestCount() > 0) {
      return true;
    } else {
      return false;

    }

  }
}