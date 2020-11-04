package com.my_go.mylibrary.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.my_go.mylibrary.interfaces.OnFinishListener
import com.my_go.mylibrary.utils.EditTextUtils
import org.greenrobot.eventbus.EventBus

/**
 * Create by Package com.my_go.mylibrary.base
 * Created by 毛勇 on 2020/10/29
 * Current System Time 13:34
 * Describe:
 */
abstract class BaseFragmentActivity<T : BaseView, P : BasePresenter<T>> : AppCompatActivity(),
    OnFinishListener {

    private var mListener: OnFinishListener? = null
    private var view: View? = null
    var toGetWindowTokenView: View? = null
    protected var bPresenter: BasePresenter<T>? = null

    //判断是否长按
    private var isKeyLongPress: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindLayout(), this)
        when {
            null == bPresenter -> {
                bPresenter = bindPresenter()
            }
        }
        bPresenter?.onAttach(this as T)
    }

    abstract fun bindPresenter(): BasePresenter<T>

    abstract fun bindLayout(): Int

    private fun setContentView(bindLayout: Int, listener: OnFinishListener) {
        super.setContentView(bindLayout)
        this.mListener = listener
        view = LayoutInflater.from(this).inflate(bindLayout, null);
    }

    override fun onStart() {
        super.onStart()
    }


    override fun finish() {
        super.finish()
        runOnUiThread(
            Runnable {
                kotlin.run {
                    when {
                        toGetWindowTokenView != null -> {
                            EditTextUtils.hindKeyboard(this, toGetWindowTokenView)
                        }
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        bPresenter?.onDetch()
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        isKeyLongPress = true
        return true
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (isKeyLongPress) {
            isKeyLongPress = false
            return true
        }
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                when {
                    null != mListener -> {
                        mListener!!.finish()
                    }

                }
            }
        }
        return super.onKeyUp(keyCode, event)
    }

}