package com.ebanma.cloud.orm.model;

import java.util.ArrayList;
import java.util.List;

import com.ebanma.cloud.orm.util.ParameterMapping;

/**
 * @author 于秦涛
 * @version $ Id: BoundSql, v 0.1 2023/03/09 15:26 98077 Exp $
 */
public class BoundSql {

    private String sqlText; //解析过后的sql

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }


}

