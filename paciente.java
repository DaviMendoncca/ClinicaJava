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

