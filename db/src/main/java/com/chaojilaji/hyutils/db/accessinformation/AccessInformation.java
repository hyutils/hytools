package com.chaojilaji.hyutils.db.accessinformation;

/**
 * 出入信息
 */
public class AccessInformation {

    private OutInformation outInformation;

    private EnterInformation enterInformation;

    public OutInformation getOutInformation() {
        return outInformation;
    }

    public void setOutInformation(OutInformation outInformation) {
        this.outInformation = outInformation;
    }

    public EnterInformation getEnterInformation() {
        return enterInformation;
    }

    public void setEnterInformation(EnterInformation enterInformation) {
        this.enterInformation = enterInformation;
    }
}
