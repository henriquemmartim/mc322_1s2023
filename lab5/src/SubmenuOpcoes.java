/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_CONDUTOR("Cadastrar condutor"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_FROTA("Cadastrar frota"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SEGUROS_CLIENTE("Listar seguros por cliente"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente em uma dada seguradora"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculo por cliente"),
	// LISTAR_VEICULOS_SEGURADORA("Listar veiculo por seguradora"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_CONDUTOR("Excluir condutor"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_FROTA("Excluir frota"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	VOLTAR("Voltar");
	
	//atributo
	private final String descricao;
	
	//Construtor
	SubmenuOpcoes(String descricao){
		this.descricao = descricao;
	}
	
	//getter
	public String getDescricao() {
		return descricao;
	}
}
