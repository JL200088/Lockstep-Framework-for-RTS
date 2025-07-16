package com.jl.lockstep;

import com.jl.lockstep.core.LogicFrameProcessor;
import com.jl.lockstep.core.demo.world.DemoCommandTransciever;
import com.jl.lockstep.core.demo.world.DemoWorldUpdater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ClientJavaApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ClientJavaApplication.class, args);

        LogicFrameProcessor logicProcessor =
                new LogicFrameProcessor(
                        new DemoWorldUpdater(),
                        new DemoCommandTransciever(),
                        10,20
                );
        Thread logicThread = new Thread(logicProcessor);

        logicThread.start();

    }


}
