/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.power.inventory.inventory.common.constant;

public enum SortEnum{
    ASCENDING("ASC",0),
    DESCENDING("DESC", 1);

    private String str;
    private int value;

    private SortEnum(String sortString, int value){
        this.str = sortString;
        this.value = value;
    }
}
