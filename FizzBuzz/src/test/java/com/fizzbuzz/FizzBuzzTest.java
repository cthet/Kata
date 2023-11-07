package com.fizzbuzz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void shoulReturn1IfNumberIs1(){
        assertEquals("1", fizzBuzz.generate(1, 1));
    }

    @Test
    public void shouldReturn2IfNumberIs2(){
        assertEquals("2", fizzBuzz.generate(2, 2));
    }

    @Test
    public void shouldReturnFizzIfNumberIs3(){
        assertEquals("Fizz", fizzBuzz.generate(3, 3));
    }

    @Test
    public void shouldReturnFizzIfNumberIs6(){
        assertEquals("Fizz", fizzBuzz.generate(6, 6));
    }

    @Test
    public void shouldReturnBuzzIfNumberIs5(){
        assertEquals("Buzz", fizzBuzz.generate(5, 5));
    }

    @Test
    public void shouldReturnBuzzIfNumberIs10(){
        assertEquals("Buzz", fizzBuzz.generate(10, 10));
    }

    @Test
    public void shouldReturnFizzBuzzIfNumberIs15(){
        assertEquals("FizzBuzz", fizzBuzz.generate(15, 15));
    }

    @Test
    public void shouldReturnFizzBuzzIfNumberis30(){
        assertEquals("FizzBuzz", fizzBuzz.generate(30, 30));
    }

    @Test
    public void shouldReturn12IfNumbersAre1And2(){
        assertEquals("12", fizzBuzz.generate(1,2));
    }

    @Test
    public void shouldReturn12FizzIfNumberAre1To3(){
        assertEquals("12Fizz",fizzBuzz.generate(1,3));
    }

    @Test
    public void shouldRetun12Fizz4BuzzIfNumbersAre1To5(){
        assertEquals("12Fizz4Buzz", fizzBuzz.generate(1,5));
    }

    @Test
    public void shouldReturn12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzzIfNumberAre1To15() {
        assertEquals("12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzz", fizzBuzz.generate(1,15));
    }

}