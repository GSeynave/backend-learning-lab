package com.gauthier.lab.concurrency;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ConcurrencyImpl {

  public static void checkTimeWithMultihread() {
    Long startTime = getTime();

    // withConcurrencyWaitingForEnd();
    // massiveThreadUsage();

    Long executionTime = Instant.ofEpochMilli(getTime() - startTime).toEpochMilli();
    System.out.println("executionTime: " + executionTime.toString() + "ms");
  }

  static long getTime() {
    return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
  }

  static void waitTimer(Long time) {
    try {
      Thread.sleep(time);
    } catch (Exception e) {

    }
  }

  static void noConcurrency() {

    waitTimer(1000l); // 1sec
    waitTimer(1000l); // 1sec
    // program end after 2 sec
  }

  static void withConcurrencyNoWaitingForEnd() {

    Runnable runnable1 = () -> waitTimer(1000l);

    Thread t1 = new Thread(runnable1);
    Thread t2 = new Thread(runnable1);
    t1.start();
    t2.start();

    // program end without waiting for any thread to be over.
  }

  static void withConcurrencyWaitingForEnd() {

    Runnable runnable1 = () -> waitTimer(1000l);

    Thread t1 = new Thread(runnable1);
    Thread t2 = new Thread(runnable1);
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (Exception e) {
    }
    // program end after completion of each thread, time ~ 1sec
  }

  static void massiveThreadUsage() {

    Runnable runnable1 = () -> waitTimer(100000l);

    List<Thread> threads = new ArrayList<>();

    for (int i = 0; i < 1000; i++) {
      Thread t = new Thread(runnable1);
      t.start();
      threads.add(t);
    }

    threads.forEach(t -> {
      try {
        t.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });
    // check for cpu spike / memory allocation
  }
}
