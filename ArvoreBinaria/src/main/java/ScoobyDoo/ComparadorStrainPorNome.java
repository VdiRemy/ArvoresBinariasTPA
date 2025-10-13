package ScoobyDoo;

import java.util.Comparator;

/**
 *
 * @author VdiRemy
 * 
 * Essa é comparadora de Strains por nome
 */

public class ComparadorStrainPorNome  implements Comparator<Strain> {
 
    @Override
    public int compare(Strain o1, Strain o2) {
        return o1.getNomeStrain().compareTo(o2.getNomeStrain());
    }
    
}