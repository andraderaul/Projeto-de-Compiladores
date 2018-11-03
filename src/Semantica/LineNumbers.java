import java.util.*;

import compiladores.analysis.*;
import compiladores.node.*;

class LineNumbers extends ReversedDepthFirstAdapter
{
	HashMap<Node, Integer> lines = new HashMap<Node, Integer>();

	public void defaultOut(Node node)
	{
		lines.put(node.parent(), lines.get(node));
	}

	public void defaultCase(Node node)
	{
		if (node instanceof Token)
		{
			Token token = (Token)node;
			lines.put(node.parent(), token.getLine());
		}
	}

	public int getLine(Node node)
	{
		if (lines.containsKey(node))
		{
			return lines.get(node);
		}
		return -1;
	}
}