
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

    public void listarClientes(String tipoCliente) {
        if (tipoCliente.equals("PF")) {
            List <Cliente> listaClientePFs = new ArrayList<Cliente>();
            for (Cliente c : listaClientes) {
                if (c instanceof ClientePF) {
                    listaClientePFs.add(c);
                }
            }
            for (Cliente c : listaClientePFs) {
                System.out.println(c.getCadastro());
            }
        }
        else {
            List <Cliente> listaClientesPJs = new ArrayList<Cliente>();
            for (Cliente c : listaClientes) {
                if (c instanceof ClientePJ) {
                    listaClientesPJs.add(c);
                }
            }
            for (Cliente c : listaClientesPJs) {
                System.out.println(c.getCadastro());
            }
        }
    }

    /*
     * Gera um sinistro
     */

    public void gerarSinistros(String data, String endereco, String veiculo, String cliente) {
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cliente)) {
                for (Veiculo v : c.listaVeiculos) {
                    if (v.getPlaca().equals(veiculo)) {
                        Sinistro sinistro =  new Sinistro(data, endereco, this, v, c);
                        listaSinistros.add(sinistro);
                    }
                }
            }
        }
    }

    /*
     * Recebe um id e retira ele da lista de sinistros da seguradora.
     */

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
     * Recebe um cliente e printa o toString dos sinistros desse cliente.
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
     * Printa os sinistros da seguradora.
     */

    public void listarSinistro() {
        for (Sinistro s : listaSinistros) {
            System.out.println(s.toString());
        }
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


    /*
     * Calcula o preco do seguro de um cliente com base no seus sinistros e no seu score.
     */

    public void calcularPrecoSeguroCliente(Cliente c) {
        int quantidade_sinistros = 0;
        for (Sinistro s : listaSinistros) {
            if(s.cliente.getCadastro().equals(c.getCadastro())) {
                quantidade_sinistros++;
            }
        }
        double preco_seguro =  c.calculaScore() * (1 + quantidade_sinistros);
        c.valorSeguro = preco_seguro;
    }


    /*
     * Soma os valores do seguro de todos os clientes.
     */

    public double calcularReceita() {
        double receita = 0;
        for (Cliente c : listaClientes) {
            receita += c.valorSeguro;

        }
        return receita;
    }


    /*
     * Recebe os cadastros de 2 clientes e passa o seguro do c1 para o c2.
     */

    public void transferirSeguro(String c1, String c2) {
        Cliente cliente1 = null;
        Cliente cliente2 = null;
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(c1)) {
                cliente1 = c;
            }
            if (c.getCadastro().equals(c2)) {
                cliente2 = c;
            }
        }
        for (Veiculo v : cliente1.listaVeiculos) {
            cliente2.listaVeiculos.add(v);
        }
        removerClientes(cliente1.getCadastro());
        calcularPrecoSeguroCliente(cliente2);
    }


    /*
     * Recebe o cadastro de um cliente e um veiculo e coloca este veiculo na lista do cliente.
     */

    public void cadastrarVeiculo(String cliente , Veiculo veiculo) {
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cliente)) {
                c.adicionarVeiculo(veiculo);
                calcularPrecoSeguroCliente(c);
            }
        }
    }


    /*
     * Recebe um cadastro e uma placa e retira o veiculo com essa placa da lista do cliente.
     */

    public void excluirVeiculo(String cliente, String veiculo) {
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cliente)) {
                c.removerVeiculo(veiculo);
                calcularPrecoSeguroCliente(c);
            }
        }
    }


    /*
     * Printa todos os veiculos da seguradora
     */

    public void listarVeiculos() {
        for (Cliente c : listaClientes) {
            for (Veiculo v : c.listaVeiculos) {
                System.out.println(v.toString());
            }
        }
    }



    /*
     * Recebe um cadastro e printa os veiculos dele.
     */

    public void listarVeiculosPorCliente(String cliente) {
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cliente)) {
                c.getListaVeiculos();
            }
        }
    }


    /*
     * Atualiza o valor do seguro de todos os cliente.
     */

    public void atualizarValorSeguro() {
        for (Cliente cliente : listaClientes) {
            calcularPrecoSeguroCliente(cliente);
        }
    }


}
