/*
 * @author hope.chen, QQ:77132995, email:kazeik@163.com
 * 2020-12-21 13:12
 * 类说明:
 */

import 'dart:io';

import 'package:app/Config.dart';
import 'package:flutter/services.dart';

class LinkOpen {
  MethodChannel _channel = const MethodChannel(Config.startApp);
  EventChannel _eventChannel = const EventChannel(Config.resultApp);

  startActivityForResult() {
    if (Platform.isAndroid) {
      _channel.invokeMethod("startActivityForResult");
    }
  }

  /*
   * Flutter 向 原生中 发送消息 的方法
   * method 为方法标识
   * arguments 为参数
   */
  Future<dynamic> invokNative(String method, {Map arguments}) async {
    if (arguments == null) {
      //无参数发送消息
      return await _channel.invokeMethod(method);
    } else {
      //有参数发送消息
      return await _channel.invokeMethod(method, arguments);
    }
  }
}
