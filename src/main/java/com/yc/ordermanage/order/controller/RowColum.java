package com.yc.ordermanage.order.controller;

public enum RowColum {
    型号品名颜色(0), 件数(1), 装箱数(2), 总数量(3), 单位(4), 单价(5), 总金额(6), 厂家(7);
    private int index;

    RowColum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
