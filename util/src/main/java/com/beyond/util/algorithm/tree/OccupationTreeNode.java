package com.beyond.util.algorithm.tree;

import lombok.Data;

import java.util.List;


@Data
public class OccupationTreeNode<T extends OccupationTreeNode> {

    /**
     * 代码
     */
    private String code;

    /**
     * 上级代码
     */
    private String pCode;

    /**
     * 子类数据
     */
    private List<T> children;
}
