/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class AVarVirgulaVarVirg extends PVarVirg
{
    private PVar _var_;
    private TVirg _virg_;

    public AVarVirgulaVarVirg()
    {
        // Constructor
    }

    public AVarVirgulaVarVirg(
        @SuppressWarnings("hiding") PVar _var_,
        @SuppressWarnings("hiding") TVirg _virg_)
    {
        // Constructor
        setVar(_var_);

        setVirg(_virg_);

    }

    @Override
    public Object clone()
    {
        return new AVarVirgulaVarVirg(
            cloneNode(this._var_),
            cloneNode(this._virg_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarVirgulaVarVirg(this);
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

    public TVirg getVirg()
    {
        return this._virg_;
    }

    public void setVirg(TVirg node)
    {
        if(this._virg_ != null)
        {
            this._virg_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._virg_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._var_)
            + toString(this._virg_);
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

        if(this._virg_ == child)
        {
            this._virg_ = null;
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

        if(this._virg_ == oldChild)
        {
            setVirg((TVirg) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
