package com.kienvt.tclgt.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by johnvi on 1/24/16.
 */
@Table(name = "OFFENCES")
public class MOffence extends Model{
    @Column(name = "offence_id")
    public int offenceId;

    @Column(name = "category_id")
    public String categoryId;

    @Column
    public String name;

    @Column
    public String description;

    @Column
    public String law;

    @Column
    public int bookmark;

    @Column(name = ("unsign_name"))
    public String unsignName;

    @Column(name = "unsign_des")
    public String unsignDesc;
}
