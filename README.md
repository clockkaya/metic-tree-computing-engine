# 元数据驱动的分布式指标树计算引擎 (Metric Tree Computing Engine)

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

### 1. 元数据驱动的树状递归计算引擎

- **模型抽象**：打破硬编码统计逻辑，将业务指标抽象为由元数据（MetricConfig）驱动的树状拓扑结构。

- **递归算子**：主导设计了支持多级依赖的递归计算链路。底层引擎根据元数据定义的依赖关系，自动完成指标的自下而上（Bottom-up）聚合计算，实现了**高频业务逻辑的零代码定义**。将新指标的上线周期从“开发-测试-发布”的周级流程缩短至**分钟级配置下发**。

- **计算下沉与代理模型**：设计 `BenefitProxyCalculator` 等代理组件，支持在计算过程中动态挂载业务逻辑，平衡了引擎的通用性与特定业务的灵活性。

### 2. “集团-省分”两级分布式数据互联体系

- **异步同步模型**：针对集团与省分公司物理隔离的异构环境，构建了基于消息中间件的双向异步同步机制，解决了跨地域海量指标传输的带宽瓶颈。
- **秒级数据汇聚**：通过“局部预计算 + 全局异步汇聚”策略，实现各省分公司明细数据在集团端的秒级准实时汇总。
- **多级容错机制**：设计了完备的数据对账与补偿逻辑，确保在复杂网络环境下，集团汇总视图与省分原始指标的最终一致性。

<img width="965" height="823" alt="交互时序图-效益" src="https://github.com/user-attachments/assets/c707372b-d6a4-47f5-bf5b-981fef77d6cb" />

### 3. 高并发架构下的极致性能优化

- **多级缓存策略**：引入 Caffeine 本地缓存与分布式缓存协同，针对高频查询的元数据与指标中间态进行加速，QPS 提升显著。
- **线程池精细化治理**：针对 IO 密集型的指标提取与计算密集型的递归运算，设计了独立的自定义线程池（ExecutorConfig），实现资源的物理隔离与平滑压测。


## 📦 技术栈
- **Core**: Java 21, Aviator (高性能表达式引擎), 递归算法
- **Engine**: Aviator (Expression Language)
- **Cache**: Caffeine, Nacos (Config)
- **Middleware**: Kafka (Data Pipe), MyBatis-Plus

## 🧱 详细项目目录结构

```text
.
├── demo-province-api (省级接口模块)
│   └── src/main/java/com/demo/api/province
│       ├── bean/               # 持久化对象与数据模型
│       │   ├── BenefitExternalEmpowermentDO.java
│       │   ├── MetricConfigDO.java
│       │   └── ... (共10余个DO类)
│       ├── bo/                 # 业务逻辑对象
│       │   ├── BenefitPreparedDataBO.java
│       │   └── PreparedDataModel.java
│       ├── dto/                # 内部传输对象
│       │   ├── PrettyLinkDTO.java
│       │   └── StatisticEfficiencyScheduleProvinceDTO.java
│       ├── enums/              # 业务枚举
│       │   └── MetricTypeEnum.java
│       ├── indicator/          # 指标常量与评估维度
│       │   ├── MetricBenefitConstants.java
│       │   ├── MetricBenefitL2CalculatorEnum.java
│       │   └── MetricComprehensiveProtectionL3ProcessingDataEnum.java
│       ├── structure/          # 视图结构模型
│       │   ├── BasicChart.java
│       │   └── MetricVisualizedNode.java
│       ├── vo/                 # 视图对象
│       │   └── VisualizedProvinceBaseVO.java
│       └── service/            # Dubbo 接口定义
│           ├── BenefitExternalEmpowermentDubboService.java
│           └── ProvinceVisualizationDubboService.java
│
├── demo-province-module (省级业务逻辑实现)
│   └── src/main/java/com/demo/province
│       ├── SamaLedgerApplication.java (启动类)
│       ├── config/             # 系统配置 (Caffeine, Executor, Nacos)
│       ├── dubboImpl/          # Dubbo 接口实现类
│       ├── Excel/              # Excel 处理 (ExcelMergeReader)
│       ├── mapper/             # 数据库访问层
│       ├── metric/             # 核心指标引擎
│       │   ├── BenefitEngineServiceImpl.java
│       │   ├── aviator/        # 表达式引擎函数 (ExcelFloor, LinearInterpolation)
│       │   ├── calculators/    # 全量计算器实现 (包含 Ability, Project, Rate, Software 四大类)
│       │   │   ├── AbilityApiGatewaySecurityCalculator.java
│       │   │   ├── ProjectLongTermDebtCalculator.java
│       │   │   ├── RateAverageBlockCalculator.java
│       │   │   └── SoftwareSoarCalculator.java
│       │   └── handlers/       # 业务处理器链 (Benefit, Compliance, Scenario 等)
│       ├── support/            # 支撑服务 (消息推送/拉取)
│       ├── service/            # 内部 Service 及其 Impl 实现
│       └── utils/              # 工具类 (Kafka, Number, Mock)
│
├── demo-province-portal (省级门户接口)
│   └── src/main/java/com/demo/portal
│       ├── controller/         # REST/WebSocket 控制器
│       ├── object/dto/ledger/  # 门户专用 DTO
│       └── service/            # 门户业务逻辑及实现
│
├── demo-group-api (集团接口模块)
│   └── service/                # 集团级 Dubbo 接口
│
├── demo-group-module (集团业务逻辑)
│   ├── config/                 # 集团模块配置
│   ├── metric/support/         # 集团级消息与可视化实现
│   ├── service/impl/           # 集团业务 Service 实现
│   └── utils/                  # 集团模块工具类
│
└── demo-group-portal (集团门户接口)
    ├── controller/             # 集团门户控制器
    ├── object/vo/              # 集团视图对象
    └── service/impl/           # 集团门户业务实现
```
