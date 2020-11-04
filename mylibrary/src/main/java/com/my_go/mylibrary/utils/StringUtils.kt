package com.my_go.mylibrary.utils

import android.widget.TextView
import java.io.File
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Create by Package com.my_go.mylibrary.utils
 * Created by 毛勇 on 2020/10/30
 * Current System Time 15:26
 * Describe:
 */
object StringUtils {
    const val EMPTY = "无"
    const val UNKNOWN = "未知"
    const val UNLIMITED = "不限"
    const val I = "我"
    const val YOU = "你"
    const val HE = "他"
    const val SHE = "她"
    const val IT = "它"

    const val MALE = "男"
    const val FEMALE = "女"

    const val TODO = "未完成"
    const val DONE = "已完成"

    const val FAIL = "失败"
    const val SUCCESS = "成功"

    const val SUNDAY = "日"
    const val MONDAY = "一"
    const val TUESDAY = "二"
    const val WEDNESDAY = "三"
    const val THURSDAY = "四"
    const val FRIDAY = "五"
    const val SATURDAY = "六"

    const val YUAN = "元"

    private var currentString: String? = ""

    fun getCurrentString(): String? {
        return when (currentString) {
            null -> {
                "";
            }
            else -> {
                currentString
            }
        }
    }

    //TODO:获取String ------------------------------------------
    fun getString(tv: TextView?): String? {
        return if (null == tv || null == tv.text) {
            "";
        } else {
            getString(tv.text)
        }
    }

    fun getString(text: CharSequence?): String? {
        return when (text) {
            null -> {
                ""
            }
            else -> {
                text.toString()
            }
        }
    }

    fun getString(any: Any?): String? {
        return when (any) {
            null -> {
                "";
            }
            else -> {
                getString(any as? String)
            }
        }
    }

    fun getString(tv: String?): String? {
        return when (tv) {
            null -> {
                ""
            }
            else -> {
                tv
            }
        }
    }
    //TODO:获取String ------------------------------------------

    //TODO:去除前后空格 ------------------------------------------
    fun getTrimedString(tv: TextView?): String? {
        return getTrimedString(getString(tv?.text))
    }

    fun getTrimedString(cs: CharSequence?): String? {
        return getTrimedString(getString(cs))
    }

    fun getTrimedString(any: Any?): String? {
        return getTrimedString(getString(any))
    }

    fun getTrimedString(str: String?): String? {
        return getTrimedString(str)?.trim()
    }
    //TODO:去除前后空格 ------------------------------------------

    //TODO:去除所有空格 ------------------------------------------
    fun getNoBlankString(tv: TextView?): String? {
        return getNoBlankString(getString(tv?.text))

    }

    fun getNoBlankString(cs: CharSequence?): String? {
        return getNoBlankString(getString(cs))
    }

    fun getNoBlankString(any: Any?): String? {
        return getNoBlankString(getString(any))
    }

    fun getNoBlankString(str: String?): String? {
        return when (str) {
            null -> {
                ""
            }
            else -> {
                getString(str)?.replace(" ", "")
            }
        }
    }
    //TODO:去除所有空格 ------------------------------------------

    //TODO:获取字符串长度-------------------------------------------
    fun getLength(tv: TextView?, trim: Boolean?): Int? {
        return getLength(getString(tv), trim)
    }

    fun getLength(cs: CharSequence?, trim: Boolean?): Int? {
        return getLength(getString(cs), trim)
    }

    fun getLength(any: Any?, trim: Boolean?): Int? {
        return getLength(getString(any), trim)
    }

    /***
     * @param trim 是否去除所有空格
     *        true 去除所有空格
     *        false 保留所有空格
     *@return 长度
     */
    fun getLength(text: String?, trim: Boolean?): Int? {
        return when (trim) {
            true -> {
                getNoBlankString(text)?.length
            }
            else -> {
                getString(text)?.length
            }
        }
    }
    //TODO:获取字符串长度-------------------------------------------

    //TODO:判断字符串是否非空-----------------------------------------

    fun isNotEmpty(tv: TextView?, trim: Boolean?): Boolean? {
        return isNotEmpty(getString(tv?.text), trim)
    }

    fun isNotEmpty(cs: CharSequence?, trim: Boolean?): Boolean? {
        return isNotEmpty(getString(cs), trim)
    }

    fun isNotEmpty(any: Any?, trim: Boolean?): Boolean? {
        return isNotEmpty(getString(any), trim)
    }

    /**
     * 判断字符串是否非空
     * @param str
     * @param trim
     *@return false ==NULL true 不为空
     */
    fun isNotEmpty(str: String?, trim: Boolean?): Boolean? {
        var s = str
        when (s) {
            null -> {
                return false
            }
        }
        when (trim) {
            true -> {
                s = s?.trim()
            }
        }
        when {
            s?.length!! <= 0 -> {
                return false
            }
        }
        currentString = s
        return true
    }
    //TODO:判断字符串是否非空-----------------------------------------


    /**
     *判断手机格式是否正确
     * @param phone 手机号
     * @return true 手机号 false 非手机号
     */
    fun isPhone(phone: String?): Boolean {
        if (isNotEmpty(phone, true) == false) {
            return false
        }
        val p: Pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-2,5-9])|(17[0-9]))\\d{8}$")
        currentString = phone
        return p.matcher(phone).matches()
    }

    /***
     *判断email格式是否正确
     * @param email
     * @return false 非邮箱 true 邮箱
     */
    fun isEmail(email: String?): Boolean {
        if (isNotEmpty(email, true) == false) {
            return false
        }
        val str =
            "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"
        val p: Pattern = Pattern.compile(str)
        currentString = email
        return p.matcher(email).matches()
    }

    /***
     *判断是否全是数字
     * @param number
     */
    fun isNumer(number: String?): Boolean {
        if (isNotEmpty(number, true) == false) {
            return false
        }
        val pattern: Pattern = Pattern.compile("[0-9]*")
        val isNum: Matcher = pattern.matcher(number)
        if (!isNum.matches()) {
            return false
        }
        currentString = number
        return true
    }

    /**判断字符类型是否是号码或字母
     * @param inputed
     * @return
     */
    fun isNumberOrAlpha(inputed: String?): Boolean {
        if (inputed == null) {
            return false
        }
        val pNumber: Pattern = Pattern.compile("[0-9]*")
        var mNumber: Matcher
        val pAlpha: Pattern = Pattern.compile("[a-zA-Z]")
        var mAlpha: Matcher
        for (i in 0 until inputed.length) {
            mNumber = pNumber.matcher(inputed.substring(i, i + 1))
            mAlpha = pAlpha.matcher(inputed.substring(i, i + 1))
            if (!mNumber.matches() && !mAlpha.matches()) {
                return false
            }
        }
        currentString = inputed
        return true
    }

    /**判断字符类型是否是身份证号
     * @param idCard
     * @return false 非身份证号 true 身份证号
     */
    fun isIDCard(idCard: String?): Boolean {
        var idCard = idCard
        if (isNumberOrAlpha(idCard) == false) {
            return false
        }
        idCard = getString(idCard)
        if (idCard!!.length == 15) {
            currentString = idCard
            return true
        }
        if (idCard.length == 18) {
            currentString = idCard
            return true
        }
        return false
    }

    val HTTP: String? = "http"
    val URL_PREFIX: String? = "http://"
    val URL_PREFIXs: String? = "https://"
    val URL_STAFFIX: String? = URL_PREFIX
    val URL_STAFFIXs: String? = URL_PREFIXs
    val FILE_PATH_PREFIX: String? = "file://"

    /**
     * 判断是否为url
     *
     * @return false 非Url true 是Url
     */
    fun isUrl(url: String?): Boolean? {
        return when (isNotEmpty(url, true)) {
            true -> {
                when (url?.startsWith(URL_STAFFIX!!)!! or url.startsWith(URL_STAFFIXs!!)) {
                    true -> {
                        currentString = url
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
            else -> {
                false
            }
        }
    }

    /***
     * 文件路径是否存在
     */
    fun isFilePathExist(path: String?): Boolean? {
        return isFilePath(path)?.and(File(path).exists())
    }

    /***
     * 判断是否为文件路径
     * @return true 是文件路径
     *      false 不是文件路径
     */
    fun isFilePath(path: String?): Boolean? {
        return when (isNotEmpty(path, true)) {
            true -> {
                when (!path?.contains(".")!! or path.endsWith(".")) {
                    true -> {
                        false
                    }
                    else -> {
                        currentString = path
                        true
                    }
                }
            }
            else -> {
                false
            }
        }
    }

    fun getNumber(tv: TextView?): String? {
        return getNumber(getString(tv?.text))
    }

    fun getNumber(cs: CharSequence?): String? {
        return getNumber(getString(cs))
    }

    fun getNumber(any: Any?): String? {
        return getNumber(getString(any))
    }

    /**
     * 去除字符串内所有非数字类型字符
     */
    fun getNumber(s: String?): String? {
        when (isNotEmpty(s, true)) {
            false -> {
                return ""
            }
            else -> {
                var numberString: String? = ""
                var single: String? = null
                //for(i in s?.indices) 等同于for(int i =0;s.length;i++)
                for (i in s?.indices!!) {
                    single = s.substring(i, i + 1)
                    when {
                        isNumer(single) -> {
                            numberString += single
                        }
                    }
                }
                return numberString
            }
        }
    }

    //TODO:自动补全 网址----------------------------------------------
    fun getCorrectUrl(tv: TextView?): String? {
        return getCorrectUrl(getString(tv?.text))
    }

    /**
     * @param url
     * @return 获取网址，自动补全
     */
    fun getCorrectUrl(url: String?): String? {
        var tempUrl: String? = url
        return when (isNotEmpty(tempUrl, true)) {
            false -> {
                ""
            }
            else -> {
                when {
                    (!tempUrl?.endsWith("/")!! && !tempUrl?.endsWith(".html")) -> {
                        //"$url/" 等用于 url+"/"
                        tempUrl = "$url/"
                    }
                }
                when {
                    isUrl(tempUrl) == false -> {
                        return URL_PREFIX + tempUrl
                    }
                }
                return url
            }
        }

    }
    //TODO:自动补全 网址----------------------------------------------

    //TODO：去掉空格，"-","+86" 后的电话号码----------------------------------------------
    fun getCorrectPhone(tv: TextView?): String? {
        return getCorrectPhone(getString(tv))
    }

    fun getCorrectPhone(phone: String?): String? {
        var tempPhone: String? = phone

        return when (isNotEmpty(tempPhone, true)) {
            false -> {
                ""
            }
            else -> {
                //去除所有空格
                tempPhone = getNoBlankString(tempPhone)
                tempPhone = tempPhone?.replace("-", "")
                when {
                    tempPhone?.startsWith("+86")!! -> {
                        tempPhone = tempPhone.substring(3)
                    }
                }
                return tempPhone
            }
        }
    }
    //TODO：去掉空格，"-","+86" 后的电话号码----------------------------------------------

    //TODO:获取邮箱，自动补全----------------------------------------------

    fun getCorrectEmail(tv: TextView?): String? {
        return getCorrectEmail(getString(tv))
    }

    fun getCorrectEmail(email: String?): String? {
        var tempEmail: String? = email
        return when (isNotEmpty(tempEmail, true)) {
            false -> {
                ""
            }
            else -> {
                tempEmail = getNoBlankString(tempEmail)
                when {
                    (!isEmail(tempEmail) && !tempEmail?.endsWith(".com")!!) -> {
                        tempEmail += ".com"
                    }
                }
                return tempEmail
            }
        }
    }
    //TODO:获取邮箱，自动补全----------------------------------------------


    //TODO:获取价格，保留小数----------------------------------------

    const val PRICE_FORMAT_DEFAULT = 0
    const val PRICE_FORMAT_PREFIX = 1
    const val PRICE_FORMAT_SUFFIX = 2
    const val PRICE_FORMAT_PREFIX_WITH_BLANK = 3
    const val PRICE_FORMAT_SUFFIX_WITH_BLANK = 4
    val PRICE_FORMATS = arrayOf(
        "", "￥", "元", "￥ ", " 元"
    )

    fun getPrice(price: String?): String? {
        return getPrice(price, PRICE_FORMAT_SUFFIX)
    }

    fun getPrice(price: String?, formatType: Int?): String? {
        var tempPrice: String? = price
        return when (isNotEmpty(tempPrice, true)) {
            false -> {
                getPrice(0, formatType)
            }
            else -> {
                var correctPrice: String? = ""
                var s: String?
                for (i in tempPrice?.indices!!) {
                    s = tempPrice.substring(i, i + 1)
                    when {
                        ("." == s || isNumer(s)) -> {
                            correctPrice += s
                        }
                    }
                }
                when {
                    correctPrice?.contains(".")!! -> {
                        when {
                            correctPrice.endsWith(".") -> {
                                correctPrice = correctPrice.replace(".", "")
                            }
                        }
                    }
                }
                when (isNotEmpty(correctPrice, true)) {
                    true -> {
                        getPrice(BigDecimal((correctPrice.plus(""))), formatType)
                    }
                    else -> {
                        getPrice(0, formatType)
                    }
                }
            }
        }
    }

    fun getPrice(price: Any?, formatType: Int?): String? {
        var s: String? = DecimalFormat("#########").format(price)
        return when (formatType) {
            PRICE_FORMAT_PREFIX -> {
                PRICE_FORMATS[PRICE_FORMAT_PREFIX] + s
            }
            PRICE_FORMAT_SUFFIX -> {
                s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX]
            }
            PRICE_FORMAT_PREFIX_WITH_BLANK -> {
                PRICE_FORMATS[PRICE_FORMAT_PREFIX_WITH_BLANK] + s
            }
            PRICE_FORMAT_SUFFIX_WITH_BLANK -> {
                s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX_WITH_BLANK]
            }
            else -> {
                s
            }
        }
    }

    //TODO:检查字符串长度，如果字符串的长度超过maxLength，就截取前maxLength个字符串并在末尾拼上appendString

    fun checkLength(str: String?, maxLength: Int): String? {
        return checkLength(str, maxLength, "...")
    }

    fun checkLength(str: String?, maxLength: Int, appendString: String): String? {
        var tempStr: String? = str
        when {
            (tempStr?.length!! > maxLength) -> {
                tempStr = tempStr.substring(0, maxLength)
                when {
                    appendString != null -> {
                        tempStr += appendString
                    }
                }
            }
        }
        return tempStr
    }

    /**
     * 判断字符串是否为空 为空即true
     *
     * @param str 字符串
     * @return
     */
    fun isNullString(str: String?): Boolean {
        return str == null || str.length == 0 || "null" == str
    }


    /**
     * 将字符串格式化为带两位小数的字符串
     *
     * @param str 字符串
     * @return
     */
    fun format2Decimals(str: String): String? {
        val df = DecimalFormat("#.00")
        return if (df.format(stringToDouble(str)).startsWith(".")) {
            "0" + df.format(stringToDouble(str))
        } else {
            df.format(stringToDouble(str))
        }
    }

    /**
     * 字符串转换成double ,转换失败将会 return 0;
     *
     * @param str 字符串
     * @return
     */
    fun stringToDouble(str: String?): Double {
        return when (isNullString(str)) {
            true -> {
                0.0
            }
            false -> {
                return try {
                    str!!.toDouble()
                } catch (e: java.lang.NumberFormatException) {
                    0.0
                }
            }
        }
    }

    /**
     * 字符串转换成整数 ,转换失败将会 return 0;
     *
     * @param str 字符串
     * @return
     */
    fun stringToInt(str: String): Int {
        return if (isNullString(str)) {
            0
        } else {
            try {
                str.toInt()
            } catch (e: NumberFormatException) {
                0
            }
        }
    }


    /**
     * 读取baseurl
     * @param url
     * @return
     */
    fun getBasUrl(url: String): String? {
        var url = url
        var head = ""
        var index = url.indexOf("://")
        if (index != -1) {
            head = url.substring(0, index + 3)
            url = url.substring(index + 3)
        }
        index = url.indexOf("/")
        if (index != -1) {
            url = url.substring(0, index + 1)
        }
        return head + url
    }
}