import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          child: Center(
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
              child: Column(children: [
                Column(children: [
                  Text(
                    "Welcome to Utup!",
                    style: TextStyle(fontSize: 30),
                  ),
                  Text(
                    "Please login before you go in",
                    style: TextStyle(fontSize: 15, fontWeight: FontWeight.w400),
                  )
                ]),
              ]),
            ),
          ),
        ),
      ),
    );
  }
}
