package com.mehome.enumDTO;

/**
 * Created by Administrator on 2017/5/19.
 */
public enum InfoOperationEnum {
    UPDATE("update"), ADD("add");

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    private String operation;

    private InfoOperationEnum(String operation) {
        this.operation = operation;
    }

    public static boolean contain(String operation) {
        InfoOperationEnum[] infoOperationEnums = InfoOperationEnum.values();
        for (InfoOperationEnum item : infoOperationEnums) {
            if (item.getOperation().equals(operation)) {
                return true;
            }
        }
        return false;
    }
}
