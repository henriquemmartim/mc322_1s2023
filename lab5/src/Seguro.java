import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.ArrayList;

public abstract class Seguro {
    protected final int id;
    protected LocalDate dataInicio;
    protected LocalDate dataFim;
    protected Seguradora seguradora;
    protected List<Sinistro> listaSinistros;
    protected List<Condutor> listaCondutores;
    protected double valorMensal;
    static int contador = 0;


    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.id = contador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
        contador++;
    }



    public int getId() {
        return this.id;
    }


    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public String printDataInicio() {
        String data = this.dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public String printDataFim() {
        String data = this.dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return data;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public List<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public List<Condutor> getListaCondutores() {
        return this.listaCondutores;
    }

    public void setListaCondutores(List<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

    public abstract void autorizarCondutor(Condutor condutor);

    public abstract boolean desautorizarCondutor(String cpf);

    public abstract void gerarSinistro(LocalDate data, String endereco, Condutor condutor);

    public abstract boolean excluirSinistro(int ID);
    
    public abstract Cliente getCliente();

    public abstract void setCliente(ClientePF cliente);

    public abstract void setCliente(ClientePJ cliente);


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dataInicio='" + getDataInicio() + "'" +
            ", dataFim='" + getDataFim() + "'" +
            ", valorMensal='" + getValorMensal() + "'" +
            "}";
    }


}