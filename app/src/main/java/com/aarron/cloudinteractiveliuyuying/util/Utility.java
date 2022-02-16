package com.aarron.cloudinteractiveliuyuying.util;

import android.app.ProgressDialog;
import android.content.Context;

public class Utility {
    private static ProgressDialog progressDialog;
    private static long lastClickTime;

    public static void showProgressDialog(Context context, String message) {
        try {
            if (progressDialog == null || !progressDialog.isShowing()) {
                progressDialog = new ProgressDialog(context);
            }

            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        } catch (Exception var3) {
            var3.printStackTrace();
            throw var3;
        }
    }

    public static void dismissProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0L < timeD && timeD < 1000L) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }
}
