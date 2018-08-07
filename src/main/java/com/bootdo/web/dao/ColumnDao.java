package com.bootdo.web.dao;

import java.util.List;
import java.util.Map;

public interface ColumnDao {
	
	List<Map<String, Object>> getColumnList();
}