import 'package:flutter/material.dart';
import 'package:seutup/src/routes/routes.dart';
import 'package:seutup/src/utils/colors.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        scaffoldBackgroundColor: primaryBlack,
        textTheme:
            const TextTheme(bodyLarge: TextStyle(), bodyMedium: TextStyle())
                .apply(bodyColor: Colors.white),
      ),
      initialRoute: AppRoutes.login,
      routes: AppRoutes.routes,
    );
  }
}
