package lab2.src;
import java.util.Random;

public class Sinistro {
    private static int id;
    private String data;
    private String endereco;

    public Sinistro(String data, String endereco) {
        valor_randomico();
        this.data = data;
        this.endereco = endereco;
    }

    /*
     * Gera um inteiro aleatório como o id do sinistro. 
     */

    public void valor_randomico() {
        Random gerador = new Random();
        id = gerador.nextInt();
    }

    public int getId() {
        return id;
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

}
