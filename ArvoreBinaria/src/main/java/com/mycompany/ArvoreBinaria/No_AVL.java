package com.mycompany.ArvoreBinaria;

/**
 *
 * @author VdiRemy
 */
public class No_AVL<T> extends No<T> {
    
    private int altura;
    
    public No_AVL(T valor) {
        super(valor);
        this.altura = 0;
    }
    
    
    public No_AVL(T valor, No<T> esq, No<T> dir){
        super(valor);
        this.setFilhoDireita(dir);
        this.setFilhoEsquerda(esq);
        this.altura = 0;
    }
    
    public int getAltura(){
        return this.altura;
    }
    
    public void setAltura(int altura){
        this.altura = altura;
    }
}