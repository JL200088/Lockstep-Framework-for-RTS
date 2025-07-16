package com.jl.lockstep.core.entity;

import com.jl.lockstep.core.interfaces.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Wrapper class for a command along with sync frame info and fetch result.
 * 封装了命令、同步帧编号和获取状态的类
 */
@Data
@AllArgsConstructor
public class CommandResult {

    /**
     * The frame index this command belongs to.
     * 命令所属的同步逻辑帧编号
     */
    private long syncFrame;

    /**
     * Whether the command was successfully fetched.
     * 是否成功获取命令
     */
    private boolean successFetching;

    /**
     * The actual command to be executed.
     * 需要执行的命令实体
     */
    private Command command;
}
