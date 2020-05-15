package com.riil.demo.cache.config.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {class description}
 * <br>
 *
 * <p>
 * Create on : 2020/5/14 10:43 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 *
 * <br>
 */
@Component
@ConfigurationProperties(prefix = "riil.cache")
public class CachePropertisDto {
    /**
     *
     */
    private List<CacheNamespaceDto> m_namespace;

    public CachePropertisDto() {
    }

    @Override
    public String toString() {
        return "CachePropertisDto{" + "m_namespace=" + m_namespace + '}';
    }

    public List<CacheNamespaceDto> getNamespace() {
        return m_namespace;
    }

    public void setNamespace(List<CacheNamespaceDto> namespace) {
        this.m_namespace = namespace;
    }

}
