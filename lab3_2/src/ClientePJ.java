
public class ClientePJ extends Cliente{
   private final String cnpj;

   private String dataFundacao;

    public ClientePJ (String nome, String endereco, String dataFundacao,
    String cnpj) {

        super(nome, endereco);
        cnpj = remover_caracteres(cnpj);
        this. cnpj = cnpj;
        this. dataFundacao = dataFundacao;
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
     * Verifica se todos os números no cpf são iguais.
     */

    public boolean sao_iguais(int tamanho_cpf) {
        Boolean sao_iguais = true;
        int i;
            for (i = 0; i < tamanho_cpf && sao_iguais == true; i++) {
                if (cnpj.charAt(i) != cnpj.charAt(0)) {
                    sao_iguais = false;
                }
            }
        return sao_iguais;
    }

    public boolean verificar_digito(int peso_inicial, int posicao) {
        
        // essa variável posição representa a posição do dígito verificador no cpf.

        // a variável peso_inicial representa o valor que multiplica o primeiro digito do cnpj.
        
        int i;
        boolean condicao = true;
        int resto;
        int soma = 0;
        for (i = 0; i < posicao; i++) {
            int numero = Character.getNumericValue(cnpj.charAt(i));
            soma += numero * (((6 + peso_inicial - i) % 8) + 2);
        }
        resto = soma % 11;
        if (resto < 2) {
            if (cnpj.charAt(posicao) != '0') {
                condicao = false;
            }
        }
        else {
            resto = 11 - resto;
            int temp1 = Character.getNumericValue(cnpj.charAt(posicao));
            if (temp1 != resto) {
                condicao = false;
            }
        }
        return condicao;
    }

    public void validar_cnpj() {
        boolean condicao = true;
        int tamanho_cnpj = cnpj.length();
        if (tamanho_cnpj != 14) {
            condicao = false;
        }
        else {
            boolean eh_igual = sao_iguais(tamanho_cnpj);
            if (eh_igual == true) {
                condicao = false;
            }
            else{
                boolean digito_verificador1 = verificar_digito(5, 12);
                if (digito_verificador1 == false) {
                    condicao = false;
                }
                else {
                    boolean digito_verificador2 = verificar_digito(6, 13);
                    if (digito_verificador2 == false) {
                        condicao = false;
                    }
                }
            }
        }
        
        if (condicao == true) {
            System.out.printf("CNPJ Válido" + "\n");
        }
        else {
            System.out.printf("CNPJ Inválido" + "\n");
        }
    }

}
