package com.jl.lockstep.core;

import com.jl.lockstep.core.entity.CommandResult;
import com.jl.lockstep.core.interfaces.CommandTransciever;
import com.jl.lockstep.core.interfaces.WorldUpdator;
import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;

/**
 * LogicFrameProcessor is responsible for processing game logic frames in a loop,
 * synchronizing commands at regular intervals, and updating the game world.
 *
 * LogicFrameProcessor 负责在循环中处理游戏逻辑帧，
 * 每隔固定帧同步一次命令，并更新游戏世界状态。
 */
@Data
public class LogicFrameProcessor implements Runnable {

    private WorldUpdator worldUpdator;           // 世界状态更新器 (World state updater)
    private CommandTransciever cmdReceiver;      // 命令接收器 (Command receiver)
    private int forceSyncFrame;                  // 强制同步间隔帧数 (Sync command every N frames)
    private int logicFrame;                      // 当前逻辑帧编号 (Current logic frame number)
    private int targetFrameRate;                 // 目标帧率 (Target frame rate, e.g. 20 FPS)
    private long targetFrameTime;                // 每帧目标持续时间（毫秒）(Frame time in milliseconds)

    /**
     * 构造方法，初始化关键参数
     * Constructor to initialize logic frame processor
     *
     * @param world      世界更新器 (World updater)
     * @param receiver   命令接收器 (Command receiver)
     * @param syncFrame  同步帧间隔 (How many frames between full syncs)
     * @param rate       帧率 (Target logic frame rate, e.g. 20)
     */
    public LogicFrameProcessor(WorldUpdator world, CommandTransciever receiver, int syncFrame, int rate) {
        this.worldUpdator = world;
        this.cmdReceiver = receiver;
        this.forceSyncFrame = syncFrame;
        this.targetFrameRate = rate;
        this.targetFrameTime = calculateFrameTime(rate);
    }

    /**
     * 启动逻辑帧主循环，每帧执行一次世界更新或同步更新
     * Main logic loop: update world every frame; fetch command if sync frame
     */
    @Override
    public void run() {
        long lastFrameStartTime = System.nanoTime();
        while (true) {
            logicFrame++;

            // 每 N 帧执行一次完整同步（取命令 + 更新世界）
            // Sync frame: get command and update world
            if (isSyncFrame()) {
                CommandResult result = cmdReceiver.getCmd();
                if (result.isSuccessFetching()) {
                    worldUpdator.updateCommand(result.getCommand());
                    worldUpdator.updateWorld();
                }
            } else {
                // 普通帧只更新世界状态
                // Non-sync frame: only update world state
                worldUpdator.updateWorld();
            }

            try {
                waitTillTargetFrameTime(lastFrameStartTime, targetFrameTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 保持线程中断状态 (Preserve interrupt)
                break; // 安全退出循环 (Exit loop gracefully)
            }

            lastFrameStartTime = System.nanoTime();
        }
    }

    /**
     * 精确等待直到目标帧时间
     * Waits until target frame time using high-precision nano timer.
     *
     * @param lastFrameStartTime 上一帧的开始时间 (单位：纳秒)
     * @param targetFrameTime    每帧目标时长 (单位：毫秒)
     */
    private void waitTillTargetFrameTime(long lastFrameStartTime, long targetFrameTime) throws InterruptedException {
        long frameDurationNanos = targetFrameTime * 1_000_000; // 毫秒转纳秒
        long now = System.nanoTime();
        long elapsed = now - lastFrameStartTime;

        if (elapsed < frameDurationNanos) {
            long sleepTimeMillis = (frameDurationNanos - elapsed) / 1_000_000;
            int sleepTimeNanos = (int) ((frameDurationNanos - elapsed) % 1_000_000);
            Thread.sleep(sleepTimeMillis, sleepTimeNanos);
        }
    }

    /**
     * 判断当前帧是否为需要同步命令的帧
     * Determines whether current frame is a sync frame
     *
     * @return 如果当前帧为同步帧则返回 true (true if command sync should be done this frame)
     */
    private boolean isSyncFrame() {
        return (logicFrame % forceSyncFrame == 0);
    }

    /**
     * 动态修改逻辑帧率（如从 20fps 改为 30fps）
     * Changes the frame rate and recalculates frame time
     *
     * @param frameRate 新帧率 (New frame rate to set)
     */
    public void changeFrameRate(int frameRate) {
        this.targetFrameRate = frameRate;
        this.targetFrameTime = calculateFrameTime(frameRate);
    }

    /**
     * 根据帧率计算每帧时间（毫秒）
     * Calculates target time per frame in milliseconds
     *
     * @param rate 帧率 (Target frames per second)
     * @return 每帧时间 (Frame duration in ms)
     */
    private long calculateFrameTime(int rate) {
        return 1000L / rate;
    }
}
