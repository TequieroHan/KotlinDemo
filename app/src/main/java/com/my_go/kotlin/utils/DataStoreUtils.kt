package com.my_go.kotlin.utils

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Create by Package com.my_go.kotlin.utils
 * Created by 毛勇 on 2020/11/24
 * Current System Time 15:17
 * Describe:
 */
object DataStoreUtils {

    private var dataStore: DataStore<Preferences>? = null
    private val KOTLIN_DATA_STORE = "dataStore"
    private val KEY_COLOR = preferencesKey<Int>(name = "color")
    private val KEY_TOP = preferencesKey<Boolean>("top")

    /**
     * 写入
     */
    suspend fun writeToDataStore(
        context: Context,
        defaultColor: Int
    ) {
        if (dataStore == null) {
            dataStore = context.createDataStore(KOTLIN_DATA_STORE)
        }
        dataStore!!.edit { user -> user[KEY_COLOR] = defaultColor }
    }

    /***
     * 读取储存
     * @param defaultColor 默认颜色
     */
    fun readToDataStore(
        context: Context, defaultColor: Int
    ): Int {
        var color: Int = defaultColor
        if (dataStore == null) {
            dataStore = context.createDataStore(KOTLIN_DATA_STORE)
        }
        dataStore!!.data.map { value ->
            color = value[KEY_COLOR] ?: defaultColor
        }
        return color

    }

    /**
     *获取是否请求置顶
     */
    fun getRequestTop(context: Context): Flow<Boolean> {
        if (dataStore == null) {
            dataStore = context.createDataStore(KOTLIN_DATA_STORE)
        }
        return dataStore!!.data.map { value -> value[KEY_TOP] ?: false }
    }

}