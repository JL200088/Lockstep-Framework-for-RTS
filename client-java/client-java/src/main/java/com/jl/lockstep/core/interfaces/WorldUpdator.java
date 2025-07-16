package com.jl.lockstep.core.interfaces;

/**
 * Interface for updating the game world and injecting commands.
 * 更新游戏世界状态并注入命令的接口
 */
public interface WorldUpdator {

    /**
     * Update the entire world state for the current logic frame.
     * 更新当前逻辑帧的整个世界状态
     */
    public void updateWorld(long frame);

    /**
     * Apply a new command to the world before updating.
     * 在更新世界前应用新的命令
     *
     * @param command The command to be processed 本帧需要处理的命令
     */
    public void updateCommand(Command command);
}
