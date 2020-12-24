import 'package:app/Config.dart';
import 'package:flutter/material.dart';
import 'package:app/LinkOpen.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  LinkOpen linkOpen = LinkOpen();
  MethodChannel _channel = const MethodChannel(Config.startApp);
  EventChannel _eventChannel = const EventChannel(Config.resultApp);

  String _token = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("测试"),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            FlatButton(
              onPressed: () {
                linkOpen.startActivityForResult();
              },
              child: Text("调app"),
            ),
            Text("得到的token = $_token")
          ],
        ),
      ),
    );
  }

  @override
  void initState() {
    super.initState();

    _eventChannel.receiveBroadcastStream().listen((event) {
      print("从原生传过消息来了,值 = $event");
    });

    _channel.setMethodCallHandler((resultCall) async {
      String method = resultCall.method;
      print("从原生传过消息来了 method = $method ");
      if (method == "getToken") {
        String value = resultCall.arguments;
        print("可以获取token了 ,$value");
        setState(() {
          _token = value;
        });
        return value;
      } else {
        return null;
      }
    });

    // linkOpen.addEventListener();
    //
    // linkOpen.addMethodListener((resultCall) {
    //   String method = resultCall.method;
    //   Map argume = resultCall.arguments;
    //   print("从原生传过消息来了 method = $method ,arguments = $argume");
    //   if (method == "getToken") {
    //     print("可以获取token了");
    //   }
    // });
  }
}
