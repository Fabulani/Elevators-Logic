package busca;

import java.util.LinkedList;
import java.util.List;

import busca.BuscaLargura;
import busca.Estado;
import busca.Nodo;

/**
 * Representa um estado do mundo: em que lado estao
 * o homem, o lobo, o alface e o carneiro
 */
public class ElevatorLogic implements Estado {
    
    public String getDescricao() {
        return
        "Por favor, ajude esses 5 caracteres a sair dos elevadores. \n"+
		"Para abrir as portas, todos os elevadores devem estar entre \n"+
		"os 21 e os 25 andares (nível 2: 21-23). \n"+
		"Existem dois botões: +8 e -13, que serão ativados apenas quando \n"+
		"2 elevadores forem selecionados. O edifício tem 49 andares. \n\n";
    }
    
    final int e1, e2, e3, e4, e5; // cada elevador.
    final String op; // operacao que gerou o estado
    
    /** construtor para o estado, recebe cada valor de atributo */
    public ElevatorLogic(int x1, int x2, int x3, int x4, int x5, String o) {
        e1 = x1;
        e2 = x2;
        e3 = x3;
        e4 = x4;
		e5 = x5;
        op = o;
    }
    
    /**
     * verifica se o estado e meta
     */
    public boolean ehMeta() {
        return (e1>20 && e1<26) && (e2>20 && e2<26) && (e3>20 && e3<26) && (e4>20 && e4<26) && (e5>20 && e5<26);
    }
    
    /**
     * Custo para geracao do estado
     */
    public int custo() {
        return 1;
    }
    
    /**
     * gera uma lista de sucessores do nodo.
     */
    public List<Estado> sucessores() {
        List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
        
        moverE1(suc); // tenta mover e1 e suas combinações.
        moverE2(suc); // tenta mover e2 e suas combinações (exceto anteriores).
        moverE3(suc); // tenta mover e3 e suas combinações (exceto anteriores).
        moverE4(suc); // tenta mover e4 e suas combinações (exceto anteriores).
        
        return suc;
    }
    
    private void moverE1(List<Estado> suc) {
		// SUBIR
		int novoE1 = subir(e1);
		
		// e2
		int novoE2 = subir(e2);
		String novoOp = "subir E1-"+Integer.toString(e1)+" E2-"+Integer.toString(e2)+" -> E1-"+Integer.toString(novoE1)+" E2-"+Integer.toString(novoE2);
		ElevatorLogic novo2 = new ElevatorLogic(novoE1, novoE2, e3, e4, e5, novoOp);
		if (novo2.ehValido()) {
			suc.add(novo2);
		}
		
		// e3
		int novoE3 = subir(e3);
		novoOp = "subir E1-"+Integer.toString(e1)+" E3-"+Integer.toString(e3)+" -> E1-"+Integer.toString(novoE1)+" E3-"+Integer.toString(novoE3);
		ElevatorLogic novo3 = new ElevatorLogic(novoE1, e2, novoE3, e4, e5, novoOp);
		if (novo3.ehValido()) {
			suc.add(novo3);
		}
		
		// e4
		int novoE4 = subir(e4);
		novoOp = "subir E1-"+Integer.toString(e1)+" E4-"+Integer.toString(e4)+" -> E1-"+Integer.toString(novoE1)+" E4-"+Integer.toString(novoE4);
		ElevatorLogic novo4 = new ElevatorLogic(novoE1, e2, e3, novoE4, e5, novoOp);
		if (novo4.ehValido()) {
			suc.add(novo4);
		}
		
		// e5
		int novoE5 = subir(e5);
		novoOp = "subir E1-"+Integer.toString(e1)+" E5-"+Integer.toString(e5)+" -> E1-"+Integer.toString(novoE1)+" E5-"+Integer.toString(novoE5);
		ElevatorLogic novo5 = new ElevatorLogic(novoE1, e2, e3, e4, novoE5, novoOp);
		if (novo5.ehValido()) {
			suc.add(novo5);
		}
		
		// DESCER
		novoE1 = descer(e1);
		
		// e2
		novoE2 = descer(e2);
		novoOp = "descer E1-"+Integer.toString(e1)+" E2-"+Integer.toString(e2)+" -> E1-"+Integer.toString(novoE1)+" E2-"+Integer.toString(novoE2);
		ElevatorLogic novo2d = new ElevatorLogic(novoE1, novoE2, e3, e4, e5, novoOp);
		if (novo2d.ehValido()) {
			suc.add(novo2d);
		}
		
		// e3
		novoE3 = descer(e3);
		novoOp = "descer E1-"+Integer.toString(e1)+" E3-"+Integer.toString(e3)+" -> E1-"+Integer.toString(novoE1)+" E3-"+Integer.toString(novoE3);
		ElevatorLogic novo3d = new ElevatorLogic(novoE1, e2, novoE3, e4, e5, novoOp);
		if (novo3d.ehValido()) {
			suc.add(novo3d);
		}
		
		// e4
		novoE4 = descer(e4);
		novoOp = "descer E1-"+Integer.toString(e1)+" E4-"+Integer.toString(e4)+" -> E1-"+Integer.toString(novoE1)+" E4-"+Integer.toString(novoE4);
		ElevatorLogic novo4d = new ElevatorLogic(novoE1, e2, e3, novoE4, e5, novoOp);
		if (novo4d.ehValido()) {
			suc.add(novo4d);
		}
		
		// e5
		novoE5 = descer(e5);
		novoOp = "descer E1-"+Integer.toString(e1)+" E5-"+Integer.toString(e5)+" -> E1-"+Integer.toString(novoE1)+" E5-"+Integer.toString(novoE5);
		ElevatorLogic novo5d = new ElevatorLogic(novoE1, e2, e3, e4, novoE5, novoOp);
		if (novo5d.ehValido()) {
			suc.add(novo5d);
		}
	
    }
    
    private void moverE2(List<Estado> suc) {
        int novoE2 = subir(e2);
		
		// e3
		int novoE3 = subir(e3);
		String novoOp = "subir E2-"+Integer.toString(e2)+" E3-"+Integer.toString(e3)+" -> E2-"+Integer.toString(novoE2)+" E3-"+Integer.toString(novoE3);
		ElevatorLogic novo3 = new ElevatorLogic(e1, novoE2, novoE3, e4, e5, novoOp);
		if (novo3.ehValido()) {
			suc.add(novo3);
		}
		
		// e4
		int novoE4 = subir(e4);
		novoOp = "subir E2-"+Integer.toString(e2)+" E4-"+Integer.toString(e4)+" -> E2-"+Integer.toString(novoE2)+" E4-"+Integer.toString(novoE4);
		ElevatorLogic novo4 = new ElevatorLogic(e1, novoE2, e3, novoE4, e5, novoOp);
		if (novo4.ehValido()) {
			suc.add(novo4);
		}
		
		// e5
		int novoE5 = subir(e5);
		novoOp = "subir E2-"+Integer.toString(e2)+" E5-"+Integer.toString(e5)+" -> E2-"+Integer.toString(novoE2)+" E5-"+Integer.toString(novoE5);
		ElevatorLogic novo5 = new ElevatorLogic(e1, novoE2, e3, e4, novoE5, novoOp);
		if (novo5.ehValido()) {
			suc.add(novo5);
		}
		
		// DESCER
		novoE2 = descer(e2);
		
		// e3
		novoE3 = descer(e3);
		novoOp = "descer E2-"+Integer.toString(e2)+" E3-"+Integer.toString(e3)+" -> E2-"+Integer.toString(novoE2)+" E3-"+Integer.toString(novoE3);
		ElevatorLogic novo3d = new ElevatorLogic(e1, novoE2, novoE3, e4, e5, novoOp);
		if (novo3d.ehValido()) {
			suc.add(novo3d);
		}
		
		// e4
		novoE4 = descer(e4);
		novoOp = "descer E2-"+Integer.toString(e2)+" E4-"+Integer.toString(e4)+" -> E2-"+Integer.toString(novoE2)+" E4-"+Integer.toString(novoE4);
		ElevatorLogic novo4d = new ElevatorLogic(e1, novoE2, e3, novoE4, e5, novoOp);
		if (novo4d.ehValido()) {
			suc.add(novo4d);
		}
		
		// e5
		novoE5 = descer(e5);
		novoOp = "descer E2-"+Integer.toString(e2)+" E5-"+Integer.toString(e5)+" -> E2-"+Integer.toString(novoE2)+" E5-"+Integer.toString(novoE5);
		ElevatorLogic novo5d = new ElevatorLogic(e1, novoE2, e3, e4, novoE5, novoOp);
		if (novo5d.ehValido()) {
			suc.add(novo5d);
		}
    }
    
    private void moverE3(List<Estado> suc) {
        int novoE3 = subir(e3);
		
		// e4
		int novoE4 = subir(e4);
		String novoOp = "subir E3-"+Integer.toString(e3)+" E4-"+Integer.toString(e4)+" -> E3-"+Integer.toString(novoE3)+" E4-"+Integer.toString(novoE4);
		ElevatorLogic novo4 = new ElevatorLogic(e1, e2, novoE3, novoE4, e5, novoOp);
		if (novo4.ehValido()) {
			suc.add(novo4);
		}
		
		// e5
		int novoE5 = subir(e5);
		novoOp = "subir E3-"+Integer.toString(e3)+" E5-"+Integer.toString(e5)+" -> E3-"+Integer.toString(novoE3)+" E5-"+Integer.toString(novoE5);
		ElevatorLogic novo5 = new ElevatorLogic(e1, e2, novoE3, e4, novoE5, novoOp);
		if (novo5.ehValido()) {
			suc.add(novo5);
		}
		
		// DESCER
		novoE3 = descer(e3);
		
		// e4
		novoE4 = descer(e4);
		novoOp = "descer E3-"+Integer.toString(e3)+" E4-"+Integer.toString(e4)+" -> E3-"+Integer.toString(novoE3)+" E4-"+Integer.toString(novoE4);
		ElevatorLogic novo4d = new ElevatorLogic(e1, e2, novoE3, novoE4, e5, novoOp);
		if (novo4d.ehValido()) {
			suc.add(novo4d);
		}
		
		// e5
		novoE5 = descer(e5);
		novoOp = "descer E3-"+Integer.toString(e3)+" E5-"+Integer.toString(e5)+" -> E3-"+Integer.toString(novoE3)+" E5-"+Integer.toString(novoE5);
		ElevatorLogic novo5d = new ElevatorLogic(e1, e2, novoE3, e4, novoE5, novoOp);
		if (novo5d.ehValido()) {
			suc.add(novo5d);
		}
    }
    
    private void moverE4(List<Estado> suc) {
    	int novoE4 = subir(e4);
		
		// e5
		int novoE5 = subir(e5);
		String novoOp = "subir E4-"+Integer.toString(e4)+" E5-"+Integer.toString(e5)+" -> E4-"+Integer.toString(novoE4)+" E5-"+Integer.toString(novoE5);
		ElevatorLogic novo5 = new ElevatorLogic(e1, e2, e3, novoE4, novoE5, novoOp);
		if (novo5.ehValido()) {
			suc.add(novo5);
		}
		
		// DESCER
		novoE4 = descer(e4);
		
		// e5
		novoE5 = descer(e5);
		novoOp = "descer E4-"+Integer.toString(e4)+" E5-"+Integer.toString(e5)+" -> E4-"+Integer.toString(novoE4)+" E5-"+Integer.toString(novoE5);
		ElevatorLogic novo5d = new ElevatorLogic(e1, e2, e3, novoE4, novoE5, novoOp);
		if (novo5d.ehValido()) {
			suc.add(novo5d);
		}
    }

    private int subir(int e) {  // sobe 8 andares.
    	e += 8;
		return e;
    }
    
	private int descer(int e) { // desce 13 andares.
		e -= 13;
		return e;
	}
	
    
    /** returna true se o estado e valido **/
    private boolean ehValido() {  // válido se todos os andares estiverem entre 0 e 49.
        return (e1>=0 && e1<=49) && (e2>=0 && e2<=49) && (e3>=0 && e3<=49) && (e4>=0 && e4<=49) && (e5>=0 && e5<=49);
    }
    
    /**
     * verifica se um estado e igual a outro
     * (usado para poda)
     */
    public boolean equals(Object o) {
        if (o instanceof ElevatorLogic) {
            ElevatorLogic e = (ElevatorLogic)o;
            return e1==e.e1 && e2==e.e2 && e3==e.e3 && e4==e.e4 && e5==e.e5;
        }
        return false;
    }
    
    /** 
     * retorna o hashCode desse estado
     * (usado para poda, conjunto de fechados)
     */
    public int hashCode() {
        return (""+Integer.toString(e1)+Integer.toString(e2)+Integer.toString(e3)+Integer.toString(e4)+Integer.toString(e5)).hashCode();
    }
    
    public String toString() {
        return "\nE1-" + Integer.toString(e1) + " E2-" + Integer.toString(e2)+ " E3-" + Integer.toString(e3)+ " E4-" + Integer.toString(e4)+ " E5-" + Integer.toString(e5) + " (" + op + ")";
    }
    
    public static void main(String[] a) {
        ElevatorLogic inicial = new ElevatorLogic(17, 26, 20, 19, 31, "inicial");
        
        // chama busca em largura
        System.out.println("busca em largura");
        Nodo n = new BuscaLargura().busca(inicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
    }
}
