package com.mashibing.userconsumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HystrixTest extends HystrixCommand {

    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
    }

    public static void main(String[] args) {
        System.out.println("test");

        Future<String> futureResult= new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
        String result = null;
        try {
            result = futureResult.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("程序结果是="+result);
    }

    /**
     *   下面的 run 方法，如果正常执行，那么就会返回 OK 值，如果出现了异常就调用 Fallback 参数值
     * @return
     * @throws Exception
     */

    @Override
    protected Object run() throws Exception {
        System.out.println("执行逻辑");
        int i = 1/0;
        return "ok";
    }

    @Override
    protected Object getFallback() {
        System.out.println("执行--getFallback()");
        return "get fall back result";
    }
}
