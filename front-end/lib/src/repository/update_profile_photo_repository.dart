import 'package:http/http.dart' as http;

class ProfileApi {
  static const baseUrl =
      'http://192.168.0.111:8081/social-network/profiles/{profileId}/photo';

  static Future<void> updateProfilePhoto(
      String profileId, List<int> photoBytes) async {
    final url = '$baseUrl/$profileId/photo';
    final response = await http.put(Uri.parse(url), body: photoBytes);
    if (response.statusCode == 200) {
      print('Foto de perfil atualizada');
    } else {
      print('Erro ao atualizar a foto de perfil');
    }
  }
}
