public class Validacao {


    public static String remover_caracteres(String cadastro) {
        cadastro = cadastro.replaceAll("[^0-9]", "");
        return cadastro;
    }

    /*
     * Verifica se todos os números no cpf são iguais.
     */

     public static boolean sao_iguais_cpf(int tamanho_cpf, String cpf) {
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

    public static boolean verificar_digito_cpf(int j, int posicao, String cpf) {
        
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

    public static boolean validar_cpf(String cpf) {
        cpf = remover_caracteres(cpf);
        boolean condicao = true;
        int tamanho_cpf = cpf.length();
        if (tamanho_cpf != 11) {
            condicao = false;
        }
        else {
            boolean eh_igual = sao_iguais_cpf(tamanho_cpf, cpf);
            if (eh_igual == true) {
                condicao = false;
            }
            else{
                boolean digito_verificador1 = verificar_digito_cpf(10, 9, cpf);
                if (digito_verificador1 == false) {
                    condicao = false;
                }
                else {
                    boolean digito_verificador2 = verificar_digito_cpf(11, 10, cpf);
                    if (digito_verificador2 == false) {
                        condicao = false;
                    }
                }
            }
        }
        if (condicao == true) {
            System.out.print("CPF válido." + "\n");
            return true;
        }
        else {
            System.out.print("CPF inválido." + "\n");
            return false;
        }
    }

    /*
    * Verifica se todos os números no cnpj são iguais.
    */

    public static boolean sao_iguais_cnpj(int tamanho_cnpj, String cnpj) {
        Boolean sao_iguais = true;
        int i;
            for (i = 0; i < tamanho_cnpj && sao_iguais == true; i++) {
                if (cnpj.charAt(i) != cnpj.charAt(0)) {
                    sao_iguais = false;
                }
            }
        return sao_iguais;
    }

    public static boolean verificar_digito_cnpj(int peso_inicial, int posicao, String cnpj) {
        
        // essa variável posição representa a posição do dígito verificador no cnpj.

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

    public static boolean validar_cnpj(String cnpj) {
        cnpj = remover_caracteres(cnpj);
        boolean condicao = true;
        int tamanho_cnpj = cnpj.length();
        if (tamanho_cnpj != 14) {
            condicao = false;
        }
        else {
            boolean eh_igual = sao_iguais_cnpj(tamanho_cnpj, cnpj);
            if (eh_igual == true) {
                condicao = false;
            }
            else{
                boolean digito_verificador1 = verificar_digito_cnpj(5, 12, cnpj);
                if (digito_verificador1 == false) {
                    condicao = false;
                }
                else {
                    boolean digito_verificador2 = verificar_digito_cnpj(6, 13, cnpj);
                    if (digito_verificador2 == false) {
                        condicao = false;
                    }
                }
            }
        }
        
        if (condicao == true) {
            System.out.printf("CNPJ Válido" + "\n");
            return true;
        }
        else {
            System.out.printf("CNPJ Inválido" + "\n");
            return false;
        }
    }

    public static boolean validar_nome(String nome) {
        boolean condicao = true;
        for (int i = 0; i < nome.length(); i++) {
            if (!Character.isLetter(nome.charAt(i))) {
                condicao = false;
            }
        }
        return condicao;
    }

}