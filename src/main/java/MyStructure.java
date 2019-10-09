import java.util.ArrayList;
import java.util.List;

public class MyStructure implements IMyStructure {

    private List<INode> nodes;

    public INode findByCode(String code) {
        List<INode> allNodes = getAllNodes(this.nodes);
        return findNode(i -> code.equals(allNodes.get(i).getCode()), allNodes);
    }

    public INode findByRenderer(String renderer) {
        List<INode> allNodes = getAllNodes(this.nodes);
        return findNode(i -> renderer.equals(allNodes.get(i).getRenderer()), allNodes);
    }

    public int count() {
        return getAllNodes(this.nodes).size();
    }

    private List<INode> getAllNodes(List<INode> list) {
        List<INode> newList = new ArrayList<>();
        for (INode n : list) {
            if (ICompositeNode.class.isAssignableFrom(n.getClass())) {
                ICompositeNode cN = (ICompositeNode) n;
                newList.addAll(getAllNodes(cN.getNodes()));
            }
            newList.add(n);
        }
        return newList;
    }

    private INode findNode(CompareParameters cP, List<INode> nodeList) {
        INode node = null;
        for (int i = 0; i < nodeList.size(); i++) {
            if (cP.isSearched(i)) {
                node = nodeList.get(i);
                break;
            }
        }
        return node;
    }
}
