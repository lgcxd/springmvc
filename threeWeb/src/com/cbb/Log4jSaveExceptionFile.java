package com.cbb;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @ClassName Log4jSaveExceptionFile
 * @Author: ChenBJ
 * @Description: 保存异常信息到单独的文件中
 * @Date: 2018/7/9 11:45
 * @Version:
 */
public class Log4jSaveExceptionFile {
    private static Logger logger = Logger.getLogger(Log4jSaveExceptionFile.class);//日志记录器 Log4jSaveExceptionFile.class.getName()新建一个记录器
    public static void main(String[] args) {
        PropertyConfigurator.configure("D:/files/conf/log4j.properties");//读取使用java的特性文件编写的配置文件
        logger.debug("debug");//插入记录信息
        logger.info("info");
        logger.error("error");
    }
}
