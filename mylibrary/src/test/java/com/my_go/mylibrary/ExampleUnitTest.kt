package com.my_go.mylibrary

import android.util.Log
import com.my_go.mylibrary.utils.StringUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        System.out.println(StringUtils.getPrice("12"))
    }
}