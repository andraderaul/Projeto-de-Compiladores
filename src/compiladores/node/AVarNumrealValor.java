/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AVarNumrealValor extends PValor
{
    private TNumreal _numreal_;

    public AVarNumrealValor()
    {
        // Constructor
    }

    public AVarNumrealValor(
        @SuppressWarnings("hiding") TNumreal _numreal_)
    {
        // Constructor
        setNumreal(_numreal_);

    }

    @Override
    public Object clone()
    {
        return new AVarNumrealValor(
            cloneNode(this._numreal_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarNumrealValor(this);
    }

    public TNumreal getNumreal()
    {
        return this._numreal_;
    }

    public void setNumreal(TNumreal node)
    {
        if(this._numreal_ != null)
        {
            this._numreal_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._numreal_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._numreal_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._numreal_ == child)
        {
            this._numreal_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._numreal_ == oldChild)
        {
            setNumreal((TNumreal) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
