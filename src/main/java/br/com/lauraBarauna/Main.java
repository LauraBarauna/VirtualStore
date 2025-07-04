package br.com.lauraBarauna;

import br.com.lauraBarauna.controller.address.AddressController;
import br.com.lauraBarauna.controller.user.UserController;
import br.com.lauraBarauna.dto.user.UserResponseDTO;
import br.com.lauraBarauna.model.user.User;
import br.com.lauraBarauna.service.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        UserResponseDTO loggedUser = null;
        UserController userController = new UserController();
        AddressController addressController = new AddressController();

        Scanner scanner = new Scanner(System.in);

        int option;

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

                    int opcaoUsuario;

                    do {

                        System.out.println("Digite o e-mail do usuario: ");
                        String email = scanner.nextLine();
                        System.out.println("Digite a senha do usuario: ");
                        String senha = scanner.nextLine();
                        loggedUser = userController.loginUser(email, senha);
                        System.out.println();

                        System.out.println("O que deseja fazer no usuario: ");
                        System.out.println("101 - Criar Endereço");
                        System.out.println("102 - Deslogar Usuários");
                        System.out.println();
                        opcaoUsuario = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoUsuario) {
                            case 101:
                                System.out.println(loggedUser.getId());
                                System.out.println("Estado: ");
                                String estado = scanner.nextLine();
                                System.out.println("Cidade: ");
                                String cidade = scanner.nextLine();
                                System.out.println("Rua: ");
                                String rua = scanner.nextLine();
                                System.out.println("Numero: ");
                                String numero = scanner.nextLine();
                                System.out.println("Complemento: ");
                                String complemento = scanner.nextLine();
                                System.out.println("Bairro: ");
                                String bairro = scanner.nextLine();
                                System.out.println("CEP: ");
                                String cep = scanner.nextLine();
                                addressController.createAddress(loggedUser.getId(), estado, cidade, rua, numero, complemento, bairro, cep);
                                System.out.println();
                                break;
                            case 102:
                                loggedUser = null;
                                System.out.println("Saindo do menu do usuário...");
                                break;
                        }

                    } while (opcaoUsuario != 102);
                    break;
                case 2:
            }

        } while (option!=3);




    }
}
