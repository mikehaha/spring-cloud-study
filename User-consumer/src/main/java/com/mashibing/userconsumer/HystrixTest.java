package com.mashibing.userconsumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 *
 */

public class HystrixTest extends HystrixCommand {

    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
    }

    public static void main(String[] args) {
        //	HystrixTest hystrixTest = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext"));
        /**
         * execute()：以同步阻塞方式执行run()。以demo为例，调用execute()后，
         * hystrix先创建一个新线程运行run()，
         * 	接着调用程序要在execute()调用处一直阻塞着，直到run()运行完成
         */
        //	System.out.println("result:" + hystrixTest.execute());

        /**
         * queue()：以异步非阻塞方式执行run()。以demo为例，
         * 	一调用queue()就直接返回一个Future对象，
         * 	同时hystrix创建一个新线程运行run()，
         * 	调用程序通过Future.get()拿到run()的返回结果，
         * 	而Future.get()是阻塞执行的
         */
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
     *   下面的 run 方法，如果正常执行，那么就会返回 run() 方法的返回值，如果出现了异常就调用 Fallback 参数值
     * @return
     * @throws Exception
     */
    @Override
    protected Object run() throws Exception {
        System.out.println("执行逻辑");
//        int i = 1/0;
        return "ok";
    }

    @Override
    protected Object getFallback() {
        System.out.println("执行--getFallback()");
        return "get fall back result";
    }
}