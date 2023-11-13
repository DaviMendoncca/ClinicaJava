import java.util.ArrayList;
import java.util.Scanner;



class Paciente extends Pessoa {
    private int id;
    private boolean atendimentoPendente;

    public Paciente(int id, String nome, String sexo, int idade, boolean atendimentoPendente) {
        super(nome, sexo, idade);
        this.id = id;
        this.atendimentoPendente = atendimentoPendente;
    }

    public int getId() {
        return id;
    }

    public boolean isAtendimentoPendente() {
        return atendimentoPendente;
    }

    public void setAtendimentoPendente(boolean atendimentoPendente) {
        this.atendimentoPendente = atendimentoPendente;
    }

    @Override
    public String toString() {
        return super.toString() + "\nID: " + id + "\nAtendimento Pendente: " + (atendimentoPendente ? "Sim" : "Não");
    }
}

class ClinicaMedica {
    private ArrayList<Paciente> pacientes = new ArrayList<>();
    private int proximoId = 1;

    public void adicionarPaciente(String nome, String sexo, int idade, boolean atendimentoPendente) {
        Paciente paciente = new Paciente(proximoId, nome, sexo, idade, atendimentoPendente);
        pacientes.add(paciente);
        proximoId++;
    }

    public Paciente encontrarPacientePorId(int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }

    public void editarPaciente(int id, String novoNome, String novoSexo, int novaIdade, boolean atendimentoPendente) {
        Paciente paciente = encontrarPacientePorId(id);
        if (paciente != null) {
            if (!novoNome.isEmpty()) {
                paciente.setNome(novoNome);
            }
            if (!novoSexo.isEmpty()) {
                paciente.setSexo(novoSexo);
            }
            if (novaIdade > 0) {
                paciente.setIdade(novaIdade);
            }
            paciente.setAtendimentoPendente(atendimentoPendente);
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void excluirPaciente(int id) {
        Paciente paciente = encontrarPacientePorId(id);
        if (paciente != null) {
            pacientes.remove(paciente);
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void mostrarRelatorioPaciente(int id) {
        Paciente paciente = encontrarPacientePorId(id);
        if (paciente != null) {
            System.out.println(paciente);
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void mostrarRelatorioTodosPacientes() {
        for (Paciente paciente : pacientes) {
            System.out.println("Paciente ID: " + paciente.getId());
            System.out.println(paciente);
            System.out.println("-------------------------");
        }
    }
}

public class Clinica {
    public static void main(String[] args) {
        ClinicaMedica clinica = new ClinicaMedica();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Clínica Médica - Menu");
            System.out.println("1. Adicionar paciente");
            System.out.println("2. Editar paciente");
            System.out.println("3. Excluir paciente");
            System.out.println("4. Ver Relatório do paciente (por ID)");
            System.out.println("5. Ver relatório de todos os pacientes");
            System.out.println("6. Sair do programa");
            System.out.print("Escolha a opção desejada: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do paciente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Sexo: ");
                    String sexo = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Atendimento pendente (sim ou não): ");
                    boolean atendimentoPendente = scanner.nextLine().equalsIgnoreCase("sim");
                    clinica.adicionarPaciente(nome, sexo, idade, atendimentoPendente);
                    break;

                case 2:
                    System.out.print("ID do paciente a ser editado: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();
                    Paciente pacienteEditar = clinica.encontrarPacientePorId(idEditar);
                    if (pacienteEditar != null) {
                        System.out.print("Novo nome (pressione Enter para manter o mesmo): ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo sexo (pressione Enter para manter o mesmo): ");
                        String novoSexo = scanner.nextLine();
                        System.out.print("Nova idade (pressione Enter para manter a mesma): ");
                        int novaIdade = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Atendimento pendente (sim ou não): ");
                        boolean novoAtendimentoPendente = scanner.nextLine().equalsIgnoreCase("sim");
                        clinica.editarPaciente(idEditar, novoNome, novoSexo, novaIdade, novoAtendimentoPendente);
                    } else {
                        System.out.println("Paciente não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("ID do paciente a ser excluído: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();
                    clinica.excluirPaciente(idExcluir);
                    break;

                case 4:
                    System.out.print("ID do paciente para ver o relatório: ");
                    int idRelatorio = scanner.nextInt();
                    scanner.nextLine();
                    clinica.mostrarRelatorioPaciente(idRelatorio);
                    break;

                case 5:
                    clinica.mostrarRelatorioTodosPacientes();
                    break;

                case 6:
                    System.out.println("Encerrando o programa...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
