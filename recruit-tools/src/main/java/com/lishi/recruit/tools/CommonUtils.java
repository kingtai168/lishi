package com.lishi.recruit.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * <P>公共工具类</P>
 * @user zt
 * @Author 张太
 * @since  2015年4月24日 上午10:19:26
 * @version 1.0.0
 */
public class CommonUtils
{

    /**
     * 把dobble,long,float,BigDecimal转换成两位小数点
     * @param
     * @return
     */
    public static String convertTwoPoint(Object param)
    {
      DecimalFormat decimalFormat = new DecimalFormat("#.##");
      
      return decimalFormat.format(param);
    }
    
    /**
     * 
     * <p>Double转换成两位小数点</p>
     * @Author 张太
     * @param  
     * @return Double
     * @since  2015年4月24日 下午2:15:04
     */
    public static Double twoPoint(Double number)
    {
        Double result = 0d;
      //换算小数点两位
        BigDecimal  big= new BigDecimal(number);
        result =big.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }
    
    /**
     * 
     * <p>年利率转换月利率</p>
     * @Author 张太
     * @param  Double
     * @return Double
     * @since  2015年4月10日 下午2:59:06
     */
    public static Double interestRateYearToMoth(Double rate)
    {
        return rate/12;
    }
    
    /**
     * 
     * <p>月利率转换成年利率</p>
     * @Author 张太
     * @param  Double
     * @return Double
     * @since  2015年4月10日 下午3:10:07
     */
    public static Double interestRateMothToYear(Double rate)
    {
        return rate*12;
    }
    
}
