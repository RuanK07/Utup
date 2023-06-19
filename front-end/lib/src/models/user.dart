class User {
  String uid;
  String username;
  String email;
  String password;
  DateTime registrationMoment;
  String role;

  User(
      {required this.uid,
      required this.username,
      required this.email,
      required this.password,
      required this.registrationMoment,
      required this.role});

  Map<String, dynamic> toJson() => {
        "uid": uid,
        "username": username,
        "email": email,
        "password": password,
        "registrationMoment": registrationMoment,
        "role": role
      };
}
