# Due date calculator
[![Build Status](https://travis-ci.com/tbugar/java-due-date-calc.svg?branch=master)](https://travis-ci.com/tbugar/java-due-date-calc)

This is an implementation of a due date calculator algorithm in Java8. 

## Motivation
Issue tracking systems can report working hours on a task. To find out when a specific task ends 
knowing the submission time and the turnaround time, we need to deal with working hours 
(9am - 5 pm) and weekends (due date cannot be a weekend day, instead next Monday). 
This calculator handles these problems.



## How to use
`DueDateCalculator.calcDueDate(@NotNull Date startDate, int turnAroundHours)`

- `startDate`: submission/start date of the issue
- `turnAroundHours`: turnaround time in hours
- returns due date as a `java.util.Date`

Example usage can be found in [`DueDateCalculatorIT`](https://github.com/tbugar/java-due-date-calc/blob/master/src/main/java/com/tbugardev/duedate/calc/DueDateCalculator.java) class.

## Requirements
- JDK 8
- Maven

## Testing
Running unit tests
```
mvn test
```
Running integration tests
```
mvn verify
```