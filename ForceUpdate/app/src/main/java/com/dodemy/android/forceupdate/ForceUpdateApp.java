package com.dodemy.android.forceupdate;

import android.app.Application;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;

public class ForceUpdateApp extends Application implements LifecycleObserver {

  private ForceUpgradeManager forceUpgradeManager;

  private static ForceUpdateApp application;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    initForceUpgradeManager();
    ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
  }

  public void initForceUpgradeManager() {
    if (forceUpgradeManager == null) {
      forceUpgradeManager = new ForceUpgradeManager(application);
    }
  }
}
