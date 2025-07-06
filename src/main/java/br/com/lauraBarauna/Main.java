package br.com.lauraBarauna;

import br.com.lauraBarauna.controller.address.AddressController;
import br.com.lauraBarauna.controller.user.UserController;
import br.com.lauraBarauna.dto.user.UserResponseDTO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Controllers
        UserController userController = new UserController();
        AddressController addressController = new AddressController();

        Scanner scanner = new Scanner(System.in);
        UserResponseDTO loggedUser = null;
        int option;

        // Menu principal
        do {
            System.out.println("O que você quer fazer: ");
            System.out.println("1 - Logar");
            System.out.println("2 - Nada ainda");
            System.out.println("3 - Sair");
            option = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (option) {
                case 1:
                    // Login
                    System.out.println("Digite o e-mail do usuário: ");
                    String email = scanner.nextLine();
                    System.out.println("Digite a senha do usuário: ");
                    String senha = scanner.nextLine();
                    System.out.println();

                    loggedUser = userController.loginUser(email, senha);

                    if (loggedUser != null) {
                        System.out.println("Login realizado com sucesso!\n");

                        // Menu do usuário logado
                        int opcaoUsuario;
                        do {
                            System.out.println("O que deseja fazer:");
                            System.out.println("101 - Criar Endereço");
                            System.out.println("102 - Listar Enderecos");
                            System.out.println("103 - Deslogar");

                            opcaoUsuario = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();

                            switch (opcaoUsuario) {
                                case 101:
                                    System.out.println("Estado: ");
                                    String estado = scanner.nextLine();
                                    System.out.println("Cidade: ");
                                    String cidade = scanner.nextLine();
                                    System.out.println("Rua: ");
                                    String rua = scanner.nextLine();
                                    System.out.println("Número: ");
                                    String numero = scanner.nextLine();
                                    System.out.println("Complemento: ");
                                    String complemento = scanner.nextLine();
                                    System.out.println("Bairro: ");
                                    String bairro = scanner.nextLine();
                                    System.out.println("CEP: ");
                                    String cep = scanner.nextLine();

                                    addressController.createAddress(
                                            loggedUser.getId(),
                                            estado, cidade, rua, numero,
                                            complemento, bairro, cep
                                    );
                                    System.out.println("Endereço criado com sucesso!\n");
                                    break;

                                case 102:
                                    addressController.showAllUsersAddress(loggedUser.getId());
                                    break;

                                case 103:
                                    System.out.println("Deslogando...\n");
                                    loggedUser = null;
                                    break;

                                default:
                                    System.out.println("Opção inválida.\n");
                            }
                        } while (loggedUser != null); // continua no menu do usuário até deslogar
                    } else {
                        System.out.println("Login falhou. Verifique as credenciais.\n");
                    }
                    break;

                case 2:
                    System.out.println("Função ainda não implementada.\n");
                    break;

                case 3:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.\n");
            }

        } while (option != 3);

        scanner.close();
    }
}
