package com.riil.demo.cache.config.ehcache;

import com.riil.demo.cache.config.CacheUtils;
import com.riil.demo.cache.config.dto.CachePropertisDto;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {class description}
 * <br>
 *
 * <p>
 * Create on : 2020/5/12 15:54 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
@ConditionalOnProperty(value = "spring.cache.type", havingValue = "ehcache")
@Configuration
@EnableCaching
public class EhcacheManagerCustom {
    @Autowired
    CachePropertisDto cachePropertisDto;

    /**
     * 添加namespace
     */
    @Bean
    public CacheManager EHcacheManager() {
        System.out.println("EHcacheManager");
        CacheManager manager = CacheManager.create();


        return CacheUtils.ehcacheNamespace(manager, cachePropertisDto);
    }


//    @Bean
//    public ResourcePools customEHResourcePools() {
//        System.out.println(1);
//        ResourcePools resourcePools = ResourcePoolsBuilder
//                .newResourcePoolsBuilder()
//                //设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
//                .heap(1000L, EntryUnit.ENTRIES)
//                //设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
//                .offheap(100L, MemoryUnit.MB)
//                // 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
//                .disk(500L, MemoryUnit.MB, false).build();
//        return resourcePools;
//    }
//
//
//    @Bean
//    public CacheConfiguration customEHcacheConfiguration(@Qualifier("customEHResourcePools") ResourcePools resourcePools) {
//        System.out.println(resourcePools);
//        CacheConfiguration<String, Object> configuration =
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class, resourcePools
//                ).withExpiry(Expirations.timeToLiveExpiration(
//                        Duration.of(120L, TimeUnit.SECONDS))
//                ).build();
//        return configuration;
//    }
//
//    @Bean
//    public CacheManager ehCacheManager(@Qualifier("customEHcacheConfiguration") CacheConfiguration cacheConfiguration) {
//        System.out.println(cacheConfiguration);
////        EhCacheManagerUtils.buildCacheManager()
//
//
//        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                // 硬盘持久化地址
////                .with(CacheManagerBuilder.persistence("java.io.tmpdir"))
//                // 设置一个默认缓存配置
//                .withCache("defaultCache", cacheConfiguration)
//                //创建之后立即初始化
//                .build();
//        return cacheManager;
//    }


    //    @Bean
//    public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
//        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
//        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
//        bean.setShared(true);
//        return bean;
//    }
//

//    private static CacheManager cacheManager;

//    public void ehCacheCacheManager() {
//        ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder
//                .newResourcePoolsBuilder()
//                //设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
//                .heap(1000L, EntryUnit.ENTRIES)
//                //设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
//                .offheap(100L, MemoryUnit.MB)
//                // 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
//                .disk(500L, MemoryUnit.MB, false);
//
//        CacheConfiguration<String, Object> configuration =
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class, resourcePoolsBuilder
//                ).withExpiry(Expirations.timeToLiveExpiration(
//                        //设置缓存过期时间
//                        Duration.of(120L, TimeUnit.SECONDS))
//                )
////                .withExpiry(Expirations.timeToIdleExpiration(
////                        //设置被访问后过期时间(同时设置和TTL和TTI之后会被覆盖,这里TTI生效,之前版本xml配置后是两个配置了都会生效)
////                        Duration.of(60L, TimeUnit.SECONDS)
////                        )
//                        // 缓存淘汰策略 默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
//                        // 这块还没看
//                        /*.withEvictionAdvisor(
//                                new OddKeysEvictionAdvisor<Long, String>())*/
////                )
//                        .build();
//
//        CacheConfigurationBuilder<String, Object> stringObjectCacheConfigurationBuilder =
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class, resourcePoolsBuilder
//                ).withExpiry(Expirations.timeToLiveExpiration(
//                        //设置缓存过期时间
//                        Duration.of(120L, TimeUnit.SECONDS))
//                );
//
//
//        // CacheManager管理缓存
//        cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                // 硬盘持久化地址
//                .with(CacheManagerBuilder.persistence("D:/ehcacheData"))
//                // 设置一个默认缓存配置
//                .withCache("defaultCache", configuration)
//                //创建之后立即初始化
//                .build(true);
//    }
}
