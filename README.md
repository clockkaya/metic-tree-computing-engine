# Metric Tree Computing Engine (MTCE)

## 🚀 项目简介
本项目是一个基于**树状计算引擎**的自动化指标量化平台。它通过将复杂的业务量化逻辑抽象为“指标树”，利用高性能表达式引擎实现多维、多源数据的灵活配置与递归计算。

项目采用**集团-省两侧分层设计**，完美适配大型组织架构下的数据采集、汇总与可视化分析需求。

## 🛠 核心模块架构
项目由以下核心模块组成：
- **API 层 (Contract)**
  - `demo-province-api`: 省侧标准化接口契约。
  - `demo-group-api`: 集团侧标准 API 定义与数据协议。
- **计算实现层 (Engine)**
  - `demo-province-module` (**核心核心**): 包含 `MetricUnitFactory` 工厂、`BaseHandler` 递归处理器以及 40+ 种基于 `Aviator` 的原子计算器。
  - `demo-group-module`: 实现全网指标聚合逻辑，负责跨省数据的汇总分析。
- **展示层 (Portal)**
  - `demo-province-portal`: 省侧维护门户，侧重原子数据导入与本省指标查看。
  - `demo-group-portal`: 集团侧监管门户，聚焦宏观态势与配置下发。

## ✨ 技术亮点
1. **高性能计算引擎**: 集成 `AviatorEvaluator`，支持复杂的线性插值 (`LinearInterpolationFunction`) 等自定义函数，响应时间达毫秒级。
2. **分级 OCP 原则实现**:
   - **配置级**: 通过 `MetricConfigTree` 动态调整权重，无需重启。
   - **逻辑级**: 继承 `BaseCalculator` 即可快速扩展新指标。
3. **递归处理架构**: `BaseHandler` 采用递归深度优先遍历，自动化处理指标树的父子节点依赖计算。
4. **全异步与缓存设计**: 基于 `Caffeine` 的配置缓存与 `ThreadPoolExecutor` 的并行计算，确保大数据量下的系统稳定性。

## 📦 技术栈
- **Core**: Java 8, Spring Boot, Dubbo (RPC)
- **Engine**: Aviator (Expression Language)
- **Cache**: Caffeine, Nacos (Config)
- **Middleware**: Kafka (Data Pipe), MyBatis-Plus