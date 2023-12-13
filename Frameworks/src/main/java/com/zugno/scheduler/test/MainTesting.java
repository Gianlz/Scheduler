package com.zugno.scheduler.test;

import com.zugno.scheduler.schedule.Scheduler;
import com.zugno.scheduler.schedule.SimpleScheduler;
import com.zugno.scheduler.script.BatchCommand;
import com.zugno.scheduler.script.Command;
import com.zugno.scheduler.script.PythonCommand;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;

public class MainTesting {

    public static void main(String[] args) {
        testSimpleScheduler();
        // Teste de outras implementações de Scheduler, se necessário
        // testComplexScheduler();
    }

    private static void testSimpleScheduler() {
        Scheduler scheduler = new SimpleScheduler();

        LocalDateTime now = LocalDateTime.now();

        // Schedule Python Command
        LocalDateTime pythonScheduledTime = LocalDateTime.of(2023, Month.DECEMBER, 12, 18, 33, 00);
        Path pythonFilePath = Paths.get("C:\\Users\\skyla\\Desktop\\Curvas2023\\src\\com\\zugno\\scheduler\\script\\test.py");
        Command pythonCommand = new PythonCommand(pythonFilePath, pythonScheduledTime);
        scheduler.schedule(pythonCommand);

        // Schedule Batch Command
        LocalDateTime batchScheduledTime = now.plusSeconds(10);
        Path batchFilePath = Paths.get("C:\\Users\\skyla\\Desktop\\Curvas2023\\src\\com\\zugno\\scheduler\\script\\test.bat");
        Command batchCommand = new BatchCommand(batchFilePath, batchScheduledTime);
        scheduler.schedule(batchCommand);

        // Aguarde um tempo para dar tempo de execução (pode ser ajustado conforme necessário)
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
