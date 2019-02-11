package alda.tree;


public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;


    public BinarySearchTreeNode(T data) {
        this.data = data;
    }

    public boolean add(T data) {
        System.out.println("i add " + data);

        int compare = this.data.compareTo(data);
        if (contains(data)) {
            return false;
        } else {
            if (compare > 0) {
                if (left == null) {
                    BinarySearchTreeNode<T> node = new BinarySearchTreeNode<>(data);
                    left = node;
                    return true;
                } else {
                    return left.add(data);
                }
            }
            if (compare < 0) {
                if (right == null) {
                    BinarySearchTreeNode<T> node = new BinarySearchTreeNode<>(data);
                    right = node;
                    return true;
                } else {
                    return right.add(data);
                }
            }
            return true;
        }
        //return false;
    }

    private T findMin() {
        if (this.left == null) {
            return this.data;
        } else {
            return left.findMin();
        }
    }

    private T findMax() {
        if (this.right == null) {
            return this.data;
        } else {
            return right.findMax();
        }
    }

    public BinarySearchTreeNode<T> remove(T data) {
        System.out.println(toString());
        int compare = this.data.compareTo(data);
        System.out.println(toString());
        if (isLeaf(data)) {
            if (deleteLeaf(data)) {
                return this;
            }
        }


        else {

            if (this.data == data) {
                BinarySearchTreeNode<T> temp = new BinarySearchTreeNode<>(this.data);
                BinarySearchTreeNode<T> test = this;
                if (right == null) {
                    this.data = left.data;
                    this.right = left.right;
                    this.left = left.left;
                    return this;
                } else {
                    this.data = right.findMin();
                    if (right.findMin() == right.data) {
                        BinarySearchTreeNode<T> node = right;
                        this.right = null;
                        return this;
                    } else {
                        right.deleteLeftLeaf(data);
                        return this;
                    }

                }
            } else {
                if (compare > 0) {
                    if (left != null) {
                        left.remove(data);
                    }
                } else {
                    if (right != null) {
                        System.out.println("7");
                        right.remove(data);
                    }
                }

            }
        }
        return this;
    }

    private boolean deleteLeftLeaf(T data) {
        if (left == null) {
            return false;
        } else {
            if (left.data == data) {
                left = null;
                return true;
            } else {
                return left.deleteLeftLeaf(data);
            }
        }

    }

    private boolean deleteLeaf(T data) {
        int compare = this.data.compareTo(data);
        if (compare > 0) {
            if (left != null) {
                if (left.data == data || left.data.equals(data)) {
                    left = null;
                    return true;
                }
            }
            if (left != null) {
                return left.deleteLeaf(data);
            }
        }
        if (compare < 0) {
            if (right != null) {
                if (right.data == data || right.data.equals(data)) {
                    right = null;
                    return true;
                } else {
                    if (right != null) {
                        return right.deleteLeaf(data);
                    }
                }
            }
        }
        return false;
    }

    private boolean findLeftLeaf(T data) {
        if (this.data == data) {
            return true;
        } else {
            if (left != null) {
                return left.findLeftLeaf(data);
            }
        }
        return false;
    }


    private T findRightLeaf() {
        if (right == null) {
            return this.data;
        } else {
            return right.findRightLeaf();
        }
    }

    public boolean contains(T data) {
        if (this.data == data || this.data.equals(data)) {
            return true;
        } else {
            if (left != null && left.contains(data)) {
                return true;
            } else {

                if (right != null && right.contains(data)) {
                    return true;
                }
            }
            return false;
        }
    }


    public int size() {
        int total = 1;
        if (left == null && right == null) {
            return total;
        }
        if (left != null) {
            total += left.size();
        }
        if (right != null) {
            total += right.size();
        }
        System.out.println("total: " + total);
        return total;
    }

    public int depth() {
        if (left != null && right != null) {
            if (left.depth() + 1 > right.depth() + 1) {
                return left.depth() + 1;
            } else return right.depth() + 1;
        } else if (right != null) {
            return right.depth() + 1;
        } else if (left != null) {
            return left.depth() + 1;
        } else return 0;
    }


    public String toString() {
        if (left != null && right != null) {
            return left.toString() + ", " + data + ", " + right.toString();
        }
        if (left != null) {
            return left.toString() + ", " + this.data;
        }
        if (right != null) {
            return this.data + ", " + right.toString();
        } else {
            return "" + data;
        }

    }


    private boolean isLeaf(T data) {
        if (left != null) {
            if (left.data == data || left.data.equals(data)) {
                if (left.left == null && left.right == null) {
                    return true;
                }
            }
        }
        if (right != null) {
            if (right.data == data || right.data.equals(data)) {
                if (right.left == null && right.right == null) {
                    return true;
                }
            }
        } else {
            if (left != null) {
                return left.isLeaf(data);
            }
            if (right != null) {
                return right.isLeaf(data);
            }
        }
        return false;
    }

    //ta bort sen



    private int compareTo(BinarySearchTreeNode<T> other) {
        return (this.data.compareTo(other.data));
    }
}
