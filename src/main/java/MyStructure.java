import java.util.ArrayList;
import java.util.List;

public class MyStructure implements IMyStructure {

    private List<INode> nodes;

    public INode findByCode(String code) {
        INode node = findNode(i -> code.equals(getAllNodes(nodes).get(i).getCode()));
        return node;
    }

    public INode findByRenderer(String renderer) {
        INode node = findNode(i -> renderer.equals(getAllNodes(nodes).get(i).getRenderer()));
        return node;
    }

    public int count() {
        return getAllNodes(nodes).size();
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

    private INode findNode(CompareParameters cP){
        INode node = null;
        List<INode> allNodes = getAllNodes(nodes);
        for (int i = 0; i < allNodes.size(); i++){
            if (cP.isSearched(i)){
                node = allNodes.get(i);
                break;
            }
        }
        return node;
    }
}
