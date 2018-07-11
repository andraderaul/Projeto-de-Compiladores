/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AComandoPassoComandos extends PComandos
{
    private TPara _para_;
    private PVar _var_;
    private TDe _de_;
    private TNum _num_;
    private TPasso _passo_;
    private PNumA _numA_;
    private TAte _ate_;
    private PNumB _numB_;
    private TFaca _faca_;
    private PComandoList _comandoList_;
    private PComandos _comandos_;
    private TFimPara _fimPara_;
    private TPontoEVirg _pontoEVirg_;

    public AComandoPassoComandos()
    {
        // Constructor
    }

    public AComandoPassoComandos(
        @SuppressWarnings("hiding") TPara _para_,
        @SuppressWarnings("hiding") PVar _var_,
        @SuppressWarnings("hiding") TDe _de_,
        @SuppressWarnings("hiding") TNum _num_,
        @SuppressWarnings("hiding") TPasso _passo_,
        @SuppressWarnings("hiding") PNumA _numA_,
        @SuppressWarnings("hiding") TAte _ate_,
        @SuppressWarnings("hiding") PNumB _numB_,
        @SuppressWarnings("hiding") TFaca _faca_,
        @SuppressWarnings("hiding") PComandoList _comandoList_,
        @SuppressWarnings("hiding") PComandos _comandos_,
        @SuppressWarnings("hiding") TFimPara _fimPara_,
        @SuppressWarnings("hiding") TPontoEVirg _pontoEVirg_)
    {
        // Constructor
        setPara(_para_);

        setVar(_var_);

        setDe(_de_);

        setNum(_num_);

        setPasso(_passo_);

        setNumA(_numA_);

        setAte(_ate_);

        setNumB(_numB_);

        setFaca(_faca_);

        setComandoList(_comandoList_);

        setComandos(_comandos_);

        setFimPara(_fimPara_);

        setPontoEVirg(_pontoEVirg_);

    }

    @Override
    public Object clone()
    {
        return new AComandoPassoComandos(
            cloneNode(this._para_),
            cloneNode(this._var_),
            cloneNode(this._de_),
            cloneNode(this._num_),
            cloneNode(this._passo_),
            cloneNode(this._numA_),
            cloneNode(this._ate_),
            cloneNode(this._numB_),
            cloneNode(this._faca_),
            cloneNode(this._comandoList_),
            cloneNode(this._comandos_),
            cloneNode(this._fimPara_),
            cloneNode(this._pontoEVirg_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAComandoPassoComandos(this);
    }

    public TPara getPara()
    {
        return this._para_;
    }

    public void setPara(TPara node)
    {
        if(this._para_ != null)
        {
            this._para_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._para_ = node;
    }

    public PVar getVar()
    {
        return this._var_;
    }

    public void setVar(PVar node)
    {
        if(this._var_ != null)
        {
            this._var_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._var_ = node;
    }

    public TDe getDe()
    {
        return this._de_;
    }

    public void setDe(TDe node)
    {
        if(this._de_ != null)
        {
            this._de_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._de_ = node;
    }

    public TNum getNum()
    {
        return this._num_;
    }

    public void setNum(TNum node)
    {
        if(this._num_ != null)
        {
            this._num_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._num_ = node;
    }

    public TPasso getPasso()
    {
        return this._passo_;
    }

    public void setPasso(TPasso node)
    {
        if(this._passo_ != null)
        {
            this._passo_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._passo_ = node;
    }

    public PNumA getNumA()
    {
        return this._numA_;
    }

    public void setNumA(PNumA node)
    {
        if(this._numA_ != null)
        {
            this._numA_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._numA_ = node;
    }

    public TAte getAte()
    {
        return this._ate_;
    }

    public void setAte(TAte node)
    {
        if(this._ate_ != null)
        {
            this._ate_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ate_ = node;
    }

    public PNumB getNumB()
    {
        return this._numB_;
    }

    public void setNumB(PNumB node)
    {
        if(this._numB_ != null)
        {
            this._numB_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._numB_ = node;
    }

    public TFaca getFaca()
    {
        return this._faca_;
    }

    public void setFaca(TFaca node)
    {
        if(this._faca_ != null)
        {
            this._faca_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._faca_ = node;
    }

    public PComandoList getComandoList()
    {
        return this._comandoList_;
    }

    public void setComandoList(PComandoList node)
    {
        if(this._comandoList_ != null)
        {
            this._comandoList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comandoList_ = node;
    }

    public PComandos getComandos()
    {
        return this._comandos_;
    }

    public void setComandos(PComandos node)
    {
        if(this._comandos_ != null)
        {
            this._comandos_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comandos_ = node;
    }

    public TFimPara getFimPara()
    {
        return this._fimPara_;
    }

    public void setFimPara(TFimPara node)
    {
        if(this._fimPara_ != null)
        {
            this._fimPara_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fimPara_ = node;
    }

    public TPontoEVirg getPontoEVirg()
    {
        return this._pontoEVirg_;
    }

    public void setPontoEVirg(TPontoEVirg node)
    {
        if(this._pontoEVirg_ != null)
        {
            this._pontoEVirg_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pontoEVirg_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._para_)
            + toString(this._var_)
            + toString(this._de_)
            + toString(this._num_)
            + toString(this._passo_)
            + toString(this._numA_)
            + toString(this._ate_)
            + toString(this._numB_)
            + toString(this._faca_)
            + toString(this._comandoList_)
            + toString(this._comandos_)
            + toString(this._fimPara_)
            + toString(this._pontoEVirg_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._para_ == child)
        {
            this._para_ = null;
            return;
        }

        if(this._var_ == child)
        {
            this._var_ = null;
            return;
        }

        if(this._de_ == child)
        {
            this._de_ = null;
            return;
        }

        if(this._num_ == child)
        {
            this._num_ = null;
            return;
        }

        if(this._passo_ == child)
        {
            this._passo_ = null;
            return;
        }

        if(this._numA_ == child)
        {
            this._numA_ = null;
            return;
        }

        if(this._ate_ == child)
        {
            this._ate_ = null;
            return;
        }

        if(this._numB_ == child)
        {
            this._numB_ = null;
            return;
        }

        if(this._faca_ == child)
        {
            this._faca_ = null;
            return;
        }

        if(this._comandoList_ == child)
        {
            this._comandoList_ = null;
            return;
        }

        if(this._comandos_ == child)
        {
            this._comandos_ = null;
            return;
        }

        if(this._fimPara_ == child)
        {
            this._fimPara_ = null;
            return;
        }

        if(this._pontoEVirg_ == child)
        {
            this._pontoEVirg_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._para_ == oldChild)
        {
            setPara((TPara) newChild);
            return;
        }

        if(this._var_ == oldChild)
        {
            setVar((PVar) newChild);
            return;
        }

        if(this._de_ == oldChild)
        {
            setDe((TDe) newChild);
            return;
        }

        if(this._num_ == oldChild)
        {
            setNum((TNum) newChild);
            return;
        }

        if(this._passo_ == oldChild)
        {
            setPasso((TPasso) newChild);
            return;
        }

        if(this._numA_ == oldChild)
        {
            setNumA((PNumA) newChild);
            return;
        }

        if(this._ate_ == oldChild)
        {
            setAte((TAte) newChild);
            return;
        }

        if(this._numB_ == oldChild)
        {
            setNumB((PNumB) newChild);
            return;
        }

        if(this._faca_ == oldChild)
        {
            setFaca((TFaca) newChild);
            return;
        }

        if(this._comandoList_ == oldChild)
        {
            setComandoList((PComandoList) newChild);
            return;
        }

        if(this._comandos_ == oldChild)
        {
            setComandos((PComandos) newChild);
            return;
        }

        if(this._fimPara_ == oldChild)
        {
            setFimPara((TFimPara) newChild);
            return;
        }

        if(this._pontoEVirg_ == oldChild)
        {
            setPontoEVirg((TPontoEVirg) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
