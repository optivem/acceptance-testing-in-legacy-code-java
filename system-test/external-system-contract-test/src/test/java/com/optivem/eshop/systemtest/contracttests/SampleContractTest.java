package com.optivem.eshop.systemtest.contracttests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleContractTest {

    @Test
    void shouldAddNumbers() {
        // Arrange
        int a = 1;
        int b = 1;

        // Act
        int result = a + b;

        // Assert
        assertEquals(2, result);
    }
}

