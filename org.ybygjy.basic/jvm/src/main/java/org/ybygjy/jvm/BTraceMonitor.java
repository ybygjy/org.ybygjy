package org.ybygjy.jvm;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.TLS;

@BTrace
public class BTraceMonitor {
    @TLS
    private static long startTime;
    @OnMethod(clazz = "/org\\.ybygjy\\.spring\\.web\\..+/", method = "/.+/", location = @Location(Kind.ENTRY))
    public static void startExecute() {
        startTime = BTraceUtils.timeMillis();
    }

    @OnMethod(clazz = "/org\\.ybygjy\\.spring\\.web\\..+/", method = "/.+/", location = @Location(Kind.RETURN))
    public static void endExecute() {
        long costTime = BTraceUtils.timeMillis() - startTime;
        BTraceUtils.println(BTraceUtils.Sys.VM.libraryPath() + ">>" + BTraceUtils.Threads.jstackAllStr() + ">>time cost=>" + costTime);
    }
}
