import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List <Sinistro> listaSinistros;
    private List <Cliente> listaClientes;

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaClientes = new ArrayList<Cliente>();
    }

    /*
     * Recebe um cliente e coloca ele na lista de clientes da seguradora.
     */

    public boolean cadastrarCliente(Cliente cliente) {
        if (listaClientes.isEmpty()) {
            listaClientes.add(cliente);
        }
        else {
            for (Cliente c : listaClientes) {
                if(c.getCadastro().equals(cliente.getCadastro())) {
                    return false; // este cliente ja esta cadastrado!
                }
            }
            listaClientes.add(cliente);
        }
        return true;
    }

    /*
     * Recebe um cpf ou cnpj e procura um cliente com esse cadastro e depois remove ele da lista.
     */
    
    public boolean removerClientes(String cliente) {
        Iterator<Cliente> i = listaClientes.iterator();
        while(i.hasNext()) {
            Cliente c = i.next();
            if (c.getCadastro().equals(cliente)) {
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    /*
     * Recebe um o tipo de cliente e cria uma lista deste tipo e adiciona os clientes desse tipo na lista
     */

    public List <Cliente> listarClientes(String tipoCliente) {
        if (tipoCliente.equals("PF")) {
            List <Cliente> listaClientePFs = new ArrayList<Cliente>();
            for (Cliente c : listaClientes) {
                if (c instanceof ClientePF) {
                    listaClientePFs.add(c);
                }
            }
            return listaClientePFs;
        }
        else {
            List <Cliente> listaClientesPJs = new ArrayList<Cliente>();
            for (Cliente c : listaClientes) {
                if (c instanceof ClientePJ) {
                    listaClientesPJs.add(c);
                }
            }
            return listaClientesPJs;
        }
    }

    /*
     * Gera um sinistro
     */

    public boolean gerarSinistros(String data, String endereco, Veiculo veiculo, Cliente cliente) {
        Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
        boolean gerar = listaSinistros.add(sinistro);
        return gerar;
    }

    /*
     * Recebe um cliente e printa o toString desse cliente
     */

    public boolean visualizarSinistro(String cliente) {
        for (Sinistro s : listaSinistros) {
            if (s.cliente.getCadastro().equals(cliente)) {
                System.out.println(s.toString());
            }
        }
        return true;
    }

    /*
     * Devolve a lista de sinistros dessa seguradora
     */

    public List <Sinistro> listarSinistro() {
        return listaSinistros;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", email='" + getEmail() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }


}
