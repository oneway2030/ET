package com.oneway.ui.pop.tips;

import java.util.Locale;

class RxPopupViewTool {

    static boolean isRtl() {
        Locale defLocal = Locale.getDefault();
        return Character.getDirectionality(defLocal.getDisplayName(defLocal).charAt(0))
                == Character.DIRECTIONALITY_RIGHT_TO_LEFT;
    }
}
