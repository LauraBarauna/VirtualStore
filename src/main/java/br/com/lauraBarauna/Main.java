package br.com.lauraBarauna;

import br.com.lauraBarauna.controller.address.AddressController;
import br.com.lauraBarauna.controller.user.UserController;
import br.com.lauraBarauna.dto.user.UserResponseDTO;
import br.com.lauraBarauna.service.ServiceFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Controllers
        UserController userController = ServiceFactory.createUserController();
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

                        if (loggedUser.getAdmin()) {
                            System.out.println("Você é um ADMIN!");
                        } else {
                            System.out.println("Você não é um ADMIN!");
                        }


                        // Menu do usuário logado
                        int opcaoUsuario;
                        do {
                            System.out.println("O que deseja fazer:");
                            System.out.println("101 - Criar Endereço");
                            System.out.println("102 - Listar Enderecos");
                            System.out.println("103 - Atualizar Endereco");
                            System.out.println("104 - Deletar um endereco");
                            System.out.println("105 - Deletar todos os enderecos");
                            System.out.println("106 - Mostrar um endereco");
                            System.out.println("107 - Atualizar usuário");
                            System.out.println("108 - Deslogar");

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
                                    System.out.println("Etiqueta do endereço: ");
                                    String etiqueta = scanner.nextLine();

                                    addressController.createAddress(
                                            loggedUser.getId(),
                                            estado, cidade, rua, numero,
                                            complemento, bairro, cep, etiqueta
                                    );
                                    System.out.println("Endereço criado com sucesso!\n");
                                    break;

                                case 102:
                                    addressController.showAllUsersAddress(loggedUser.getId());
                                    break;

                                case 103:
                                    System.out.println("Estado: ");
                                    estado = scanner.nextLine();
                                    System.out.println("Cidade: ");
                                    cidade = scanner.nextLine();
                                    System.out.println("Rua: ");
                                    rua = scanner.nextLine();
                                    System.out.println("Número: ");
                                    numero = scanner.nextLine();
                                    System.out.println("Complemento: ");
                                    complemento = scanner.nextLine();
                                    System.out.println("Bairro: ");
                                    bairro = scanner.nextLine();
                                    System.out.println("CEP: ");
                                    cep = scanner.nextLine();
                                    System.out.println("Address ID: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Etiqueta do endereço: ");
                                    etiqueta = scanner.nextLine();
                                    addressController.updateAddress(loggedUser.getId(), estado, cidade, rua, numero,
                                            complemento, bairro, cep, etiqueta, id);
                                    break;

                                case 104:
                                    System.out.println("Qual endereco você quer deletar? ");
                                    int addressId = scanner.nextInt();
                                    scanner.nextLine();
                                    addressController.deleteOneAddress(addressId, loggedUser.getId());
                                    break;

                                case 105: System.out.println("Deslogando...\n");
                                    addressController.deleteAll(loggedUser.getId());
                                    break;

                                case 106:
                                    System.out.println("Endereço que quer ver: ");
                                    int seeAddressId = scanner.nextInt();
                                    scanner.nextLine();
                                    addressController.showOneAddress(loggedUser.getId(), seeAddressId);
                                    break;

                                case 107:
                                    System.out.println("Nome do usuário: ");
                                    String nome = scanner.nextLine();
                                    System.out.println("E-mail novo: ");
                                    String emailNovo = scanner.nextLine();
                                    System.out.println("Telefone novo: ");
                                    String telefone = scanner.nextLine();
                                    userController.updateUser(loggedUser.getId(), nome,  emailNovo, telefone);
                                    break;

                                case 108:
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
