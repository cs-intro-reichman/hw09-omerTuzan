/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }
    
    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the CharData of the first element in this list. */
    public CharData getFirst() {
        // Your code goes here
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        // Your code goes here
        if (this.first == null) {this.first = new Node(new CharData(chr)); this.size++; return;}
        this.first = new Node(new CharData(chr), first);
        this.size++;

    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        // Your code goes here
        StringBuilder str = new StringBuilder("(");
        ListIterator iter = this.listIterator(0);
        if (iter == null) {return str.append(")").toString();}

        while(iter.hasNext()) {
            str.append(iter.next() + " ");
        }
        return str.replace(str.length() - 1, str.length(), ")").toString();
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        // Your code goes here
        int index = 0;
        while(this.listIterator(index) != null && this.listIterator(index).current != null) {
            if (this.listIterator(index).current.cp.chr == chr) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        int index = this.indexOf(chr);
        if (index == -1) {
            this.addFirst(chr);
            return;
        }

        this.listIterator(index).current.cp.count++;    

        // Your code goes here
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        // Your code goes here
        int index = this.indexOf(chr);
        if (index == -1) { return false;}
        if (this.size == 1) {this.first = null; this.size--; return true;}

        Node tempNode = this.first;

        while (index - 1 > 0 ) {
            tempNode = tempNode.next;
            index--;
        }

        if (tempNode.next.next == null) {
            tempNode.next = null;
        }
        else {
            tempNode.next = tempNode.next.next;
        }

        this.size--;           
        return true;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        // Your code goes here
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("nu nu nu nu");
        }
        
        Node tempNode = this.first;
        int tempIndex = index;
        while (tempIndex > 0 ) {
            tempNode = tempNode.next;
            tempIndex--;
        }
        return tempNode.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}