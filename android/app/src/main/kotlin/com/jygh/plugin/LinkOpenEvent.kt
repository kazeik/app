package com.jygh.plugin

import android.app.Activity
import android.text.TextUtils
import android.util.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.timerTask

/*
 * @author hope.chen, QQ:77132995, email:kazeik@163.com
 * 2020-12-22 11:36
 * 类说明:
 */
class LinkOpenEvent(var activity: Activity, messenger: BinaryMessenger) : EventChannel.StreamHandler {
    private val channel: EventChannel = EventChannel(messenger, "com.jingsong.app.linkopenevent")
    private var events: EventChannel.EventSink? = null
    private var index = 0

    init {
        channel.setStreamHandler(this)
//        startTimer()
//        submitToken("12312312312312312312312312313");
    }

    fun startTimer() {
        var timer = Timer().schedule(timerTask {
            index++
            val map = mapOf("name" to "laomeng${index}",
                    "age" to "${index}"
            )
            activity.runOnUiThread {
                events.success(map)
            }
        }, 0, 1000)

    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        this.events = events
    }

    override fun onCancel(arguments: Any?) {
        this.events = null
    }

    fun submitToken(token: String?) {
        if (!TextUtils.isEmpty(token)) {
//            activity.runOnUiThread {
            val map = HashMap<String, Any>()
            map["token"] = token!!
            Log.e("tag", "准备发送数据 $map")
            events.success(map)
//            }
        }
    }
}