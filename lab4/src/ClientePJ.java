public class ClientePJ extends Cliente{
    private final String cnpj;
    int qtdeFuncionarios;
    private String dataFundacao;

    public ClientePJ (String nome, String endereco, String dataFundacao,
    String cnpj, int qtdeFuncionarios) {

        super(nome, endereco);
        cnpj = remover_caracteres(cnpj);
        this. cnpj = cnpj;
        this. dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    @Override
    public String getCadastro() {
        return cnpj;
    }


    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            " endereco='" + getEndereco() + "'" +
            " cnpj='" + getCadastro() + "'" +
            ", dataFundacao='" + getDataFundacao() + "'" +
            "}";
    }

    /*
     * Remove os caracteres não numéricos maiores que 0 e menores que 9 no cpf.
     */

    public String remover_caracteres(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");
        return cnpj;
    }

    /*
     * Calcula o score com base na fórmula fornecida.
     */

    @Override
    public double calculaScore() {
        int quantidade_carros = listaVeiculos.size();
        double valor = CalcSeguro.VALOR_BASE.getValor() * (1 + (qtdeFuncionarios)/100) * quantidade_carros;
        return valor;
        
    }

}
