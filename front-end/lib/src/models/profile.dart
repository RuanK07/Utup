import 'package:seutup/src/models/post.dart';
import 'package:seutup/src/models/user.dart';

class UserProfile {
  String uid;
  User user;
  String photoUrl;
  String aboutMe;
  List<String> following;
  List<String> followers;
  List<Post> posts;

  UserProfile({
    required this.uid,
    required this.user,
    required this.photoUrl,
    required this.aboutMe,
    required this.following,
    required this.followers,
    required this.posts,
  });
}
