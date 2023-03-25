package lab2.src;
public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    /*
     * Remove os caracteres que não são números da string do cpf. 
     */

    public void remover_caracteres() {
        cpf = cpf.replaceAll("[^0-9]", "");
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
        remover_caracteres();
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
            System.out.printf("Válido" + "\n");
        }
        else {
            System.out.printf("Inválido" + "\n");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String toString() {
        return "{" +
            "nome:'" + getNome() + "'" +
            ", cpf:'" + getCpf() + "'" +
            ", dataNascimento:'" + getDataNascimento() + "'" +
            ", idade:'" + getIdade() + "'" +
            ", endereco:'" + getEndereco() + "'" +
            "}";
    }   
}
