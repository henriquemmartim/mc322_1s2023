import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Condutor {
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNascimento;
    private List<Sinistro> listaSinistros;
    private static List<Condutor> listaCondutores = new ArrayList<Condutor>();


    public Condutor(String cpf, String nome, String telefone, String endereco, String email, LocalDate dataNascimento) {
        this.cpf = Validacao.remover_caracteres(cpf);
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinistros = new ArrayList<Sinistro>();
        listaCondutores.add(this);
    }

    /*
     * Adiciona um sinistro na sua lista.
     */

    public void adicionarSinistro(Sinistro sinistro) {
        if (listaSinistros.isEmpty()) {
            listaSinistros.add(sinistro);
        }
        else {
            for (Sinistro s : listaSinistros) {
                if (s.getID() == sinistro.getID()) {
                    System.out.println("Já tem esse carro.");
                    return; 
                }
            }
            listaSinistros.add(sinistro);
        }
    }

    /*
     * Remove um sinistro da sua lista.
     */

    public boolean excluirSinistro(int ID) {
        Iterator<Sinistro> i = listaSinistros.iterator();
        while(i.hasNext()) {
            Sinistro sin = i.next();
            if (sin.getID() == ID) {
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    public String getCpf() {
        return this.cpf;
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public String printDataNascimento() {
        String data = this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }


    @Override
    public String toString() {
        return "{" +
            " cpf='" + getCpf() + "'" +
            ", nome='" + getNome() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", email='" + getEmail() + "'" +
            ", dataNascimento='" + printDataNascimento() + "'" +
            "}";
    }



}
