package example;

import base.AIBusAPIConn;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Task(), 0, 30, TimeUnit.SECONDS);


        AIBusAPIConn aiBusAPIConn=new AIBusAPIConn();
        aiBusAPIConn.getConnection();
        }
    }
