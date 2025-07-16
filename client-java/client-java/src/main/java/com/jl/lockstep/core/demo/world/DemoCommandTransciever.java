package com.jl.lockstep.core.demo.world;

import com.jl.lockstep.core.entity.CommandResult;
import com.jl.lockstep.core.interfaces.Command;
import com.jl.lockstep.core.interfaces.CommandTransciever;

public class DemoCommandTransciever implements CommandTransciever {
    @Override
    public CommandResult getCmd() {
        return new CommandResult(10l, true, new Command() {
        });
    }
}
