package com.oneway.tool.utils.file;

import android.os.Environment;
import android.os.StatFs;

import com.oneway.tool.utils.log.LogUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by oneway on 2017/12/26.
 */

public class SDCardUtils {
    private SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取手机内内存总容量大小
     *
     * @return long
     */
    @SuppressWarnings("deprecation")
    public static long getMobileAllRAM() {
        StatFs stat = new StatFs(getDataPath());
        return stat.getBlockSize() * stat.getBlockCount();
    }

    /**
     * 获取手机内存Path存储路径
     *
     * @return String
     */
    public static String getDataPath() {
        return Environment.getDataDirectory().getPath() + File.separator;
    }

    /**
     * 获取SDCard的AbsolutePath路径
     *
     * @return String
     */
    public static String getSdCardAbsolutePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SdCard的Path路径
     *
     * @return String
     */
    public static String getSdCardPath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }


    /**
     * 检查SDCard是否可用，是否存在
     *
     * @return boolean
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


//    /**
//     * 获取 SD 卡路径
//     *
//     * @param removable true : 外置 SD 卡<br>false : 内置 SD 卡
//     * @return SD 卡路径
//     */
//    @SuppressWarnings("TryWithIdenticalCatches")
//    public static List<String> getSDCardPaths(final boolean removable) {
//        List<String> paths = new ArrayList<>();
//        StorageManager mStorageManager = (StorageManager) ToolConfig.getContext()
//                .getSystemService(Context.STORAGE_SERVICE);
//        try {
//            Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
//            Method getVolumeList = StorageManager.class.getMethod("getVolumeList");
//            Method getPath = storageVolumeClazz.getMethod("getPath");
//            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
//            Object result = getVolumeList.invoke(mStorageManager);
//            final int length = Array.getLength(result);
//            for (int i = 0; i < length; i++) {
//                Object storageVolumeElement = Array.get(result, i);
//                String path = (String) getPath.invoke(storageVolumeElement);
//                boolean res = (Boolean) isRemovable.invoke(storageVolumeElement);
//                if (removable == res) {
//                    paths.add(path);
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return paths;
//    }
//
//    /**
//     * 获取 SD 卡路径
//     *
//     * @return SD 卡路径
//     */
//    @SuppressWarnings("TryWithIdenticalCatches")
//    public static List<String> getSDCardPaths() {
//        StorageManager storageManager = (StorageManager) ToolConfig.getContext()
//                .getSystemService(Context.STORAGE_SERVICE);
//        List<String> paths = new ArrayList<>();
//        try {
//            Method getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths");
//            getVolumePathsMethod.setAccessible(true);
//            Object invoke = getVolumePathsMethod.invoke(storageManager);
//            paths = Arrays.asList((String[]) invoke);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return paths;
//    }


    /**
     * 在SdCard中创建文件
     *
     * @param filename 文件名称
     * @return File
     */
    public static File createSDFile(String filename) {
        File file = null;
        try {
            file = new File(getSdCardAbsolutePath() + filename);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("AppFileMgr-->>createSDFile:", "创建文件失败！" + e.getMessage());
        }
        return file;
    }


    /**
     * 在SdCard中创建目录
     *
     * @param directory
     * @return void
     */
    public static void createSDDirectory(String directory) {
        File file = new File(getSdCardAbsolutePath() + directory);
        if (!file.exists()) {
            file.mkdir();
        }
    }


    /**
     * 在SdCard中创建目录
     *
     * @param directorys
     */
    public static void createSDDirectorys(String directorys) {
        File file = new File(getSdCardAbsolutePath() + directorys);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 判断文件是否在SdCard中存在
     *
     * @param filename
     * @return boolean
     */
    public static boolean isFileExistSdCard(String filename) {
        File file = new File(getSdCardPath() + filename);
        return file.exists();
    }



    /**
     * 获取SDCard卡的剩余容量(单位byte)
     *
     * @return long  返回大小
     */
    @SuppressWarnings("deprecation")
    public static long getSdCardEnableSize() {
        //首先判断SdCard是否存在
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSdCardAbsolutePath());
            //获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            //获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }


    /**
     * 获取SDCard总存储容量大小(单位byte)
     *
     * @return long  返回大小
     */
    @SuppressWarnings("deprecation")
    public long getSdCardAllSize() {
        if (isSDCardEnable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        }
        return 0;
    }




}
