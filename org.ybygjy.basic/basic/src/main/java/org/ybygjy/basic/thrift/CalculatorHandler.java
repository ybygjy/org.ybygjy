package org.ybygjy.basic.thrift;

import java.util.HashMap;

import org.ybygjy.basic.thrift.tutorial.*;
import org.ybygjy.basic.thrift.shared.SharedStruct;
import org.ybygjy.basic.thrift.tutorial.InvalidOperation;
import org.ybygjy.basic.thrift.tutorial.Work;

public class CalculatorHandler implements Calculator.Iface {
    private HashMap<Integer, SharedStruct> log;
    public CalculatorHandler() {
        this.log = new HashMap<Integer, SharedStruct>();
    }
    public void ping() {
        System.out.println("ping()");
    }
    public int add(int n1, int n2) {
        System.out.println("add (" + n1 + "," + n2 + ")");
        return n1 + n2;
    }
    public int calculate(int logid, Work work) throws InvalidOperation {
        System.out.println("calculate(" + logid + ",{" + work.op + "," + work.num1 + "," + work.num2 + "}" + ")");
        int val = 0;
        switch(work.op) {
        case ADD:
            val = work.num1 + work.num2;
            break;
        case SUBTRACT:
            val = work.num1 - work.num2;
            break;
        case MULTIPLY:
            val = work.num1 * work.num2;
            break;
        case DIVIDE:
            if (work.num2 == 0) {
                InvalidOperation invalidOperation = new InvalidOperation();
                invalidOperation.whatOp = work.op.getValue();
                invalidOperation.why = "Cannot divide by 0";
                throw invalidOperation;
            }
            val = work.num1 / work.num2;
            break;
        default:
            InvalidOperation invalidOperation = new InvalidOperation();
            invalidOperation.whatOp = work.op.getValue();
            invalidOperation.why = "Unknown operation";
            throw invalidOperation;
        }
        SharedStruct entry = new SharedStruct();
        entry.key = logid;
        entry.value = Integer.toString(val);
        log.put(logid, entry);
        
        return val;
    }
    public SharedStruct getStruct(int key) {
        System.out.println("getStruct(" + key + ")");
        return log.get(key);
    }
    public void zip() {
        System.out.println("zip()");
    }
}
