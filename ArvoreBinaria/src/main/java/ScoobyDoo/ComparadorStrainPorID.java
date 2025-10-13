package ScoobyDoo;

import java.util.Comparator;

/**
 *
 * @author VdiRemy
 * 
 * Essa é comparadora de Strains por ID. Ideal para apreciadores nerds!
 */

public class ComparadorStrainPorID implements Comparator<Strain> {
    /*O nosso comparador utiliza o método compare da classe integer para comparar os IDS de 2 Strains
    */    
    @Override
    public int compare(Strain o1, Strain o2) {
        return Integer.compare(o1.getStrainID(), o2.getStrainID());
    }

}