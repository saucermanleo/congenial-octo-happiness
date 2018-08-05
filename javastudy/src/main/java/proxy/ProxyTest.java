package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
	public static Person getPersonProxy(Person pp){
		
		Person p = (Person)Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), pp.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
				System.out.println("run before");
				Object o = arg1.invoke(arg0, arg2);
				System.out.println("run after");
				return o;
			}
		});
		
		return p;
		
	}
}
