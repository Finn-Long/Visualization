package com.example;

import com.google.firebase.database.*;

import java.io.IOException;

public class UpdateDB implements Runnable {
    public void run() {
        FireBaseService fbs = null;
        try {
            fbs = new FireBaseService();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatabaseReference ref = fbs.getDb().getReference("/Logs");

        Object student = new Student("asd12a", "TaoHuang");

        System.out.println("Update Initialized");

        ref.setValue(student, null);

        System.out.println("Update Completed");


    }
}
