/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AFatorNegativoFator extends PFator
{
    private TMenos _menos_;
    private PFator _fator_;

    public AFatorNegativoFator()
    {
        // Constructor
    }

    public AFatorNegativoFator(
        @SuppressWarnings("hiding") TMenos _menos_,
        @SuppressWarnings("hiding") PFator _fator_)
    {
        // Constructor
        setMenos(_menos_);

        setFator(_fator_);

    }

    @Override
    public Object clone()
    {
        return new AFatorNegativoFator(
            cloneNode(this._menos_),
            cloneNode(this._fator_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFatorNegativoFator(this);
    }

    public TMenos getMenos()
    {
        return this._menos_;
    }

    public void setMenos(TMenos node)
    {
        if(this._menos_ != null)
        {
            this._menos_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._menos_ = node;
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._menos_)
            + toString(this._fator_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._menos_ == child)
        {
            this._menos_ = null;
            return;
        }

        if(this._fator_ == child)
        {
            this._fator_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._menos_ == oldChild)
        {
            setMenos((TMenos) newChild);
            return;
        }

        if(this._fator_ == oldChild)
        {
            setFator((PFator) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
