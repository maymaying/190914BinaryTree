import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private static class Node {
        private char val;
        private Node left = null;
        private Node right = null;

        private Node(char val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("{%c}", val);
        }
    }

    public static Node buildTree() {
        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');
        Node f = new Node('F');
        Node g = new Node('G');
        Node h = new Node('H');

        a.left = b; a.right = c;
        b.left = d; b.right = e;
        c.left = f; c.right = g;
        e.right = h;

        return a;
    }

    //前序遍历
    public static void preOrderTraversal(Node root) {
        // 如何找终止条件，肯定是在形参中
        if (root == null) {
            return;
        }

        // 根 + 左子树的前序遍历 + 右子树的前序遍历
        System.out.println(root);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    //中序遍历
    public static void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        // 左子树的中序遍历 + 根 + 右子树的中序遍历
        inOrderTraversal(root.left);
        System.out.println(root);
        inOrderTraversal(root.right);
    }

    //后序遍历
    public static void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root);
    }

    //求结点的个数
    private static int count = 0;
    //遍历思想
    public static void getSize(Node root) {
        if (root == null) {
            return;
        }
        count++;
        getSize(root.left);
        getSize(root.right);
    }
    //汇总思想
    public static int getSize2(Node root) {
        if (root == null) {
            return 0;
        }

        int left = getSize2(root.left);
        int right = getSize2(root.right);
        // 汇总(left, right)
        return left + right + 1;
    }

    //给定一个二叉树，返回它的 中序 遍历
    private static List<Character> inorderList = new ArrayList<>();
    private static void inorderReturnList(Node root) {
        if (root != null) {
            inorderReturnList(root.left);
            inorderList.add(root.val);
            inorderReturnList(root.right);
        }
    }

    private static List<Character> inorderReturnList2(Node root) {
        List<Character> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        list.addAll(inorderReturnList2(root.left));
        list.add(root.val);
        list.addAll(inorderReturnList2(root.right));
        return list;
    }

    //求叶子结点的个数
    private static int leafSize = 0;
    private static void getLeafSize(Node root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            leafSize++;
        }
        getLeafSize(root.left);
        getLeafSize(root.right);
    }

    public static int getLeafSize2(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = getLeafSize2(root.left);
        int right = getLeafSize2(root.right);
        return left + right;
    }

    //求二叉树的高度
    public static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;

        //return Math.max(getHeight(root.left),getHeight(root.right))+1;

    }

    //求二叉树第k层结点的个数
    // K >= 1，如果 k < 1，方法无法处理
    public static int getKLevel(Node root, int k) {
        if (root == null) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        return getKLevel(root.left, k - 1) + getKLevel(root.right, k - 1);
    }

    // 查找 val 所在结点，没有找到返回 null
    // 按照 根 -> 左子树 -> 右子树的顺序进行查找
    // 一旦找到，立即返回，不需要继续在其他位置查找
    public static Node find(Node root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        Node n = find(root.left, val);
        if (n != null) {
            return n;
        }
        n = find(root.right, val);
        if (n != null) {
            return n;
        }
        return null;
    }

    public static boolean find2(Node root, int val) {
        if (root == null) {
            return false;
        }
        if (root.val == val) {
            return true;
        }
        if (find2(root.left, val)) {
            return true;
        }

        return find2(root.right, val);
    }

    public static boolean find3(Node root, int val) {
        return root != null
                && (
                root.val == val
                        || find3(root.left, val)
                        || find3(root.right, val)
        );
    }


    public static void main(String[] args) {
        Node root = buildTree();
        System.out.println("Success");

        //前中后序遍历
        preOrderTraversal(root);
        System.out.println("====================");
        inOrderTraversal(root);
        System.out.println("====================");
        postOrderTraversal(root);

        //求结点的个数
        System.out.println("====================");
        count = 0;
        getSize(root);
        System.out.println("结点个数: " + count);
        System.out.println("结点个数: " + getSize2(root));
        System.out.println("====================");

        //给定一个二叉树，返回它的 中序 遍历
        inorderList.clear();
        inorderReturnList(root);
        System.out.println(inorderList);
        System.out.println(inorderReturnList2(root));
        System.out.println("====================");

        //求叶子结点的个数
        leafSize = 0;
        getLeafSize(root);
        System.out.println("叶子结点个数： " + leafSize);
        System.out.println(getLeafSize2(root));
        System.out.println("====================");

        //求二叉树的高度
        System.out.print("二叉树的高度：");
        System.out.println(getHeight(root));
        System.out.println("====================");

        //求二叉树第K层结点的个数
        System.out.println(getKLevel(root, 1)); // 1
        System.out.println(getKLevel(root, 2)); // 2
        System.out.println(getKLevel(root, 3)); // 4
        System.out.println(getKLevel(root, 4)); // 1
        System.out.println(getKLevel(root, 5)); // 0
        System.out.println("====================");
    }
}

