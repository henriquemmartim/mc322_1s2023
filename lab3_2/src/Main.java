// import java.util;
// import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // cria a seguradora e alguns clientes e coloca eles na seguradora
        Seguradora seg = new Seguradora("Holder", "(61) 90965-3287", "holder.seguradora@gamil.com", "Campinas");
        ClientePF cliente_pf = new ClientePF("Henrique", "Campinas", "18/01/2023", "Ensino Médio", "Masculino", "Muito rico", "076.888.481-05", "26/02/2004");
        ClientePJ cliente_pj = new ClientePJ("Empresa do Henricão", "Campinas", "23/07/2008", "46.068.425/0001-33");
        seg.cadastrarCliente(cliente_pf);
        seg.cadastrarCliente(cliente_pj);

        // adicionar veiculos nos clientes
        Veiculo carrinho = new Veiculo("ABC1D23", "Vrum", "Corre muito", 2014);
        Veiculo carrao = new Veiculo("EFG4H56", "Jefferson", "Caminhão", 2022);
        cliente_pf.adicionarVeiculo(carrinho);
        cliente_pj.adicionarVeiculo(carrao);

        // verificar o cpf e cnpj
        cliente_pf.validar_cpf();
        cliente_pj.validar_cnpj();

        // criar sinistro
        seg.gerarSinistros("18/04/2023", "IC3", carrinho, cliente_pf);


        // tostrings
        System.out.println(seg.toString());
        System.out.println(cliente_pf.toString());
        System.out.println(cliente_pj.toString());
        seg.visualizarSinistro("07688848105");
        System.out.println(carrinho.toString());

        //  chamar alguns metodos da seguradora
        List <Cliente> lista_cliente = seg.listarClientes("PF");
        List <Sinistro> lista_sinistro = seg.listarSinistro();

        // remover os clientes
        seg.removerClientes("46068425000133");

        Scanner scan = new Scanner(System.in);
        String leitura;
        do {
            System.out.print("Digite o número da operação que você quer fazer: " + "\n");
            System.out.print("1 - Mostrar o sinistro." + "\n");
            switch(leitura = scan.next()) {
                case "1":
                    System.out.println("Digite o cpf de quem você quer ver o sinistro:");
                    String cpf = scan.next();
                    seg.visualizarSinistro(cpf);
                
            }
        } while (!leitura.equals("Parar"));
        scan.close();
    }
}
