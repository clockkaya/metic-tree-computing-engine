package com.sama.ledger;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sama.api.ledger.bean.CloudTableConfigDO;
import com.sama.api.ledger.bean.ConstructionDO;
import com.sama.ledger.service.LedgerConstructionService;
import com.sama.ledger.service.TableConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest(classes = SamaLedgerApplication.class)
public class SamaLedgerApplicationTests {
    Logger logger = LogManager.getLogger();

    @Resource
    private TableConfigService tableConfigService;

    @Resource
    private LedgerConstructionService  ledgerConstructionService;

    @Test
    public void getAllConfigsTest() {
        Map<String, CloudTableConfigDO> configs = tableConfigService.getAllConfigs();
        logger.info(configs);
    }

    @Test
    public void createDefaultConfigTest() throws JsonProcessingException {
        List<Map<String, Object>> defaultHeader = Arrays.asList(
                new HashMap<String, Object>() {{
                    put("prop", "serialNumber");
                    put("label", "序号");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "province");
                    put("label", "省份");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "protectObject");
                    put("label", "防护对象");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "cloudType");
                    put("label", "云类型");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "resourcePoolName");
                    put("label", "资源池/平台名称");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "networkSystemName");
                    put("label", "定级备案网络/系统名称");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "level");
                    put("label", "定级备案级别");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "isExistContainer");
                    put("label", "是否存在容器");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "isPublicExposed");
                    put("label", "是否公网暴露面");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "isSensitiveInfo");
                    put("label", "是否涉及敏感信息");
                }},
                new HashMap<String, Object>() {{
                    put("prop", "isExistWebApp");
                    put("label", "是否存在云平台自身的web应用");
                }},
                new HashMap<String, Object>() {{
                    put("label", "安全基础能力覆盖现状");
                    put("children", Arrays.asList(
                            new HashMap<String, Object>() {{
                                put("label", "防火墙");
                                put("children", Arrays.asList(
                                        new HashMap<String, Object>() {{
                                            put("prop", "ngfwProcessingBandwidth");
                                            put("label", "处理带宽（网络层）");
                                        }},
                                        new HashMap<String, Object>() {{
                                            put("prop", "ngfwEgressBandwidth");
                                            put("label", "出口总带宽（网络层）");
                                        }},
                                        new HashMap<String, Object>() {{
                                            put("prop", "ngfwSecurityCapability");
                                            put("label", "提供防护的安全能力");
                                        }}
                                ));
                            }}
                            // 其他安全基础能力项...
                    ));
                }},
                new HashMap<String, Object>() {{
                    put("label", "安全增强能力覆盖现状");
                    put("children", Arrays.asList(
                            new HashMap<String, Object>() {{
                                put("label", "邮件安全");
                                put("children", Arrays.asList(
                                        new HashMap<String, Object>() {{
                                            put("prop", "emailSecurityStatus");
                                            put("label", "具备/缺失");
                                            put("formatter", "row.emailSecurityStatus === 1 ? '具备' : '缺失'");
                                        }},
                                        new HashMap<String, Object>() {{
                                            put("prop", "emailSecurityCapability");
                                            put("label", "提供防护的安全能力");
                                        }}
                                ));
                            }}
                            // 其他安全增强能力项...
                    ));
                }}
        );

        logger.info(defaultHeader);
        // 保存并激活默认配置
        CloudTableConfigDO config = tableConfigService.saveConfig("default", defaultHeader, "1.0");
        tableConfigService.activateConfig(config.getConfigName());
    }

    @Test
    public void testLedgerConstructionServiceSave(){
//        ConstructionDO constructionDO = new ConstructionDO();
//        constructionDO.setAcceptType("1");
//        constructionDO.setAgreeProjCode("202523");
//        boolean result = ledgerConstructionService.save(constructionDO);
//        System.out.println(result);
    }
    @Test
    public void testLedgerConstructionServiceSaveOrUpdate(){
//        ConstructionDO constructionDO = new ConstructionDO();
//        constructionDO.setAcceptType("1");
//        constructionDO.setAgreeProjCode("202523");
//        boolean result = ledgerConstructionService.saveOrUpdate(constructionDO);
//        System.out.println(result);
    }

}
