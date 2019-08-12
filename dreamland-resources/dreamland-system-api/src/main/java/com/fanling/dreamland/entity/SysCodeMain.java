package com.fanling.dreamland.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SysCodeMain {
    private String code_type_value;
    private List<Map<String, String>> code_item;
}
