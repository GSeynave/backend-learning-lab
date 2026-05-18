package com.gauthier.lab.collections;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionImpl {

  // ========================
  // ========= LIST =========
  // ========================

  /*
   * Use cases :
   * Maintaining ordered data
   * Frequent read operation with random access (Array List)
   */
  public static void ArrayList() {
    List<String> list = new ArrayList<>();
    list.add("java");
    list.add("Spring");
    list.add("java");
    System.out.print("ArrayList: " + list);
  }

  /*
   * Use cases :
   * Maintaining ordered data
   * Frequent insertion / removals (Array List)
   */
  public static void LinkedList() {
    List<String> list = new ArrayList<>();
    list.add("java");
    list.add("Linked");
    list.add("Spring");
    list.remove("java");
    list.add("java");
    list.remove("Spring");
    System.out.print("LinkedList: " + list);
  }

  // ========================
  // ========= Set =========
  // ========================

  /*
   * Use cases :
   * Ensure uniqueness
   * sorted data storage
   *
   * Maintains insertion order
   */
  public static void linkedHashSet() {
    Set<String> linkedHashSet = new LinkedHashSet<>();
    linkedHashSet.add("Java");
    linkedHashSet.add("Spring");
    linkedHashSet.add("Apache");
    linkedHashSet.add("Java");
    System.out.print("LinkedHashSet: " + linkedHashSet);
  }

  /*
   * Store elements in natural sorted order
   */
  public static void treeSet() {
    Set<String> treeSet = new TreeSet<>();
    treeSet.add("Banana");
    treeSet.add("Apple");
    treeSet.add("Mango");
    System.out.print("treeSet: " + treeSet);
  }

  // ========================
  // ========= Queue =========
  // ========================

  /*
   * Use cases :
   * First In First Out
   * Implementation of LInkedList, PriorityQueue, ArrayDeque
   *
   * Used for task scheduling, buffer management
   */
  public static void queue() {
    Queue<String> queue = new LinkedList<>();
    queue.add("First");
    queue.add("Second");
    queue.add("before third !");
    queue.add("Third");
    System.out.print("Queue: " + queue + "\n");
    System.out.print("Polled: " + queue.poll() + "\n");
    System.out.print("After poll: " + queue + "\n");
  }

  // ========================
  // ========= Deque ========
  // ========================

  /*
   * Use cases :
   * Allow insertion / removal at both ends
   * Implementation of LInkedList, ArrayDeque
   *
   */
  public static void deque() {
    Deque<String> deque = new LinkedList<>();
    deque.addFirst("First");
    deque.addLast("Second");
    deque.addFirst("before first !");
    deque.addLast("Third");
    System.out.print("Deque: " + deque + "\n");
  }

  // ========================
  // ========= Map ==========
  // ========================

  /*
   * Use cases :
   * Store key / value paris
   * Key must be unique, values can be duplicated
   *
   * Fast lookup of data using key
   * Sorted maps(treeMap)
   * Thread-Safe applications (ConcurrentHashMap)
   *
   * Implementation of HashMap, TreeMap, LinkedHashMap, concurrentHashMap
   *
   */
  public static void hashMap() {
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "Java");
    map.put(2, "Spring");
    map.put(1, "Hibernate");

    System.out.print("HashMap: " + map + "\n");
  }

  /*
   * Maintains insertion order
   */
  public static void linkedHashMap() {
    Map<Integer, String> map = new LinkedHashMap<>();
    map.put(1, "Java");
    map.put(3, "Spring");
    map.put(2, "Hibernate");

    System.out.print("LinkedHashMap: " + map + "\n");
  }

  /*
   * Maintains key in natural sorted order
   */
  public static void treeMap() {
    Map<String, Integer> map = new TreeMap<>();
    map.put("Banana", 2);
    map.put("Apple", 3);
    map.put("Mango", 1);

    System.out.print("TreeMap: " + map + "\n");
  }

  /*
   * Thread-Safe version of HashMap
   */
  public static void concurrentHashMap() {
    Map<String, Integer> map = new ConcurrentHashMap<>();
    map.put("One", 1);
    map.put("Two", 2);

    System.out.print("ConcurrentHashMap: " + map + "\n");
  }

  public static void display() {

    System.out.println("=== Collections ===");
    System.out.println("== LIST");

    System.out.println("\n");
    CollectionImpl.ArrayList();
    System.out.println("\n");
    CollectionImpl.LinkedList();
    System.out.println("\n");

    System.out.println("== Set");
    System.out.println("\n");
    CollectionImpl.linkedHashSet();
    System.out.println("\n");
    CollectionImpl.treeSet();
    System.out.println("\n");

    System.out.println("== Queue");
    System.out.println("\n");
    CollectionImpl.queue();
    System.out.println("\n");
    CollectionImpl.deque();
    System.out.println("\n");

    System.out.println("== Map");
    System.out.println("\n");
    CollectionImpl.hashMap();
    System.out.println("\n");
    CollectionImpl.linkedHashMap();
    System.out.println("\n");
    CollectionImpl.treeMap();
    System.out.println("\n");
    CollectionImpl.concurrentHashMap();
  }
}
