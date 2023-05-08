import java.util.Random;


public class Sinistro {
    private final int ID;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    public Cliente cliente;

    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = valor_randomico();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public int getID() {
        return ID;
    }

    public int valor_randomico() {
        Random gerador = new Random();
        int num = gerador.nextInt();
        return num;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSeguradora() {
        return seguradora.getNome();
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public String getVeiculo() {
        return veiculo.getPlaca();
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getCliente() {
        return cliente.getCadastro();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        return "{" +
            " ID='" + getID() + "'" +
            ", data='" + getData() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", seguradora='" + seguradora.getNome() + "'" +
            ", veiculo='" + veiculo.getPlaca() + "'" +
            ", cliente='" + cliente.getCadastro() + "'" +
            "}";
    }
}
