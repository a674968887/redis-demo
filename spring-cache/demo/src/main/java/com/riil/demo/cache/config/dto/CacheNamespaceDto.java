package com.riil.demo.cache.config.dto;

/**
 * {class description}
 * <br>
 * <p>
 * Create on : 2020/5/15 14:54 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
public class CacheNamespaceDto {
    /**
     * 缓存名称
     */
    private String m_name;
    /**
     * 过期时间
     */
    private Integer m_ttl;

    public CacheNamespaceDto() {
    }

    @Override
    public String toString() {
        return "CacheNamespaceDto{" + "name='" + m_name + '\'' + ", ttl=" + m_ttl + '}';
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        this.m_name = name;
    }

    public Integer getTtl() {
        return m_ttl;
    }

    public void setTtl(Integer ttl) {
        this.m_ttl = ttl;
    }
}
