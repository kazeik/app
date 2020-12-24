package com.jygh.plugin

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ComponentName
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.jygh.Config
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import java.lang.reflect.Method
import java.util.*
import kotlin.concurrent.timerTask


/*
 * @author hope.chen, QQ:77132995, email:kazeik@163.com
 * 2020-12-21 10:38
 * 类说明:
 */
class LinkOpen(messenger: BinaryMessenger, private val activity: Activity) : MethodChannel.MethodCallHandler {
    private var channel = MethodChannel(messenger, Config.startApp)
    private var index = 0

    init {
        channel.setMethodCallHandler(this)
//        startTimer()
    }

    private fun startTimer() {
        var timer = Timer().schedule(timerTask {
            index++
            val map = mapOf("name" to "laomeng${index}",
                    "age" to "${index}"
            )
//            activity.runOnUiThread {
//                channel.invokeMethod("token", map)
//            }
            submitToken("1213213123123123123123123123123");
        }, 0, 1000)

    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Log.e("tag", "传过来的方法  =  ${call.method}")
        when (call.method) {
            "startActivityForResult" -> {
                try {
                    val intt = Intent()
                    intt.setClassName("com.jingsong.testvalue", "com.jingsong.testvalue.MainActivity")
//                    intt.setClassName("com.beyondbit.ias.zjjy", "io.dcloud.PandoraEntry")
                    intt.putExtra("appcode", "ghej")
//                    intt.addCategory(Intent.CATEGORY_LAUNCHER);
//                    intt.flags = Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK
//                    intt.component = ComponentName("com.beyondbit.ias.zjjy", "io.dcloud.PandoraEntry")

//                    activity.startActivity(intt)
                    activity.startActivityForResult(intt, RESULT_OK)
//                    submitToken("1213213123123123123123123123123")
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    fun submitToken(token: String?) {
        if (!TextUtils.isEmpty(token)) {
            activity.runOnUiThread {
                Log.e("tag", "准备提交的token = $token | channel = $channel")
                channel.invokeMethod("getToken", token)
            }
        }
    }
}