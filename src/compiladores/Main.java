/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import compiladores.lexer.*;
import java.io.FileReader;
import java.io.PushbackReader;
import compiladores.node.* ;
import java.io.IOException;
import static java.lang.System.out;

/**
 *
 * @author howl
 */

public class Main {
   public static void main(String[] args) throws LexerException {
      if (args.length > 0) {
         try {
            /* Form our AST */
             MyLexer lexer = new MyLexer(new PushbackReader(
              new FileReader(args[0]), 1024));
              Token token = null;
            while (true) {
                try {
                    token = lexer.next();
                    if (token instanceof EOF) {
                        break;
                    }
                    else if (token instanceof TBlank) {
                        out.print(token.getText());
                    }
                    else {
                        out.print(token.getClass().getSimpleName());
                    }
                }
                catch (LexerException e ){
                    out.println(e);
                    token = lexer.next();
                }
           }
           out.println();
         }
         catch (IOException e) {
            out.println (e) ;
         }
      } else {
         System.err.println("usage: java compiladores inputFile");
         System.exit(1);
      }
   }
}

