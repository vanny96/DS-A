package structures;

public class AVLtree extends BinarySearchTree {
    public AVLtree(int[] array) {
        firstNode = new AVLNode(array[0]);

        for (int i = 1; i < array.length; i++){
            insert(array[i]);
        }
    }

    @Override
    public void insert(int value) {
        insert(value, (AVLNode) firstNode);
    }

    private void insert(int value, AVLNode node){
        // Classic insertion
        if(value < node.getElement()){
            if(node.getLeftChild() == null){
                node.setLeftChild(new AVLNode(value));
                node.getLeftChild().setParent(node);
            } else {
                insert(value, node.getLeftChild());
            }
        } else {
            if(node.getRightChild() == null){
                node.setRightChild(new AVLNode(value));
                node.getRightChild().setParent(node);
            } else {
                insert(value, node.getRightChild());
            }
        }

        // Balance the tree
        boolean rotated = balance(node);

        // Update height of child (eventually parent)
        node.height = countHeight(node);
        if(rotated)
            node.getParent().height = countHeight(node.getParent());
    }

    private boolean balance(AVLNode node){
        BalanceDirection balance = checkBalance(node);
        boolean rotated = false;
        switch (balance){
            case DOUBLE_RIGHT:
                if(checkBalance(node.getRightChild()).equals(BalanceDirection.LEFT))
                    rightRotate(node.getRightChild()); /* Run double rotation if right child is left-heavy */
                leftRotate(node);
                rotated = true;
                break;
            case DOUBLE_LEFT:
                if(checkBalance(node.getLeftChild()).equals(BalanceDirection.RIGHT))
                    rightRotate(node.getLeftChild()); /* Run double rotation if left child is right-heavy */
                rightRotate(node);
                rotated = true;
                break;
            default:
                break;
        }
        return rotated;
    }

    private BalanceDirection checkBalance(AVLNode node){
        int heightLeft = node.getLeftChild() == null ? -1 : node.getLeftChild().height;
        int heightRight = node.getRightChild() == null ? -1 : node.getRightChild().height;

        if((heightRight - heightLeft) > 1) {
            return BalanceDirection.DOUBLE_RIGHT;
        } else if((heightRight - heightLeft) < -1){
            return BalanceDirection.DOUBLE_LEFT;
        } else if((heightRight - heightLeft) == 1){
            return BalanceDirection.RIGHT;
        } else if((heightRight - heightLeft) == -1){
            return BalanceDirection.LEFT;
        } else {
            return BalanceDirection.BALANCED;
        }
    }

    private int countHeight(AVLNode node){
        int heightLeft = node.getLeftChild() == null ? -1 : node.getLeftChild().height;
        int heightRight = node.getRightChild() == null ? -1 : node.getRightChild().height;

        return Math.max(heightLeft, heightRight) + 1;
    }

    private enum BalanceDirection {
        DOUBLE_LEFT,
        DOUBLE_RIGHT,
        LEFT,
        RIGHT,
        BALANCED
    }

    private static class AVLNode extends BinarySearchNode{
        private int height = 0;

        @Override
        public AVLNode getParent() {
            return (AVLNode) super.getParent();
        }

        @Override
        public AVLNode getLeftChild() {
            return (AVLNode) super.getLeftChild();
        }

        @Override
        public AVLNode getRightChild() {
            return (AVLNode) super.getRightChild();
        }

        public AVLNode(int element) {
            super(element);
        }
    }
}
