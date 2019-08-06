package com.biaobai.utils;

/**
 * com.api.common.util
 * Descrption:全局id生成器
 */
public class IdGen
{
    /** 工作机器ID(0~31) */
    private long workerId;
    /** 数据中心ID(0~31) */
    private long datacenterId;
    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;
    /** 开始时间截 */
    private long twepoch = 1542943619804L;
    /** 机器id所占的位数 */
    private long workerIdBits = 5L;
    /** 数据标识id所占的位数 */
    private long datacenterIdBits = 5L;
    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /** 支持的最大数据标识id，结果是31 */
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /** 序列在id中占的位数 */
    private long sequenceBits = 12L;
    /** 机器ID向左移12位 */
    private long workerIdShift = sequenceBits;
    /** 数据标识id向左移17位(12+5) */
    private long datacenterIdShift = sequenceBits + workerIdBits;
    /** 时间截向左移22位(5+5+12) */
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;
    private String suffix;
    private boolean flag;

    private static class IdGenHolder {
        private static final IdGen instance = new IdGen();
    }

    public static IdGen get(){
        return IdGenHolder.instance;
    }

    /**
     * 获取字符串拼接id
     * @param suffix    字符串
     * @param flag      true:前缀 false:后缀
     * @return
     */
    public static IdGen get(String suffix,boolean flag){
        if(suffix == null || suffix.trim().length() == 0)
            return IdGenHolder.instance;
        else{
            return new IdGen(suffix,flag);
        }
    }

    public IdGen() {
        this(0L, 0L);
    }

    public IdGen(String suffix, boolean flag){
        this.suffix = suffix;
        this.flag = flag;
    }

    public IdGen(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     */
    public synchronized String nextId() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {  // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        long serialNumber = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
        // 移位并通过或运算拼到一起组成64位的ID
        return (suffix == null || suffix.trim().length() == 0) ? serialNumber + ""
                : (flag ? (new StringBuffer()).append(suffix).append(serialNumber).toString()
                        : (new StringBuffer()).append(serialNumber).append(suffix).toString());
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * 
     * @param lastTimestamp
     *        上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * 
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
    
}
