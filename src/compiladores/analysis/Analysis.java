/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.analysis;

import compiladores.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseTTipo(TTipo node);
    void caseTId(TId node);
    void caseTNuminteiro(TNuminteiro node);
    void caseTNumreal(TNumreal node);
    void caseTCaractere(TCaractere node);
    void caseTBlank(TBlank node);
    void caseTComentarioDeBloco(TComentarioDeBloco node);
    void caseTComentarioFim(TComentarioFim node);
    void caseTComentarioCorpo(TComentarioCorpo node);
    void caseTEstrela(TEstrela node);
    void caseTBarra(TBarra node);
    void caseTComentarioLinha(TComentarioLinha node);
    void caseTDoisPontos(TDoisPontos node);
    void caseTPontoEVirgula(TPontoEVirgula node);
    void caseTAspasSimples(TAspasSimples node);
    void caseTAspasDuplas(TAspasDuplas node);
    void caseTSublinhado(TSublinhado node);
    void caseTEPar(TEPar node);
    void caseTDPar(TDPar node);
    void caseTECol(TECol node);
    void caseTDCol(TDCol node);
    void caseTMais(TMais node);
    void caseTMenos(TMenos node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTMod(TMod node);
    void caseTMaiorQue(TMaiorQue node);
    void caseTMenorQue(TMenorQue node);
    void caseTMenorOuIgualQue(TMenorOuIgualQue node);
    void caseTMaiorOuIgualQue(TMaiorOuIgualQue node);
    void caseTIgual(TIgual node);
    void caseTDiferente(TDiferente node);
    void caseTNegacao(TNegacao node);
    void caseTLogicalAnd(TLogicalAnd node);
    void caseTLogicalOr(TLogicalOr node);
    void caseTAtribuicao(TAtribuicao node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
