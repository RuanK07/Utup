class Post {
  String uid;
  String username;
  DateTime postDate;
  String title;
  List<String> photos;
  List<String> videos;
  int likes;

  Post({
    required this.uid,
    required this.username,
    required this.postDate,
    required this.title,
    required this.photos,
    required this.videos,
    required this.likes,
  });

  Map<String, dynamic> toJson() => {
        "uid": uid,
        "username": username,
        "postDate": postDate,
        "title": title,
        'photos': photos,
        'videos': videos,
        'likes': likes,
      };
}
