package com.jl.lockstep.core.interfaces;

import com.jl.lockstep.core.entity.CommandResult;

/**
 * Interface for receiving commands from clients or network.
 * 接收来自客户端或网络的命令接口
 */
public interface CommandTransciever {

    /**
     * Fetch the command result for the current frame.
     * 获取当前帧的命令结果
     *
     * @return CommandResult object 包含同步帧信息和命令
     */
    public CommandResult getCmd();
}
