import compiladores.lexer.Lexer;
import compiladores.lexer.Lexer.State;
import compiladores.lexer.LexerException;
import compiladores.node.*;
import java.io.IOException;

public class ComentarioAninhado extends Lexer { 
    private int count;
    private TComentarioBloco comment;
    private StringBuffer text;
    
    // We define a constructor
    public ComentarioAninhado(java.io.PushbackReader in) {
         super(in);
    }
    
    // We define a filter that recognizes nested comments.
    protected void filter() throws LexerException, IOException{
        // if we are in the comment state
        if(state.equals(State.COMENTARIO)) {
            // if we are just entering this state
            if(comment == null) {
                // The token is supposed to be a comment.
                // We keep a reference to it and set the count to one
                comment = (TComentarioBloco) token;
                text = new StringBuffer(comment.getText());
                count = 1;
                token = null; // continue to scan the input.
                } else {
                 // we were already in the comment state
                text.append(token.getText()); // accumulate the text.
                if(token instanceof TComentarioLinha);
                if(token instanceof TComentarioBloco)
                  count++;
                else if(token instanceof TComentarioFimBloco)
                  count--;
                if(count != 0) {
                    if(token instanceof EOF){
                        state = State.NORMAL;
                        throw new LexerException(null , "Token desconhecido ('" + comment + "') [Linha - " + comment.getLine() + ", Posicao - " + comment.getPos() + "]");
                    }
                    token = null; // continue to scan the input.
                } else {
                    token = comment; //return a comment with the full text.
                    state = State.NORMAL; //go back to normal.
                    comment = null; // We release this reference.
                }
            }
        }
    }
}
