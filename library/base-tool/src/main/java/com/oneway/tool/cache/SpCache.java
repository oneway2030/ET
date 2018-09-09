package com.oneway.tool.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;

import com.oneway.tool.ToolConfig;
import com.oneway.tool.utils.cipher.BASE64;
import com.oneway.tool.utils.convert.ByteUtil;
import com.oneway.tool.utils.convert.HexUtil;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 *  SpCache 缓存
 * Created by oneway on 2017/12/26.
 * <p>
 * 关于commit 与 apply 参考 https://blog.csdn.net/u010198148/article/details/51706483
 * commit  同步操作会有结果返回
 * 此类中全部默认使用commit同步提交
 * apply 异步操作 无操作结果返回  (项目中异步慎用)
 */

public class SpCache implements ICache {
    /**
     * 默认sp名字
     */
    private static final String DEF_SP_NAME = "Def_sp_name";
    /**
     * sp集合的缓存
     */
    private static SimpleArrayMap<String, SpCache> SP_UTILS_MAP = new SimpleArrayMap<>();
    private SharedPreferences sp;

    /**
     * 获取 SP 实例
     *
     * @return {@link SpCache}
     */
    public static SpCache getInstance() {
        return getInstance("");
    }

    /**
     * 获取 SP 实例
     *
     * @param spName sp 名
     * @return {@link SpCache}
     */
    public static SpCache getInstance(String spName) {
        if (isSpace(spName)) spName = DEF_SP_NAME;
        SpCache spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            spUtils = new SpCache(spName);
            SP_UTILS_MAP.put(spName, spUtils);
        }
        return spUtils;
    }

    private SpCache(final String spName) {
        sp = ToolConfig.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * SP 中获取所有键值对
     *
     * @return Map 对象
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * SP 中是否存在该 key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    @Override
    public boolean contains(@NonNull final String key) {
        return sp.contains(key);
    }

    /**
     * SP 中移除该 key
     *
     * @param key 键
     */
    @Override
    public void remove(@NonNull final String key) {
        remove(key, true);
    }

    /**
     * SP 中移除该 key
     *
     * @param key      键
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void remove(@NonNull final String key, final boolean isCommit) {
        if (isCommit) {
            sp.edit().remove(key).commit();
        } else {
            sp.edit().remove(key).apply();
        }
    }

    /**
     * SP 中清除所有数据
     */
    @Override
    public void clear() {
        clear(true);
    }


    /**
     * SP 中清除所有数据
     *
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void clear(final boolean isCommit) {
        if (isCommit) {
            sp.edit().clear().commit();
        } else {
            sp.edit().clear().apply();
        }
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * ******************************   put  start  ********************************
     */

    /**
     * 加密并存储
     * 注意 只有此方法 加密obj 后并保存
     */
    public void putAndEncryption(String key, Object obj) {
        putAndEncryption(key, obj, true);
    }

    /**
     * 加密并存储
     */
    public void putAndEncryption(String key, Object obj, boolean isCommit) {
        try {
            if (obj == null) {
                remove(key, isCommit);
            } else {
                byte[] bytes = ByteUtil.objectToByte(obj);
                bytes = BASE64.encode(bytes);
                put(key, HexUtil.encodeHexStr(bytes), isCommit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 同步提交
     */
    @Override
    public void put(String key, Object value) {
        put(key, value, true);
    }


    /**
     * 通用的缓存key
     */
    public void put(String key, Object value, final boolean isCommit) {
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Set) {
            edit.putStringSet(key, (Set<String>) value);
        }
        if (isCommit) {
            edit.commit();
        } else {
            edit.apply();
        }
    }


    /**
     * SP 中写入 String
     *
     * @param key   键
     * @param value 值
     */
    public void putString(@NonNull final String key, @NonNull final String value) {
        putString(key, value, true);
    }

    /**
     * SP 中写入 String
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void putString(@NonNull final String key, @NonNull final String value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putString(key, value).commit();
        } else {
            sp.edit().putString(key, value).apply();
        }
    }


    /**
     * SP 中写入 int
     *
     * @param key   键
     * @param value 值
     */
    public void putInt(@NonNull final String key, final int value) {
        putInt(key, value, true);
    }

    /**
     * SP 中写入 int
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void putInt(@NonNull final String key, final int value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putInt(key, value).commit();
        } else {
            sp.edit().putInt(key, value).apply();
        }
    }


    /**
     * SP 中写入 long
     *
     * @param key   键
     * @param value 值
     */
    public void putLong(@NonNull final String key, final long value) {
        putLong(key, value, true);
    }

    /**
     * SP 中写入 long
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void putLong(@NonNull final String key, final long value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit();
        } else {
            sp.edit().putLong(key, value).apply();
        }
    }


    /**
     * SP 中写入 float
     *
     * @param key   键
     * @param value 值
     */
    public void putFloat(@NonNull final String key, final float value) {
        putFloat(key, value, true);
    }

    /**
     * SP 中写入 float
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void putFloat(@NonNull final String key, final float value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putFloat(key, value).commit();
        } else {
            sp.edit().putFloat(key, value).apply();
        }
    }


    /**
     * SP 中写入 boolean
     *
     * @param key   键
     * @param value 值
     */
    public void putBoolean(@NonNull final String key, final boolean value) {
        putBoolean(key, value, true);
    }

    /**
     * SP 中写入 boolean
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void putBoolean(@NonNull final String key, final boolean value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putBoolean(key, value).commit();
        } else {
            sp.edit().putBoolean(key, value).apply();
        }
    }


    /**
     * SP 中写入 String 集合
     *
     * @param key    键
     * @param values 值
     */
    public void putSet(@NonNull final String key, @NonNull final Set<String> values) {
        putSet(key, values, true);
    }

    /**
     * SP 中写入 String 集合
     *
     * @param key      键
     * @param values   值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void putSet(@NonNull final String key, @NonNull final Set<String> values, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putStringSet(key, values).commit();
        } else {
            sp.edit().putStringSet(key, values).apply();
        }
    }

    /**
     * ******************************    put  end   ********************************
     */

    /**
     * ******************************   get  start  ********************************
     */
    /**
     * 获取解密后的值
     * 注意: 此方式是获取加密后的缓存值 其他的get方法均无加密
     */
    @Override
    public Object get(String key) {
        try {
            String hex = getString(key, null);
            if (hex == null) return null;
            byte[] bytes = HexUtil.decodeHex(hex.toCharArray());
            bytes = BASE64.decode(bytes);
            Object obj = ByteUtil.byteToObject(bytes);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SP 中读取 String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code ""}
     */
    public String getString(@NonNull final String key) {
        return getString(key, "");
    }

    /**
     * SP 中读取 String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(@NonNull final String key, @NonNull final String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * SP 中读取 float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public float getFloat(@NonNull final String key, final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * SP 中读取 int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public int getInt(@NonNull final String key) {
        return getInt(key, -1);
    }

    /**
     * SP 中读取 int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(@NonNull final String key, final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * SP 中读取 boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, false);
    }

    /**
     * SP 中读取 boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * SP 中读取 long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public long getLong(@NonNull final String key) {
        return getLong(key, -1L);
    }

    /**
     * SP 中读取 long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public long getLong(@NonNull final String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * SP 中读取 float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public float getFloat(@NonNull final String key) {
        return getFloat(key, -1f);
    }

    /**
     * SP 中读取 StringSet
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code Collections.<String>emptySet()}
     */
    public Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * SP 中读取 StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public Set<String> getStringSet(@NonNull final String key, @NonNull final Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }
    /**
     * ******************************   get  end********************************
     */


}
