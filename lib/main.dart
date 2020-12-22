import 'package:flutter/material.dart';
import 'package:app/LinkOpen.dart';

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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("测试"),
      ),
      body: Center(
          child: FlatButton(
        onPressed: () {
          linkOpen.startActivityForResult();
        },
        child: Text("调app"),
      )),
    );
  }

  @override
  void initState() {
    super.initState();

    linkOpen.addEventListener();

    linkOpen.addMethodListener((resultCall) {
      String method = resultCall.method;
      Map argume = resultCall.arguments;
      print("从原生传过消息来了 method = $method ,arguments = $argume");
      if (method == "getToken") {
        print("可以获取token了");
      }
    });
  }
}
