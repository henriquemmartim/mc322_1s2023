import java.time.LocalDate;
import java.util.Iterator;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF clientePF;



    public SeguroPF(LocalDate dataInicio, LocalDate dataFim , Seguradora seguradora, Veiculo veiculo, ClientePF clientePF) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.clientePF = clientePF;
        calcularValor();
    }

    public Cliente getCliente() {
        return clientePF;
    }

    public void setCliente(ClientePJ cliente) {
    }

    public void setCliente(ClientePF cliente) {
        this.clientePF = cliente;
    }

    /*
     * Adiciona um condutor na sua lista.
     */

    public void autorizarCondutor(Condutor condutor) {
        if (listaCondutores.isEmpty()) {
            listaCondutores.add(condutor);
        }
        else {
            for (Condutor c : listaCondutores) {
                if (c.getCpf().equals(condutor.getCpf())) {
                    System.out.println("Já tem esse condutor.");
                    return; 
                }
            }
            listaCondutores.add(condutor);
        }
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    /*
     * Remove um condutor da sua lista, dado o seu cpf.
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
     * Cria um novo sinistro com os parametros recebidos e o coloca na lista.
     */

    public void gerarSinistro(LocalDate data, String endereco, Condutor condutor) {
        // System.out.println("to chegando aqui");
        Sinistro sin = new Sinistro(data, endereco, this, condutor);
        listaSinistros.add(sin);
        condutor.getCpf();
        condutor.adicionarSinistro(sin); // adiciona o sinistro na lista de sinistros do condutor.
        calcularValor();
    }

    /*
     * Remove um sinistro da lista, dado o seu id.
     */

    public boolean excluirSinistro(int ID) {
        for (Condutor c : listaCondutores) { // remove o sinistro da lista de sinistros do condutos.
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
     * Calcula o valor do seguro, com base na formula.
     */

    public double calcularValor() {
        int quantidadeVeiculos = clientePF.getListaSeguros().size();
        int quantidadeSinistrosCondutores = 0;
        for (Condutor c : listaCondutores) {
                quantidadeSinistrosCondutores += c.getListaSinistros().size();
        }
        int quantidadeSinistrosCliente = 0;
        for (Seguro se : clientePF.getListaSeguros()) {
            quantidadeSinistrosCliente += se.getListaSinistros().size();
        }
        LocalDate agora = LocalDate.now();
        int ano_agora = agora.getYear();
        int ano_nascimento = clientePF.getDataNascimento().getYear();
        int idade = ano_agora - ano_nascimento;
        double fator_idade;
        if (idade >= 18 && idade <= 30) {
            fator_idade = CalcSeguro.FATOR_18_30.getValor();
        }
        else if (idade > 30 && idade <= 60) {
            fator_idade = CalcSeguro.FATOR_30_60.getValor();
        }
        else {
            fator_idade = CalcSeguro.FATOR_60_90.getValor();
        }
        double valor = (CalcSeguro.VALOR_BASE.getValor() * fator_idade * (1 + 1/(quantidadeVeiculos+2)) * (2 + quantidadeSinistrosCliente/10) * (5 + quantidadeSinistrosCondutores/10));
        valorMensal = valor;
        return valor;
    }

}
