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

/**
 *
 * @author howl
 */

public class Main {
   public static void main(String[] args) {
      if (args.length > 0) {
         try {
            /* Form our AST */
          //  Lexer lexer = new Lexer (new PushbackReader(
          //     new FileReader(args[0]), 1024));
             MyLexer lexer = new MyLexer(new PushbackReader(
              new FileReader(args[0]), 1024));
            while (true) {
                Token token = lexer.next();
                if (token instanceof EOF) {
                    break;
                }
                else if (!(token instanceof TComentario))
                    System.out.println("Texto: " + token.getText() + " " + "| Classe: " + token.getClass().getSimpleName());
                    
           }
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

