import java.time.LocalDate;
// import java.util.List;
import java.util.Iterator;


public class SeguroPJ extends Seguro{
    private Frota frota;
    private ClientePJ clientepj;


    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePJ clientepj) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.clientepj = clientepj;
    }

    public Frota getFrota() {
        return frota;
    }

    public Cliente getCliente() {
        return clientepj;
    }

    public void setCliente(ClientePJ cliente) {
        this.clientepj = cliente;
    }

    public void setCliente(ClientePF cliente) {
    }

    /*
     * Adiciona um condutor. na sua lista.
     */

    public void autorizarCondutor(Condutor condutor) {
        if (listaCondutores.isEmpty()) {
            listaCondutores.add(condutor);
        }
        else {
            for (Condutor c : listaCondutores) {
                if (c.getCpf().equals(condutor.getCpf())) {
                    System.out.println("Já tem esse condutor.");
                    return; // talvez seja break.
                }
            }
            listaCondutores.add(condutor);
        }
    }

    /*
     * Remove um condutor da lista, dado o seu cpf.
     */

    public boolean desautorizarCondutor(String cpf) {
        Iterator<Condutor> i = listaCondutores.iterator();
        while(i.hasNext()) {
            Condutor c = i.next();
            if (c.getCpf().equals(cpf)) {
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    /*
     * Cria um novo sinistro com os parametros dados e o coloca na lista
     */

    public void gerarSinistro(LocalDate data, String endereco, Condutor condutor) {
        Sinistro sin = new Sinistro(data, endereco, this, condutor);
        listaSinistros.add(sin);
        condutor.adicionarSinistro(sin); // adiciona o sinistro na lista de sinistros do condutor.
        calcularValor();
    }

    /*
     * Remove um sinsitro da lista, dado o seu id.
     */

    public boolean excluirSinistro(int ID) {
        for (Condutor c : listaCondutores) { // remove o sinistro da lista de sinistros do condutor.
            c.excluirSinistro(ID);
        }
        Iterator<Sinistro> i = listaSinistros.iterator();
        while(i.hasNext()) {
            Sinistro sin = i.next();
            if (sin.getID() == ID) {
                i.remove();
                return true;
            }
        }
        return false; // nao encontrou alguem com o nome dado para ser retirado
    }

    /*
     * Calcula o valor do seguro com base na formula dada.
     */

    public double calcularValor() {
        int quantidadeFuncionarios = clientepj.getqntdFuncionarios();
        int quantidadeVeiculos = 0;
        for (Frota f : clientepj.getListaFrota()) {
            quantidadeVeiculos += f.getListaVeiculos().size();
        }
        LocalDate agora = LocalDate.now();
        int ano_agora = agora.getYear();
        int ano_fundacao = clientepj.getDataFundacao().getYear();
        int AnosPosFundacao = ano_agora - ano_fundacao;
        int quantidadeSinistrosCliente = 0;
        for (Seguro se : clientepj.getListaSeguros()) {
            quantidadeSinistrosCliente += se.getListaSinistros().size();
        }
        int quantidadeSinistrosCondutores = 0;
        for (Condutor c : listaCondutores) {
                quantidadeSinistrosCondutores += c.getListaSinistros().size();
        }
        double valor = (CalcSeguro.VALOR_BASE.getValor() * (10 + (quantidadeFuncionarios/10)) * (1 + 1/(quantidadeVeiculos+2)) * (1 + 1/(AnosPosFundacao+2)) * (2 + quantidadeSinistrosCliente/10) * (5 + quantidadeSinistrosCondutores/10));
        valorMensal = valor;
        return valor;
    }

}
