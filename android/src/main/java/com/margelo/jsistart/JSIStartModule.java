package com.margelo.jsistart;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = JSIStartModule.NAME)
public class JSIStartModule extends ReactContextBaseJavaModule {
  public static final String NAME = "JSIStart";

  JSIStartModule(ReactApplicationContext context) {
    super(context);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  private native void nativeInstall(long jsi);

  @ReactMethod(isBlockingSynchronousMethod = true)
  public boolean install() {
    try {
      Log.i(NAME, "Loading C++ library...");
      System.loadLibrary("reactnativeJSIStart");
      JavaScriptContextHolder jsContext = getReactApplicationContext().getJavaScriptContextHolder();
      Log.i(NAME, "Installing JSI Bindings...");
      nativeInstall(jsContext.get());
      return true;
    } catch (Exception exception) {
      Log.e(NAME, "Failed to install JSI Bindings!", exception);
      return false;
    }
  }
}
