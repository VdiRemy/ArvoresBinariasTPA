package ScoobyDoo;


import com.mycompany.ArvoreBinaria.lib.IArvoreBinaria;
import ScoobyDoo.ComparadorStrainPorNome;
import ScoobyDoo.ComparadorStrainPorID;
import ScoobyDoo.Strain;
import com.mycompany.ArvoreBinaria.ArvoreBinaria;
import java.util.Comparator;


public class DooStore {
    
    // A instância da sua biblioteca ArvoreBinaria, indexada por ID:
    private IArvoreBinaria<Strain> indicePorID;
    
    // Instância do comparador de busca por nome (Chave Secundária):
    private Comparator<Strain> comparadorNome;
    
    public DooStore() {
        // Inicializar o comparador de nome:
        comparadorNome = new ComparadorStrainPorNome();
        
        // Inicializar a ArvoreBinaria, usando o comparador de ID para indexação:
        indicePorID = new ArvoreBinaria<>(new ComparadorStrainPorID());
    }
    
    // Método para a funcionalidade 1: Adicionar
    public void cadastrarStrain(int id, String nome, String dados) {
        // Criar Strain e chamar indicePorID.adicionar()
        Strain novaStrain = new Strain(id,nome,dados);
        
        this.indicePorID.adicionar(novaStrain);
    }
    public Strain buscarPorID(int id) {
        Strain auxStrain = new Strain(id,"","");
        
        return this.indicePorID.pesquisar(auxStrain);
        
    } 
    // Método para a funcionalidade 4: Buscar por Nome (Chave Secundária)
    public Strain buscarPorNome(String nome) {
        // Criar um Strain 'mock' e chamar indicePorID.pesquisar(valor, comparadorNome)
        Strain auxStrain = new Strain(0,nome,"");
        
        return this.indicePorID.pesquisar(auxStrain,comparadorNome);
        
    }
    
    /**
     * Remove um Strain do índice pela sua chave primária (StrainID).
     * @param id O ID do Strain a ser removido.
     * @return O Strain removido, ou null se não for encontrado.
     */
    public Strain removerStrainPorID(int id) {

        Strain auxStrain = new Strain(id,"","");
        

        // 2. Chame o método remover da sua biblioteca.
        return this.indicePorID.remover(auxStrain);
    }
    /**
    * Retorna todos os Strains cadastrados, ordenados por StrainID.
    * @return Uma String formatada com os dados.
    */
   public String listarTodosEmOrdem() {
       // Basta chamar o método de caminhamento da sua biblioteca.
       return this.indicePorID.caminharEmOrdem();
   }

}