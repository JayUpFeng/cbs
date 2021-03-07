package com.jinjike.cbs.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者：zhanghe
 * @时间：2021-02-28 15:17:57
 * @注释：
 */
@Configuration
public class MybatisPlusConfiguration {

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;


    @Bean
    public GlobalConfig mpGlobalConfig() {
        // 全局配置文件
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // 默认为自增
        dbConfig.setIdType(IdType.AUTO);
        dbConfig.setLogicDeleteValue("1");
        dbConfig.setLogicNotDeleteValue("0");
        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, GlobalConfig globalConfig) throws Exception {
        return getSessionFactoryBean(dataSource, globalConfig);
    }

    private MybatisSqlSessionFactoryBean getSessionFactoryBean(DataSource dataSource, GlobalConfig globalConfig) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        // 源码里面如果有configuration, 不会注入BaseMapper里面的方法, 所以这里要这样写
        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.init(globalConfig);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.pxkj.pojo.*");
        List<Interceptor> interceptors = new ArrayList<>();
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置分页插件
        interceptors.add(paginationInterceptor);
        /*if (!ProjectStageUtil.isProd(projectStage)) {
            // 如果是dev环境,打印出sql, 设置sql拦截插件, prod环境不要使用, 会影响性能*/
        //TODO 正式环境要移除
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        interceptors.add(performanceInterceptor);
        /*}*/
        sqlSessionFactoryBean.setPlugins(interceptors.toArray(new Interceptor[0]));
        return sqlSessionFactoryBean;
    }

}
