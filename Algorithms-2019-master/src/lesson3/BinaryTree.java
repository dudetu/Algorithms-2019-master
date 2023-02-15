package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        throw new NotImplementedError();
        //надеюсь, доберусь до  нее
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> now = null;
        private Queue<Node<T>> iterator = new LinkedList<>();

        private BinaryTreeIterator() {
            if (root != null) creator(root);
        }
        //Т=O(n), R=O(1)
        //т.к заполняем iterator
        private void creator(Node<T> root) {
            if (root.left != null) creator(root.left);
            iterator.offer(root);
            if (root.right != null) creator(root.right);
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
        @Override
        //Т=O(1), R=O(1)
        //Сложность peek взята за основу
        public boolean hasNext() {
            Node answer = iterator.peek();
            return answer != null;
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        @Override
        //Т=O(1), R=O(1)
        //просто взята сложность ремува для LinkedList
        public T next() {
            Node node = iterator.remove();
            return (node!= null) ? (T) node.value : null;

        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            iterator.poll();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    //Вывод: Т=O(1), R=O(1)
    // Так как просто вызываем конструктор, содержащий в себе ссылку на дерево и два ограничения
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return new SubBinaryTree(this, fromElement, toElement, 0);
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    //Вывод: Т=O(1), R=O(1)
    // Так как просто вызываем конструктор, содержащий в себе ссылку на дерево и два ограничения
    public SortedSet<T> headSet(T toElement) {
        return new SubBinaryTree(this, null, toElement, 1);
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return new SubBinaryTree(this, fromElement, null, -1);
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
    class SubBinaryTree extends BinaryTree<T> {
        private BinaryTree<T> tree;
        private final T from;
        private final T to;
        private final int headOrTail;

        SubBinaryTree(BinaryTree<T> tree, T from, T to, int headOrTail) {
            this.tree = tree;
            this.from = from;
            this.to = to;
            this.headOrTail = headOrTail;
            //HeadOrTail использован, чтобы определять, входит число в заданные границы или нет. Если равен 0, то
            //обе границы есть, если -1 то левая, если 1 - правая
        }


        @Override
        public boolean add(T t) {
            if (!inSubSet(t)) throw new IllegalArgumentException();
            return tree.add(t);
        }

        @Override
        public boolean contains(Object o) {
            if (!inSubSet((T) o)) return false;
            return tree.contains(o);
        }

        @Override
        public boolean remove(Object o) {
            if (!inSubSet((T) o)) throw new IllegalArgumentException();
            return tree.remove(o);
            //я знаю, вызывется пустой ремув, никак не успеваю его написать, не бейте ;)
        }

        @Override
        public int size() {
           return (int) tree.stream().filter(this::inSubSet).count();
        }

        private boolean inSubSet(T t) {
            if ((headOrTail <=0) && t.compareTo(from) < 0) return false;
            if ((headOrTail >=0) && t.compareTo(to)>= 0) return false;
           return true;
        }
    }
}
