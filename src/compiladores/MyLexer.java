/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

/**
 *
 * @author howl
 */
import compiladores.lexer.*;
import compiladores.node.*;

public class MyLexer extends Lexer { 
  private int count;
  private TComentarioDeBloco comentario;
  private StringBuffer text;
  // We define a constructor
  public MyLexer(java.io.PushbackReader in) { 
      super(in);
  }
  // We define a filter that recognizes nested comments.
@Override
  protected void filter() throws LexerException { // if we are in the comment state
    //System.out.println("PILHA: " +count);
    if (state.equals(State.COMENTARIO)) { // if we are just entering this state
      if (comentario == null) { // The token is supposed to be a comment.
        // We keep a reference to it and set the count to one
        if (token instanceof TComentarioFim) {
            // checando se começa com comentario de final
            state = State.NORMAL;
            String message;
            message = "Erro de comentario de bloco. ('" + token.getText() + "') [Linha - " + token.getLine() + ", Posicao - " + token.getPos() + "]";
            throw new LexerException(null, message);
        }
        else {
            comentario = (TComentarioDeBloco) token;
            text = new StringBuffer(comentario.getText());
            count = 1;
            token = null; // continue to scan the input.
        }
      }
      else { // we were already in the comment state
        text.append(token.getText()); // accumulate the text.
        if (token instanceof TComentarioLinha);
        if (token instanceof TComentarioDeBloco)
          count++;
        else if (token instanceof TComentarioFim)
          count--;
        if(count != 0) {
            if (token instanceof EOF) {
               state = State.NORMAL;
               String message;
               message = "Erro de comentario de bloco. ('" + comentario.getText() + "') [Linha - " + comentario.getLine() + "], Posicao - " + comentario.getPos();
               
              throw new LexerException(null, message);
           } 
            
            token = null; // continue to scan the input.
        }
        else { 
          //comentarioInicio.setText(s);
          token = comentario; //return a comment with the full text.
          state = State.NORMAL; //go back to normal.
          comentario = null; // We release this reference.
        }
      }
    }
  }
}