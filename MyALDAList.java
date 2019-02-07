package alda.linear;

import javax.lang.model.element.Element;
import java.util.Iterator;
import java.util.NoSuchElementException;

class MyALDAList<E>  implements ALDAList<E>,Iterable<E> {

    private Node<E> head;
    private Node<E> tail;


    @Override
    public void add(E element) {
        if(head == null){
            head = new Node<E>(element, null);
            tail = head;
        } else{
            tail.next = new Node<E>(element, null);
            tail = tail.next;
        }
    }

    @Override
    public void add(int index, E element) {
        System.out.println("adding " + element + " at " + index);
        int counter = 0;
        Node node = new Node<E>(element, null);
        Node temp = head;
        try{
            if(index == 0){
                if(head == null){
                    add(element);
                    System.out.println("1");
                } else {
                    node.next = head;
                    head = node;
                    System.out.println("head element " + head.element);
                    Node test = head.next;
                    System.out.println("head.next " + test.element);
                    System.out.println("2");
                }
            } else {
                if(index > size() || index < 0){

                    throw new java.lang.IndexOutOfBoundsException();

                } else {
                    if(index == size()){
                        System.out.println("4");
                        tail.next = node;
                        tail = node;
                    } else {
                        while(counter +1 != index){
                            System.out.println("5");
                            temp = temp.next;
                            counter++;
                        }
                        System.out.println("6");

                        node.next = temp.next;
                        temp.next = node;
                    }
                }

            }
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }

    }

    public E remover(int index){
        Node<E> temp = head;
        if(index < 0 || index > size() || head == null){
            throw new IndexOutOfBoundsException();
        } else {
            if(index == 0){
                head = head.next;
                return temp.element;
            } else{
                int counter = 1;
                for(Node<E> node = head.next; node != null; node = node.next){
                    if(counter == index){
                        if(index == size() -1){
                            tail = temp;
                            tail.next = null;
                            return node.element;
                        }
                        temp.next = node.next;
                        return node.element;
                    }
                    counter++;
                    temp = node;
                }
            }

        }
        return null;
    }

    @Override
    public E remove(int index) {
        Node<E> temp = head;
        if(index < 0 || index + 1 > size() || head == null){
            throw new IndexOutOfBoundsException();
        } else {
            if(index == 0){
                head = head.next;
                return temp.element;
            } else{
                int counter = 1;
                for(Node<E> node = head.next; node != null; node = node.next){
                    if(counter == index){
                        if(index == size() -1){
                            tail = temp;
                            tail.next = null;
                            return node.element;
                        }
                        temp.next = node.next;
                        return node.element;
                    }
                    counter++;
                    temp = node;
                }
            }

        }
        return null;
    }

    @Override
    public boolean remove(E element) {
        Node<E> temp = head;
        if(head.element == element || head.element.equals(element)){
            head = head.next;
            return true;
        } else {
            for(Node<E> node = head.next; node != null; node = node.next ){
                if(node.element == element){
                    if(node == tail){
                        tail = temp;
                        temp.next = null;
                        return true;
                    } else {
                        temp.next = node.next;
                        return true;
                    }
                }
                temp = temp.next;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        int counter = 0;
        Node<E> temp = head;
        if(index < 0 || index + 1 > size() || head == null){
            throw new IndexOutOfBoundsException();
        } else {
            if(index == size()){
                temp = tail;
                return temp.element;
            }
            if(index == 0){
                return temp.element;
            }  else {
                while (counter < index){
                    temp = temp.next;
                    counter++;
                }
                return temp.element;
            }
        }

    }

    @Override
    public boolean contains(E element) {
        for(Node<E> node = head; node != null; node = node.next){
            if(node.element == element || node.element.equals(element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E element) {
        int counter = 0;
        for(Node<E> node = head; node != null; node = node.next){
            if(node.element == element || node.element.equals(element)){
                return counter;
            }
            counter++;
        }
        return -1;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;

    }

    @Override
    public int size() {
        int counter = 0;
        Node temp = head;
        if(temp == null){
            return counter;
        } else{
            while(temp.next != null){
                counter++;
                temp = temp.next;
            }
        }
        return counter+1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private boolean iterationStarted = false;
            private boolean moreThanOneRemoveInARow = false;

            private Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                if (currentNode.next != null) {
                    return true;
                }
                return false;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (!iterationStarted) {
                    iterationStarted = true;

                } else {

                    currentNode = currentNode.next;
                }

                moreThanOneRemoveInARow = false;
                E element = currentNode.element;
                return element;
            }

            @Override
            public void remove() {
                if (!iterationStarted || moreThanOneRemoveInARow) {
                    throw new IllegalStateException();
                } else {
                    MyALDAList.this.remove(currentNode.element);
                    moreThanOneRemoveInARow = true;
                }

            }

        };
    }

    public String toString(){
        if(head == null){
            return "[]";
        } else{
            Node temp = head;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(temp.element);
            int counter = 0;
            try{
                while(counter < size()){
                    temp = temp.next;
                    System.out.println(temp.element);
                    sb.append(", " + temp.element);
                    counter++;
                }
            } catch (NullPointerException np){
                System.out.println("Nullpointer");
            }
            sb.append("]");
            return sb.toString();
        }
    }



    private static class Node<E>{
        E element;
        Node<E> next;
        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
    }
}



