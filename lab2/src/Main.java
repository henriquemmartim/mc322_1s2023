package lab2.src;
public class Main {
    public static void main(String[] args) {
        Cliente c1 = new Cliente("Henrique", "964.605.700-43", "12/03/2004", 19, "Campinas");
        //se quiser testar: cpf invalido(964.605.700-43).
        Veiculo v1 = new Veiculo("ABC1D23", "Mclaren", "Senna");
        Sinistro s1 = new Sinistro("20/04/2023", "Campinas");
        Seguradora se1 = new Seguradora("Essa_Seguradora", "1234-5678", "seguradora@gmail.com", "Campinas");
        c1.setNome("null");
        System.out.printf("%s\n", c1.getNome());
        System.out.printf("%s\n", c1.getCpf());
        c1.validar_cpf();
        System.out.printf("%s\n", c1.getDataNascimento());
        System.out.printf("%d\n", c1.getIdade());
        System.out.printf("%s\n", c1.getEndereco());
        System.out.printf("%s\n", c1.toString());
        System.out.printf("%s\n", v1.getPlaca());
        System.out.printf("%s\n", v1.getMarca());
        System.out.printf("%s\n", v1.getModelo());
        v1.setModelo("null");
        System.out.printf("%s\n", s1.getData());
        System.out.printf("%s\n", s1.getEndereco());
        s1.setEndereco("null");
        System.out.printf("%d\n", s1.getId());
        System.out.printf("%s\n", se1.getNome());
        se1.setNome("null");
        System.out.printf("%s\n", se1.getTelefone());
        System.out.printf("%s\n", se1.getEmail());
        System.out.printf("%s\n", se1.getEndereco());  
    }   
}
