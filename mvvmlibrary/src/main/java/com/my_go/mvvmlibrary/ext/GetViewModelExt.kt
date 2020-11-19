package com.my_go.mvvmlibrary.ext

import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.my_go.mvvmlibrary.base.BaseApp
import java.lang.NullPointerException

/**
 * Create by Package com.my_go.mvvmlibrary.ext
 * Created by 毛勇 on 2020/11/13
 * Current System Time 16:01
 * Describe:
 */

/***
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

/***
 * TODO://inline 将内联函数的函数体 复制到 调用处实现 内联（inline 内联函数）
 * TODO://reified 可以被具体化 （运用在 泛型类型 前面进行修饰）
 *
 * 获取Application 上下文的 ViewModel
 */
inline fun <reified VM : BaseViewModel> AppCompatActivity.getAppViewModel(): VM {
    (this.application as? BaseApp).let {
        when (it) {
            null -> {
                throw NullPointerException("你的Application没有继承框架自带的BaseApp类")
            }
            else -> {
                return it.getAppViewModelProvider().get(VM::class.java)
            }
        }
    }
}

/***
 * 提示，在fragment中调用该方法时，请在该Fragment onCreate以后调用
 * 或者请用by lazy方式懒加载初始化调用，不然会提示requireActivity没有导致错误
 */
inline fun <reified VM : BaseViewModel> Fragment.getAppViewModel(): VM {
    (this.requireActivity().application as? BaseApp).let {
        when (it) {
            null -> {
                throw NullPointerException("你的Application没有继承框架自带的BaseApp类")
            }
            else -> {
                return it.getAppViewModelProvider().get(VM::class.java)
            }
        }
    }
}

/***
 *得到当前 Activity 上下文的 ViewModel
 */
@Deprecated("已过时的方法，现在可以直接使用Ktx函数 viewmodels()获取")
inline fun <reified VM : BaseViewModel> AppCompatActivity.getViewModel(): VM {
    return ViewModelProvider(
        this,
        ViewModelProvider.AndroidViewModelFactory(application)
    ).get(VM::class.java)
}

@Deprecated("已过时的方法，现在可以直接使用Ktx函数 viewmodels()获取")
inline fun <reified VM : BaseViewModel> Fragment.getViewModel(): VM {

    return ViewModelProvider(
        this,
        ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
    ).get(VM::class.java)
}

/**
 *Fragment中得到 父类 Activity的共享ViewModel
 *TODO，在fragment中调用该方法时，
 * 请在该Fragment onCreate以后调用或者请用by lazy方式懒加载初始化调用，
 * 不然会提示requireActivity没有导致错误
 */
@Deprecated("已过时的方法，现在可以直接使用Ktx函数 activityViewModels()获取")
inline fun <reified VM : BaseViewModel> Fragment.getActivityViewModel(): VM {
    return ViewModelProvider(
        requireActivity(),
        ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
    ).get(VM::class.java)
}
