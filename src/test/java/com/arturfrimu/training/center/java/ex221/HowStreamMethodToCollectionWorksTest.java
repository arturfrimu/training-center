package com.arturfrimu.training.center.java.ex221;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class HowStreamMethodToCollectionWorksTest {

    @Test
    void testToCollection() {
        Stream<String> stream = Stream.of("a", "b", "c");

        // 1. ArrayList
        List<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        assertThat(arrayList).containsExactly("a", "b", "c");

        // 2. HashSet
        Set<Integer> hashSet = Stream.of(1, 2, 3).collect(Collectors.toCollection(HashSet::new));
        assertThat(hashSet).containsExactlyInAnyOrder(1, 2, 3);

        // 3. ArrayDeque
        Deque<String> arrayDeque = Stream.of("x", "y", "z").collect(Collectors.toCollection(ArrayDeque::new));
        assertThat(arrayDeque).containsExactly("x", "y", "z");

        // 4. LinkedList
        LinkedList<Double> linkedList = Stream.of(1.1, 2.2, 3.3).collect(Collectors.toCollection(LinkedList::new));
        assertThat(linkedList).containsExactly(1.1, 2.2, 3.3);

        // 5. TreeSet
        TreeSet<String> treeSet = Stream.of("apple", "banana", "cherry").collect(Collectors.toCollection(TreeSet::new));
        assertThat(treeSet).containsExactly("apple", "banana", "cherry");

        // 6. Vector
        Vector<Integer> vector = Stream.of(10, 20, 30).collect(Collectors.toCollection(Vector::new));
        assertThat(vector).containsExactly(10, 20, 30);

        // 7. Stack
        Stack<Integer> stack = Stream.of(4, 5, 6).collect(Collectors.toCollection(Stack::new));
        assertThat(stack).containsExactly(4, 5, 6);

        // 8. PriorityQueue
        PriorityQueue<Integer> priorityQueue = Stream.of(7, 8, 9).collect(Collectors.toCollection(PriorityQueue::new));
        assertThat(priorityQueue).containsExactlyInAnyOrder(7, 8, 9);

        // 9. LinkedHashSet
        LinkedHashSet<String> linkedHashSet = Stream.of("one", "two", "three").collect(Collectors.toCollection(LinkedHashSet::new));
        assertThat(linkedHashSet).containsExactly("one", "two", "three");

        // 10. CopyOnWriteArrayList
        CopyOnWriteArrayList<String> cowArrayList = Stream.of("alpha", "beta", "gamma").collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        assertThat(cowArrayList).containsExactly("alpha", "beta", "gamma");

        // 11. ConcurrentSkipListSet
        ConcurrentSkipListSet<String> concurrentSkipListSet = Stream.of("delta", "epsilon", "zeta").collect(Collectors.toCollection(ConcurrentSkipListSet::new));
        assertThat(concurrentSkipListSet).containsExactlyInAnyOrder("delta", "epsilon", "zeta");

        // 12. ConcurrentLinkedQueue
        ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = Stream.of(100, 200, 300).collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
        assertThat(concurrentLinkedQueue).containsExactly(100, 200, 300);

        // 13. ConcurrentLinkedDeque
        ConcurrentLinkedDeque<Integer> concurrentLinkedDeque = Stream.of(400, 500, 600).collect(Collectors.toCollection(ConcurrentLinkedDeque::new));
        assertThat(concurrentLinkedDeque).containsExactly(400, 500, 600);

        // 14. LinkedBlockingQueue
        LinkedBlockingQueue<Integer> linkedBlockingQueue = Stream.of(700, 800, 900).collect(Collectors.toCollection(LinkedBlockingQueue::new));
        assertThat(linkedBlockingQueue).containsExactly(700, 800, 900);

        // 15. LinkedBlockingDeque
        LinkedBlockingDeque<Integer> linkedBlockingDeque = Stream.of(111, 222, 333).collect(Collectors.toCollection(LinkedBlockingDeque::new));
        assertThat(linkedBlockingDeque).containsExactly(111, 222, 333);

        // 16. ArrayBlockingQueue
        ArrayBlockingQueue<Integer> arrayBlockingQueue = Stream.of(444, 555, 666).collect(Collectors.toCollection(() -> new ArrayBlockingQueue<>(10)));
        assertThat(arrayBlockingQueue).containsExactly(444, 555, 666);
    }
}