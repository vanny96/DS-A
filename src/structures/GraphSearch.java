package structures;

import java.util.*;

public class GraphSearch<T> {
    private IGraphSearch<T> graphProgress;
    private IGraphFind<T> findElement;
    private T voidElement;

    public GraphSearch(IGraphSearch<T> graphProgress, IGraphFind<T> findElement, T voidElement) {
        this.graphProgress = graphProgress;
        this.findElement = findElement;
        this.voidElement = voidElement;
    }

    public List<T> breathFirstSearch(T source) {
        Map<T, T> parent = new HashMap<>();
        parent.put(source, voidElement);

        List<T> queue = Collections.singletonList(source);

        while (!queue.isEmpty()) {
            List<T> nextQueue = new ArrayList<>();
            for (T node : queue) {
                LinkedList<T> edges = graphProgress.getOptions(node);

                for (T edge : edges) {
                    if (!parent.containsKey(edge)) {
                        parent.put(edge, node);

                        if(findElement.find(edge)){
                            return getPath(edge, parent);
                        }
                    }
                }
                nextQueue.addAll(edges);
            }
            queue = nextQueue;
        }
        return Collections.emptyList();
    }

    private LinkedList<T> getPath(T edge, Map<T,T> parent) {
        LinkedList<T> path = new LinkedList<>();
        T reference = edge;
        while(!reference.equals(voidElement)){
            path.addFirst(reference);
            reference = parent.get(reference);
        }
        return path;
    }

    @FunctionalInterface
    public interface IGraphSearch<T>{
        LinkedList<T> getOptions(T source);
    }

    @FunctionalInterface
    public interface IGraphFind<T>{
        boolean find(T source);
    }
}
