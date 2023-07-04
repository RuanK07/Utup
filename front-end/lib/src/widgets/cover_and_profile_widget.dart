import 'package:flutter/material.dart';

class CoverAndProfileWidget extends StatelessWidget {
  final Size size;

  const CoverAndProfileWidget({required this.size});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 200,
      width: size.width,
      child: Stack(
        children: [
          SizedBox(
            height: 170,
            width: size.width,
            child: FutureBuilder<String>(
              future:
                  fetchProfileImage(), // Função para buscar a imagem de perfil do usuário
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  // Enquanto a imagem está carregando, pode mostrar um indicador de carregamento
                  return CircularProgressIndicator();
                } else if (snapshot.hasError) {
                  // Se ocorrer um erro ao buscar a imagem, pode mostrar uma mensagem de erro ou uma imagem padrão
                  return Icon(Icons.error);
                } else {
                  // Uma vez que a imagem está carregada, pode mostrá-la no perfil
                  return CircleAvatar(
                    backgroundImage: NetworkImage(snapshot.data!),
                  );
                }
              },
            ),
          ),
          Positioned(
            right: 0,
            child: IconButton(
              onPressed: () {
                // Lógica para abrir as configurações do perfil
              },
              icon:
                  Icon(Icons.dashboard_customize_outlined, color: Colors.white),
            ),
          ),
          Positioned(
            right: 40,
            child: IconButton(
              splashRadius: 20,
              onPressed: () {
                // Lógica para atualizar a imagem de capa
              },
              icon: Icon(Icons.add_box_outlined, color: Colors.white),
            ),
          ),
        ],
      ),
    );
  }

  Future<String> fetchProfileImage() {
    // Lógica para buscar a imagem de perfil do usuário (pode ser uma chamada de API, acesso ao banco de dados, etc.)
    return Future.delayed(Duration(seconds: 2), () {
      // Simulação de uma chamada assíncrona que retorna a URL da imagem
      return 'https://localhost:8081/profiles/';
    });
  }
}
