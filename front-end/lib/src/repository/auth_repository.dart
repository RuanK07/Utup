import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import '../routes/routes.dart';

class AuthRepository {
  Future<void> fazerLogin(
      BuildContext context, String email, String password) async {
    var url = Uri.parse('http://192.168.0.111:8080/security-api/auth');

    var response = await http.post(
      url,
      headers: {
        'Content-Type': 'application/json',
      },
      body: jsonEncode({'email': email, 'password': password}),
    );
    if (response.statusCode == 200) {
      final storage = FlutterSecureStorage();
      var responseBody = jsonDecode(response.body);
      var token = responseBody['token'];
      await storage.write(key: 'auth_token', value: '$token');

      print('Token: $token');

      Navigator.pushReplacementNamed(context, AppRoutes.home);
    }
  }
}
