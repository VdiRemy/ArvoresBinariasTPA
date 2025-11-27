package com.mycompany.ArvoreBinaria;
import com.mycompany.ArvoreBinaria.lib.IArvoreBinaria;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author VdiRemy
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
  
    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
    }
    
    @Override
    public void adicionar(T novoValor) {
        //Se a raiz está nula, vamos alocá-la e armazenar a informação nela
        No<T> novoNo = new No<>(novoValor);
        if (raiz == null){
            
            raiz = novoNo;
            novoNo.setFilhoEsquerda(null);
            novoNo.setFilhoDireita(null);
        }
        else{
            No<T> atual = raiz;
            while(true){
                //Se a raiz não for nula e a informação for menor que a armazenada na raiz
                //vamos inserir na sub-arvore esquerda
                int cmp = comparador.compare(novoValor, atual.getValor());

                if (cmp < 0){
                    if (atual.getFilhoEsquerda()==null) {
                        atual.setFilhoEsquerda(novoNo);
                        return;
                    }
                    atual = atual.getFilhoEsquerda();
                } else {
                    if (atual.getFilhoDireita()==null){
                        atual.setFilhoDireita(novoNo);
                        return;
                    }
                    atual = atual.getFilhoDireita();
                }
            }
        }
   }

    @Override
    public T pesquisar(T valor) {
        if (valor == null){
            return null;
        } else {
            
            No<T> atual = this.raiz;
            while (atual != null){
                int cmp = this.comparador.compare(valor, atual.getValor());
                
                if (cmp < 0){
                    atual = atual.getFilhoEsquerda();
                }
                
                else if (cmp > 0){
                    atual = atual.getFilhoDireita();
                }
                
                else {
                    return atual.getValor();
                }
                
            }
            return null;
        }
    }

    private T pesquisar(No<T> no, T valor, Comparator<T> comparador){
        if (no == null){
            return null;
        } else{
            int cmp = comparador.compare(valor, no.getValor());
            
            if (cmp == 0){
                return no.getValor();
            }
            
            T resultado_esq = pesquisar(no.getFilhoEsquerda(), valor, comparador);
            if (resultado_esq != null){
                return resultado_esq;
            }
            
            T resultado_dir = pesquisar(no.getFilhoDireita(), valor, comparador);
            if (resultado_dir != null){
                return resultado_dir;
            }
            return null;
    }
    }
    @Override
    public T pesquisar(T valor, Comparator comparador) {
        Comparator<T> cmpAux = (Comparator<T>) comparador;
        return pesquisar(this.raiz, valor, cmpAux);
   }
    
    private No<T> encontrarMaximo(No<T> no){
        No<T> atual = no;
        
        while (atual.getFilhoDireita() != null){
            atual = atual.getFilhoDireita();
        }
        return atual;
    }
    
    
    private No<T> remover(No<T> raizSubarvore, T valor){
        if (raizSubarvore == null){
            return null;
        }
        int cmp = this.comparador.compare(valor, raizSubarvore.getValor());
        
        if (cmp < 0){
           //valor é menor
           //o filho esquerdo assume valor da recursão
           No<T> novoEsquerda = remover(raizSubarvore.getFilhoEsquerda(), valor);
           raizSubarvore.setFilhoEsquerda(novoEsquerda);
           return raizSubarvore;
        }
        
        else if (cmp > 0){
            //valor maior
            //mema fita mas da direita
            No<T> novoDireita = remover(raizSubarvore.getFilhoDireita(), valor);
            raizSubarvore.setFilhoDireita(novoDireita);
            return raizSubarvore;
        }
        
        else{
            //sem filhos ou um filho
            if (raizSubarvore.getFilhoEsquerda() == null) {
                //se esquerdo ´é nulo, temos 0 ou 1 filho direito
                return raizSubarvore.getFilhoDireita();
            }
            
            if (raizSubarvore.getFilhoDireita() == null) {
               return raizSubarvore.getFilhoEsquerda();
            }
            
            //dois filhos
            No<T> antecessor = encontrarMaximo(raizSubarvore.getFilhoEsquerda());
            
            raizSubarvore.setValor(antecessor.getValor());
            
            No<T> novoEsquerda = remover(raizSubarvore.getFilhoEsquerda(), antecessor.getValor());
            
            raizSubarvore.setFilhoEsquerda(novoEsquerda);
            
            return raizSubarvore;
        }
    }
    @Override
    public T remover(T valor) {
        T valorRemovido = pesquisar(valor);
        
        if (valorRemovido == null){
            return null;
        }
        
        this.raiz = this.remover(this.raiz, valor);
        
        return valorRemovido;
        
    }
    
    private int altura(No<T> no){
        if (no == null){
            return -1;
        } else{
            int altura_esq = altura(no.getFilhoEsquerda());
            int altura_dir = altura(no.getFilhoDireita());
            int maior = Math.max(altura_esq, altura_dir);
            int resultado = maior + 1;
            return resultado;
        }
        
    }
    

    @Override
    public int altura() {
        return altura(this.raiz);

    } 
       
    private int quantidadeNos(No<T> no){
        if (no == null){
            return 0;
        } else{
            int qtd_nosDir = quantidadeNos(no.getFilhoDireita());
            int qtd_nosEsq = quantidadeNos(no.getFilhoEsquerda());
            return qtd_nosDir + qtd_nosEsq + 1;
        }
    }
    
    @Override
    public int quantidadeNos() {
        return quantidadeNos(this.raiz);
    }

    @Override
    public String caminharEmNivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }
    
    private String caminharEmOrdem(No<T> no){
        if (no==null){
            return "";
        }
        
        String resultadoEsquerda = caminharEmOrdem(no.getFilhoEsquerda());
        String valorAtual = no.getValor().toString() + " \n ";
        String resultadoDireita =  caminharEmOrdem(no.getFilhoDireita());
        
        return resultadoEsquerda + valorAtual + resultadoDireita;
    }
    
    @Override
    public String caminharEmOrdem() {
        String resultado = caminharEmOrdem(this.raiz).trim();
        return "[" + resultado + "]";
    }
        
}