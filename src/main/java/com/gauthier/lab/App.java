package com.gauthier.lab;

import com.gauthier.lab.collections.CollectionImpl;

public class App {
  public static void main(String[] args) {

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
