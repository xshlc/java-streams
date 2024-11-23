package com.amigoscode.examples;


import com.amigoscode.beans.Car;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsWithStreams {

    @Test
    public void count() throws Exception {
        List<Car> cars = MockData.getCars();
        System.out.println("The number of Ford cars: ");
        // count all cars with the make "Ford"
        long countOfFordCars = cars.stream()
                .filter(car -> car.getMake()
                        .equalsIgnoreCase("Ford"))
                .count();
        System.out.println(countOfFordCars);

        int count = 0;
        AtomicInteger countAtomic = new AtomicInteger(0);

        // thread safe approach to operations
        // using AtomicInteger
        MockData.getCars()
                .forEach(car -> {
                    if (car.getMake()
                            .equalsIgnoreCase("Ford")) {
                        countAtomic.incrementAndGet(); // Increment the count atomically
                    }
                });

        count = countAtomic.get(); // Get the final count value
        System.out.println(count);

        System.out.println("The number of Ford cars made past 2010: ");
        // count all cars with the make "Ford"
        long count2 = cars.stream()
                .filter(car -> car.getMake()
                        .equalsIgnoreCase("Ford"))
                .filter(car -> car.getYear() > 2010)
                .count();
        System.out.println(count2);
    }

    @Test
    public void min() throws Exception {
        List<Car> cars = MockData.getCars();
        // how can we use min() function without a comparator?
        // find the cheapest car in the list
        OptionalDouble cheapestCar = cars.stream()
                .mapToDouble(Car::getPrice)
                .min();
        double cheapestCar2 = cars.stream()
                .mapToDouble(Car::getPrice)
                .min()
                .orElse(0);
        System.out.println(cheapestCar2);
    }

    @Test
    public void max() throws Exception {
        List<Car> cars = MockData.getCars();
        double mostExpensive = cars.stream()
                .mapToDouble(Car::getPrice)
                .max()
                .orElse(0);
        System.out.println(mostExpensive);
    }


    @Test
    public void average() throws Exception {
        List<Car> cars = MockData.getCars();
        double avg = cars.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
        System.out.println(avg);
    }

    @Test
    public void sum() throws Exception {
        List<Car> cars = MockData.getCars();
        double sum = cars.stream()
                .mapToDouble(Car::getPrice)
                .sum();
        System.out.println(BigDecimal.valueOf(sum));
    }

    @Test
    public void statistics() throws Exception {
        List<Car> cars = MockData.getCars();
    }

}