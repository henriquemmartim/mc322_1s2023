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
        String segCadastro;
        int code;
        String cadastro;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));
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
            case GERAR_SEGURO:
                System.out.println("Escreva o cnpj da seguradora (apenas os números)");
                segCadastro = scanner.nextLine();
                for (Seguradora s : listaSeguradoras) {
                    if (segCadastro.equals(s.getCnpj())) {
                        seg = s;
                    }
                }
                System.out.println("Escreva a data do início do seguro");
                String data_inicio = scanner.nextLine();
                LocalDate date_inicio = LocalDate.parse(data_inicio, format);
                System.out.println("Escreva a data do fim do seguro");
                String data_fim = scanner.nextLine();
                LocalDate date_fim = LocalDate.parse(data_fim, format);
                System.out.println("Escreva o cpf/cnpj do condutor do cliente (apenas os números)");
                cadastro = scanner.nextLine();
                if (cadastro.length() == 11) {
                    System.out.println("Escreva a placa do veículo");
                    String placa = scanner.nextLine();
                    seg.gerarSeguroPF(date_inicio, date_fim, cadastro, placa);
                }
                else {
                    System.out.println("Escreva o código da frota");
                    code = scanner.nextInt();
                    seg.gerarSeguroPJ(date_inicio, date_fim, cadastro, code);
                }
                break;
			case GERAR_SINISTRO:
                System.out.println("Escreva o cnpj da seguradora (apenas os números)");
                segCadastro = scanner.nextLine();
                for (Seguradora s : listaSeguradoras) {
                    if (segCadastro.equals(s.getCnpj())) {
                        seg = s;
                    }
                }
                System.out.println("Escreva o codigo do seguro");
                code = scanner.nextInt();
                System.out.println("Escreva a data da licença");
                String data_sinistro = scanner.nextLine();
               
                LocalDate date_sinistro = LocalDate.parse(data_sinistro, format);
                System.out.println("Escreva o endereço do sinistro");
                String endereco = scanner.nextLine();
                System.out.println("Escreva o cpf do condutor do sinistro");
                cadastro = scanner.nextLine();
                seg.chamarGerarSinistros(code, date_sinistro, endereco, cadastro);
				break;
			case TRANSFERIR_SEGURO:
                System.out.println("Escreva o cnpj da seguradora (apenas os números)");
                segCadastro = scanner.nextLine();
                for (Seguradora s : listaSeguradoras) {
                    if (segCadastro.equals(s.getCnpj())) {
                        seg = s;
                    }
                }
                System.out.println("Escreva o cpf/cnpj de quem vai transferir o seguro");
                String cad_transfere = scanner.nextLine();
                System.out.println("Escreva o cpf/cnpj de quem vai receber o seguro");
                String cad_recebe = scanner.nextLine();
                System.out.println("Se eles forem pessoas físicas, digite : PF, caso contrário digite: PJ ");
                String tipo = scanner.nextLine();
                if (tipo.equals("PF")) {
                    seg.transferirSeguroPF(cad_transfere, cad_recebe);
                }
                else {
                    seg.transferirSeguroPJ(cad_transfere, cad_recebe);
                }
				break;
			case CALCULAR_RECEITA:
                System.out.println("Escreva o cnpj da seguradora (apenas os númeoros)");
                segCadastro = scanner.nextLine();
                for (Seguradora s : listaSeguradoras) {
                    if (segCadastro.equals(s.getCnpj())) {
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
        String segCadastro;
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
        int code;
        String data_nascimento;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
            System.out.println("Escreva o cnpj da seguradora (apenas os números)");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
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
                    LocalDate date_licenca = LocalDate.parse(data_licenca, format);
                    System.out.println("Escreva a data de nascimento");
                    data_nascimento = scanner.nextLine();
                    LocalDate date_nascimento = LocalDate.parse(data_nascimento, format);
                    ClientePF cliente = new ClientePF(nome, endereco, date_licenca, educacao, genero, classe_economica, cadastro, date_nascimento);
                    seg.cadastrarClientePF(cliente);      
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
                    System.out.println("Escreva a data da licença");
                    data_fundacao = scanner.nextLine();
                    LocalDate date_fundacao = LocalDate.parse(data_fundacao, format);
                    ClientePJ cliente = new ClientePJ(nome, endereco, date_fundacao, cadastro, num_funcionarios);
                    seg.cadastrarClientePJ(cliente);
                }
            }
			break;
        case CADASTRAR_CONDUTOR:
            System.out.println("Escreva o cnpj da seguradora (apenas os números)");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf do condutor (apenas os números)");
            cadastro = scanner.nextLine();
            System.out.println("Escreva o nome do condutor");
            nome = scanner.nextLine();
            System.out.println("Escreva o telefone do conduto");
            telefone = scanner.nextLine();
            System.out.println("Escreva o email do conduto");
            email = scanner.nextLine();
            System.out.println("Escreva o endereço do condutor");
            endereco = scanner.nextLine();
            System.out.println("Escreva a data de nascimento");
            data_nascimento = scanner.nextLine();
            LocalDate date_nascimento = LocalDate.parse(data_nascimento, format);
            Condutor condutor = new Condutor(cadastro, nome, telefone, endereco, email, date_nascimento);
            System.out.println("Escreva o id do seguro");
            code = scanner.nextInt();
            seg.adicionarCondutor(code, condutor);
            break;
		case CADASTRAR_VEICULO:
            System.out.println("Escreva o cnpj da seguradora (apenas os números)");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
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
            System.out.println("Escreva o cpf ou cnpj do dono do veículo (apenas os números)");
            cadastro = scanner.nextLine();
            Veiculo veiculo = new Veiculo(placa, marca, modelo, ano_fab);
            if (cadastro.length() == 11) {
                seg.cadastrarVeiculoPF(cadastro, veiculo);
            }
            else {
                System.out.println("Escreva o código da frota");
                code = scanner.nextInt();
                seg.cadastrarVeiculoPJ(cadastro, veiculo, code);
            }
			break;
        case CADASTRAR_FROTA:
            System.out.println("Escreva o cnpj da seguradora (apenas os números)");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cnpj do dono da frota (apenas os números)");
            cadastro = scanner.nextLine();
            Frota frota = new Frota();
            seg.adicionarFrota(cadastro, frota);
            break;
		case CADASTRAR_SEGURADORA:
            System.out.println("Escreva o cnpj da seguradora (apenas os números)");
            segCadastro = scanner.nextLine();
            System.out.println("Escreva o nome da seguradora");
            nome = scanner.nextLine();
            System.out.println("Escreva o telefone da seguradora");
            telefone = scanner.nextLine();
            System.out.println("Escreva o email da seguradora");
            email = scanner.nextLine();
            System.out.println("Escreva o endereço da seguradora");
            endereco = scanner.nextLine();
            Seguradora seguradora = new Seguradora(segCadastro, nome, telefone, email, endereco);
            listaSeguradoras.add(seguradora);
			break;
		case LISTAR_CLIENTES:
            System.out.println("Escreva o cnpj da seguradora (apenas os números)");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o tipo de cliente (PF ou PJ)");
            String tipo = scanner.nextLine();
            seg.listarClientes(tipo);
			break;
		case LISTAR_SINISTROS_CLIENTE:
            System.out.println("Escreva o cnpj da seguradora (apenas os números)");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o nome cpf/cnpj do cliente");
            cadastro = scanner.nextLine();
            List <Sinistro> listaSin;
            listaSin = seg.getSinistroPorCliente(cadastro);
            for (Sinistro sin : listaSin) {
                System.out.println(sin.toString());
            }
			break;
        case LISTAR_SEGUROS_CLIENTE:
            System.out.println("Escreva o cnpj da seguradora (apenas os números)");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o nome cpf/cnpj do cliente");
            cadastro = scanner.nextLine();
            List <Seguro> listaSe;
            listaSe = seg.getSeguroPorCliente(cadastro);
            for (Seguro se : listaSe) {
                System.out.println(se.toString());
            }
			break;
		case LISTAR_VEICULOS_CLIENTE:
            System.out.println("Escreva o cnpj da seguradora (apenas os números) em que o cliente está cadastrado");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf/cnpj do cliente (apenas os números)");
            cadastro = scanner.nextLine();
            if (cadastro.length() == 11) {
                seg.listarVeiculosPorClientePF(cadastro);
            } 
            else {
                seg.listarVeiculosPorClientePJ(cadastro);
            }
			break;
		case EXCLUIR_CLIENTE:
            System.out.println("Escreva o cnpj da seguradora (apenas os números) em que o cliente está cadastrado");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf/cnpj do cliente (apenas os números)");
            cadastro = scanner.nextLine();
            if (cadastro.length() == 11) {
                seg.removerClientesPF(cadastro);
            }
            else {
                seg.removerClientesPJ(cadastro);
            }
            seg.removerClientes(cadastro);
			break;
        case EXCLUIR_CONDUTOR:
            System.out.println("Escreva o cnpj da seguradora (apenas os números) em que o cliente está cadastrado");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf/cnpj do condutor");
            cadastro = scanner.nextLine();
            System.out.println("Escreva o id do seguro");
            code = scanner.nextInt();
            seg.removerCondutor(code, cadastro);
            break;
		case EXCLUIR_VEICULO:
            System.out.println("Escreva o cnpj da seguradora (apenas os números) em que o cliente está cadastrado");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cpf/cnpj do cliente");
            cadastro = scanner.nextLine();
            System.out.println("Escreva a placa do veículo");
            placa = scanner.nextLine();
            if (cadastro.length() == 11) {
                seg.removerVeiculoPF(cadastro, placa);
            }
            else {
                seg.removerVeiculoPJ(cadastro, placa);
            }
			break;
        case EXCLUIR_FROTA:
            System.out.println("Escreva o cnpj da seguradora (apenas os números) em que o cliente está cadastrado");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
                    seg = s;
                    break;
                }
            }
            System.out.println("Escreva o cnpj do cliente dono da frota");
            cadastro = scanner.nextLine();
            System.out.println("Escreva o código da frota");
            code = scanner.nextInt();
            seg.removerFrota(cadastro, code);
            break;
		case EXCLUIR_SINISTRO:
            System.out.println("Escreva o cnpj da seguradora (apenas os números) em que o cliente está cadastrado");
            segCadastro = scanner.nextLine();
            for (Seguradora s : listaSeguradoras) {
                if (segCadastro.equals(s.getCnpj())) {
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
        Seguradora seg = new Seguradora("63.788.572/0001-05", "Holder", "(61) 9753-3245", "holderseguradora@gmail.com", "Campinas");
        listaSeguradoras.add(seg);
        ClientePF clientePF = new ClientePF("Henrique", "Campinas", LocalDate.of(2023, 05, 10), "escolarizado", "maculino", "rico", "076.888.481-05", LocalDate.of(2004, 02, 26));
        ClientePJ clientePJ = new ClientePJ("Empresa do Henricão", "Campinas", LocalDate.of(2015, 05, 24), "46.068.425/0001-33", 150);
        Veiculo carro1 = new Veiculo("ABC1D23", "Vrum", "Corre muito", 2014);
        Veiculo carro2 = new Veiculo("EFG4H56", "Jefferson", "Caminhão", 2022);
        Frota frota = new Frota();
        Condutor condutor1 = new Condutor("591.808.740-02", "Gerador de Nomes", "(14) 3844-1621", "São Paulo", "geradordenome@gmail.com", LocalDate.of(2002, 11, 23));
        Condutor condutor2 = new Condutor("088.433.760-04", "Alfredo Jonnas Brothers", "(47) 3665-8854", "Santa Catarina", "alfjonnas@gmail.com", LocalDate.of(1996, 01, 16));
        clientePF.adicionarVeiculo(carro1);
        clientePJ.atualizarFrota(frota.getCode());
        clientePJ.atualizarFrota(carro2, frota.getCode());
        seg.cadastrarClientePF(clientePF);
        seg.cadastrarClientePJ(clientePJ);
        seg.gerarSeguroPF(LocalDate.of(2023, 05, 12), LocalDate.of(2028, 05, 12), "07688848105", "ABC1D23");
        seg.adicionarCondutor(0, condutor1);
        seg.gerarSeguroPJ(LocalDate.of(2023, 05, 26), LocalDate.of(2028, 05, 26), "46068425000133", frota.getCode());
        seg.adicionarCondutor(1, condutor2);
        seg.chamarGerarSinistros(0, LocalDate.of(2023, 04, 12), "Campinas", "59180874002"); // essa linha e a debaixo chamam o gerar sinistro
        seg.chamarGerarSinistros(1, LocalDate.of(2023, 05, 13), "Campinas", "08843376004"); // da classe seguro.
        seg.listarClientes("PJ");
        seg.listarSinistro();
        seg.getSinistroPorCliente("07688848105");
        double receita = seg.calcularReceita();
        System.out.println(receita);
        System.out.println(seg.toString());
        System.out.println(clientePF.toString());
        System.out.println(clientePJ.toString());
        System.out.println(carro1.toString());
        System.out.println(carro2.toString());
        System.out.println(frota.toString());
        System.out.println(condutor1.toString());
        System.out.println(condutor2.toString());
        seg.visualizarSinistro(clientePJ.getCadastro()); // isto chama o método toString de um sinistro.



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
