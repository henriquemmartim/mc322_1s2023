import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Cliente {
    protected String nome;
    protected String endereco;
    protected List <Veiculo> listaVeiculos;
    protected String cadastro;
    protected double valorSeguro;

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
        valorSeguro = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public void getListaVeiculos() {
        for (Veiculo v : listaVeiculos) {
            System.out.print(v.toString());
        }
    }

    public double calculaScore() {
        return valorSeguro;
    }

    public String getCadastro() {
        return null;
    }

    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }
}
