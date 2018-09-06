package com.ymu.servicefileclient.config;

import com.ymu.framework.dao.ds.AbstractDataSourceAspect;
import com.ymu.framework.dao.ds.DSInject;
import com.ymu.framework.dao.ds.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *  解析注入的数据源。
 */
@Aspect
@Component
public class DynamicDataSourceAspect extends AbstractDataSourceAspect {

	@Override
	public String defaultDb() {
		return DSType.YMU_FILE_MASTER;
	}
}
