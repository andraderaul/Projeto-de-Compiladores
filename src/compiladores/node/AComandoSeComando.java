/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AComandoSeComando extends PComando
{
    private TSe _se_;
    private TAParentese _aParentese_;
    private PExpLogica _expLogica_;
    private TFParentese _fParentese_;
    private TEntao _entao_;
    private PComandoList _comandoList_;
    private PComando _comando_;
    private PSenaoComando _senaoComando_;
    private TFimSe _fimSe_;
    private TPontoEVirg _pontoEVirg_;

    public AComandoSeComando()
    {
        // Constructor
    }

    public AComandoSeComando(
        @SuppressWarnings("hiding") TSe _se_,
        @SuppressWarnings("hiding") TAParentese _aParentese_,
        @SuppressWarnings("hiding") PExpLogica _expLogica_,
        @SuppressWarnings("hiding") TFParentese _fParentese_,
        @SuppressWarnings("hiding") TEntao _entao_,
        @SuppressWarnings("hiding") PComandoList _comandoList_,
        @SuppressWarnings("hiding") PComando _comando_,
        @SuppressWarnings("hiding") PSenaoComando _senaoComando_,
        @SuppressWarnings("hiding") TFimSe _fimSe_,
        @SuppressWarnings("hiding") TPontoEVirg _pontoEVirg_)
    {
        // Constructor
        setSe(_se_);

        setAParentese(_aParentese_);

        setExpLogica(_expLogica_);

        setFParentese(_fParentese_);

        setEntao(_entao_);

        setComandoList(_comandoList_);

        setComando(_comando_);

        setSenaoComando(_senaoComando_);

        setFimSe(_fimSe_);

        setPontoEVirg(_pontoEVirg_);

    }

    @Override
    public Object clone()
    {
        return new AComandoSeComando(
            cloneNode(this._se_),
            cloneNode(this._aParentese_),
            cloneNode(this._expLogica_),
            cloneNode(this._fParentese_),
            cloneNode(this._entao_),
            cloneNode(this._comandoList_),
            cloneNode(this._comando_),
            cloneNode(this._senaoComando_),
            cloneNode(this._fimSe_),
            cloneNode(this._pontoEVirg_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAComandoSeComando(this);
    }

    public TSe getSe()
    {
        return this._se_;
    }

    public void setSe(TSe node)
    {
        if(this._se_ != null)
        {
            this._se_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._se_ = node;
    }

    public TAParentese getAParentese()
    {
        return this._aParentese_;
    }

    public void setAParentese(TAParentese node)
    {
        if(this._aParentese_ != null)
        {
            this._aParentese_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._aParentese_ = node;
    }

    public PExpLogica getExpLogica()
    {
        return this._expLogica_;
    }

    public void setExpLogica(PExpLogica node)
    {
        if(this._expLogica_ != null)
        {
            this._expLogica_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expLogica_ = node;
    }

    public TFParentese getFParentese()
    {
        return this._fParentese_;
    }

    public void setFParentese(TFParentese node)
    {
        if(this._fParentese_ != null)
        {
            this._fParentese_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fParentese_ = node;
    }

    public TEntao getEntao()
    {
        return this._entao_;
    }

    public void setEntao(TEntao node)
    {
        if(this._entao_ != null)
        {
            this._entao_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._entao_ = node;
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

    public PComando getComando()
    {
        return this._comando_;
    }

    public void setComando(PComando node)
    {
        if(this._comando_ != null)
        {
            this._comando_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comando_ = node;
    }

    public PSenaoComando getSenaoComando()
    {
        return this._senaoComando_;
    }

    public void setSenaoComando(PSenaoComando node)
    {
        if(this._senaoComando_ != null)
        {
            this._senaoComando_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._senaoComando_ = node;
    }

    public TFimSe getFimSe()
    {
        return this._fimSe_;
    }

    public void setFimSe(TFimSe node)
    {
        if(this._fimSe_ != null)
        {
            this._fimSe_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fimSe_ = node;
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
            + toString(this._se_)
            + toString(this._aParentese_)
            + toString(this._expLogica_)
            + toString(this._fParentese_)
            + toString(this._entao_)
            + toString(this._comandoList_)
            + toString(this._comando_)
            + toString(this._senaoComando_)
            + toString(this._fimSe_)
            + toString(this._pontoEVirg_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._se_ == child)
        {
            this._se_ = null;
            return;
        }

        if(this._aParentese_ == child)
        {
            this._aParentese_ = null;
            return;
        }

        if(this._expLogica_ == child)
        {
            this._expLogica_ = null;
            return;
        }

        if(this._fParentese_ == child)
        {
            this._fParentese_ = null;
            return;
        }

        if(this._entao_ == child)
        {
            this._entao_ = null;
            return;
        }

        if(this._comandoList_ == child)
        {
            this._comandoList_ = null;
            return;
        }

        if(this._comando_ == child)
        {
            this._comando_ = null;
            return;
        }

        if(this._senaoComando_ == child)
        {
            this._senaoComando_ = null;
            return;
        }

        if(this._fimSe_ == child)
        {
            this._fimSe_ = null;
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
        if(this._se_ == oldChild)
        {
            setSe((TSe) newChild);
            return;
        }

        if(this._aParentese_ == oldChild)
        {
            setAParentese((TAParentese) newChild);
            return;
        }

        if(this._expLogica_ == oldChild)
        {
            setExpLogica((PExpLogica) newChild);
            return;
        }

        if(this._fParentese_ == oldChild)
        {
            setFParentese((TFParentese) newChild);
            return;
        }

        if(this._entao_ == oldChild)
        {
            setEntao((TEntao) newChild);
            return;
        }

        if(this._comandoList_ == oldChild)
        {
            setComandoList((PComandoList) newChild);
            return;
        }

        if(this._comando_ == oldChild)
        {
            setComando((PComando) newChild);
            return;
        }

        if(this._senaoComando_ == oldChild)
        {
            setSenaoComando((PSenaoComando) newChild);
            return;
        }

        if(this._fimSe_ == oldChild)
        {
            setFimSe((TFimSe) newChild);
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
