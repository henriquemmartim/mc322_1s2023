

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    protected String nome;
    protected String endereco;
    protected List <Veiculo> listaVeiculos;
    protected String cadastro;

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
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
                    break;
                }
            }
            listaVeiculos.add(veiculo);
        }
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
