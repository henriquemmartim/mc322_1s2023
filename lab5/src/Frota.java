import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;


public class Frota {
    private final int code;
    private List<Veiculo> listaVeiculos;
    static int contador;


    public Frota() {
        this.code = contador;
        this.listaVeiculos = new ArrayList<Veiculo>();
        contador++;
    }


    public int getCode() {
        return this.code;
    }


    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    /*
     * Adiciona um veiculo na sua lista.
     */

    public void adicionarVeiculo(Veiculo veiculo) {
        if (listaVeiculos.isEmpty()) {
            listaVeiculos.add(veiculo);
        }
        else {
            for (Veiculo v : listaVeiculos) {
                if (v.getPlaca().equals(veiculo.getPlaca())) {
                    System.out.println("Já tem esse carro.");
                    break; // talvez seja break.
                }
            }
            listaVeiculos.add(veiculo);
        }
    }

    /*
     * Remove um veiculo da lista, dada a sua placa.
     */

    public boolean removerVeiculo(String placa) {
        Iterator<Veiculo> i = listaVeiculos.iterator();
            while(i.hasNext()) {
                Veiculo v = i.next();
                if (v.getPlaca().equals(placa)) {
                    i.remove();
                    return true;
                }
            }
            return false; // nao encontrou alguem com o nome dado para ser retirado
        }


    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            "}";
    }

}


