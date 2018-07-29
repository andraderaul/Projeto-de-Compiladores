/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AElogicaFatorMaiorqExpLogicaFator extends PExpLogicaFator
{
    private PExp _exp_;
    private TMaiorq _maiorq_;
    private PTermo _termo_;

    public AElogicaFatorMaiorqExpLogicaFator()
    {
        // Constructor
    }

    public AElogicaFatorMaiorqExpLogicaFator(
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TMaiorq _maiorq_,
        @SuppressWarnings("hiding") PTermo _termo_)
    {
        // Constructor
        setExp(_exp_);

        setMaiorq(_maiorq_);

        setTermo(_termo_);

    }

    @Override
    public Object clone()
    {
        return new AElogicaFatorMaiorqExpLogicaFator(
            cloneNode(this._exp_),
            cloneNode(this._maiorq_),
            cloneNode(this._termo_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAElogicaFatorMaiorqExpLogicaFator(this);
    }

    public PExp getExp()
    {
        return this._exp_;
    }

    public void setExp(PExp node)
    {
        if(this._exp_ != null)
        {
            this._exp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp_ = node;
    }

    public TMaiorq getMaiorq()
    {
        return this._maiorq_;
    }

    public void setMaiorq(TMaiorq node)
    {
        if(this._maiorq_ != null)
        {
            this._maiorq_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._maiorq_ = node;
    }

    public PTermo getTermo()
    {
        return this._termo_;
    }

    public void setTermo(PTermo node)
    {
        if(this._termo_ != null)
        {
            this._termo_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._termo_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._exp_)
            + toString(this._maiorq_)
            + toString(this._termo_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._maiorq_ == child)
        {
            this._maiorq_ = null;
            return;
        }

        if(this._termo_ == child)
        {
            this._termo_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._maiorq_ == oldChild)
        {
            setMaiorq((TMaiorq) newChild);
            return;
        }

        if(this._termo_ == oldChild)
        {
            setTermo((PTermo) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}