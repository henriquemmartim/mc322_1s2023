import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class ClientePF extends Cliente{
    private final String cpf;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;
    private List <Veiculo> listaVeiculos;

    public ClientePF (String nome, String endereco, LocalDate dataLicenca,
     String educacao, String genero, String classeEconomica,
     String cpf, LocalDate dataNascimento) {

        super(nome, endereco);
        cpf = remover_caracteres(cpf);
        this.cpf = cpf;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
        this.listaVeiculos = new ArrayList<Veiculo>();

    }

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public String printDataLicenca() {
        String licenca = this.dataLicenca.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return licenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public String getCadastro() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String printDataNascimento() {
        String data = this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            " endereco='" + getEndereco() + "'" +
            " cpf='" + getCadastro() + "'" +
            ", genero='" + getGenero() + "'" +
            ", dataLicenca='" + printDataLicenca() + "'" +
            ", educacao='" + getEducacao() + "'" +
            ", dataNascimento='" + printDataNascimento() + "'" +
            ", classeEconomica='" + getClasseEconomica() + "'" +
            "}";
    }

    /*
     * Remove os caracteres não numéricos maiores que 0 e menores que 9 no cpf.
     */

    public String remover_caracteres(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf;
    }

        // Recebe um veiculo e adiciona ele na lista de veiculos do cliente

        public void adicionarVeiculo(Veiculo veiculo) {
            if (listaVeiculos.isEmpty()) {
                listaVeiculos.add(veiculo);
            }
            else {
                for (Veiculo v : listaVeiculos) {
                    if (v.getPlaca().equals(veiculo.getPlaca())) {
                        System.out.println("Já tem esse carro.");
                        return; // talvez seja break.
                    }
                }
                listaVeiculos.add(veiculo);
            }
        }
    
        // Recebe a placa do veiculo e remove ele da lista.
    
        public boolean removerVeiculo(String veiculo) {
            Iterator<Veiculo> i = listaVeiculos.iterator();
            while(i.hasNext()) {
                Veiculo v = i.next();
                if (v.getPlaca().equals(veiculo)) {
                    i.remove();
                    return true;
                }
            }
            return false; // nao encontrou alguem com o nome dado para ser retirado
        }
    
    
        /*
         * Printa os veiculos do cliente.
         */
    
        public void imprimeListaVeiculos() {
            for (Veiculo v : listaVeiculos) {
                System.out.print(v.toString());
            }
        }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

}

