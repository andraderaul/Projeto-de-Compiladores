/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.analysis;

import compiladores.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAStart(AStart node);
    void caseATipoDeclaracoes(ATipoDeclaracoes node);
    void caseAConstDeclaracoes(AConstDeclaracoes node);
    void caseADeclaracao(ADeclaracao node);
    void caseARealTipo(ARealTipo node);
    void caseAIntTipo(AIntTipo node);
    void caseACharTipo(ACharTipo node);
    void caseABooleanoTipo(ABooleanoTipo node);
    void caseAIdVar(AIdVar node);
    void caseAIndexVar(AIndexVar node);
    void caseAStrValor(AStrValor node);
    void caseANumValor(ANumValor node);
    void caseANumrealValor(ANumrealValor node);
    void caseAExpExpOuElogica(AExpExpOuElogica node);
    void caseAElogicaExpOuElogica(AElogicaExpOuElogica node);
    void caseAComando(AComando node);
    void caseAAtribComandos(AAtribComandos node);
    void caseALeiaComandos(ALeiaComandos node);
    void caseAEscrevaComandos(AEscrevaComandos node);
    void caseASeComandos(ASeComandos node);
    void caseAAvalieComandos(AAvalieComandos node);
    void caseAEnquantoComandos(AEnquantoComandos node);
    void caseARepitaComandos(ARepitaComandos node);
    void caseAParaComandos(AParaComandos node);
    void caseAPassoComandos(APassoComandos node);
    void caseAValorExp(AValorExp node);
    void caseAVarExp(AVarExp node);
    void caseAFatorNegativoExp(AFatorNegativoExp node);
    void caseASomaExp(ASomaExp node);
    void caseASubtracaoExp(ASubtracaoExp node);
    void caseAMultExp(AMultExp node);
    void caseADivExp(ADivExp node);
    void caseAIgualExpLogica(AIgualExpLogica node);
    void caseADiferenteExpLogica(ADiferenteExpLogica node);
    void caseAMenorigualExpLogica(AMenorigualExpLogica node);
    void caseAMaiorigualExpLogica(AMaiorigualExpLogica node);
    void caseAMenorqExpLogica(AMenorqExpLogica node);
    void caseAMaiorqExpLogica(AMaiorqExpLogica node);
    void caseANaoElogicaExpLogica(ANaoElogicaExpLogica node);
    void caseAEExpLogica(AEExpLogica node);
    void caseAOuExpLogica(AOuExpLogica node);
    void caseAXorExpLogica(AXorExpLogica node);

    void caseTPrograma(TPrograma node);
    void caseTInicio(TInicio node);
    void caseTFimPrograma(TFimPrograma node);
    void caseTSe(TSe node);
    void caseTEntao(TEntao node);
    void caseTSenao(TSenao node);
    void caseTFimSe(TFimSe node);
    void caseTEnquanto(TEnquanto node);
    void caseTFaca(TFaca node);
    void caseTFimEnquanto(TFimEnquanto node);
    void caseTPara(TPara node);
    void caseTPasso(TPasso node);
    void caseTDe(TDe node);
    void caseTFimPara(TFimPara node);
    void caseTRepita(TRepita node);
    void caseTAte(TAte node);
    void caseTAvalie(TAvalie node);
    void caseTFimAvalie(TFimAvalie node);
    void caseTCaso(TCaso node);
    void caseTVerdadeiro(TVerdadeiro node);
    void caseTFalso(TFalso node);
    void caseTLeia(TLeia node);
    void caseTEscreva(TEscreva node);
    void caseTInteiro(TInteiro node);
    void caseTCaractere(TCaractere node);
    void caseTReal(TReal node);
    void caseTBooleano(TBooleano node);
    void caseTConst(TConst node);
    void caseTNao(TNao node);
    void caseTE(TE node);
    void caseTOu(TOu node);
    void caseTXor(TXor node);
    void caseTId(TId node);
    void caseTString(TString node);
    void caseTNum(TNum node);
    void caseTNumreal(TNumreal node);
    void caseTBlank(TBlank node);
    void caseTComentarioBloco(TComentarioBloco node);
    void caseTComentarioCorpo(TComentarioCorpo node);
    void caseTComentarioFimBloco(TComentarioFimBloco node);
    void caseTEstrela(TEstrela node);
    void caseTBarra(TBarra node);
    void caseTComentarioLinha(TComentarioLinha node);
    void caseTDoisPontos(TDoisPontos node);
    void caseTPontoEVirg(TPontoEVirg node);
    void caseTVirg(TVirg node);
    void caseTAParentese(TAParentese node);
    void caseTFParentese(TFParentese node);
    void caseTAColchete(TAColchete node);
    void caseTFColchete(TFColchete node);
    void caseTAspasDuplas(TAspasDuplas node);
    void caseTSublinhado(TSublinhado node);
    void caseTMais(TMais node);
    void caseTMenos(TMenos node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTMod(TMod node);
    void caseTMaiorq(TMaiorq node);
    void caseTMenorq(TMenorq node);
    void caseTMenorOuIgualQ(TMenorOuIgualQ node);
    void caseTMaiorOuIgualQ(TMaiorOuIgualQ node);
    void caseTIgual(TIgual node);
    void caseTDiferente(TDiferente node);
    void caseTAtrib(TAtrib node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
