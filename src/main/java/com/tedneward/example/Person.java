package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;

  private static int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    Person.count++;
  }

  // Static Methods

  public static List<Person> getNewardFamily() {
    List<Person> newardFamily = new ArrayList<>();

    newardFamily.add(new Person("Ted", 41, 250000));
    newardFamily.add(new Person("Charlotte", 43, 150000));
    newardFamily.add(new Person("Michael", 22, 10000));
    newardFamily.add(new Person("Matthew", 15, 0));

    return newardFamily;
  }

  // Getters and Setters

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be less than 0");
    }

    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must not be null");
    }

    this.name = name;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  // Methods

  public int count() {
    return Person.count;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public String tostring() {
    return "{{FIXME}}";
  }

  // Comparable

  public int compareTo(Person other) {
    double difference = this.salary - other.salary;

    if (difference > 0) {
      return 1;
    } else if (difference < 0) {
      return -1;
    } else {
      return 0;
    }
  }

  // toString

  @Override
  public String toString() {
    return "[Person name:"+ this.name +" age:"+ this.age +" salary:"+ this.salary +"]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  // Internal Class

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.age - p2.age;
    }
  }
}
