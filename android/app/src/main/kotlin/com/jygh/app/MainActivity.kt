package com.jygh.app

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.jygh.plugin.LinkOpen
import com.jygh.plugin.LinkOpenEvent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine


class MainActivity : FlutterActivity() {
    private var linkFactory: LinkOpen? = null
    private var linkEventFactory: LinkOpenEvent? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        linkFactory = LinkOpen(flutterEngine.dartExecutor.binaryMessenger, this)
        linkEventFactory = LinkOpenEvent(this, flutterEngine.dartExecutor.binaryMessenger)
    }

    override fun onResume() {
        super.onResume()
        getToken()
    }


    fun getToken() {
        val token = intent?.getStringExtra("token")
        Log.e("tag", "收到的token = $token")
        if (!TextUtils.isEmpty(token)) {
            Log.e("tag", "token = $token")
//            linkFactory?.submitToken(token)
            linkEventFactory?.submitToken(token)
        }
    }


//    fun startAction() {
//        try {
//            val intt = Intent(Intent.ACTION_MAIN);
//            intt.putExtra("appcode", "SSOdemo")
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.flags = Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK
//            intent.component = ComponentName("com.beyondbit.ias.zjjy", "io.dcloud.PandoraEntry")
//            startActivityForResult(intt, 100)
//            finish()
//        } catch (ex: Exception) {
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (intent == null) {

            return
        }

        if (!intent.hasExtra("token")) {
            return
        }
        if (requestCode == 100) {
            val token = intent.getStringExtra("token")
            Log.e("tag", "onActivityResult, token = $token")
        }
    }
}
