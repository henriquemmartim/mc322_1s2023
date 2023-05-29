import java.util.List;


import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class ClientePJ extends Cliente{
    private final String cnpj;
    private int qtdeFuncionarios;
    private LocalDate dataFundacao;
    private List<Frota> listaFrota;


    public ClientePJ (String nome, String endereco, LocalDate dataFundacao,
    String cnpj, int qtdeFuncionarios) {

        super(nome, endereco);
        cnpj = remover_caracteres(cnpj);
        this. cnpj = cnpj;
        this. dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
        this.listaFrota = new ArrayList<Frota>();
    }

    public List<Frota> getListaFrota() {
        return listaFrota;
    }

    public String getCadastro() {
        return cnpj;
    }

    public int getqntdFuncionarios() {
        return qtdeFuncionarios;
    }


    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public String printDataFundacao() {
        String data = this.dataFundacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            " endereco='" + getEndereco() + "'" +
            " cnpj='" + getCadastro() + "'" +
            " quantidade de funcionários='" + getqntdFuncionarios() + "'" +
            ", dataFundacao='" + printDataFundacao() + "'" +
            "}";
    }

    /*
     * Remove os caracteres não numéricos maiores que 0 e menores que 9 no cpf.
     */

    public String remover_caracteres(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");
        return cnpj;
    }

    public void cadastrarFrota(Frota frota) {
        if (listaFrota.isEmpty()) {
            listaFrota.add(frota);
        }
        else {
            for (Frota f : listaFrota) {
                if (f.getCode() == frota.getCode()) {
                    System.out.println("Já tem esse carro.");
                    break; // talvez seja break.
                }
            }
            listaFrota.add(frota);
        }
    }

    /*
     * Remove a frota da lista, dado o seu id.
     */

    public boolean atualizarFrota(int code) {
        Iterator<Frota> i = listaFrota.iterator();
            while(i.hasNext()) {
                Frota f = i.next();
                if (f.getCode() == code) {
                    i.remove();
                    return true;
                }
            }
            return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    /*
     * Remove um veiculo da frota, dada a sua placa.
     */

    public void atualizarFrota(String placa) {
        for (Frota f : listaFrota) {
            boolean condicao = f.removerVeiculo(placa);
            if (condicao ==  true) {
                break;
            }
        }
    }

    /*
     * Adiciona um veiculo na frota de codigo correspondente.
     */

    public void atualizarFrota(Veiculo veiculo, int code) {
        for (Frota f : listaFrota) {
            if (f.getCode() == code) {
                f.adicionarVeiculo(veiculo);
                return;
            }
        }
    }

    /*
     * Retorna a lista de veiculos de uma frota.
     */

    public List<Veiculo> getVeiculoPorFrota(int code) {
        for (Frota f : listaFrota) {
            if (f.getCode() == code) {
                return f.getListaVeiculos();
            }
        }
        return null;
    }

}

