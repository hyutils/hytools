package com.chaojilaji.hy.developutils.model;

public class Vuln {
    private String influenceTarget;
    private String category;
    private String type;
    private String level;
    private String description;
    private String detail;
    private String solveMethod;
    private String belongUnit;
    private String handleOrgName;
    private String handleRealName;
    private String netWorkLocation;

    public void setValue(String name,String value){
        switch (name){
            case "受影响目标":{
                this.influenceTarget = value;
                break;
            }
            case "漏洞类别":{
                this.category = value;
                break;
            }
            case "漏洞类型":{
                this.type = value;
                break;
            }
            case "漏洞等级":{
                this.level = value;
                break;
            }
            case "漏洞描述":{
                this.description = value;
                break;
            }
            case "漏洞详情":{
                this.detail = value;
                break;
            }
            case "解决方案":{
                this.solveMethod = value;
                break;
            }
            case "所属定级单元":{
                this.belongUnit = value;
                break;
            }
            case "漏洞处置团队":{
                this.handleOrgName = value;
                break;
            }
            case "漏洞处置人":{
                this.handleRealName = value;
                break;
            }
            case "网络位置":{
                this.netWorkLocation = value;
                break;
            }
            default:break;
        }
    }

    public String getNetWorkLocation() {
        return netWorkLocation;
    }

    public void setNetWorkLocation(String netWorkLocation) {
        this.netWorkLocation = netWorkLocation;
    }

    public String getInfluenceTarget() {
        return influenceTarget;
    }

    public void setInfluenceTarget(String influenceTarget) {
        this.influenceTarget = influenceTarget;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSolveMethod() {
        return solveMethod;
    }

    public void setSolveMethod(String solveMethod) {
        this.solveMethod = solveMethod;
    }

    public String getBelongUnit() {
        return belongUnit;
    }

    public void setBelongUnit(String belongUnit) {
        this.belongUnit = belongUnit;
    }

    public String getHandleOrgName() {
        return handleOrgName;
    }

    public void setHandleOrgName(String handleOrgName) {
        this.handleOrgName = handleOrgName;
    }

    public String getHandleRealName() {
        return handleRealName;
    }

    public void setHandleRealName(String handleRealName) {
        this.handleRealName = handleRealName;
    }

}
