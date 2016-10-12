package org.lazicats.commodity.service.base;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lazicats.commodity.service.base.component.ICommodityCheckService;
import org.lazicats.commodity.service.base.model.CommodityCheckDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class CommodityCheckTest
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICommodityCheckService commodityCheckService;
    
    /**
     * 添加审核
     * <p>TODO</p>
     * @Author 张太
     * @param  
     * @return void
     * @since  2014年10月24日 下午3:12:49
     */
    @Test
    public void addCommodityCheck()throws Exception
    {
        List<CommodityCheckDo> list = new ArrayList<CommodityCheckDo>();
        commodityCheckService.addCommodityCheck(list);
        assertEquals("111","111");
    }
    
    /**
     * 修改审核
     * <p>TODO</p>
     * @Author 张太
     * @param  
     * @return void
     * @since  2014年10月24日 下午3:13:05
     */
    @Test
    public void updateCommodityCheck()throws Exception
    {
        CommodityCheckDo commodityCheckDo = new CommodityCheckDo();
        Boolean isUpdate = commodityCheckService.updateCommodityCheck(commodityCheckDo);
        assertEquals(isUpdate,true);
    }
    
    /**
     * 
     * <p>联合查询商品及商品审核信息</p>
     * @Author 张太
     * @param  
     * @return void
     * @since  2014年10月24日 下午3:19:37
     */
    @Test
    public void getCommodityAndCheckList()throws Exception
    {
//        commodityCheckService.getCommodityAndCheckList("");
    }
}
