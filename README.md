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
5. **双向交互**

## 📦 技术栈
- **Core**: Java 8, Spring Boot, Dubbo (RPC)
- **Engine**: Aviator (Expression Language)
- **Cache**: Caffeine, Nacos (Config)
- **Middleware**: Kafka (Data Pipe), MyBatis-Plus

## 🧱 详细项目目录结构

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