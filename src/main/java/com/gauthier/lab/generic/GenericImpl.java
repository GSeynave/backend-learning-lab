package com.gauthier.lab.generic;

import java.util.List;
import java.util.function.Function;

public class GenericImpl {
  /*
   * public interface Repository<T, ID> {
   * void save(T entity);
   * }
   * 
   * public class OrderRepository implements Repository<String, Long> {
   * 
   * @Override
   * public void save(String string) {
   * 
   * }
   * }
   */

  public static void test() {
    Function<String, Integer> stringLength = str -> str.length();
    System.out.println(stringLength.apply("test"));
  }

  public void test2(List<? extends Number> num) {

    test3(num.get(0));
    test3(1.0);
    test3(1);
    test3(1.1D);
    test3(1.1F);
  }

  public void test3(Number num) {

  }
}
