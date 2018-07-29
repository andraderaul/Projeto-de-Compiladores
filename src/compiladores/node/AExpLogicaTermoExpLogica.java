/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AExpLogicaTermoExpLogica extends PExpLogica
{
    private PExpLogicaTermo _expLogicaTermo_;

    public AExpLogicaTermoExpLogica()
    {
        // Constructor
    }

    public AExpLogicaTermoExpLogica(
        @SuppressWarnings("hiding") PExpLogicaTermo _expLogicaTermo_)
    {
        // Constructor
        setExpLogicaTermo(_expLogicaTermo_);

    }

    @Override
    public Object clone()
    {
        return new AExpLogicaTermoExpLogica(
            cloneNode(this._expLogicaTermo_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExpLogicaTermoExpLogica(this);
    }

    public PExpLogicaTermo getExpLogicaTermo()
    {
        return this._expLogicaTermo_;
    }

    public void setExpLogicaTermo(PExpLogicaTermo node)
    {
        if(this._expLogicaTermo_ != null)
        {
            this._expLogicaTermo_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expLogicaTermo_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expLogicaTermo_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expLogicaTermo_ == child)
        {
            this._expLogicaTermo_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expLogicaTermo_ == oldChild)
        {
            setExpLogicaTermo((PExpLogicaTermo) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}