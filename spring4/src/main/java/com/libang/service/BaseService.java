package com.libang.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author libang
 * @date 2018/7/14 11:32
 */
public class BaseService {
    private Integer id;
    private String name;
    private Double score;
    private List<String> stringList;
    private Set<Integer> numSets;
    private Map<String, String> maps;
    private Properties properties;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Set<Integer> getNumSets() {
        return numSets;
    }

    public void setNumSets(Set<Integer> numSets) {
        this.numSets = numSets;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }




}
