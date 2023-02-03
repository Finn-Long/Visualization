package com.example;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Thread t = new Thread(new ShowDbChanges());
        Thread u = new Thread(new UpdateDB());

        u.run();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.run();
    }
}
