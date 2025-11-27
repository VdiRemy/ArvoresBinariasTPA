package com.mycompany.ArvoreBinaria;
import com.mycompany.ArvoreBinaria.lib.IArvoreBinaria;
import java.util.ArrayList;
import java.util.Comparator;


/**
 *
 * @author VdiRemy
 */
public class ArvoreAvl<T> extends ArvoreBinaria<T> {
    
    public ArvoreAvl(Comparator<T> comp){
        super(comp);
    }
    
    private int getAltura(No_AVL<T> no){
        if (no == null){
            return -1;
        }
        else{
            return no.getAltura();
        }
    }
    
    // FB = altura(direita)-altura(esquerda)
    
    private int getFB(No_AVL<T> no){
        if (no == null){
            return 0;
        }
        else{
            return getAltura((No_AVL<T>) no.getFilhoDireita()) - getAltura((No_AVL<T>) no.getFilhoEsquerda());
        }
    }
    
    private void atualizaAltura(No_AVL<T> no){
        no.setAltura(1 + Math.max(getAltura((No_AVL<T>) no.getFilhoEsquerda()), getAltura((No_AVL<T>) no.getFilhoDireita())));
    }
    /**
     *  
     * @param pivo (no desbalanceado)
     * @return nova raiz da subarvore (filho esquerdo do pivo)
     */
    private No_AVL<T> rtcDireita(No_AVL<T> pivo){
        No_AVL<T> novo_p = (No_AVL<T>) pivo.getFilhoEsquerda();
        
        pivo.setFilhoEsquerda(novo_p.getFilhoDireita());
        
        novo_p.setFilhoDireita(pivo);
        
        atualizaAltura(pivo);
        atualizaAltura(novo_p);
        
        return novo_p; 
        
    }
    
    private No_AVL<T> rtcEsquerda(No_AVL<T> pivo){
        No_AVL<T> novo_p = (No_AVL<T>) pivo.getFilhoDireita();
        
        pivo.setFilhoDireita(novo_p.getFilhoEsquerda());
        
        novo_p.setFilhoEsquerda(pivo);
        
        atualizaAltura(pivo);
        atualizaAltura(novo_p);
        
        return novo_p; 
        
    }
    
    private No_AVL<T> rtcDuplaEsquerdaDireita(No_AVL<T> pivo) {
        
        pivo.setFilhoEsquerda(rtcEsquerda((No_AVL<T>) pivo.getFilhoEsquerda()));
        
        
        return rtcDireita(pivo);
    }
    
    private No_AVL<T> rtcDuplaDireitaEsquerda(No_AVL<T> pivo) {
        
        pivo.setFilhoDireita(rtcDireita((No_AVL<T>) pivo.getFilhoDireita()));
        
        
        return rtcEsquerda(pivo);
    }
    
    
    @Override
    public void adicionar(T novoValor) {
        // Chama a função recursiva e atualiza a raiz principal da árvore.
        // O cast é seguro pois o método só cria e retorna NoAVL.
        this.raiz = addRecursivo((No_AVL<T>) this.raiz, novoValor);
    }

    private No_AVL<T> addRecursivo(No_AVL<T> no, T valor) {
        if (no==null){
            return new No_AVL<>(valor);
        }
        int cmp = comparador.compare(valor, no.getValor());
        
        if (cmp < 0){
            //desce pela esquerda
            no.setFilhoEsquerda(addRecursivo((No_AVL<T>) no.getFilhoEsquerda(), valor));
        }
        else{
            no.setFilhoDireita(addRecursivo((No_AVL<T>) no.getFilhoDireita(), valor));
        }
        
        //recalcula altura do no
        atualizaAltura(no);
        
        int fb = getFB(no);
        
        No_AVL<T> no_dsb;

        if (fb > 0) {
            no_dsb = (No_AVL<T>) no.getFilhoDireita();
        } else {
            no_dsb = (No_AVL<T>) no.getFilhoEsquerda();
        }
        
        if (fb == 2) {
            if (getFB(no_dsb) >= 0) {
                
                return rtcEsquerda(no);
            } else {
                
                return rtcDuplaDireitaEsquerda(no);
            }
        } 
        
        
        else if (fb == -2) {
            if (getFB(no_dsb) <= 0) {
                
                return rtcDireita(no);
            } else {
                
                return rtcDuplaEsquerdaDireita(no);
            }
        }
        
        
        return no;
    }
    
    @Override
    public int altura() {
        return getAltura((No_AVL<T>) this.raiz);
    }
}

