import compiladores.lexer.*;
import compiladores.node.*;

public class ComentarioAninhado extends Lexer { 
  private int count;
  private TComentarioBloco comentario;
  private StringBuffer text;
  // We define a constructor
  public ComentarioAninhado(java.io.PushbackReader in) { 
      super(in);
  }
  // We define a filter that recognizes nested comments.
@Override
  protected void filter() throws LexerException { // if we are in the comment state
    //System.out.println("PILHA: " +count);
    if (state.equals(State.COMENTARIO)) { // if we are just entering this state
      if (comentario == null) { // The token is supposed to be a comment.
        // We keep a reference to it and set the count to one
        if (token instanceof TComentarioFimBloco) {
            // checando se começa com comentario de final
            state = State.NORMAL;
            String message;
            message = "Erro de comentario de bloco. Linha: " + token.getLine() + ", posicao: " + token.getPos() + ". : TComentarioBlocoFimErrado";
            token = null;
            throw new LexerException(null, message);
        } else {
            comentario = (TComentarioBloco) token;
            text = new StringBuffer(comentario.getText());
            count = 1;
            token = null; // continue to scan the input.
        }
      } else { // we were already in the comment state
        text.append(token.getText()); // accumulate the text.
        if (token instanceof TComentarioLinha);
        if (token instanceof TComentarioBloco)
          count++;
        else if (token instanceof TComentarioFimBloco)
          count--;
        if(count != 0) {
            if (token instanceof EOF) {
               state = State.NORMAL;
               String message;
               message = "Erro de comentario de bloco. Linha: " + comentario.getLine() + ", posicao: " + comentario.getPos() + ".";
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