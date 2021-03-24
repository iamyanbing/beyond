package com.beyond.util.algorithm.tree;

import lombok.Data;
import java.util.Date;

@Data
public class OccupationClassifyTreeNode extends OccupationTreeNode<OccupationClassifyTreeNode> {

    private String occupationClassifyName;

    private String lifeInsurance;

    private Short category;

    private String createdBy;

    private String createdId;

    private Date createdTime;

    private String updatedBy;

    private String updatedId;

    private Date updatedTime;

    private String id;

}
