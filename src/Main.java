import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<ItemBiblioteca> itens = new ArrayList<>(); 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Cadastrar item");
            System.out.println("2 - Visualizar itens cadastrados");
            System.out.println("3 - Realizar empréstimo");
            System.out.println("4 - Realizar devolução");
            System.out.println("5 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("Selecione o tipo de item que deseja cadastrar (livro, dvd, revista):");
                    String tipo = scanner.nextLine().toLowerCase();

                    switch (tipo) {
                        case "livro":
                            System.out.println("Digite o título do livro:");
                            String tituloLivro = scanner.nextLine();
                            System.out.println("Digite o autor do livro:");
                            String autor = scanner.nextLine();
                            System.out.println("Digite o ano do livro:");
                            int anoLivro = scanner.nextInt();
                            System.out.println("Digite o número de cópias do livro:");
                            int numerocopiasLivro = scanner.nextInt();
                            scanner.nextLine(); 
                            Livro livro = new Livro();
                            livro.titulo = tituloLivro;
                            livro.autor = autor;
                            livro.ano = anoLivro;
                            livro.numerocopias = numerocopiasLivro;
                            itens.add(livro); 
                            System.out.println("Livro cadastrado com sucesso!");
                            break;

                        case "dvd":
                            System.out.println("Digite o título do DVD:");
                            String tituloDVD = scanner.nextLine();
                            System.out.println("Digite a duração do DVD (em minutos):");
                            int duracao = scanner.nextInt();
                            System.out.println("Digite o ano do DVD:");
                            int anoDVD = scanner.nextInt();
                            System.out.println("Digite o número de cópias do DVD:");
                            int numerocopiasDVD = scanner.nextInt();
                            scanner.nextLine(); 
                            DVD dvd = new DVD();
                            dvd.titulo = tituloDVD;
                            dvd.duracao = duracao;
                            dvd.ano = anoDVD;
                            dvd.numerocopias = numerocopiasDVD;
                            itens.add(dvd); 
                            System.out.println("DVD cadastrado com sucesso!");
                            break;

                        case "revista":
                            System.out.println("Digite o título da revista:");
                            String tituloRevista = scanner.nextLine();
                            System.out.println("Digite a edição da revista:");
                            String edicao = scanner.nextLine();
                            System.out.println("Digite o ano da revista:");
                            int anoRevista = scanner.nextInt();
                            System.out.println("Digite o número de cópias da revista:");
                            int numerocopiasRevista = scanner.nextInt();
                            scanner.nextLine(); 
                            Revista revista = new Revista();
                            revista.titulo = tituloRevista;
                            revista.Edição = edicao;
                            revista.ano = anoRevista;
                            revista.numerocopias = numerocopiasRevista;
                            itens.add(revista); 
                            System.out.println("Revista cadastrada com sucesso!");
                            break;

                        default:
                            System.out.println("Tipo inválido.");
                    }
                    break;

                case 2:
                    if (itens.isEmpty()) {
                        System.out.println("Nenhum item cadastrado.");
                    } else {
                        System.out.println("\nItens cadastrados:");
                        for (ItemBiblioteca item : itens) {
                            System.out.println(item.toString()); 
                        }
                    }
                    break;

                case 3:
                    System.out.println("Digite o título do item que deseja emprestar:");
                    String tituloEmprestimo = scanner.nextLine();
                    boolean encontrado = false;

                    for (ItemBiblioteca item : itens) {
                        if (item.titulo.equalsIgnoreCase(tituloEmprestimo)) {
                            encontrado = true;
                            if (item.numerocopias > 0) {
                                item.numerocopias--; 
                                item.dataInicioEmprestimo = LocalDate.now();
                                System.out.println("Digite a data de devolução (formato: yyyy-MM-dd):");
                                item.dataFimEmprestimo = LocalDate.parse(scanner.nextLine());
                                System.out.println("Empréstimo realizado com sucesso! Cópias restantes: " + item.numerocopias);
                            } else {
                                System.out.println("Não há cópias disponíveis para empréstimo.");
                            }
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Item não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Digite o título do item que deseja devolver:");
                    String tituloDevolucao = scanner.nextLine();
                    encontrado = false;

                    for (ItemBiblioteca item : itens) {
                        if (item.titulo.equalsIgnoreCase(tituloDevolucao)) {
                            encontrado = true;
                            item.numerocopias++; 
                            if (item.dataFimEmprestimo != null && LocalDate.now().isAfter(item.dataFimEmprestimo)) {
                                long diasAtraso = ChronoUnit.DAYS.between(item.dataFimEmprestimo, LocalDate.now());
                                double multa = diasAtraso * 5.0;
                                System.out.printf("Devolução realizada com atraso de %d dias. Multa: R$ %.2f%n", diasAtraso, multa);
                            } else {
                                System.out.println("Devolução realizada com sucesso!");
                            }
                            item.dataInicioEmprestimo = null;
                            item.dataFimEmprestimo = null;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Item não encontrado.");
                    }
                    break;

                case 5:
                    continuar = false;
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}