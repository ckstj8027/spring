import java.util.LinkedList;

public class linkedList{
    public static void main(String[] args) {
        // LinkedList 선언
        LinkedList<String> linkedList = new LinkedList<>();

        /*--------삽입---------*/

        // insert front
        linkedList.addFirst("First");

        // insert back
        linkedList.addLast("Last");

        // insert at
        linkedList.add(1, "Inserted at index 1");

        /*--------제거---------*/

        // remove front
        linkedList.removeFirst();

        // remove back
        linkedList.removeLast();

        // remove at (by index)
        linkedList.remove(0); // Remove first element

        /*--------조회---------*/

        // get front
        if (!linkedList.isEmpty()) {
            String first = linkedList.getFirst();
            System.out.println("First element: " + first);
        } else {
            System.out.println("LinkedList is empty");
        }

        // get back
        if (!linkedList.isEmpty()) {
            String last = linkedList.getLast();
            System.out.println("Last element: " + last);
        } else {
            System.out.println("LinkedList is empty");
        }

        // get at (by index)
        if (!linkedList.isEmpty()) {
            String elementAtIndex = linkedList.get(0);
            System.out.println("Element at index 0: " + elementAtIndex);
        } else {
            System.out.println("LinkedList is empty");
        }

        /*--------변경---------*/

        //데이터 변경
        if (!linkedList.isEmpty()) {
            linkedList.set(0, "New value at index 0");
            System.out.println("Updated element at index 0: " + linkedList.get(0));
        } else {
            System.out.println("LinkedList is empty");
        }
    }
}
