package ScoobyDoo;

/**
 *
 * @author VdiRemy
 * 
 * Essa é a classe Aluno que será utilizada como tipo do conteúdo das árvores nos 
 * programas de teste para redigir os relatórios.
 */

public class Strain  {
    private int strainID;
    private String nome;
    private String dados;

    public Strain(int strainID, String nome, String dados){
        this.strainID = strainID;
        this.nome = nome;  
        this.dados = dados;         
    }


    public int getStrainID() {
        return strainID;
    }

    public void setStrainID(int strainID) {
        this.strainID = strainID;
    }

    public String getNomeStrain() {
        return nome;
    }

    public void setNomeStrain(String nome) {
        this.nome = nome;
    }

    
    /**
     * Sobrescreve o método toString() para retornar o ID e o Nome
     * em um formato legível para o caminhamento em ordem.
     */
    @Override
    public String toString() {
        // Formato: [ID: XXX - Nome: YYY]
        return "ID: " + strainID + " - Nome: " + nome + "\n Características: " + dados + "\n";
    }

}