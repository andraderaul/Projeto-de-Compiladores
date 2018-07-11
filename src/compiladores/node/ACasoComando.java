/* This file was generated by SableCC (http://www.sablecc.org/). */

package compiladores.node;

import java.util.*;
import compiladores.analysis.*;

@SuppressWarnings("nls")
public final class ACasoComando extends PCasoComando
{
    private final LinkedList<PCasoComandos> _casoComandos_ = new LinkedList<PCasoComandos>();

    public ACasoComando()
    {
        // Constructor
    }

    public ACasoComando(
        @SuppressWarnings("hiding") List<?> _casoComandos_)
    {
        // Constructor
        setCasoComandos(_casoComandos_);

    }

    @Override
    public Object clone()
    {
        return new ACasoComando(
            cloneList(this._casoComandos_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACasoComando(this);
    }

    public LinkedList<PCasoComandos> getCasoComandos()
    {
        return this._casoComandos_;
    }

    public void setCasoComandos(List<?> list)
    {
        for(PCasoComandos e : this._casoComandos_)
        {
            e.parent(null);
        }
        this._casoComandos_.clear();

        for(Object obj_e : list)
        {
            PCasoComandos e = (PCasoComandos) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._casoComandos_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._casoComandos_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._casoComandos_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PCasoComandos> i = this._casoComandos_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PCasoComandos) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}
