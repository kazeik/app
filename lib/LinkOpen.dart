/*
 * @author hope.chen, QQ:77132995, email:kazeik@163.com
 * 2020-12-21 13:12
 * 类说明:
 */

import 'dart:io';

import 'package:flutter/services.dart';

class LinkOpen {
  MethodChannel _channel = const MethodChannel("com.jingsong.app.linkopen");

  EventChannel _eventChannel = const EventChannel("com.jingsong.app.linkopenevent");

  addMethodListener(Function event) {
    return _channel.setMethodCallHandler(event);
  }

  addEventListener() {
    _eventChannel.receiveBroadcastStream().listen((event) {
      print("从原生传过消息来了,值 = $event");
    });
  }

  Future<String> startActivityForResult() async {
    if (Platform.isAndroid) {
      dynamic value = await _channel.invokeMethod("startActivityForResult");
      print("得到的返回值 ： $value");
      return value;
    } else {
      return "";
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
