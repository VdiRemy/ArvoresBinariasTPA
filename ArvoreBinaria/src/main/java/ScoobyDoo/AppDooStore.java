package ScoobyDoo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppDooStore {
    
    // Serviço que gerencia a árvore
    private DooStore loja = new DooStore();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AppDooStore app = new AppDooStore();
        
        // Dados iniciais para teste (para não começar vazia)
        app.inicializarDados();
        
        app.exibirMenu();
    }
    
    // Método para popular a árvore com dados de teste
    private void inicializarDados() {
        loja.cadastrarStrain(100, "Blue Dream", "Sativa dominante, euforia e energia.");
        loja.cadastrarStrain(50, "Gelato", "Híbrido potente, relaxamento e bem-estar.");
        loja.cadastrarStrain(150, "Amnesia Haze", "Sativa clássica, foco e inspiração.");
        loja.cadastrarStrain(75, "OG Kush", "Híbrido, relaxamento corporal e felicidade.");
        loja.cadastrarStrain(125, "Sour Diesel", "Sativa, combate o stress e melhora o humor.");
        loja.cadastrarStrain(200, "Northern Lights", "Indica pura, ideal para dormir.");
        loja.cadastrarStrain(5, "Prensado de Cinco (Dóla)", "Efeito desconhecido, só dá o que tem."); // Novo item
        System.out.println("Base de dados inicializada com 7 Strains."); // Contagem corrigida
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- DooStore: Gerenciador de Strains ---");
            System.out.println("1. Cadastrar novo Strain (Adicionar)");
            System.out.println("2. Buscar Strain por ID (Busca Otimizada)");
            System.out.println("3. Buscar Strain por Nome (Busca Exaustiva)");
            System.out.println("4. Listar todos em Ordem (Caminhamento Em Ordem)");
            System.out.println("5. Remover Strain por ID");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1: cadastrarStrain(); break;
                    case 2: buscarPorID(); break;
                    case 3: buscarPorNome(); break;
                    case 4: listarEmOrdem(); break;
                    case 5: removerStrain(); break;
                    case 0: System.out.println("Encerrando DooStore. Volta mermo!"); break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");
                scanner.nextLine(); // Limpa o buffer
            }
        }
    }
    
    // MÉTODOS DE FUNCIONALIDADE (Chamando DooStore)

    private void cadastrarStrain() {
        System.out.println("\n 🍁QUAL A NOVA ONDA?🍃");
        System.out.print("ID (Número inteiro): ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        scanner.nextLine(); 
        
        System.out.print("Características: ");
        String dados = scanner.nextLine();
        
        loja.cadastrarStrain(id, nome, dados);
        System.out.println("Strain " + nome + " (ID: " + id + ") cadastrado com sucesso.");
    }
    
    private void buscarPorID() {
        System.out.println("\n--- BUSCA POR ID (Otimizada) ---");
        System.out.print("Digite o ID do Strain: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Strain resultado = loja.buscarPorID(id); 
        
        if (resultado != null) {
            System.out.println(">> ENCONTRADO: ID " + resultado.getStrainID() + ", Nome: " + resultado.getNomeStrain());
        } else {
            System.out.println(">> Strain com ID " + id + " não encontrado.");
        }
    }
    
    private void buscarPorNome() {
        System.out.println("\n--- BUSCA POR NOME (Exaustiva) ---");
        System.out.print("Digite o Nome do Strain: ");
        String nome = scanner.nextLine();
        
        Strain resultado = loja.buscarPorNome(nome); 
        
        if (resultado != null) {
            System.out.println(">> ENCONTRADO: Nome: " + resultado.getNomeStrain() + ", ID: " + resultado.getStrainID());
        } else {
            System.out.println(">> Strain com nome '" + nome + "' não encontrado.");
        }
    }
    
    private void listarEmOrdem() {
        System.out.println("\n--- LISTAGEM EM ORDEM (Por ID) ---");
        String lista = loja.listarTodosEmOrdem();
        
        // Remove os colchetes para melhor visualização (opcional)
        if (lista.length() > 2) {
             System.out.println(lista.substring(1, lista.length() - 1));
        } else {
             System.out.println("O catálogo está vazio.");
        }
    }
    
    private void removerStrain() {
        System.out.println("\n--- REMOÇÃO POR ID ---");
        System.out.print("Digite o ID do Strain a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Strain removido = loja.removerStrainPorID(id);
        
        if (removido != null) {
            System.out.println(">> Strain removido com sucesso: " + removido.getNomeStrain() + " (ID: " + removido.getStrainID() + ")");
        } else {
            System.out.println(">> Strain com ID " + id + " não encontrado para remoção.");
        }
    }
}