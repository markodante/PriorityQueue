 package com.Marko;

/**
 * @author Marko Orlando
 * date 04/26/2024
 * An Implenetation of A Generic Type Linked List in Java
 */

public class WLinkedList<T> {

    private Node<T> head;
    private int size;

    public WLinkedList() {
        this.head = null;
    }

    /**
     * Checks whether the list is Empty
     * @return true or false
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /*
     * Adds a some value T to the front of the list
     */
    public void addToFront(T value) {
        // Create a new Node from the value
        Node<T> node = new Node<T>(value);
        size++;

        if (this.isEmpty()) {
            this.head = node;
            // I'm done with this function
            return;
        }
        // Make the new node the head of the list
        Node<T> oldHead = head;
        node.setNext(oldHead);
        this.head = node;
    }

    /**
     * Looks at the front of the list and returns that frontmost value of type T
     * @return The frontmost value
     * @throws Exception
     */
    public T lookAtFront() throws Exception {
        if (isEmpty()) {
            throw new Exception("Cannot look at the front of an empty list.");
        }

        return this.head.value;
    }

    /**
     * Remove the frontmost value of type T from the list
     */
    public void removeFromFront() {
        if (!isEmpty()) {
            size--;
            this.head = this.head.next;
        }
    }

    /**
     * Adds a value of type T to the back of a the list
     * @param The value 
     */
    public void addToBackSlow(T value) {
        // Create our new node
        Node<T> node = new Node<T>(value);
        size++;

        // Handle empty case
        if (isEmpty()) {
            this.head = node;
            return;
        }

        // We need to find the back of the list
        Node<T> curr = this.head;
        while (curr.next != null) {
            curr = curr.next;
        }

        // Add our new node to the back's next
        curr.next = node;
    }

    /**
     * Removes the value of type T located at the back of the list
     */
    public void removeFromBackSlow() {
        if (!isEmpty()) {
            size--;
            // Check if there is only one element in the list
            if (this.head.next == null) {
                // Empty the list
                this.head = null;
                return;
            }

            // DON'T MESS UP THE LIST
            Node<T> curr = head;
            while (curr.next.next != null) {
                curr = curr.next;
            }
            curr.next = null;

        }
    }

    /**
     * Looks at the back of the list and returns that value of type T located there
     * @return The value at the very back
     * @throws Exception
     */
    public T lookAtBack() throws Exception {
        if (isEmpty()) {
            throw new Exception("Cannot look at the back of an empty list.");
        }

        // Go to the back of the list
        Node<T> curr = this.head;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr.value;
    }

    /**
     * Determines the size of the list
     * @return The size of the list
     */
    public int size() {
        return this.size;
    }
    
	/**
	 * Prints out the list
	 * 
	 * @return The contents of the queue in the format "[<element>, <element>, ...]"
	 */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }

        String listRep = "[";

        // "Loop" over every element in the list
        Node<T> curr = head;
        while (curr.next != null) {
            // Add the current value to the String
            listRep += curr + ", ";
            // Move up the list
            curr = curr.next;
        }
        // Right now, curr is at the LAST value of the list
        listRep += curr;

        listRep += "]";
        return listRep;

    }

    // TODO: Ensure this is private
    class Node<U> {
        private U value;
        private Node<U> next;

        public Node(U value2) {
            this.value = value2;
            this.next = null;
        }

        /**
         * Sets the value of the next node
         * @param next The next node
         */
        public void setNext(Node<U> next) {
            this.next = next;
        }

        @Override
        /**
         * Prints the string represntation of the value
         * 
         * @return The string represntation of the value
         */
        public String toString() {
            return String.valueOf(value);
        }
    }
}