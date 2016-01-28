package com.kienvt.tclgt.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by johnvi on 1/29/16.
 */
@Table(name = "CATEGORY")
public class MCategory extends Model {
    @Column(name = "category_id")
    public String categoryId;

    @Column(name = "parent_id")
    public String parentId;

    public String name;
}
