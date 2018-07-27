package com.bootdo.activiti.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bootdo.activiti.domain.SalaryDO;

/**
 * 实体toString
 * @author wyh
 *
 */
public class Bean2StringUtil {
	private static final Logger logger = LoggerFactory.getLogger(Bean2StringUtil.class);
	/**
	 * 实体字段toString
	 * @param obj
	 * @return
	 */
    public static Object fieldToString( Object obj) {
    	try {
    		if(obj instanceof List<?>){
    			StringBuffer sb = new StringBuffer();
    			for (Object s : (List<?>)obj) {
    				sb.append(filed2String(s)).append("\n");
				}
    			return sb;
    		}
    		if(obj instanceof Map<?,?>){
    			return obj;
    		}
    		return filed2String(obj);
		} catch (Exception e) {
			return obj;
		}
        
    }
    private static StringBuffer filed2String(Object obj){
    	StringBuffer sb = new StringBuffer();
        if(obj != null && !"".equals(obj))
        {
            Field [] fields = obj.getClass().getDeclaredFields();
            sb.append(obj.getClass().getName())
            .append(">>>>>>\t");
             
            for(Field f:fields){
            	if(!("serialVersionUID").equals(f.getName())){
            		f.setAccessible(true);
                    try {
                    	if(f.get(obj)!=null){
                    		sb.append(f.getName())
                            .append("=")
                            .append(f.get(obj)).append(";");
                    	}
                    } catch (Exception e) {
                        logger.error("实体类 toString 错误");
                    }
            	}
            }  
        }
        return sb;
    }
    /**
     * 方法 toString
     * @param obj
     * @return
     */
    public static StringBuffer methodToString(Object obj){
    	StringBuffer sb = new StringBuffer();
        Method[] methods = obj.getClass().getMethods();
         
        for(Method m:methods){
            sb.append(m.getName()).append(";");
        }
        return sb;
    }
    public static void main(String[] args) {
    	SalaryDO sd = new SalaryDO();
    	sd.setAge("2");
    	sd.setAddNum("222");
    	SalaryDO sd2 = new SalaryDO();
    	sd2.setAge("1");
    	sd2.setAddNum("111");
    	List<SalaryDO> sl = new ArrayList<SalaryDO>();
    	sl.add(sd);
    	sl.add(sd2);
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("1", sd);
    	m.put("12", sd2);
    	System.out.println(fieldToString("main"));
	}
}
