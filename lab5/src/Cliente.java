import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class Cliente {
    protected String nome;
    protected String endereco;
    protected String telefone;  
    protected List <Seguro> listaSeguros;
    protected String cadastro;
    protected double valorSeguro;

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaSeguros = new ArrayList<Seguro>();
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

    /*
     * Remove um seguro da lista, dado o id dele.
     */

    public boolean removerSeguro(int id) {
        Iterator<Seguro> i = listaSeguros.iterator();
        while(i.hasNext()) {
            Seguro s = i.next();
            if (s.getId() == id) {
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    /*
     * Adiciona um seguro na lista
     */

    public void adicionarSeguro(Seguro seguro) {
        if (listaSeguros.isEmpty()) {
            listaSeguros.add(seguro);
        }
        else {
            for (Seguro se : listaSeguros) {
                if (se.getId() == seguro.getId()) {
                    System.out.println("Já tem esse seguro.");
                    return;
                }
            }
            listaSeguros.add(seguro);
        }
    }

    public abstract String getCadastro();
    

    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }

    public List<Seguro> getListaSeguros() {
        return listaSeguros;
    }


}
