/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AFatorParentesesFator extends PFator
{
    private TAParentese _aParentese_;
    private PFator _fator_;
    private TFParentese _fParentese_;

    public AFatorParentesesFator()
    {
        // Constructor
    }

    public AFatorParentesesFator(
        @SuppressWarnings("hiding") TAParentese _aParentese_,
        @SuppressWarnings("hiding") PFator _fator_,
        @SuppressWarnings("hiding") TFParentese _fParentese_)
    {
        // Constructor
        setAParentese(_aParentese_);

        setFator(_fator_);

        setFParentese(_fParentese_);

    }

    @Override
    public Object clone()
    {
        return new AFatorParentesesFator(
            cloneNode(this._aParentese_),
            cloneNode(this._fator_),
            cloneNode(this._fParentese_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFatorParentesesFator(this);
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

    public PFator getFator()
    {
        return this._fator_;
    }

    public void setFator(PFator node)
    {
        if(this._fator_ != null)
        {
            this._fator_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fator_ = node;
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._aParentese_)
            + toString(this._fator_)
            + toString(this._fParentese_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._aParentese_ == child)
        {
            this._aParentese_ = null;
            return;
        }

        if(this._fator_ == child)
        {
            this._fator_ = null;
            return;
        }

        if(this._fParentese_ == child)
        {
            this._fParentese_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._aParentese_ == oldChild)
        {
            setAParentese((TAParentese) newChild);
            return;
        }

        if(this._fator_ == oldChild)
        {
            setFator((PFator) newChild);
            return;
        }

        if(this._fParentese_ == oldChild)
        {
            setFParentese((TFParentese) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
