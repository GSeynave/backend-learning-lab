package com.gauthier.lab.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamImpl {

  // ========================
  // ========= Stream =======
  // ========================

  /*
   * Use cases :
   */
  public static void stream() {
  }

  public static void display() {

    System.out.println("=== Stream ===");
    Employee[] employees = {
        new Employee(1, "Mickey Mouse", 100000.0),
        new Employee(2, "Donald Duck", 200000.0),
        new Employee(3, "Goofy Goo", 300000.0)
    };
    Employee brotherBear = new Employee(4, "Brother Bear", 5000.0);
    Employee mufasa = new Employee(5, "Mufasa - The Lion King", 500000.0);

    // Create stream from existing array :
    Stream<Employee> streamFromArray = Stream.of(employees);
    // Create stream from existing list :
    Stream<Employee> streamFromList = Arrays.asList(employees).stream();
    // Create stream from builder :
    Stream.Builder<Employee> builder = Stream.builder();
    builder.accept(brotherBear);
    builder.accept(mufasa);
    builder.accept(new Employee(6, "Sherkan", 450000.0));
    Stream<Employee> streamFromBuilder = builder.build();

    // Foreach - Terminal operation
    List<Employee> empList = Arrays.asList(employees);
    empList.stream()
        .forEach(e -> e.incrementSalary(10.0));

    // Map - Intermediate operation
    // Collect - Terminal operation
    empList.stream()
        .map(Employee::getSalary)
        .collect(Collectors.toList());

    // Filter - Intermediate
    // FindFirst - Terminal
    empList.stream()
        .filter(e -> e.getSalary() < 200000)
        .findFirst();

    // ToArray - Terminal
    empList.stream()
        .toArray(Employee[]::new);

    // FlatMap - Intermediate
    List<List<String>> couples = Arrays.asList(
        Arrays.asList("Donald Duck", "Daisy Duck"),
        Arrays.asList("Mickey Mouse", "Minnie Mouse"),
        Arrays.asList("Nobita", "Shizuka"));
    List<String> empl = couples.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    // Short circuit on steam :
    Stream.iterate(1, n -> n + 1)
        .skip(5)
        .limit(10)
        .forEach(System.out::println);

    // Lazy Evaluation
    Employee employee = Stream.of(employees)
        .filter(e -> e != null)
        .filter(e -> e.getSalary() > 100000)
        .findFirst()
        .orElse(null);

    // Sorted
    // empList.stream()
    // .sorted((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1.getSalary() :
    // e2.getSalary())
    // .collect(Collectors.toList());
    //
    // Min / Max
    Employee empHighestSalary = empList.stream()
        .max(Comparator.comparing(Employee::getSalary))
        .orElseThrow(NoSuchElementException::new);

    Employee empLowestSalary = empList.stream()
        .min(Comparator.comparing(Employee::getSalary))
        .orElseThrow(NoSuchElementException::new);

    // Distinct
    Stream.of(1, 2, 3, 2, 2, 8, 3).distinct().toList();

    // AllMatch - anyMatch - noneMatch
    boolean allEven = empList.stream().map(Employee::getSalary).allMatch(i -> i % 2 == 0);
    boolean oneEven = empList.stream().map(Employee::getSalary).anyMatch(i -> i % 2 == 0);
    boolean noneMultipleOfThree = empList.stream().map(Employee::getSalary).noneMatch(i -> i % 2 == 0);

    // Reduce
    Double totalSalaries = empList.stream()
        .map(Employee::getSalary)
        .reduce(0.0, Double::sum);

    // Advanced collect
    String empNames = empList.stream()
        .map(Employee::getName)
        .collect(Collectors.joining(", "))
        .toString();

    Set<String> uniqueEmpNames = empList.stream()
        .map(Employee::getName)
        .collect(Collectors.toSet());

    // SummarizingDouble
    DoubleSummaryStatistics stats = empList.stream()
        .collect(Collectors.summarizingDouble(Employee::getSalary));
    stats.getCount();
    stats.getSum();
    stats.getMax();
    stats.getMin();
    stats.getAverage();

    // PartitionningBy
    Map<Boolean, List<Integer>> mapOfEvenOdd = Stream.of(2, 4, 5, 6, 8)
        .collect(Collectors.partitioningBy(i -> i % 2 == 0));

    System.out.println("\n");
    StreamImpl.stream();
  }

}

class Employee {
  private int ID;
  private String name;
  private double salary;

  Employee(int ID, String name, double salary) {
    this.ID = ID;
    this.name = name;
    this.salary = salary;
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public void incrementSalary(double percentage) {
    this.salary += (this.salary * percentage) / 100;
  }
}
