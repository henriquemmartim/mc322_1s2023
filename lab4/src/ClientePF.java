import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ClientePF extends Cliente{
    private final String cpf;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;

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

    }

    public String getDataLicenca() {
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

    @Override
    public String getCadastro() {
        return cpf;
    }

    public String getDataNascimento() {
        String nascimento = this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return nascimento;
        // return dataNascimento;
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
            ", dataLicenca='" + getDataLicenca() + "'" +
            ", educacao='" + getEducacao() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
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

    /*
     * Calcula o score com base na fórmula fornecida.
     */

    @Override
    public double calculaScore() {
        int quantidade_carros = listaVeiculos.size();
        LocalDate agora = LocalDate.now();
        int ano_agora = agora.getYear();
        int ano_nascimento = dataNascimento.getYear();
        int idade = ano_agora - ano_nascimento;
        double fator_idade;
        if (idade >= 18 && idade <= 30) {
            fator_idade = CalcSeguro.FATOR_18_30.getValor();
        }
        else if (idade > 30 && idade <= 60) {
            fator_idade = CalcSeguro.FATOR_30_60.getValor();
        }
        else {
            fator_idade = CalcSeguro.FATOR_60_90.getValor();
        }
        double valor = CalcSeguro.VALOR_BASE.getValor() * fator_idade * quantidade_carros;
        return valor;
    }



}
