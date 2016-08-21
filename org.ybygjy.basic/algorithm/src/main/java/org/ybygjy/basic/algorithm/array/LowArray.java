package org.ybygjy.basic.algorithm.array;

public class LowArray {
    private long[] a;
    public LowArray(int size) {
        this.a = new long[size];
    }
    public void setElem(int index, long value) {
        this.a[index] = value;
    }
    public long getElem(int index) {
        return this.a[index];
    }
    public static void main(String[] args) {
        LowArray lowArray = new LowArray(100);
        int nElems = 0;
        int j;
        lowArray.setElem(0, 77);
        lowArray.setElem(1, 99);
        lowArray.setElem(2, 44);
        lowArray.setElem(3, 55);
        lowArray.setElem(4, 22);
        lowArray.setElem(5, 88);
        lowArray.setElem(6, 11);
        lowArray.setElem(7, 00);
        lowArray.setElem(8, 66);
        lowArray.setElem(9, 33);
        nElems = 10;

        for (j = 0; j < nElems; j++) {
            System.out.print(lowArray.getElem(j) + " ");
        }
        System.out.println("");
        int searchKey = 26;
        for (j = 0; j < nElems; j++) {
            if (lowArray.getElem(j) == searchKey) {
                break;
            }
        }
        if (j == nElems) {
            System.out.println("Can't find " + searchKey);
        } else {
            System.out.println("Found " + searchKey);
        }
        for (j = 0; j < nElems; j++) {
            if (lowArray.getElem(j) == 55) {
                break;
            }
        }
        for (int k = j; k < nElems; k++) {
            lowArray.setElem(k, lowArray.getElem(k+1));
        }
        nElems--;
        for(j = 0; j < nElems; j++) {
            System.out.print(lowArray.getElem(j) + " ");
        }
        System.out.println();
    }
}
