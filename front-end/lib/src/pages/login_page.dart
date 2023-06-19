import 'package:flutter/material.dart';
import 'package:seutup/src/pages/register_page.dart';
import 'package:seutup/src/repository/auth_repository.dart';
import 'package:seutup/src/utils/colors.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final AuthRepository _authRepository = AuthRepository();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  Future<void> fazerLogin() async {
    String email = emailController.text;
    String password = passwordController.text;
    await _authRepository.fazerLogin(context, email, password);
  }

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
              Padding(
                padding: const EdgeInsets.only(bottom: 60, top: 10),
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
                            controller: emailController,
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
                            controller: passwordController,
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
                      onPressed: fazerLogin,
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Color.fromARGB(214, 255, 255,
                            255), // Set the background color to white
                      ),
                      child: const Text("Login",
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
                    "don't have an account?",
                    style: TextStyle(color: Colors.grey),
                  ),
                  SizedBox(
                    width: 5,
                  ),
                  GestureDetector(
                    onTap: () => Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const RegisterPage())),
                    child: const Text(
                      "Register here",
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
