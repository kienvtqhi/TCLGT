package com.kienvt.tclgt.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by johnvi on 1/24/16.
 */
@Table(name = "Offence")
public class MOffence extends Model{
    @Column
    public String detail;

    @Column
    public String money;

    @Column
    public String info;
}
