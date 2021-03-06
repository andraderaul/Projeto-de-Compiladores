/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AAtribComandos extends PComandos
{
    private PVar _var_;
    private PExpOuElogica _expOuElogica_;

    public AAtribComandos()
    {
        // Constructor
    }

    public AAtribComandos(
        @SuppressWarnings("hiding") PVar _var_,
        @SuppressWarnings("hiding") PExpOuElogica _expOuElogica_)
    {
        // Constructor
        setVar(_var_);

        setExpOuElogica(_expOuElogica_);

    }

    @Override
    public Object clone()
    {
        return new AAtribComandos(
            cloneNode(this._var_),
            cloneNode(this._expOuElogica_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAtribComandos(this);
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

    public PExpOuElogica getExpOuElogica()
    {
        return this._expOuElogica_;
    }

    public void setExpOuElogica(PExpOuElogica node)
    {
        if(this._expOuElogica_ != null)
        {
            this._expOuElogica_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expOuElogica_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._var_)
            + toString(this._expOuElogica_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._var_ == child)
        {
            this._var_ = null;
            return;
        }

        if(this._expOuElogica_ == child)
        {
            this._expOuElogica_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._var_ == oldChild)
        {
            setVar((PVar) newChild);
            return;
        }

        if(this._expOuElogica_ == oldChild)
        {
            setExpOuElogica((PExpOuElogica) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
