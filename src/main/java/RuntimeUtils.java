/**
 * pengyang
 * 2021/6/1 9:31
 * 1.0
 */

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * 系统环境工具类
 * Ref. https://docs.oracle.com/javase/10/docs/api/com/sun/management/OperatingSystemMXBean.html#method.summary
 */
public class RuntimeUtils {

    private static final OperatingSystemMXBean systemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private static final Runtime runtime = Runtime.getRuntime();

    /**
     * 获取物理内存总大小
     *
     * @return
     */
    public static long getTotalPhysicalMemorySize() {
        return systemMXBean.getTotalPhysicalMemorySize();
    }

    /**
     * 获取物理内存剩余大小
     *
     * @return
     */
    public static long getFreePhysicalMemorySize() {
        return systemMXBean.getFreePhysicalMemorySize();
    }

    /**
     * 获取物理内存已使用大小
     *
     * @return
     */
    public static long getUsedPhysicalMemorySize() {
        return systemMXBean.getTotalPhysicalMemorySize() - systemMXBean.getFreePhysicalMemorySize();
    }

    /**
     * 获取 Swap 总大小
     *
     * @return
     */
    public static long getTotalSwapSpaceSize() {
        return systemMXBean.getTotalSwapSpaceSize();
    }

    /**
     * 获取 Swap 剩余大小
     *
     * @return
     */
    public static long getFreeSwapSpaceSize() {
        return systemMXBean.getFreeSwapSpaceSize();
    }

    /**
     * 获取 Swap 已使用大小
     *
     * @return
     */
    public static long getUsedSwapSpaceSize() {
        return systemMXBean.getTotalSwapSpaceSize() - systemMXBean.getFreeSwapSpaceSize();
    }

    /**
     * 获取 JVM 最大内存
     *
     * @return
     */
    public static long getJvmMaxMemory() {
        return runtime.maxMemory();
    }

    /**
     * 获取 JVM 内存总大小
     *
     * @return
     */
    public static long getJvmTotalMemory() {
        return runtime.totalMemory();
    }

    /**
     * 获取 JVM 内存剩余大小
     *
     * @return
     */
    public static long getJvmFreeMemory() {
        return runtime.freeMemory();
    }

    /**
     * 获取 JVM 内存已使用大小
     *
     * @return
     */
    public static long getJvmUsedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    /**
     * 获取系统 CPU 使用率
     *
     * @return
     */
    public static double getSystemCpuLoad() {
        return systemMXBean.getSystemCpuLoad();
    }

    /**
     * 获取 JVM 进程 CPU 使用率
     *
     * @return
     */
    public static double getProcessCpuLoad() {
        return systemMXBean.getProcessCpuLoad();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=1;i<=3;i++){
            System.out.println("第"+i+"次");
            while (true){
                Double cpuLoad=RuntimeUtils.getSystemCpuLoad();
                System.out.println(cpuLoad);
                Thread.sleep(1000);
                if(cpuLoad!=0&&cpuLoad<0.04){
                    break;
                }
            }
        }

    }

}
