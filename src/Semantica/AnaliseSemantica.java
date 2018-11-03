/* 
        group: Jaine Conceicao e Raul Andrade
        created on : 06/09/2018
*/

import compiladores.analysis.DepthFirstAdapter;
import compiladores.node.*;
import java.util.*;


public class AnaliseSemantica extends DepthFirstAdapter {
	
	//Tabela de Símbolos
        private Tabela table = new Tabela();
        
        //Classe para pegar o número da linha dado um "node"
        LineNumbers lineNumbers = null;
        
        public AnaliseSemantica(LineNumbers lineNumbers) {
            this.lineNumbers = lineNumbers;
        }
	
	//Verificar elementos da tabela pelo Main
	public Tabela getTable() {
		return table;
	}
	
    @Override
    public void outATipoDeclaracoes(ATipoDeclaracoes node) {        
        /*Pega o tipo do nó e o transforma em String*/
        PTipo tipo_noh = node.getTipo();
        String tipo = tipo_noh.toString().replace(" ", "");
        
        /*Checa se o tipo faz parte dos tipos existentes na nossa linguagem Jah*/
        if (!tipo.equals("inteiro") && !tipo.equals("real") && !tipo.equals("caractere"))
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipo não existente.");
        
        /*Se o tipo é válido, entra nesse else*/
        else {
            /*Checa variável por variável para saber se ela já foi declarada*/
            LinkedList<PVar> ident = node.getVars();
            for (PVar pVar : ident) {
                if(pVar instanceof AIdVar) {
                    String key = ((AIdVar) pVar).getId().toString().replace(" ", "");
                    if (table.contains(key)) { // report an error
                       System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '" + key + "' já foi definida.");
                    }
                    else {
                        table.insert(key, null, tipo, "0", "v");
                    }
                    
                /*Se a variável for um vetor, insere posição por posição dele na tabela hash*/    
                } else if(pVar instanceof AIndexVar){
                    String key = ((AIndexVar) pVar).getId().toString().replace(" ", "");
                    String tam_aux = ((AIndexVar) pVar).getNum().toString().replace(" ", "");
                    int tam = Integer.parseInt(tam_aux);
                    for (Integer i = 0; i < tam; i ++) {
                        key = ((AIndexVar) pVar).getId().toString().replace(" ", "")+i.toString();
                        if (table.contains(key)) { // report an error
                           System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '" + ((AIndexVar) pVar).getId().toString().replace(" ", "") + "' já foi definida.");
                        }
                        else {
                            table.insert(key, null, tipo, tam_aux, "v");
                        }
                    }
                }
            }
            /*Faz a mesma coisa de cima para a última variável da lista*/
            PVar var = node.getVar();
            if(var  instanceof AIdVar){
                String key = ((AIdVar) var).getId().toString().replace(" ", "");
                if (table.contains(key)) { // report an error
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '" + key + "' já foi definida.");
                }
                else {
                    table.insert(key, null, tipo, "0", "v");
                }
                
            }else if(var instanceof AIndexVar){
                    String key = ((AIndexVar) var).getId().toString().replace(" ", "");
                    String tam_aux = ((AIndexVar) var).getNum().toString().replace(" ", "");
                    int tam = Integer.parseInt(tam_aux);
                    for (Integer i = 0; i < tam; i ++) {
                        key = ((AIndexVar) var).getId().toString().replace(" ", "")+i.toString();
                        if (table.contains(key)) { // report an error
                           System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '" + ((AIndexVar) var).getId().toString().replace(" ", "") + "' já foi definida.");
                        }
                        else {
                            table.insert(key, null, tipo, tam_aux, "v");
                        }
                    }
            }
            
        }
        //imprimeTable();
    } 
    
    @Override
    public void outAConstDeclaracoes (AConstDeclaracoes node) {
        /*Verifica se uma constante ja foi declarada*/
        TId ident = node.getId();
        String key = ident.toString().replace(" ", "");
        if (table.contains(key)) { // report an error
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Constante '" + key + "' já foi definida.");
        }
        else {
            table.insert(key, node.getValor().toString().replace(" ", ""), type(node.getValor().toString().replace(" ", "")), "0", "const");
        }
    }

    @Override
    public void outAAtribComandos(AAtribComandos node) {
        /*Pega o nome (id) de uma variável que está recebendo uma atribuição e transforma esse id em String*/
        PVar ident = node.getVar();
        String key = ident.toString().replace(" ", "");
        
        /*Verifica se essa variável foi declarada, se não foi, lança um erro*/
        if (!table.contains(key)) {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '" +key.replace(" ", "")+ "' não declarada.");
        
        /*Se a variável foi declara, então checa agora se é "constante", pois se for o seu valor não pode ser alterado*/
        }else if (table.getKeyPos(key).equals("const")) {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Não é possível alterar o valor de uma constante.");
            
        /*Se a variável existir e não for constante, entra nesse else*/    
        } else {
            /*Pega o lado direito da atribuição, isto é, a expressão que está sendo atribudía à variável
            e a transforma em String*/
            PExp exp = node.getExp();
            String e = exp.toString().replace(" ", "");
             
            /*Checa se essa expressão de atribuição é uma variável*/
            if(exp instanceof AVarExp) {
                /*Se for, verifica se ela realmente foi declarada*/
                if(table.contains(e)) {
                    /*E salva o tipo dessa variável de atribuição*/
                    String tipo_var_atribuicao = table.getKeyTipo(e);
                    /*Se os tipos das variáveis forem iguais, entra aqui*/
                    if (tipo_var_atribuicao.equals(table.getKeyTipo(key))) {
                        /* (*) Se a variável de atribuição possui valor diferente de null, atribui seu valor
                        a vairável do lado esquerdo*/
                        if (table.getKeyValue(e) != null) {
                            String val = table.getKeyValue(e);
                            table.insert(key, val, table.getKeyTipo(key), "0", "-1");
                        /*Caso valor dela seja null, atribui null a var do lado esquerdo*/
                        } else table.insert(key, null, table.getKeyTipo(key), "0", "-1");
                        
                    /*Se os tipos não forem iguais, checa se são inteiro e real ao menos.
                        Se sim, o procedimento (*) se repete*/
                    } else if (tipo_var_atribuicao.equals("inteiro") && table.getKeyTipo(key).equals("real")){
                        if (table.getKeyValue(e) != null) {
                            String val = table.getKeyValue(e)+",0";
                            table.insert(key, val, table.getKeyTipo(key), "0", "-1");
                        } else table.insert(key, null, table.getKeyTipo(key), "0", "-1");

                    } else if (tipo_var_atribuicao.equals("real") && table.getKeyTipo(key).equals("inteiro")){
                        if (table.getKeyValue(e) != null) {
                            String[] val = table.getKeyValue(e).split(",");
                            table.insert(key, val[0], table.getKeyTipo(key), "0", "-1");
                        } else table.insert(key, null, table.getKeyTipo(key), "0", "-1");

                    /*Se os tipos não forem iguais ou ao menos númericos de ambas as variáveis, lança um erro*/
                    } else {
                       System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Atribuição não permitida (variável '"+key+"' é do tipo "+table.getKeyTipo(key)+" e '"+e+"' é do tipo "+tipo_var_atribuicao+").");
                    }
                /*Se a var de atribuição não foi declarada*/    
                }else {
                        System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável de atribuição '" +e.replace(" ", "")+ "' não declarada.");
                }

            } else if(exp instanceof AValorExp){
                 String tipo_valor_atribuicao = type(e);
                 if (tipo_valor_atribuicao.equals(table.getKeyTipo(key))) 
                     table.insert(key, e, table.getKeyTipo(key), "0", "-1");

                 else if (tipo_valor_atribuicao.equals("inteiro") && table.getKeyTipo(key).equals("real")){
                     String val = e+",0";
                     table.insert(key, val, table.getKeyTipo(key), "0", "-1");

                 }  else if (tipo_valor_atribuicao.equals("real") && table.getKeyTipo(key).equals("inteiro")){
                     String[] val = e.split(",");
                     table.insert(key, val[0], table.getKeyTipo(key), "0", "-1");

                 } else
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Atribuição não permitida (variável '"+key+"' é do tipo "+table.getKeyTipo(key)+" e "+e+" é do tipo "+tipo_valor_atribuicao+").");

            /*Se a exp de atribuição não foi uma variável e nem valor, 
                 só pode ser alguma expressão que foi calculada e salva na tabela*/     
            } else {
                if(!table.contains(e)){
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Atribuição inválida (a expressão que está sendo atribuída a variável '"+key+"' não é calculável).");
                
                }else {
                    String tipo_valor_atribuicao = table.getKeyTipo(e);
                    if (tipo_valor_atribuicao.equals(table.getKeyTipo(key))) 
                        table.insert(key, table.getKeyValue(e), table.getKeyTipo(key), "0", "-1");

                    else if (tipo_valor_atribuicao.equals("inteiro") && table.getKeyTipo(key).equals("real")){
                        String val = table.getKeyValue(e)+",0";
                        table.insert(key, val, table.getKeyTipo(key), "0", "-1");

                    }  else if (tipo_valor_atribuicao.equals("real") && table.getKeyTipo(key).equals("inteiro")){
                        String[] val = table.getKeyValue(e).split(",");
                        table.insert(key, val[0], table.getKeyTipo(key), "0", "-1");

                    } else
                        System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Atribuição inválida (variável '"+key+"' é do tipo "+table.getKeyTipo(key)+" e variável '"+e+"' é do tipo "+tipo_valor_atribuicao+").");
                }

            }
        }
 
        //imprimeTable();
    }
    
    /*Função para saber o tipo de um valor*/
    public String type (String s) {
        if(s != null && s.matches("[0-9]+"))
            return "inteiro";  
        else if (s.contains(",") && !s.contains("'"))
            return "real";
        return "caractere";
    }
     
    /*Função para imprimir os campos da tabela*/
    public void imprimeTable() {
        System.out.println(table.getIds());
        System.out.println(table.getTipos());
        System.out.println(table.getValues());
        System.out.println(table.getTams());
        System.out.println(table.getPosicoes());
            
    }
    
    @Override
    public void outALeiaComandos (ALeiaComandos node) {
        /*Verifica se as variáveis do comando "leia" realmente foram declaradas antes*/
        LinkedList<PVar> vars = node.getVars();
        for (PVar pVar : vars) {
            if(!table.contains(pVar.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '"+pVar.toString().replace(" ", "")+"' não declarada");
            }
        }
        PVar var = node.getVar();
         if(!table.contains(var.toString().replace(" ", ""))) {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '"+var.toString().replace(" ", "")+"' não declarada");
        }
    }
    
    @Override
    public void outAIndexVar (AIndexVar node) {
        try {
            /*Se a variável for um vetor, salvo o ID e tamanho (index)*/
            String key = node.getId().toString().replace(" ", "");
            String index = node.getNum().toString().replace(" ", "");            
            String tam = "0";
            /*Se esse vetor ainda não existe na tabela, ou seja, está sendo declarado
            agora pela primeira vez, então seu tamanho na tabela é 0*/
            if(!table.contains(key+"0")) tam = "0";
            /*Senão pego o tamanho que existe na tabela*/
            else tam = table.getKeyTam(key+"0");
            Integer index_i = Integer.parseInt(index);
            Integer tam_i = Integer.parseInt(tam);
            /*Se o tam != 0 é pq o vetor ja existe na tabela e possui um tamanho 
            definido*/
            if (tam_i != 0){
                /*Se o índice que estou tentando acessar é maior ou igual que o
                tamanho do vetor: erro*/
                if (index_i >= tam_i) 
                    System.err.println("[" + node.getId().getLine() + ", "+node.getId().getPos()+"] Erro Semântico: index out of range.");
            }
        } catch(Exception e){}
    }
    
    /*A Lógica empregada aqui para a expressão soma foi a mesma para subtração,
    multiplicação e dividsão*/
    @Override
    public void outASomaExp (ASomaExp node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
        
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", ""))) 
                return;
            else {
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) left).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Soma não é possível (o lado esquerdo da expressão é null).");
                    return;
                }
            }
            
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Soma não é possível (o lado esquerdo da expressão não é calculável).");
                return;
            }
            else 
                tipo_l = table.getKeyTipo(left.toString().replace(" ", ""));
        }
        
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", "")))      
                return;
            else {
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) right).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Soma não é possível (o lado direito da expressão é null).");
                    return;
                }
            }
            
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Soma não é possível (o lado direito da expressão não é calculável).");
                return;
            }
            else
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }
        
    
        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para somar.");
            ocorreuErro = true;
        }
        
        
        if(!ocorreuErro) {
            if(tipo_l.equals("inteiro") && tipo_l.equals(tipo_r)) {
                int l, r;
                
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Integer.parseInt(left.toString().replace(" ", ""));
                else 
                    l = Integer.parseInt(table.getKeyValue(left.toString().replace(" ", "")));

                
                if(!table.contains(right.toString().replace(" ", "")))
                    r = Integer.parseInt(right.toString().replace(" ", ""));
                else
                    r = Integer.parseInt(table.getKeyValue(right.toString().replace(" ", "")));
                
                Integer soma = l + r;
                table.insert(node.toString().replace(" ", ""), soma.toString(), tipo_l, "0", "-1");
                    
            } else if (tipo_l.equals("real") && tipo_l.equals(tipo_r)) {
                double l, r;
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Double.parseDouble(left.toString().replace(" ", ""));
                else
                    l = Double.parseDouble(table.getKeyValue(left.toString().replace(" ", "")));
                
                if(!table.contains(right.toString().replace(" ", ""))) 
                    r = Double.parseDouble(right.toString().replace(" ", ""));
                else
                    r = Double.parseDouble(table.getKeyValue(right.toString().replace(" ", "")));
                    
                Double soma = l + r;
                table.insert(node.toString().replace(" ", ""), soma.toString(), tipo_l, "0", "-1");
               
                
            } else if(tipo_l.equals("caractere")) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Caracteres não podem ser somados.");
                
                
            } else if (tipo_l.equals("inteiro")){
                int l;
                Double r;
                Integer aux2;
                
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Integer.parseInt(left.toString().replace(" ", ""));
                else
                    l = Integer.parseInt(table.getKeyValue(left.toString().replace(" ", "")));
                
                if(!table.contains(right.toString().replace(" ", ""))) {
                    String[] aux = right.toString().replace(" ", "").split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2.toString() + "." + aux[1];
                    r = Double.parseDouble(a);
                }else {
                    String str = table.getKeyValue(right.toString().replace(" ", ""));
                    String[] aux = str.split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2+"."+aux[1];
                    r = Double.parseDouble(a);
                }
                    
                Double soma = l + r;
                String s = soma.toString().replace(".", ",");
                table.insert(node.toString().replace(" ", ""), s, "real", "0", "-1");
                
                
            } else if (tipo_r.equals("inteiro")) {
                Double l;
                int r;
                Integer aux2;
                
                if(!table.contains(left.toString().replace(" ", ""))) {
                    String[] aux = left.toString().replace(" ", "").split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2.toString() + "." + aux[1];
                    l = Double.parseDouble(a); 
                }else {
                    String str = table.getKeyValue(left.toString().replace(" ", ""));
                    String[] aux = str.split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2 + "." + aux[1];
                    l = Double.parseDouble(a);
                }
                
                if(!table.contains(right.toString().replace(" ", ""))) 
                    r = Integer.parseInt(right.toString().replace(" ", ""));
                else
                    r = Integer.parseInt(table.getKeyValue(right.toString().replace(" ", "")));
                    
                Double soma = l + r;
                String s = soma.toString().replace(".", ",");
                table.insert(node.toString().replace(" ", ""), s, "real", "0", "-1");
            }
                       
        }
       
        //imprimeTable();
    }
    
    @Override
    public void outAVarExp (AVarExp node) {
        PVar v = node.getVar();
        String var = v.toString().replace(" ", "");
        if(!table.contains(var)) {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '" +var+ "' não declarada.");
        }
    }
    
    @Override
    public void outASubtracaoExp (ASubtracaoExp node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
        
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", ""))) 
                return;
            else {
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) left).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Subtração não é possível (o lado esquerdo da expressão é null).");
                    return;
                }
            }
            
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Subtração não é possível (o lado esquerdo da expressão não é calculável).");
                return;
            }
            else 
                tipo_l = table.getKeyTipo(left.toString().replace(" ", ""));
        }
        
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", ""))) 
                return;
            
            else {
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) right).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Subtração não é possível (o lado direito da expressão é null).");
                    return;
                }
            }
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Subtração não é possível (o lado direito da expressão não é calculável).");
                return;
            }
            else 
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }
            
        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para realizar subtração.");
            ocorreuErro = true;
        }

        if(!ocorreuErro) {
            if(tipo_l.equals("inteiro") && tipo_l.equals(tipo_r)) {
                int l, r;
                
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Integer.parseInt(left.toString().replace(" ", ""));
                else 
                    l = Integer.parseInt(table.getKeyValue(left.toString().replace(" ", "")));

                
                if(!table.contains(right.toString().replace(" ", "")))
                    r = Integer.parseInt(right.toString().replace(" ", ""));
                else
                    r = Integer.parseInt(table.getKeyValue(right.toString().replace(" ", "")));
                
                Integer subtracao = l - r;
                table.insert(node.toString().replace(" ", ""), subtracao.toString(), tipo_l, "0", "-1");
                    
            } else if (tipo_l.equals("real") && tipo_l.equals(tipo_r)) {
                double l, r;
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Double.parseDouble(left.toString().replace(" ", ""));
                else
                    l = Double.parseDouble(table.getKeyValue(left.toString().replace(" ", "")));
                
                if(!table.contains(right.toString().replace(" ", ""))) 
                    r = Double.parseDouble(right.toString().replace(" ", ""));
                else
                    r = Double.parseDouble(table.getKeyValue(right.toString().replace(" ", "")));
                    
                Double subtracao = l - r;
                table.insert(node.toString().replace(" ", ""), subtracao.toString(), tipo_l, "0", "-1");
               
                
            } else if(tipo_l.equals("caractere")) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Caracteres não podem ser subtraídos.");
              
            } else if (tipo_l.equals("inteiro")){
                int l;
                Double r;
                Integer aux2;
                
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Integer.parseInt(left.toString().replace(" ", ""));
                else
                    l = Integer.parseInt(table.getKeyValue(left.toString().replace(" ", "")));
                
                if(!table.contains(right.toString().replace(" ", ""))) {
                    String[] aux = right.toString().replace(" ", "").split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2.toString() + "." + aux[1];
                    r = Double.parseDouble(a);
                }
                else {
                    String str = table.getKeyValue(right.toString().replace(" ", ""));
                    String[] aux = str.split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2+"."+aux[1];
                    r = Double.parseDouble(a);
                }

                Double subtracao = l - r;
                String s = subtracao.toString().replace(".", ",");
                table.insert(node.toString().replace(" ", ""), s, "real", "0", "-1");
                
                
            } else if (tipo_r.equals("inteiro")) {
                Double l;
                int r;
                Integer aux2;
                
                if(!table.contains(left.toString().replace(" ", ""))) {
                    String[] aux = left.toString().replace(" ", "").split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2.toString() + "." + aux[1];
                    l = Double.parseDouble(a);
                }
                else {
                    String str = table.getKeyValue(left.toString().replace(" ", ""));
                    String[] aux = str.split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2 + "." + aux[1];
                    l = Double.parseDouble(a);
                }
                
                if(!table.contains(right.toString().replace(" ", ""))) 
                    r = Integer.parseInt(right.toString().replace(" ", ""));
                else
                    r = Integer.parseInt(table.getKeyValue(right.toString().replace(" ", "")));
                    
                Double subtracao = l - r;
                String s = subtracao.toString().replace(".", ",");
                table.insert(node.toString().replace(" ", ""), s, "real", "0", "-1");
            }
                      
        }
        
        //imprimeTable();
    }
    
    @Override
    public void outAMultExp (AMultExp node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
        
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", ""))) 
                return;
            else {
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) left).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Multiplicação não é possível (o lado esquerdo da expressão é null).");
                    return;
                }
            }
            
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Multiplicação não é possível (o lado esquerdo da expressão não é calculável).");
                return;
            }
            else
                tipo_l = table.getKeyTipo(left.toString().replace(" ", ""));
        }
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", ""))) 
                return;
            else {
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) right).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Multiplicação não é possível (o lado direito da expressão é null).");
                    return;
                }
            }
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Multiplicação não é possível (o lado direito da expressão não é calculável).");
                return;
            }
            else 
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }

        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para realizar multiplicação.");
            ocorreuErro = true;
        }
        
        if(!ocorreuErro) {
            if(tipo_l.equals("inteiro") && tipo_l.equals(tipo_r)) {
                int l, r;
                
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Integer.parseInt(left.toString().replace(" ", ""));
                else 
                    l = Integer.parseInt(table.getKeyValue(left.toString().replace(" ", "")));

                
                if(!table.contains(right.toString().replace(" ", "")))
                    r = Integer.parseInt(right.toString().replace(" ", ""));
                else
                    r = Integer.parseInt(table.getKeyValue(right.toString().replace(" ", "")));
                
                Integer mult = l * r;
                table.insert(node.toString().replace(" ", ""), mult.toString(), tipo_l, "0", "-1");
                    
            } else if (tipo_l.equals("real") && tipo_l.equals(tipo_r)) {
                double l, r;
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Double.parseDouble(left.toString().replace(" ", ""));
                else
                    l = Double.parseDouble(table.getKeyValue(left.toString().replace(" ", "")));
                
                if(!table.contains(right.toString().replace(" ", ""))) 
                    r = Double.parseDouble(right.toString().replace(" ", ""));
                else
                    r = Double.parseDouble(table.getKeyValue(right.toString().replace(" ", "")));
                    
                Double mult = l * r;
                table.insert(node.toString().replace(" ", ""), mult.toString(), tipo_l, "0", "-1");
               
                
            } else if(tipo_l.equals("caractere")) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Caracteres não podem ser multiplicados.");
                
            } else if (tipo_l.equals("inteiro")){
                int l;
                Double r;
                Integer aux2;
                
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Integer.parseInt(left.toString().replace(" ", ""));
                else
                    l = Integer.parseInt(table.getKeyValue(left.toString().replace(" ", "")));
                
                if(!table.contains(right.toString().replace(" ", ""))) {
                    String[] aux = right.toString().replace(" ", "").split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2.toString() + "." + aux[1];
                    r = Double.parseDouble(a);
                }
                else {
                    String str = table.getKeyValue(right.toString().replace(" ", ""));
                    String[] aux = str.split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2+"."+aux[1];
                    r = Double.parseDouble(a);
                }
                    
                Double mult = l * r;
                String s = mult.toString().replace(".", ",");
                table.insert(node.toString().replace(" ", ""), s, "real", "0", "-1");
                
                
            } else if (tipo_r.equals("inteiro")) {
                Double l;
                int r;
                Integer aux2;
                
                if(!table.contains(left.toString().replace(" ", ""))) {
                    String[] aux = left.toString().replace(" ", "").split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2.toString() + "." + aux[1];
                    l = Double.parseDouble(a);
                    
                }
                else {
                    String str = table.getKeyValue(left.toString().replace(" ", ""));
                    String[] aux = str.split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2 + "." + aux[1];
                    l = Double.parseDouble(a);
                }
                
                if(!table.contains(right.toString().replace(" ", ""))) 
                    r = Integer.parseInt(right.toString().replace(" ", ""));
                else
                    r = Integer.parseInt(table.getKeyValue(right.toString().replace(" ", "")));
                
                Double mult = l * r;
                String s = mult.toString().replace(".", ",");
                table.insert(node.toString().replace(" ", ""), s, "real", "0", "-1");
            }

        }
        //imprimeTable();
    }
    
    @Override
    public void outADivExp (ADivExp node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
        
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", ""))) 
                return;
            else {
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) left).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Divisão não é possível (o lado esquerdo da expressão é null).");
                    return;
                }
            }
            
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Divisão não é possível (o lado esquerdo da expressão não é calculável).");
                return;
            }
            else
                tipo_l = table.getKeyTipo(left.toString().replace(" ", ""));
        }
        
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", "")))
                return;
            else {
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) right).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Divisão não é possível (o lado direito da expressão é null).");
                    return;
                }
            }
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", ""))) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Divisão não é possível (o lado direito da expressão não é calculável).");
                return;
            }
            else 
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }
        
    
        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para realizar divisão.");
            ocorreuErro = true;
        }
           
        if(!ocorreuErro) {
            if(tipo_l.equals("inteiro") && tipo_l.equals(tipo_r)) {
                int l, r;
                
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Integer.parseInt(left.toString().replace(" ", ""));
                else 
                    l = Integer.parseInt(table.getKeyValue(left.toString().replace(" ", "")));

                
                if(!table.contains(right.toString().replace(" ", "")))
                    r = Integer.parseInt(right.toString().replace(" ", ""));
                else
                    r = Integer.parseInt(table.getKeyValue(right.toString().replace(" ", "")));
                
                Integer div = l / r;
                table.insert(node.toString().replace(" ", ""), div.toString(), tipo_l, "0", "-1");
                    
            } else if (tipo_l.equals("real") && tipo_l.equals(tipo_r)) {
                double l, r;
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Double.parseDouble(left.toString().replace(" ", ""));
                else
                    l = Double.parseDouble(table.getKeyValue(left.toString().replace(" ", "")));
                
                if(!table.contains(right.toString().replace(" ", ""))) 
                    r = Double.parseDouble(right.toString().replace(" ", ""));
                else
                    r = Double.parseDouble(table.getKeyValue(right.toString().replace(" ", "")));
                    
                Double div = l / r;
                table.insert(node.toString().replace(" ", ""), div.toString(), tipo_l, "0", "-1");
               
                
            } else if(tipo_l.equals("caractere")) {
                System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Caracteres não podem ser divididos.");
                
            } else if (tipo_l.equals("inteiro")){
                int l;
                Double r;
                Integer aux2;
                
                if(!table.contains(left.toString().replace(" ", ""))) 
                    l = Integer.parseInt(left.toString().replace(" ", ""));
                else
                    l = Integer.parseInt(table.getKeyValue(left.toString().replace(" ", "")));
                
                if(!table.contains(right.toString().replace(" ", ""))) {
                    String[] aux = right.toString().replace(" ", "").split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2.toString() + "." + aux[1];
                    r = Double.parseDouble(a);
                }
                else {
                    String str = table.getKeyValue(right.toString().replace(" ", ""));
                    String[] aux = str.split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2+"."+aux[1];
                    r = Double.parseDouble(a);
                }
              
                Double div = l / r;
                String s = div.toString().replace(".", ",");
                table.insert(node.toString().replace(" ", ""), s, "real", "0", "-1");
                
                
            } else if (tipo_r.equals("inteiro")) {
                Double l;
                int r;
                Integer aux2;
                
                if(!table.contains(left.toString().replace(" ", ""))) {
                    String[] aux = left.toString().replace(" ", "").split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2.toString() + "." + aux[1];
                    l = Double.parseDouble(a);
                    
                }
                else {
                    String str = table.getKeyValue(left.toString().replace(" ", ""));
                    String[] aux = str.split(",");
                    aux2 =  Integer.parseInt(aux[0]);
                    String a = aux2 + "." + aux[1];
                    l = Double.parseDouble(a);
                }
                
                if(!table.contains(right.toString().replace(" ", ""))) 
                    r = Integer.parseInt(right.toString().replace(" ", ""));
                else
                    r = Integer.parseInt(table.getKeyValue(right.toString().replace(" ", "")));

                Double div = l / r;
                String s = div.toString().replace(".", ",");
                table.insert(node.toString().replace(" ", ""), s, "real", "0", "-1");
            }
        }
        //imprimeTable();
    }
    
    @Override
    public void outAFatorNegativoExp (AFatorNegativoExp node) {
        PExp exp = node.getExp();
        String tipo = null;
        boolean ocorreuErro = false;
       
        if(exp instanceof AVarExp) {
            if(!table.contains(((AVarExp) exp).toString().replace(" ", "")))                 
                return;
            else {
                tipo = table.getKeyTipo(((AVarExp) exp).toString().replace(" ", ""));
                String val = table.getKeyValue(((AVarExp) exp).toString().replace(" ", ""));
                if(val == null) {
                    System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: 'Negativação' não é possível (o valor da expressão é null).");
                    return;
                }
            }
            
        } else if (exp instanceof AValorExp) 
            tipo = type(((AValorExp) exp).toString().replace(" ", ""));
        else {
            if(!table.contains(exp.toString().replace(" ", ""))) 
                return;
             else 
                tipo = table.getKeyTipo(exp.toString().replace(" ", ""));
        }
        
        if (tipo.equals("caractere")) {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipo caractere não pode ser 'negativado'.");
            ocorreuErro = true;
        }

        if(!ocorreuErro) {
            if(tipo.equals("inteiro")) {
                Integer e = 0;
                if(!table.contains(exp.toString().replace(" ", ""))) {
                    e = -Integer.parseInt(exp.toString().replace(" ", ""));
                    
                } else if(table.contains(exp.toString().replace(" ", ""))) { 
                    e = -Integer.parseInt(table.getKeyValue(exp.toString().replace(" ", "")));
                }
 
                String key = node.toString().replace(" ", "");
                table.insert(key, e.toString(), tipo, "0", "-1");
                    
            } else if (tipo.equals("real")) {
                Double e = null;
                if(!table.contains("-"+exp.toString().replace(" ", ""))) {
                    if(!table.contains(exp.toString().replace(" ", ""))) 
                        e = -Double.parseDouble(exp.toString().replace(" ", ""));
                     
                }else if(table.contains(exp.toString().replace(" ", ""))) 
                    e = -Double.parseDouble(table.getKeyValue(exp.toString().replace(" ", "")));
                    
                String key = node.toString().replace(" ", "");
                table.insert(key, e.toString(), tipo, "0", "-1");
            }
        }
        //imprimeTable();
    }
    
    @Override
    public void outAIgualExpLogica (AIgualExpLogica node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
        
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", ""))) 
                return;
            else
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
            
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) 
                return;
            else
                tipo_l = table.getKeyTipo(left.toString().replace(" ", "")); 
        }
        
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", ""))) 
                return;
            else
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
          
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", "")))    
                return;
            else 
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }

        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para comparação.");
            ocorreuErro = true;
        }
        //imprimeTable();
    }
    
    @Override
    public void outADiferenteExpLogica (ADiferenteExpLogica node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;

        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", "")))
                return;
            else 
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
            
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) 
                return;
            else 
                tipo_l = table.getKeyTipo(left.toString().replace(" ", ""));
        }
        
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", ""))) 
                return;
            else 
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
            
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", "")))
                return;
            else 
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }
        
    
        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para comparação.");
            ocorreuErro = true;
        }
    }
    
    @Override
    public void outAMenorigualExpLogica (AMenorigualExpLogica node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
        
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", ""))) 
                return;
            else
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
             
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) 
                return;
            else 
                tipo_l = table.getKeyTipo(left.toString().replace(" ", ""));
        }
        
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", ""))) 
                return;
            else 
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
           
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", ""))) 
                return;
            else
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }

        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para comparação.");
            ocorreuErro = true;
        }
    }
    
    @Override
    public void outAMaiorigualExpLogica (AMaiorigualExpLogica node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
       
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", "")))
                return;
            else 
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
            
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) 
                return;
            else 
                tipo_l = table.getKeyTipo(left.toString().replace(" ", "")); 
        }
                
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", "")))  
                return;
            else 
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));

        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", ""))) 
                return;
            else 
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }

        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para comparação.");
            ocorreuErro = true;
        }
    }
    
    @Override
    public void outAMenorqExpLogica (AMenorqExpLogica node) {
        PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
      
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", ""))) 
                return;
            else
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
     
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", ""))) 
                return;
            else 
                tipo_l = table.getKeyTipo(left.toString().replace(" ", ""));
        }
        
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", ""))) 
                return;
            else 
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
                
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", ""))) 
                return;
            else 
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }
    
        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para comparação.");
            ocorreuErro = true;
        }
    }
     
    @Override
    public void outAMaiorqExpLogica (AMaiorqExpLogica node) {
       PExp left = node.getL();
        PExp right = node.getR();
        String tipo_l = null;
        String tipo_r = null;
        boolean ocorreuErro = false;
      
        if(left instanceof AVarExp) {
            if(!table.contains(((AVarExp) left).toString().replace(" ", ""))) 
                return;
            else 
                tipo_l = table.getKeyTipo(((AVarExp) left).toString().replace(" ", ""));
            
        } else if (left instanceof AValorExp) {
            tipo_l = type(((AValorExp) left).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(left.toString().replace(" ", "")))
                return;
            else 
                tipo_l = table.getKeyTipo(left.toString().replace(" ", ""));
        }
        
        
        if (right instanceof AVarExp) {
            if(!table.contains(((AVarExp) right).toString().replace(" ", ""))) 
                return;
            else 
                tipo_r = table.getKeyTipo(((AVarExp) right).toString().replace(" ", ""));
   
        } else if (right instanceof AValorExp) {
            tipo_r = type(((AValorExp) right).toString().replace(" ", ""));
            
        } else {
            if(!table.contains(right.toString().replace(" ", "")))
                return;
            else 
                tipo_r = table.getKeyTipo(right.toString().replace(" ", ""));
        }
        
    
        if (tipo_l.equals(tipo_r) || (tipo_l.equals("inteiro") && tipo_r.equals("real")) || (tipo_l.equals("real") && tipo_r.equals("inteiro")) ) {
            ocorreuErro = false;
        } else {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Tipos incompatíveis para comparação.");
            ocorreuErro = true;
        }
    }
    
    @Override
    public void outAParaComandos (AParaComandos node) {
        PVar var = node.getVar();
        if(!table.contains(var.toString().replace(" ", ""))) {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '" +var.toString().replace(" ", "")+ "' não declarada.");
        }
        if (!(node.getA() instanceof ANumValor)) { 
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: O Tipo do contador não é inteiro.");
        } 
        if (!(node.getB() instanceof ANumValor)) { 
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: O Tipo do contador não é inteiro.");
        }
    }
    
    @Override
    public void outAPassoComandos (APassoComandos node) {
        PVar var = node.getVar();
        if(!table.contains(var.toString().replace(" ", ""))) {
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: Variável '" +var.toString().replace(" ", "")+ "' não declarada.");
        }
        if (!(node.getA() instanceof ANumValor)) { 
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: O Tipo do contador não é inteiro.");
        } 
        if (!(node.getB() instanceof ANumValor)) { 
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: O Tipo do contador não é inteiro.");
        }
        if (!(node.getC()instanceof ANumValor)) { 
            System.err.println("[Linha: " + lineNumbers.getLine(node)+"] Erro Semântico: O Tipo do contador não é inteiro.");
        } 
    }
   
    /*Imprime os valores finais das variáveis presentes na tabela*/
    public void valoresFinaisVariaveis () {
        System.out.println("\nVALORES FINAIS DAS VARIÁVEIS: ");
        for (String id : table.getIds()) {
            if (table.getKeyPos(id).equals("v")) {
                System.out.println(id + " = " + table.getKeyValue(id));
            }
        }
    }
    
    @Override
    public void inStart(Start node){
        System.out.println("Iniciando Análise Semântica!\n\n");
    }

    @Override
    public void outStart(Start node){
        System.out.println("\n\nAnálise Semântica realizada com sucesso!");
    }
    
}