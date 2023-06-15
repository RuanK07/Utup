import 'package:flutter/material.dart';
import 'package:seutup/src/utils/colors.dart';

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
          padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 50),
          child: Column(
            children: [
              Image.asset(
                'assets/images/LogoUtup.png', // Replace with the path to your image
                width: 1000,
                height: 250,
              ),
              Padding(
                padding: const EdgeInsets.only(bottom: 40),
                child: Column(
                  children: [
                    Text(
                      "Welcome to Utup!",
                      style: TextStyle(fontSize: 30),
                    ),
                    Text(
                      "Please login before you go in",
                      style:
                          TextStyle(fontSize: 15, fontWeight: FontWeight.w400),
                    )
                  ],
                ),
              ),
              Column(
                children: [
                  Container(
                      padding: const EdgeInsets.symmetric(
                          vertical: 5, horizontal: 15),
                      decoration: BoxDecoration(
                          color: secondaryBlue,
                          borderRadius: BorderRadius.circular(10),
                          boxShadow: const [
                            BoxShadow(
                                blurRadius: 10.0,
                                offset: Offset(5, 1),
                                spreadRadius: 0,
                                color: Colors.black38)
                          ]),
                      child: TextFormField(
                          style: const TextStyle(
                            color: Colors.white,
                          ),
                          decoration: const InputDecoration(
                              hintText: "email",
                              hintStyle: TextStyle(color: Colors.grey),
                              labelStyle: TextStyle(color: Colors.white),
                              border: InputBorder.none)))
                ],
              )
            ],
          ),
        ),
      ),
    )));
  }
}
