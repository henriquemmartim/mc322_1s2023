

public class ClientePF extends Cliente{
    private final String cpf;
    private String genero;
    private String dataLicenca;
    private String educacao;
    private String dataNascimento;
    private String classeEconomica;

    public ClientePF (String nome, String endereco, String dataLicenca,
     String educacao, String genero, String classeEconomica,
     String cpf, String dataNascimento) {

        super(nome, endereco);
        cpf = remover_caracteres(cpf);
        this.cpf = cpf;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;

    }

    public String getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(String dataLicenca) {
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
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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
     * Verifica se todos os números no cpf são iguais.
     */

    public boolean sao_iguais(int tamanho_cpf) {
        Boolean sao_iguais = true;
        int i;
            for (i = 0; i < tamanho_cpf && sao_iguais == true; i++) {
                if (cpf.charAt(i) != cpf.charAt(0)) {
                    sao_iguais = false;
                }
            }
        return sao_iguais;
    }

    /*
     * Verifica se os dígitos verificadores são válidos 
     */

    public boolean verificar_digito(int j, int posicao) {
        
        // essa variável posição representa a posição do dígito verificador no cpf.

        // a variável j representa o valor q deve ser multiplicado pelo número correspondente
        // nas posições do cpf.
        
        int i;
        boolean condicao = true;
        int resto;
        int soma = 0;
        for (i = 0; i < posicao; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * j;
            j--;
        }
        resto = soma % 11;
        if (resto == 0 || resto == 1) {
            if (cpf.charAt(posicao) != '0') {
                condicao = false;
            }
        }
        else {
            resto = 11 - resto;
            int temp1 = Character.getNumericValue(cpf.charAt(posicao));
            if (temp1 != resto) {
                condicao = false;
            }
        }
        return condicao;
    }

    public void validar_cpf() {
        boolean condicao = true;
        int tamanho_cpf = cpf.length();
        if (tamanho_cpf != 11) {
            condicao = false;
        }
        else {
            boolean eh_igual = sao_iguais(tamanho_cpf);
            if (eh_igual == true) {
                condicao = false;
            }
            else{
                boolean digito_verificador1 = verificar_digito(10, 9);
                if (digito_verificador1 == false) {
                    condicao = false;
                }
                else {
                    boolean digito_verificador2 = verificar_digito(11, 10);
                    if (digito_verificador2 == false) {
                        condicao = false;
                    }
                }
            }
        }
        if (condicao == true) {
            System.out.print("CPF válido." + "\n");
        }
        else {
            System.out.print("CPF inválido." + "\n");
        }
    }

}
