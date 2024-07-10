package com.example.forthhook;//package com.example.forthhook;
//
//public class XpodesHookDemo {
//}


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;



public class XposedHookDemo implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // Hook into the package's code and modify its behavior here
        if (lpparam.packageName.equals("com.example.secondnoactivity")) {
            XposedBridge.log(lpparam.packageName+" has Hooked!");
            Class clazz = lpparam.classLoader.loadClass(
                    "SecondNoActivity"
            );
            XposedHelpers.findAndHookMethod(clazz,"generateRandomNumber", new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("input :" +param.args[0]);
                }
            })
        }

    }
}