package com.sama.analytic;

import com.api.analytic.service.LedgerBasicNetworkDubboService;
import com.api.analytic.service.LedgerBasicNetworkResultDubboService;
import com.api.analytic.service.LedgerDefenceOverviewDubboService;
import com.api.analytic.service.LedgerScheduleDubboService;
import com.core4ct.constants.Constants;
import com.core4ct.utils.RsaUtilsPlus;
import com.sama.api.ledger.bean.BasicNetworkDO;

import com.sama.analytic.service.LedgerNetworkDefenceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Chang Zhou
 * @CreateTime: 2025-05-27
 * @Description:测试类
 * @Version:1.0
 */
@SpringBootTest(classes = AnalyticApplication.class)
public class LedgerBasicDubboServiceImplTest {

    @Autowired
    LedgerBasicNetworkDubboService ledgerBasicNetworkDubboService;

    @DubboReference
    LedgerBasicNetworkResultDubboService ledgerBasicNetworkResultService;

    @DubboReference
    LedgerScheduleDubboService ledgerScheduleDubboService;


    @DubboReference
    LedgerDefenceOverviewDubboService ledgerDefenceOverviewDubboService;


    @Test
    public void testAdd() throws Exception {
//        BasicNetworkDO coreNetworkSecurityDefenceDO = new BasicNetworkDO();
//        coreNetworkSecurityDefenceDO.setAbnormalTrafficStatus("xxx");
//        List<BasicNetworkDO> basicNetworkSecurityDefenceDOS = new ArrayList<>();
//        basicNetworkSecurityDefenceDOS.add(coreNetworkSecurityDefenceDO);
//         ledgerBasicNetworkDubboService.submit("111",basicNetworkSecurityDefenceDOS,1L);
         ledgerBasicNetworkDubboService.list(new BasicNetworkDO());
    }



    @Test
    public void testCode() throws Exception{
        String smsCode = RsaUtilsPlus.encryptByPublicKey(RsaUtilsPlus.PUBLIC_KEY, "Test189!");
        System.out.println("密码是***********************"+smsCode);
    }

    @Test
    public void test(){
//        ledgerBasicNetworkResultService.getResult();
    }

//    @Test
//    public void testSchedule(){
//        ledgerScheduleDubboService.calculateDefence();
//    }

    @Test
    public void testView(){
        System.out.println("结果是----------------"+ledgerDefenceOverviewDubboService.getFirstScore("02260062"));
    }

//    @Test
//    public void testFlat(){
//        List<String> list = new ArrayList<>();
//        list.add("云");
//        System.out.println(ledgerDefenceOverviewDubboService.getDetailedFlattenedEntries("02260062", list));
//    }



//    @Test
//    public void testGroup(){
//        System.out.println(ledgerDefenceOverviewDubboService.groupByAbility("02260062"));
//    }

    @Test
    public void testGroup2(){
        System.out.println(ledgerDefenceOverviewDubboService.groupByVendor("02260062"));
    }

    @Test
    public void testGroup3(){
        System.out.println(ledgerDefenceOverviewDubboService.groupByCity("02260062"));
    }

//    @Test
//    public void testTotal(){
//        System.out.println(ledgerDefenceOverviewDubboService.getLedgerOverview("02260062"));
//    }

    @Test
    public void testOverview(){
        System.out.println(ledgerDefenceOverviewDubboService.getOverview("02260062"));
    }

//    @Test
//    public void testCount(){
//        System.out.println(ledgerNetworkDefenceService.countSecurityAbilities("02260062", Constants.DelFlag.AVAILABLE));
//    }
//
//    @Test
//    public void testAbilityCount(){
//        System.out.println(ledgerDefenceOverviewDubboService.getAbilityCount("02260062"));
//    }

}
