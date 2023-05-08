
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
        calcularPrecoSeguroCliente(cliente);
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

    public boolean excluirSinistro(int ID) {
        Iterator<Sinistro> i = listaSinistros.iterator();
        while(i.hasNext()) {
            Sinistro s = i.next();
            if (s.getID() == ID) {
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
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

    public void calcularPrecoSeguroCliente(Cliente c) {
        int quantidade_sinistros = 0;
        for (Sinistro s : listaSinistros) {
            if(s.cliente.getCadastro().equals(c.getCadastro())) {
                quantidade_sinistros++;
            }
        }
        double preco_seguro =  c.calculaScore() * (1 + quantidade_sinistros);
        c.valorSeguro = preco_seguro;
        // return preco_seguro;
    }

    public double calcularReceita() {
        double receita = 0;
        for (Cliente c : listaClientes) {
            // receita += calcularPrecoSeguroCliente(c);
            receita += c.valorSeguro;

        }
        return receita;
    }

    public void transferirSeguro(Cliente c1, Cliente c2) {
        for (Veiculo v : c1.listaVeiculos) {
            c2.listaVeiculos.add(v);
        }
        removerClientes(c1.getCadastro());
        calcularPrecoSeguroCliente(c2);
    }

    public void cadastrarVeiculo(String cliente , Veiculo veiculo) {
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cliente)) {
                c.adicionarVeiculo(veiculo);
                calcularPrecoSeguroCliente(c);
            }
        }
    }

    public void excluirVeiculo(String cliente, String veiculo) {
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cliente)) {
                c.removerVeiculo(veiculo);
                calcularPrecoSeguroCliente(c);
            }
        }
    }

    public List<Veiculo> listarVeiculos() {
        List <Veiculo> veiculosSeguradora = new ArrayList<Veiculo>();
        for (Cliente c : listaClientes) {
            for (Veiculo v : c.listaVeiculos) {
                veiculosSeguradora.add(v);
            }
        }
        return veiculosSeguradora;
    }


}
