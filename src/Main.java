import compiladores.lexer.*;
import java.io.FileReader;
import java.io.PushbackReader;
import compiladores.node.* ;
import static java.lang.System.out;


public class Main {
   public static void main(String[] args) {
      if (args.length > 0) {
         try {
            /* Form our AST */
        	 ComentarioAninhado lexer = new ComentarioAninhado(new PushbackReader(
        			 new FileReader(args[0]), 1024));
             Token token = null;
             while (true) {
            	try {
	                token = lexer.next();
	                if (token instanceof EOF) {
	                    break;
	                }
	                else if (token instanceof TBlank) {
	                    System.out.print(token.getText());
	                }
	                else {
	                    System.out.print(token.getClass().getSimpleName());
	                }    
            	} catch (LexerException e) {
 	                System.out.println(e.getMessage());
 	                token = lexer.next();
				}
           }
           out.println();
         }
         catch (Exception e) {
            System.out.println (e) ;
         }
      } else {
         System.err.println("usage: java simpleAdder inputFile");
         System.exit(1);
      }
   }
}