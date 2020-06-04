package com.jvm.zzmAction;

import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Dispatcher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;

/**
 * @Author: MikeWang
 * @Date: 2020/2/11 2:32 PM
 * @Description:
 *
# 初始空间大小，达到该值就会触发垃圾收集进行类型卸载，同时GC会对该值进行调整
# 如果释放了大量的空间，就适当降低该值。
# 如果释放了很少的空间，那么在不超过 MaxMetaspaceSize 下适当提高该值。
-XX:MetaspaceSize

# 最大空间，默认没有限制。
# 防止因为某些情况导致 Metaspace 无限的使用本地内存，影响到其他程序。应设置大小。
-XX:MaxMetaspaceSize


# Metaspace 增长时的最大幅度。
-XX:MaxMetaspaceExpansion

# Metaspace 增长时的最小幅度。
-XX:MinMetaspaceExpansion


# 当进行过Metaspace GC之后， 会计算当前Metaspace的空闲空间比，如果空闲比大于这个参数，那么虚拟机会释放 Metaspace 的部分空间。在本机该参数的默认值为70，也就是70%。
-XX:MaxMetaspaceFreeRatio

# 当进行过Metaspace GC之后，会计算当前 Metaspace 的空闲空间比，如果空闲比小于这个参数，那么虚拟机将增长 Metaspace 的大小。在本机该参数的默认值为40，也就是40%。
# 设置该参数可以控制 Metaspace 的增长的速度，太小的值会导致 Metaspace 增长的缓慢，Metaspace 的使用逐渐趋于饱和，可能会影响之后类的加载。而太大的值会导致 Metaspace 增长的过快，浪费内存。
-XX:MinMetaspaceFreeRatio
 */
public class MetaSpaceOomError {

    public static void main(String[] args) {
        ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
        // 借助 CGlib 来动态地生成大量的 Class
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MetaSpaceOomError.class);
            enhancer.setCallbackTypes(new Class[]{Dispatcher.class, MethodInterceptor.class});
            enhancer.setCallbackFilter(new CallbackFilter() {
                @Override
                public int accept(Method method) {
                    return 1;
                }

                @Override
                public boolean equals(Object obj) {
                    return super.equals(obj);
                }
            });
            Class clazz = enhancer.createClass();

            System.out.println(clazz.getName() + "===================================");
            // 显示数量信息(共加载过的类型数目,当前还有效的类型数目,已经被卸载的类型数目)
            System.out.println("total:" + loadingBean.getTotalLoadedClassCount());
            System.out.println("active:" + loadingBean.getLoadedClassCount());
            System.out.println("unloaded:" + loadingBean.getUnloadedClassCount());
        }
    }
}
