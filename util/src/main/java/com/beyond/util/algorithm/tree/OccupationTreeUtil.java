package com.beyond.util.algorithm.tree;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>树形结构工具/p>
 *
 * @author PengCheng
 * @date 2018/10/31
 */
public class OccupationTreeUtil {

    public static final String PPID_SPLIT = ",";

    /**
     * 提取出跟节点（未折叠）
     *
     * @param treeNodeList 节点数据
     * @param <T>
     * @return
     */
    public static <T extends OccupationTreeNode<T>> List<T> extractRootNode(List<T> treeNodeList) {
        if (treeNodeList != null && treeNodeList.size() > 0) {
            return null;
        }
        List<T> roots = new ArrayList<>();
        for (T treeNode : treeNodeList) {
            if (StringUtils.isEmpty(treeNode.getPCode()) || treeNode.getPCode().equals("0")) {
                roots.add(treeNode);
            }
        }
        return roots;
    }

    /**
     * 折叠出根节点树结构
     * @param treeNodeList 所有节点数据
     * @param <T>
     * @return
     */
    public static <T extends OccupationTreeNode<T>> List<T> foldRootTree(List<T> treeNodeList) {
        List<T> treeNodes = extractRootNode(treeNodeList);
        if (treeNodeList != null && treeNodeList.size() > 0) {
            treeNodes.forEach(
                    treeNode -> mountChildrenNode(treeNode, treeNodeList)
            );
        }
        return treeNodes;
    }

    /**
     * 挂载子节点,形成树结构 （所有可挂载的子节点已确定）
     * 递归处理
     *
     * @param pNode        父节点,最终所有数据都会存在pNode下
     * @param treeNodeList 所有与该根关联的下级节点
     */
    public static <T extends OccupationTreeNode<T>> void mountChildrenNode(T pNode, List<T> treeNodeList) {
        //找出所有的子节点
        List<T> children = new ArrayList<>();
        for (T child : treeNodeList) {
            //递归形成子节点
            if (StringUtils.isNotEmpty(child.getPCode())
                    && child.getPCode().equals(pNode.getCode())) {
                //递归挂载子节点
                mountChildrenNode(child, treeNodeList);
                children.add(child);
            }
        }
        //节点挂载
        pNode.setChildren(children);
    }

    /**
     * 铺平树结构
     *
     * @param isRetainChildren 是否要在铺平树结构后,继续展示各自的子节点数据
     * @return 铺平后的树
     */
    public static <T extends OccupationTreeNode<T>> List<T> tilingTreeNodes(T pNode, boolean isRetainChildren) {
        List<T> storageContainer = new LinkedList<>();
        openChildrenNode(pNode, storageContainer, isRetainChildren);
        return storageContainer;
    }

    /**
     * 从非成树的结构组织中提取一个节点下所有关联的子节点（包含自己）
     *
     * @param treeNodes 所有的节点数据
     * @param pid  要查询的上级节点id
     * @param <T>
     * @return
     */
    public static <T extends OccupationTreeNode<T>> List<T> extractAllChildrenNode(List<T> treeNodes, String pid) {
        if (treeNodes != null && treeNodes.size() > 0) {
            return null;
        }
        List<T> children = new ArrayList<>();
        extractChildrenNode(treeNodes, pid, children);
        return children;
    }

    /**
     * 提取所有与传入节点相关的下层节点
     *
     * @param treeNodes   所有的数据
     * @param pid       上级节点id
     * @param container   存储数据的容器
     * @param <T>rootNode
     */
    private static <T extends OccupationTreeNode<T>> void extractChildrenNode(List<T> treeNodes, String pid, List<T> container) {
        for (T treeNode : treeNodes) {
            if (treeNode.getPCode().equals(pid)) {
                container.add(treeNode);
                extractChildrenNode(treeNodes, treeNode.getPCode(), container);
            }
        }
    }


    private static <T extends OccupationTreeNode<T>> void openChildrenNode(T pNode, List<T> storageContainer, boolean isRetainChildren) {
        //取出子节点数据
        List<T> childrenNode = pNode.getChildren();
        //判断是否要保留子节点数据做展示
        if (!isRetainChildren) {
            pNode.setChildren(null);
        }
        storageContainer.add(pNode);
        if (childrenNode != null && childrenNode.size() > 0) {
            for (T childNode : childrenNode) {
                openChildrenNode(childNode, storageContainer, isRetainChildren);
            }
        }
    }
}
