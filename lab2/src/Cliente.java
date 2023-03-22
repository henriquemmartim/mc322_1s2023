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

    public void remover_caracteres() {
        cpf = cpf.replaceAll("[^0-9]", "");
    }

    public int validar_cpf() {
        remover_caracteres();
        int condicao = 1;
        int i;
        int soma = 0;
        int resto;
        int tamanho_cpf = cpf.length();
        if (tamanho_cpf != 11) {

            condicao = 0;
        }
        else {
            int sao_iguais = 1;
            for (i = 0; i < tamanho_cpf && sao_iguais == 1; i++) {
                if (cpf.charAt(i) != cpf.charAt(0)) {
                    sao_iguais = 0;
                }
            }
            if (sao_iguais == 1) {
                condicao = 0;
            }
            else {  
                int j = 10;
                for (i = 0; i < 9; i++) {
                    int numero = Character.getNumericValue(cpf.charAt(i));
                    soma += numero * j;
                    j--;
                    // System.out.printf("%c", cpf.charAt(i));
                }
                System.out.println("\n");
                resto = soma % 11;
                // System.out.println(soma);
                // System.out.println(resto);
                if (resto == 0 || resto == 1) {
                    if (cpf.charAt(9) != '0') {
                        // System.out.printf("3");
                        condicao = 0;
                    }
                }
                else {
                    resto = 11 - resto;
                    int temp1 = Character.getNumericValue(cpf.charAt(9));
                    if (temp1 != resto) {
                        // System.out.printf("4");
                        condicao = 0;
                    }
                }
                soma = 0;
                j = 11;
                for (i = 0; i < 10; i++) {
                    int numero = Character.getNumericValue(cpf.charAt(i));
                    soma += numero * j;
                    j--;
                }
                resto = soma % 11;
                if (resto == 0 || resto == 1) {
                    if (cpf.charAt(10) != '0') {
                        // System.out.printf("5");
                        condicao = 0;
                    }
                }
                else {
                    resto = 11 - resto;
                    int temp2 = Character.getNumericValue(cpf.charAt(10));
                    if (temp2 != resto) {
                        // System.out.printf("6");
                        condicao = 0;
                    }
                }
            }
        }
        return condicao;
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
