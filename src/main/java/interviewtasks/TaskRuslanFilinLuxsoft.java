package interviewtasks;

public class TaskRuslanFilinLuxsoft {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.addNode(node2).addNode(node3);

        String values = getValues(node1, "");
        System.out.println(values);

//        concepts
//                - id
//                - name
//        attributes
//                -id
//                -name
//                -concept_id
//
//        select * from concepts c
//            join (select concept_id, count(*) AS numbersAttributes from attributes group by concept_id) AS ga ON ga.concept_id = c.id
//            where ga.numbersAttributes > 5;

    }

    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node addNode(Node next) {
            this.next = next;
            return next;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }

    public static String getValues(Node node, String str) {
        int value = node.getValue();
        Node next = node.getNext();
        if (next != null) {
            return getValues(next,  value + str);
        }
        return value + str;
    }

}
