package com.example.neil.espressotest;

import android.support.test.espresso.IdlingResource;

import com.octo.android.robospice.sample.retrofit.BaseSampleSpiceActivity;

public class RobospiceIdlingResource implements IdlingResource {
  private final BaseSampleSpiceActivity activity;
  private ResourceCallback resourceCallback;

  public RobospiceIdlingResource(BaseSampleSpiceActivity activity) {
    this.activity = activity;
  }

  @Override
  public String getName() {
    return RobospiceIdlingResource.class.getName();
  }

  @Override
  public boolean isIdleNow() {
    boolean idle = !isPendingRoboSpiceRequests();
    if (idle && resourceCallback != null) {
      resourceCallback.onTransitionToIdle();
    }
    return idle;
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
    this.resourceCallback = resourceCallback;
  }

  private boolean isPendingRoboSpiceRequests() {
      return  activity.getSpiceManager().getPendingRequestCount() > 0;
  }
}