/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import compiladores.lexer.Lexer;
//import compiladores.lexer.State;
import compiladores.node.*;

/**
 *
 * @author howl
 */
public class Comentario extends Lexer {
    private int contador;
    private TComentarioInicio comentario;
    private StringBuffer text;
    
    // definindo o construtor
    public Comentario(java.io.PushbackReader in) {
        super(in);
    }
    // definindo um filter que reconhece comentários aninhados.
    @Override
    protected void filter() {
        if (state.equals(State.COMENTARIO)) {
            // entrando no estado cometario
            if (comentario == null) { // o token deve ser um comentario
               comentario = (TComentarioInicio) token;
               text = new StringBuffer(comentario.getText());
               contador = 1;
               token = null;
            }
        }
        else {
            
            // ja estavamos em um estado comentario
            text.append(token.getText()); // 
            if (token instanceof TComentarioInicio) contador++ ;
            else if (token instanceof TComentarioFim) contador--;
            if (contador != 0) token = null;
            else {
                comentario.setText(text.toString());
                token = comentario;
                state = State.NORMAL;
                comentario = null;
            }
        }
    }
}
