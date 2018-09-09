package com.oneway.tool.utils.file;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.log.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oneway on 2017/12/27.
 */

public class AssetsUtils {

    public static final String LAYTOUT="layout";
    public static final String DRAWABLE="drawable";
    public static final String MIPMAP="mipmap";
    public static final String MENU="menu";
    public static final String RAW="raw";
    public static final String ANIM="anim";
    public static final String STRING="string";
    public static final String STYLE="style";
    public static final String STYLEABLE="styleable";
    public static final String INTEGER="integer";
    public static final String ID="id";
    public static final String DIMEN="dimen";
    public static final String COLOR="color";
    public static final String BOOL="bool";
    public static final String ATTR="attr";

    /**
     * 根据本地Assets目录下资源名称，获取String数据信息
     * @param context  上下文对象
     * @param fileName 文件名称
     * @return String  返回数据
     */
    public static String getStringByAssets(Context context, String fileName) {
        if (context == null || EmptyUtils.isEmpty(fileName)) {
            return null;
        }
        try {
            StringBuilder s = new StringBuilder("");
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("AppResourceMgr-->>getStringByAssets", "根据本地Assets目录下资源名称，获取String数据信息失败！" + e.getMessage());
            return null;
        }
    }


    /**
     * 根据本地Assets目录下资源名称，获取List集合信息
     * @param context  上下文对象
     * @param fileName 文件名称
     * @return List<String>  返回集合
     */
    public static List<String> getListByAssets(Context context, String fileName) {
        if (context == null || EmptyUtils.isEmpty(fileName)) {
            return null;
        }
        List<String> fileContent = new ArrayList<String>();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            br.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("AppResourceMgr-->>getListByAssets", "根据本地Assets目录下资源名称，获取List集合信息失败！" + e.getMessage());
            return null;
        }
    }

    /**
     * 从assets目录下读取文件内容
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 文件字节流
     */
    public static byte[] readBytesFromAssets(Context context, String fileName) {
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = context.getAssets().open(fileName);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return buffer;
    }

    /**
     * 从assets目录读取文本
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 文本内容
     */
    public static String readStringFromAssets(Context context, String fileName) {
        String result = null;
        byte[] buffer = readBytesFromAssets(context, fileName);
        try {
            result = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 从assets目录中复制整个文件夹内容
     *
     * @param context Context 使用CopyFiles类的Activity
     * @param oldPath String  原文件路径  如：/aa
     * @param newPath String  复制后路径  如：xx:/bb/cc
     */
    public static void copyFilesAassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesAassets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 根据本地Raw目录下资源标识，获取String数据信息
     * @param context 上下文对象
     * @param resId   资源标识
     * @return String 返回数据
     */
    public static String getStringByRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }
        try {
            StringBuilder s = new StringBuilder();
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("AppResourceMgr-->>getStringByRaw", "根据本地Raw目录下资源标识，获取String数据信息失败！" + e.getMessage());
            return null;
        }
    }



    /**
     * 根据本地Raw目录下资源标识，获取List集合信息
     * @param context 上下文对象
     * @param resId   资源标识
     * @return List<String> 返回集合
     */
    public static List<String> getListByRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }
        List<String> fileContent = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("AppResourceMgr-->>getListByRaw", "根据本地Raw目录下资源标识，获取List集合信息失败！" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据资源名获得资源id
     * @param context 上下文
     * @param name 资源名
     * @param type 资源类型
     * @return 资源id，找不到返回0
     */
    public static int getResourceId(Context context,String name,String type){
        Resources resources=null;
        PackageManager pm=context.getPackageManager();
        try {
            resources=context.getResources();
            return resources.getIdentifier(name, type, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 从res/raw目录下读取文件内容
     *
     * @param context 上下文
     * @param rawId   rawId
     * @return 文件字节流
     */
    public static byte[] readBytesFromRaw(Context context, int rawId) {
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = context.getResources().openRawResource(rawId);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return buffer;
    }



    /**
     * 从raw目录读取文本
     *
     * @param context 上下文
     * @param rawId   id值
     * @return 文本内容
     */
    public static String readStringFromRaw(Context context, int rawId) {
        String result = null;
        byte[] buffer = readBytesFromRaw(context, rawId);
        try {
            result = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


}
