# 🧠 Universal Command Frame Sync Core

> 🧬 A general-purpose, engine-agnostic, command-based frame synchronization kernel for building large-scale multiplayer strategy games.

## 🌟 Motivation

Modern hardware can handle massive real-time simulations, but network costs remain a major bottleneck for indie game developers—especially in RTS, tower defense, or sandbox tactical games. State sync models consume massive bandwidth, while P2P systems suffer from poor connectivity and weak cheat protection.

This project aims to solve that problem by offering a modular, engine-independent **command frame synchronization core** with minimal bandwidth requirements.

## 🧱 Languages & Architecture

- **Server-side**: Implemented in [`Java + Netty`](https://netty.io/) for high-performance, event-driven networking
- **Client core**:
  - `C#` version for Unity and .NET environments
  - `Java` version for Android or desktop simulations

> Protocols support JSON or binary serialization

## 📦 Project Structure

```
/frame-sync-core
├── server-java/       # Java Netty-based authoritative server
├── client-csharp/     # C# client frame sync core for Unity
├── client-java/       # Java client core for simulation/testing
├── logic-template/    # Platform-agnostic deterministic game logic
├── examples/          # RTS, Tower Defense sample projects
├── docs/              # Technical explanation and diagrams
└── tests/             # Unit and regression tests
```

## ✨ Features

| Feature | Description |
|--------|-------------|
| ✅ Cross-language core | Java & C# client versions |
| ✅ Frame-driven logic | All state changes are input-command based |
| ✅ Minimal bandwidth | No full-state sync required |
| ✅ Anti-cheat ready | Hook points for validation, rollback, hash |
| ✅ Render-logic separation | Works with any game engine frontend |

## 📈 Roadmap

- [ ] Snapshot/rollback system
- [ ] WebSocket protocol layer
- [ ] Unity/UE integration adapters
- [ ] WebAssembly runtime support
- [ ] Observer & replay support

## 📜 License

MIT License — Free for commercial and non-commercial use with attribution.

# 🧠 通用游戏指令帧同步核心（Universal Command Frame Sync Core）

> 🧬 一个通用的、与引擎无关的指令帧同步内核，专为支持大规模多人策略类游戏设计。

## 🌟 项目初衷（Project Motivation）

现代计算设备早已具备承载超大规模单位策略游戏的能力，但网络带宽和稳定性仍是主要瓶颈。尤其是 RTS、塔防类、战术模拟器等游戏，在多人同步下极易出现流量和延迟压力。

虽然 P2P 技术可绕过服务器运营成本，但其连接成功率低、防作弊难度高的问题依旧严重。而状态同步则带来巨大流量消耗，成为开发者难以承受之重。

### 🧩 本项目的目标是：

- 提供一个**通用、跨引擎、与渲染解耦**的帧同步核心
- **极大降低带宽使用**
- 同步仅包括**指令数据（每帧操作）**，由客户端本地逻辑驱动状态模拟

## 🧱 技术语言与架构（Languages & Architecture）

- **服务端**：使用 [`Java + Netty`](https://netty.io/) 实现高性能事件驱动网络服务器
- **客户端内核**：
  - `C#` 版本：适用于 Unity 等引擎接入
  - `Java` 版本：适用于 Android、跨平台、或逻辑模拟测试

> 网络协议支持 JSON / 二进制序列化

## 📦 项目结构（Project Structure）

```
/frame-sync-core
├── server-java/       # Java 服务端（基于 Netty）
├── client-csharp/     # C# 客户端同步内核（适配 Unity）
├── client-java/       # Java 客户端同步内核
├── logic-template/    # 可移植的游戏逻辑模板（纯逻辑）
├── examples/          # 示例：RTS 单位移动、塔防
├── docs/              # 技术文档 & 同步机制说明
└── tests/             # 单元测试
```

## ✨ 特性（Features）

| 特性 | 描述 |
|------|------|
| ✅ 跨语言支持 | 支持 C# 和 Java 客户端逻辑 |
| ✅ 帧驱动逻辑 | 所有状态变化均由输入指令驱动 |
| ✅ 极简同步数据结构 | 带宽需求远低于状态同步 |
| ✅ 支持客户端回滚与验证接口 | 可实现防作弊机制 |
| ✅ 解耦渲染与模拟 | 易嵌入各类游戏引擎中 |

## 📈 发展路线（Roadmap）

- [ ] 支持断点回滚和状态校验
- [ ] WebSocket 支持
- [ ] Unity/Unreal 引擎适配器
- [ ] WebAssembly 客户端内核
- [ ] 旁观者模式与回放支持

## 📜 许可证（License）

MIT 开源协议：可商用、可修改、无需授权但需注明来源。
