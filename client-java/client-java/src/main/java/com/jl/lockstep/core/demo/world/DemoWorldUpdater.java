package com.jl.lockstep.core.demo.world;

import com.jl.lockstep.core.interfaces.Command;
import com.jl.lockstep.core.interfaces.WorldUpdator;

public class DemoWorldUpdater implements WorldUpdator {

    public DemoWorldUpdater(){
        System.out.println("Hello LockStep Logic World");
    }

    @Override
    public void updateWorld(long frame) {
        if (frame%10==0){
            System.out.println(
                    String.format("LogicWorld has been updated frames %d", frame));
        }
    }

    @Override
    public void updateCommand(Command cmd) {

    }
}
