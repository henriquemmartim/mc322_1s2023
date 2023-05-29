
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List <Cliente> listaClientes;
    private List <ClientePF> listaClientePFs;
    private List <ClientePJ> listaClientePJs;
    private List <Seguro> listaSeguros;

    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) {
        this.cnpj = Validacao.remover_caracteres(cnpj);
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSeguros = new ArrayList<Seguro>();
        this.listaClientes = new ArrayList<Cliente>();
        this.listaClientePFs = new ArrayList<ClientePF>();
        this.listaClientePJs = new ArrayList<ClientePJ>();
    }

    public String getCnpj() {
        return cnpj;
    }

    /*
     * Recebe um cliente pf e coloca ele na lista de clientes da seguradora.
     */

    public boolean cadastrarClientePF(ClientePF cliente) {
        if (listaClientePFs.isEmpty()) {
            listaClientes.add(cliente);
            listaClientePFs.add(cliente);
        }
        else {
            for (ClientePF c : listaClientePFs) {
                if(c.getCadastro().equals(cliente.getCadastro())) {
                    return false; // este cliente ja esta cadastrado!
                }
            }
            listaClientes.add(cliente);
            listaClientePFs.add(cliente);
        }
        return true;
    }

    /*
     * Recebe um cliente pj e coloca ele na lista de clientes da seguradora.
     */

    public boolean cadastrarClientePJ(ClientePJ cliente) {
        if (listaClientePJs.isEmpty()) {
            listaClientes.add(cliente);
            listaClientePJs.add(cliente);
        }
        else {
            for (ClientePJ c : listaClientePJs) {
                if(c.getCadastro().equals(cliente.getCadastro())) {
                    return false; // este cliente ja esta cadastrado!
                }
            }
            listaClientes.add(cliente);
            listaClientePJs.add(cliente);
        }
        return true;
    }

    /*
     * Recebe um cpf ou cnpj e procura um cliente com esse cadastro e depois remove ele da lista de clientes geral.
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
     * Recebe um cliente pf e cremove ele da lista de clientes pf da seguradora.
     */

    public boolean removerClientesPF(String cliente) {
        Iterator<ClientePF> i = listaClientePFs.iterator();
        while(i.hasNext()) {
            ClientePF c = i.next();
            if (c.getCadastro().equals(cliente)) {
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    /*
     * Recebe um cliente pj e cremove ele da lista de clientes pj da seguradora.
     */

    public boolean removerClientesPJ(String cliente) {
        Iterator<ClientePJ> i = listaClientePJs.iterator();
        while(i.hasNext()) {
            ClientePJ c = i.next();
            if (c.getCadastro().equals(cliente)) {
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    /*
     * Recebe um o tipo de cliente e printa o toString dos clientes da lista desse tipo.
     */

    public void listarClientes(String tipoCliente) {
        if (tipoCliente.equals("PF")) {
            for (Cliente c : listaClientePFs) {
                System.out.println(c.getCadastro());
            }
        }
        else {
            for (Cliente c : listaClientePJs) {
                System.out.println(c.getCadastro());
            }
        }
    }

    /*
     * Gera um seguro pf com base nos parametros.
     */

    public void gerarSeguroPF(LocalDate inicio, LocalDate fim, String cliente, String veiculo) {
        for (ClientePF c : listaClientePFs) {
            if (c.getCadastro().equals(cliente)) {
                for (Veiculo v : c.getListaVeiculos()) {
                    if (v.getPlaca().equals(veiculo)) {
                        Seguro seguro = new SeguroPF(inicio, fim, this, v, c);
                        listaSeguros.add(seguro);
                        c.listaSeguros.add(seguro);
                    }
                }
            }
        }
    }

    /*
     * Gera um seguro pj com base nos parametros.
     */

    public void gerarSeguroPJ(LocalDate inicio, LocalDate fim, String cliente, int code) {
        for (ClientePJ c : listaClientePJs) {
            if (c.getCadastro().equals(cliente)) {
                for (Frota f : c.getListaFrota()) {
                    if (f.getCode() == code) {
                        Seguro seguro = new SeguroPJ(inicio, fim, this, f, c);
                        listaSeguros.add(seguro);
                        c.listaSeguros.add(seguro);
                    }
                }
            }
        }
    }

    /*
     * Remove um seguro da lista, com base no seu id.
     */

    public boolean cancelarSeguro(int id) {
        Iterator<Seguro> i = listaSeguros.iterator();
        while(i.hasNext()) {
            Seguro s = i.next();
            if (s.getId() == id) {
                s.getCliente().removerSeguro(id); // remove o seguro da lista de seguros do cliente.
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    /*
     * Chama a funcao de gerar sinistros do seguro.
     */

    public void chamarGerarSinistros(int id, LocalDate data, String endereco, String condutor) {
        for (Seguro se : listaSeguros) {
            for (Condutor c : se.getListaCondutores()) {
                if (c.getCpf().equals(condutor)) {
                    
                    if (se.getId() == id) {
                        se.gerarSinistro(data, endereco, c);
                    }
                }
            }
        }
    }


    /*
     * Remove um sinistros da lista de sinistros do seguro, com base no id do sinistro.
     */

    public boolean excluirSinistro(int ID) {
        for (Seguro se : listaSeguros) {
            boolean cond = se.excluirSinistro(ID);
            if (cond == true) {
                return true;
            }
        }
        return false;
    }


    /*
     * Recebe um cliente e printa o toString dos sinistros desse cliente.
     */

    public void visualizarSinistro(String cliente) {
        for (Cliente c : listaClientes) {
            for (Seguro se : c.getListaSeguros()) {
                for (Sinistro sin : se.getListaSinistros()) {
                    System.out.println(sin.toString());
                }
            }
        }
    }

    /*
     * Printa os sinistros da seguradora.
     */

    public void listarSinistro() {
        for (Seguro se : listaSeguros) {
            for (Sinistro sin : se.getListaSinistros()) {
                System.out.println(sin.toString());

            }
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
            " cnpj='" + getCnpj() + "'" +
            " nome='" + getNome() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", email='" + getEmail() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }

    /*
     * Recebe os cadastros de 2 clientes pf e passa o seguro do c1 para o c2.
     */

    public void transferirSeguroPF(String c1, String c2) {
        ClientePF cliente1 = null;
        ClientePF cliente2 = null;
        for (ClientePF c : listaClientePFs) {
            if (c.getCadastro().equals(c1)) {
                cliente1 = c;
            }
            if (c.getCadastro().equals(c2)) {
                cliente2 = c;
            }
        }
        for (Seguro se : listaSeguros) {
            if (se.getCliente().getCadastro().equals(c1)) {                
                se.setCliente(cliente2);
                cliente1.removerSeguro(se.getId());
                cliente2.adicionarSeguro(se);
            }
        }
    }

    /*
     * Recebe os cadastros de 2 clientes pj e passa o seguro do c1 para o c2.
     */

    public void transferirSeguroPJ(String c1, String c2) {
        ClientePJ cliente1 = null;
        ClientePJ cliente2 = null;
        for (ClientePJ c : listaClientePJs) {
            if (c.getCadastro().equals(c1)) {
                cliente1 = c;
            }
            if (c.getCadastro().equals(c2)) {
                cliente2 = c;
            }
        }
        for (Seguro se : listaSeguros) {
            if (se.getCliente().getCadastro().equals(c1)) {                
                se.setCliente(cliente2);
                cliente1.removerSeguro(se.getId());
                cliente2.adicionarSeguro(se);
            }
        }
    }




    /*
     * Recebe o cadastro de um cliente e um veiculo e coloca este veiculo na lista do cliente.
     */

    public void cadastrarVeiculoPF(String cliente, Veiculo veiculo) {
        for (ClientePF c : listaClientePFs) {
            if (c.getCadastro().equals(cliente)) {
                c.adicionarVeiculo(veiculo);
            }
        }
    }

    /*
     * Remove um veiculo da lista de veiculos de um cliente pf, com base na sua placa.
     */

    public void removerVeiculoPF(String cliente, String placa) {
        for (ClientePF c : listaClientePFs) {
            if (c.getCadastro().equals(cliente)) {
                c.removerVeiculo(placa);
            }
        }
    }

    /*
     * Recebe o cadastro de um cliente e um veiculo e coloca este veiculo na lista do cliente.
     */

    public void cadastrarVeiculoPJ(String cliente, Veiculo veiculo, int code) {
        for (ClientePJ c : listaClientePJs) {
            if (c.getCadastro().equals(cliente)) {
                c.atualizarFrota(veiculo, code);
            }
        }
    }

    /*
     * Remove um veiculo da lista de veiculos de um cliente pj, com base na sua placa.
     */

    public void removerVeiculoPJ(String cliente, String placa) {
        for (ClientePJ c : listaClientePJs) {
            if (c.getCadastro().equals(cliente)) {
                c.atualizarFrota(placa);
            }
        }
    }

    /*
     * Remove uma frota da lista de frotas de um cliente pj, com base no seu codigo.
     */

    public void removerFrota(String cliente, int code) {
        for (ClientePJ c : listaClientePJs) {
            if (c.getCadastro().equals(cliente)) {
                c.atualizarFrota(code);
            }
        }
    }

    /*
     * Adciona uma frota na lista de frotas de um cliente pj.
     */

    public void adicionarFrota(String cliente, Frota frota) {
        for (ClientePJ c : listaClientePJs) {
            if (c.getCadastro().equals(cliente)) {
                c.cadastrarFrota(frota);
            }
        }
    }

    /*
     * Recebe um cpf e printa os veiculos dele.
     */

    public List <Veiculo> listarVeiculosPorClientePF(String cliente) {
        for (ClientePF c : listaClientePFs) {
            if (c.getCadastro().equals(cliente)) {
                return c.getListaVeiculos();
            }
        }
        return null;
    }

    /*
     * Recebe um cnpj e printa os veiculos dele.
     */

    public List <Veiculo> listarVeiculosPorClientePJ(String cliente) {
        List <Veiculo> lista = new ArrayList<Veiculo>();
        for (ClientePJ c : listaClientePJs) {
            if (c.getCadastro().equals(cliente)) {
                for (Frota f : c.getListaFrota()) {
                    for (Veiculo v : f.getListaVeiculos()) {
                        lista.add(v);
                    }
                }
            }
        }
        return lista;
    }

    /*
     * Recebe um cadastro e e devolve uma lista com todos os seguros deste cliente
     */

    public List<Seguro> getSeguroPorCliente(String cadastro) {
        List<Seguro> lista = new ArrayList<Seguro>();
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cadastro)) {
                lista = c.getListaSeguros();
            }
        }
        return lista;
    }

    /*
     * Recebe um cadastro e e devolve uma lista com todos os sinistros no nome desse cliente.
     */

    public List<Sinistro> getSinistroPorCliente(String cadastro) {
        List<Sinistro> lista = new ArrayList<Sinistro>();
        for (Cliente c : listaClientes) {
            if (c.getCadastro().equals(cadastro)) {
                for (Seguro s : c.listaSeguros) {
                    for (Sinistro sin : s.listaSinistros) {
                        lista.add(sin);
                    }
                }
            }
        }
        return lista;
    }

    /*
     * Adiciona um condutor em um seguro, com base em seu id.
     */

    public void adicionarCondutor(int id, Condutor condutor) {
        for (Seguro se : listaSeguros) {
            if (se.getId() == id) {
                se.autorizarCondutor(condutor);
            }
        }
    }

    /*
     * Remove um condutor da lista de condutores de um seguro, com base no id o seguro e no cpf do condutor.
     */

    public void removerCondutor(int id, String cpf) {
        for (Seguro se : listaSeguros) {
            if (se.getId() == id) {
                se.desautorizarCondutor(cpf);
            }
        }
    }

    /*
     * Calcula a receita de uma seguradora, somando os valores de cada seguro que a seguradora possui.
     */

    public double calcularReceita() {
        double receita = 0;
        for (Seguro se : listaSeguros) {
            receita += se.getValorMensal();
        }
        return receita;
    }


}