package com.oneway.tool.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by oneway on 2017/12/25.
 */

public class MemoryConstants {
    /**
     * Byte 与 Byte 的倍数
     */
    public static final int BYTE = 1;
    /**
     * KB 与 Byte 的倍数
     */
    public static final int KB   = 1024;
    /**
     * MB 与 Byte 的倍数
     */
    public static final int MB   = 1048576;
    /**
     * GB 与 Byte 的倍数
     */
    public static final int GB   = 1073741824;

    @IntDef({BYTE, KB, MB, GB})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }
}
