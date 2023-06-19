import 'package:flutter/material.dart';

import '../pages/favorite_page.dart';
import '../pages/home_page.dart';
import '../pages/login_page.dart';
import '../pages/post_page.dart';
import '../pages/profile_page.dart';
import '../pages/register_page.dart';
import '../pages/search_page.dart';

class AppRoutes {
  static const String home = '/home';
  static const String login = '/login';
  static const String register = '/register';
  static const String search = '/search';
  static const String post = '/post';
  static const String favorite = '/favorite';
  static const String profile = '/profile';

  static Map<String, WidgetBuilder> routes = {
    home: (context) => HomePage(),
    login: (context) => LoginPage(),
    register: (context) => RegisterPage(),
    search: (context) => SearchPage(),
    post: (context) => PostPage(),
    favorite: (context) => FavoritePage(),
    profile: (context) => ProfilePage()
    // Outras rotas do seu aplicativo
  };
}
