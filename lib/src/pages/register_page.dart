import 'package:flutter/material.dart';

import '../utils/colors.dart';
import 'login_page.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SafeArea(
            child: SingleChildScrollView(
      child: Center(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 20),
          child: Column(
            children: [
              Image.asset(
                'assets/images/LogoUtup.png', // Replace with the path to your image
                width: 1000,
                height: 125,
              ),
              Padding(
                padding: const EdgeInsets.only(bottom: 40, top: 10),
                child: Column(
                  children: [
                    Text(
                      "Create New Account",
                      style: TextStyle(fontSize: 30),
                    ),
                    Text(
                      "Please fill all field",
                      style:
                          TextStyle(fontSize: 15, fontWeight: FontWeight.w400),
                    )
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(bottom: 66, top: 5),
                child: Column(
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
                                hintText: "fullname",
                                hintStyle: TextStyle(color: Colors.grey),
                                labelStyle: TextStyle(color: Colors.white),
                                border: InputBorder.none))),
                    const SizedBox(
                      height: 10,
                    ),
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
                                hintText: "username",
                                hintStyle: TextStyle(color: Colors.grey),
                                labelStyle: TextStyle(color: Colors.white),
                                border: InputBorder.none))),
                    const SizedBox(
                      height: 10,
                    ),
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
                                border: InputBorder.none))),
                    const SizedBox(
                      height: 10,
                    ),
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
                            obscureText: true,
                            style: const TextStyle(
                              color: Colors.white,
                            ),
                            decoration: const InputDecoration(
                                hintText: "password",
                                hintStyle: TextStyle(color: Colors.grey),
                                labelStyle: TextStyle(color: Colors.white),
                                border: InputBorder.none)))
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(bottom: 20),
                child: Container(
                  height: 50,
                  width: double.infinity,
                  decoration:
                      BoxDecoration(borderRadius: BorderRadius.circular(100)),
                  child: ElevatedButton(
                      onPressed: () {},
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Color.fromARGB(214, 255, 255,
                            255), // Set the background color to white
                      ),
                      child: const Text("Register",
                          style: TextStyle(
                              color: primaryBlack,
                              fontWeight: FontWeight.bold,
                              fontSize: 16))),
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Text(
                    "Alredy have an account?",
                    style: TextStyle(color: Colors.grey),
                  ),
                  SizedBox(
                    width: 5,
                  ),
                  GestureDetector(
                    onTap: () => Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const LoginPage())),
                    child: const Text(
                      "Just login here",
                      style: TextStyle(
                          color: Colors.white, fontWeight: FontWeight.bold),
                    ),
                  )
                ],
              )
            ],
          ),
        ),
      ),
    )));
  }
}
