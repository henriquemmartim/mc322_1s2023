import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class Sinistro {
    private final int ID;
    private LocalDate data;
    private String endereco;
    private Seguro seguro;
    private Condutor condutor;

    public Sinistro(LocalDate data, String endereco, Seguro seguro, Condutor condutor) {
        this.ID = valor_randomico();
        this.data = data;
        this.endereco = endereco;
        this.seguro = seguro;
        this.condutor = condutor;
    }

    public int getID() {
        return ID;
    }

    public int valor_randomico() {
        Random gerador = new Random();
        int num = gerador.nextInt();
        return num;
    }

    public LocalDate getData() {
        return data;
    }

    public String printDataFim() {
        String data = this.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getSeguro() {
        return seguro.getId();
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String getCondutor() {
        return condutor.getCpf();
    }

    public void setCliente(Condutor condutor) {
        this.condutor = condutor;
    }

    public String toString() {
        return "{" +
            " ID='" + getID() + "'" +
            ", data='" + getData() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", seguro='" + seguro.getId() + "'" +
            ", condutor='" + condutor.getCpf() + "'" +
            "}";
    }
}
