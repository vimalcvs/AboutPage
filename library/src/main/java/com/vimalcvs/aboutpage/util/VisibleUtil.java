package com.vimalcvs.aboutpage.util;

import android.view.View;

/**
 * Created by VimalCVS on 22/08/18.
 */

public final class VisibleUtil {

    public static void handle(View v, String s) {
        v.setVisibility(s == null || s.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
