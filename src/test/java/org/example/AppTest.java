package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void greeting() {
        App app = new App();
        app.Greeting();
    }

    @Test
    void main() {
        App app = new App();
        assertEquals( app.val, 11, "val should be 10");
    }
}