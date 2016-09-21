package org.ybygjy.jvm;

@BTrace
public class BTraceMonitor {
    private long startTime;
    public void startExecute() {
        this.startTime = System.currentTimeMillis();
    }
    public void endExecute() {
        long costTime = System.currentTimeMillis() - this.startTime;
        if (costTime > 500) {
            print("BTrace cost overhead limit >>" + costTime);
        }
    }
}
