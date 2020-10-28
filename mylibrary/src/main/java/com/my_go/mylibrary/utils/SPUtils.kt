package com.my_go.mylibrary.utils

import android.content.Context
import android.content.SharedPreferences
import java.lang.reflect.Method

/**
 * Create by Package com.my_go.mylibrary.utils
 * Created by 毛勇 on 2020/10/28
 * Current System Time 13:49
 * Describe:SharedPreference封装类
 */
class SPUtils {
    constructor() {
        throw UnsupportedOperationException("没有实例化")
    }

    companion object {
        const val FILE_NAME: String = "share_data"

        /**
         *保存数据
         * @param context
         * @param key
         * @param obj obj:Any 同等与 java中的 Object obj
         */
        fun put(context: Context?, key: String?, obj: Any?) {
            val sp = context?.getSharedPreferences(
                FILE_NAME,
                Context.MODE_PRIVATE
            ) as SharedPreferences
            val editor: SharedPreferences.Editor = sp.edit()
            when (obj) {
                String -> {
                    editor.putString(key, obj as String?)
                }
                Int -> {
                    editor.putInt(key, obj as Int)
                }
                Boolean -> {
                    editor.putBoolean(key, obj as Boolean)
                }
                Float -> {
                    editor.putFloat(key, obj as Float)
                }
                Long -> {
                    editor.putLong(key, obj as Long)
                }
                else -> {
                    editor.putString(key, obj as String?)
                }
            }
            SharedPreferencesCompat.apply(editor)
        }

        /**
         * 获取数据
         */
        fun get(context: Context?, key: String?, any: Any?): Any? {
            val sp =
                context?.getSharedPreferences(key, Context.MODE_PRIVATE) as SharedPreferences
            when (any) {
                String -> {
                    sp.getString(key, any as String?)
                }
                Int -> {
                    sp.getInt(key, any as Int)
                }
                Boolean -> {
                    sp.getBoolean(key, any as Boolean)
                }
                Float -> {
                    sp.getFloat(key, any as Float)
                }
                Long -> {
                    sp.getLong(key, any as Long)
                }
                else -> {
                    sp.getString(key, any as String?)
                }
            }
            return null
        }

        /**
         * 移除key对应的值
         */
        fun remove(context: Context?, key: String?) {
            val sp =
                context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) as SharedPreferences
            val editor: SharedPreferences.Editor = sp.edit()
            editor.remove(key)
            SharedPreferencesCompat.apply(editor)
        }

        /**
         * 清除所有数据
         */
        fun clear(context: Context?) {
            val sp =
                context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) as SharedPreferences
            val editor: SharedPreferences.Editor = sp.edit()
            editor.clear()
            SharedPreferencesCompat.apply(editor)
        }

        /**
         * 判断key是否存在
         */
        fun contains(context: Context?, key: String?): Boolean {
            val sp =
                context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) as SharedPreferences
            return sp.contains(key)
        }

        /**
         * 返回所有的键值对
         */
        fun getAll(context: Context?): Map<String, *> {
            val sp =
                context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) as SharedPreferences
            return sp.all
        }

        /**
         * 解决 SharedPreferences.apply()兼容问题
         */
        private object SharedPreferencesCompat {
            val sApplyMethod = findApplyMethod() as Method?

            /**
             * 查找 apply
             */
            fun findApplyMethod(): Method? {
                try {
                    //SharedPreferences.Editor::class.java 等同于 SharedPreferences.Editor.class
                    val clz: Class<*> = SharedPreferences.Editor::class.java
                    return clz.getMethod("apply")
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
                return null
            }

            /**
             * 如果找到则使用apply执行，否则使用commit
             */
            fun apply(editor: SharedPreferences.Editor?) {
                try {
                    sApplyMethod?.invoke(editor)
                } catch (ex: java.lang.Exception) {
                    ex.printStackTrace()
                }

            }
        }
    }

}