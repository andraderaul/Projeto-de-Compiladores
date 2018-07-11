/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class ADeclaracaoConstIdDeclaracaoList extends PDeclaracaoList
{
    private TConst _const_;
    private TId _id_;
    private PValor _valor_;
    private TPontoEVirg _pontoEVirg_;

    public ADeclaracaoConstIdDeclaracaoList()
    {
        // Constructor
    }

    public ADeclaracaoConstIdDeclaracaoList(
        @SuppressWarnings("hiding") TConst _const_,
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") PValor _valor_,
        @SuppressWarnings("hiding") TPontoEVirg _pontoEVirg_)
    {
        // Constructor
        setConst(_const_);

        setId(_id_);

        setValor(_valor_);

        setPontoEVirg(_pontoEVirg_);

    }

    @Override
    public Object clone()
    {
        return new ADeclaracaoConstIdDeclaracaoList(
            cloneNode(this._const_),
            cloneNode(this._id_),
            cloneNode(this._valor_),
            cloneNode(this._pontoEVirg_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADeclaracaoConstIdDeclaracaoList(this);
    }

    public TConst getConst()
    {
        return this._const_;
    }

    public void setConst(TConst node)
    {
        if(this._const_ != null)
        {
            this._const_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._const_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public PValor getValor()
    {
        return this._valor_;
    }

    public void setValor(PValor node)
    {
        if(this._valor_ != null)
        {
            this._valor_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._valor_ = node;
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
            + toString(this._const_)
            + toString(this._id_)
            + toString(this._valor_)
            + toString(this._pontoEVirg_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._const_ == child)
        {
            this._const_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._valor_ == child)
        {
            this._valor_ = null;
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
        if(this._const_ == oldChild)
        {
            setConst((TConst) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._valor_ == oldChild)
        {
            setValor((PValor) newChild);
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
