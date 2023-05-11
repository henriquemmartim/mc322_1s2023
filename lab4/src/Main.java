import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static List <Seguradora> listaSeguradoras = new ArrayList<Seguradora>();

    //exibir menu externo
	private static void exibirMenuExterno() {
		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
		System.out.println("Menu principal");
		for(MenuOpcoes op: menuOpcoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}
	
	/* exibir submenus
	 * se a lista de constantes do submenu for percorrida da mesma forma que o meu externo, a opção Voltar
	 * é printada com a posição que está na lista do enum (9 - Voltar). Por isso, a lista é percorrida 
	 * de forma diferente, tendo i como o inteiro correspondente. Assim, para listar o submenu de cadastros,
	 * por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOpcoes op) {
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
	}
	
	//ler opções do menu externo
	private static MenuOpcoes lerOpcaoMenuExterno(Scanner scanner) {
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
            scanner.nextLine();
		}while(opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);
		opUsuarioConst = MenuOpcoes.values()[opUsuario];
		return opUsuarioConst;
	}
	
	//ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op, Scanner scanner) {
		int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
            scanner.nextLine();
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	//executar opções do menu externo
	private static void executarOpcaoMenuExterno(MenuOpcoes op, Scanner scanner) {
        Seguradora seg = null;
        String nome_seg;
		switch(op) {
			case CADASTROS:
                executarSubmenu(op, scanner);
                break;
			case LISTAR:
                executarSubmenu(op, scanner);
                break;
			case EXCLUIR:
				executarSubmenu(op, scanner);
				break;
			case GERAR_SINISTRO:
                System.out.println("Escreva o nome da seguradora");
                nome_seg = scanner.nextLine();
                for (Seguradora s : listaSeguradoras) {
                    if (nome_seg.equals(s.getNome())) {
                        seg = s;
                    }
                }
                System.out.println("Escreva a data do sinistro");
                String data = scanner.nextLine();
                System.out.println("Escreva o endereço do sinistro");
                String endereco = scanner.nextLine();
                System.out.println("Escreva a placa do carro do sinistro");
                String placa = scanner.nextLine();
                System.out.println("Escreva o cpf/cnpj do cliente do sinistro");
                String cadastro = scanner.nextLine();
                seg.gerarSinistros(data, endereco, placa, cadastro);
				break;
			case TRANSFERIR_SEGURO:
                System.out.println("Escreva o nome da seguradora");
                nome_seg = scanner.nextLine();
                for (Seguradora s : listaSeguradoras) {
                    if (nome_seg.equals(s.getNome())) {
                        seg = s;
                    }
                }
                System.out.println("Escreva o cpf/cnpj de quem vai transferir o seguro");
                String cad_transfere = scanner.nextLine();
                System.out.println("Escreva o cpf/cnpj de quem vai receber o seguro");
                String cad_recebe = scanner.nextLine();
                seg.transferirSeguro(cad_transfere, cad_recebe);
				break;
			case CALCULAR_RECEITA:
                System.out.println("Escreva o nome da seguradora");
                nome_seg = scanner.nextLine();
                for (Seguradora s : listaSeguradoras) {
                    if (nome_seg.equals(s.getNome())) {
                        seg = s;
                    }
                }
                double receita = seg.calcularReceita();
                System.out.println(receita);
				break;
			case SAIR:
                break;
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, Scanner scanner) {
        Seguradora seg = null;
        String nome_seg;
        String nome;
        String endereco;
        String educacao;
        String genero;
        String classe_economica;
        String data_fundacao;
        int num_funcionarios;
        String placa;
        String marca;
        String modelo;
        int ano_fab;
        String telefone;
        String email;
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
            System.out.println("Escreva o nome da seguradora");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf ou cnpj:");
            String cadastro = scanner.nextLine();
            if (cadastro.length() == 14) {
                if (!Validacao.validar_cpf(cadastro)) {
                    break;
                }
                else {
                    System.out.println("Escreva o nome");
                    nome = scanner.nextLine();
                    System.out.println("Escreva o endereço");
                    endereco = scanner.nextLine();
                    System.out.println("Escreva sua educação");
                    educacao = scanner.nextLine();
                    System.out.println("Escreva seu gênero");
                    genero = scanner.nextLine();
                    System.out.println("Escreva sua classe economica");
                    classe_economica = scanner.nextLine();
                    System.out.println("Escreva a data da licença");
                    String data_licenca = scanner.nextLine();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));
                    LocalDate date_licenca = LocalDate.parse(data_licenca, format);
                    System.out.println("Escreva a data de nascimento");
                    String data_nascimento = scanner.nextLine();
                    LocalDate date_nascimento = LocalDate.parse(data_nascimento, format);
                    ClientePF cliente = new ClientePF(nome, endereco, date_licenca, educacao, genero, classe_economica, cadastro, date_nascimento);
                    seg.cadastrarCliente(cliente);      
                }
            }
            else {
                if (!Validacao.validar_cnpj(cadastro)) {
                    break;
                }
                else {
                    System.out.println("Escreva o nome");
                    nome = scanner.nextLine();
                    System.out.println("Escreva o endereço");
                    endereco = scanner.nextLine();
                    System.out.println("Escreva a data de fundação");
                    data_fundacao = scanner.nextLine();
                    System.out.println("Escreva o número de funcionários");
                    num_funcionarios = scanner.nextInt();
                    ClientePJ cliente = new ClientePJ(nome, endereco, data_fundacao, cadastro, num_funcionarios);
                    seg.cadastrarCliente(cliente);
                }
            }
			break;
		case CADASTRAR_VEICULO:
            System.out.println("Escreva o nome da seguradora");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva a placa");
            placa = scanner.nextLine();
            System.out.println("Escreva a marca");
            marca = scanner.nextLine();
            System.out.println("Escreva o modelo");
            modelo = scanner.nextLine();
            System.out.println("Escreva o ano de fabricação");
            ano_fab = scanner.nextInt();
            System.out.println("Escreva o cpf ou cnpj do dono do veículo");
            cadastro = scanner.nextLine();
            Veiculo veiculo = new Veiculo(placa, marca, modelo, ano_fab);
            seg.cadastrarVeiculo(cadastro, veiculo);
			break;
		case CADASTRAR_SEGURADORA:
            System.out.println("Escreva o nome da seguradora");
            nome = scanner.nextLine();
            System.out.println("Escreva o telefone da seguradora");
            telefone = scanner.nextLine();
            System.out.println("Escreva o email da seguradora");
            email = scanner.nextLine();
            System.out.println("Escreva o endereço da seguradora");
            endereco = scanner.nextLine();
            Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
            listaSeguradoras.add(seguradora);
			break;
		case LISTAR_CLIENTES:
            System.out.println("Escreva o nome da seguradora");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o tipo de cliente (PF ou PJ)");
            String tipo = scanner.nextLine();
            seg.listarClientes(tipo);
			break;
		case LISTAR_SINISTROS_CLIENTE:
            System.out.println("Escreva o nome da seguradora");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o nome cpf/cnpj do cliente");
            cadastro = scanner.nextLine();
            seg.visualizarSinistro(cadastro);
			break;
        case LISTAR_SINISTROS_SEGURADORA:
            System.out.println("Escreva o nome da seguradora");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            seg.listarSinistro();
			break;
		case LISTAR_VEICULOS_CLIENTE:
            System.out.println("Escreva o nome da seguradora em que o cliente está cadastrado");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf/cnpj do cliente");
            cadastro = scanner.nextLine();
            seg.listarVeiculosPorCliente(cadastro);
			break;
        case LISTAR_VEICULOS_SEGURADORA:
            System.out.println("Escreva o nome da seguradora");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            seg.listarVeiculos();
			break;
		case EXCLUIR_CLIENTE:
            System.out.println("Escreva o nome da seguradora em que o cliente está cadastrado");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf/cnpj do cliente");
            cadastro = scanner.nextLine();
            seg.removerClientes(cadastro);
			break;
		case EXCLUIR_VEICULO:
            System.out.println("Escreva o nome da seguradora em que o cliente está cadastrado");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf/cnpj do cliente");
            cadastro = scanner.nextLine();
            System.out.println("Escreva a placa do veículo");
            placa = scanner.nextLine();
            seg.excluirVeiculo(cadastro, placa);
			break;
		case EXCLUIR_SINISTRO:
            System.out.println("Escreva o nome da seguradora em que o cliente está cadastrado");
            nome_seg = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (nome_seg.equals(s.getNome())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva ID do sinistro");
            int ID = scanner.nextInt();
            seg.excluirSinistro(ID);
			break;
		case VOLTAR:
			break;
		}
	}
	
	private static void executarSubmenu(MenuOpcoes op, Scanner scanner) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op, scanner);
			executarOpcaoSubMenu(opSubmenu, scanner);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
	
	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Seguradora seg = new Seguradora("Holder", "(61) 9753-3245", "holderseguradora@gmail.com", "Campinas");
        listaSeguradoras.add(seg);
        ClientePF clientePF = new ClientePF("Henrique", "Campinas", LocalDate.of(2023, 05, 10), "escolarizado", "maculino", "rico", "076.888.481-05", LocalDate.of(2004, 02, 26));
        ClientePJ clientePJ = new ClientePJ("Empresa do Henricão", "Campinas", "24/05/2015", "46.068.425/0001-33", 150);
        Veiculo carro1 = new Veiculo("ABC1D23", "Vrum", "Corre muito", 2014);
        Veiculo carro2 = new Veiculo("EFG4H56", "Jefferson", "Caminhão", 2022);
        clientePF.adicionarVeiculo(carro1);
        clientePJ.adicionarVeiculo(carro2);
        seg.cadastrarCliente(clientePF);
        seg.cadastrarCliente(clientePJ);
        seg.gerarSinistros("08/04/2022", "Campinas", carro2.getPlaca(), clientePJ.getCadastro());
        seg.gerarSinistros("16/05/2023", "Campinas", carro2.getPlaca(), clientePJ.getCadastro());
        seg.listarClientes("PJ");
        seg.visualizarSinistro(clientePJ.getCadastro());
        seg.listarSinistro();
        seg.atualizarValorSeguro();
        double receita = seg.calcularReceita();
        System.out.println(receita);




		MenuOpcoes op;
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno(scanner);
			executarOpcaoMenuExterno(op, scanner);
		} while(op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
        scanner.close();
	}

}
