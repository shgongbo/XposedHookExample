package com.example.forthhook;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;



public class XposedHookDemo implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.d("two","11111111111111111111111111111111111111");
        // Hook into the package's code and modify its behavior here
        if (lpparam.packageName.equals("com.example.secondnoactivity")) {
            Log.d("two","22222222222222222222222222222222222222");
            XposedBridge.log(lpparam.packageName+" has Hooked!");
            Class clazz = lpparam.classLoader.loadClass(
                    "com.example.secondnoactivity.SecondNoActivity"
            );
            XposedHelpers.findAndHookMethod(clazz,"generateRandomNumber", String.class, new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("input :" +param.args[0]);
                }
                protected  void afterHookedMethod(MethodHookParam param) throws Throwable{
                    param.setResult(666);
                }
            });
        }

    }
}