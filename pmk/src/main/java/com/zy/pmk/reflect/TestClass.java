package com.zy.pmk.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zy.pmk.pojo.A;
import com.zy.pmk.pojo.D;
import com.zy.pmk.pojo.EnumA;
import com.zy.pmk.pojo.InterfaceB;
import com.zy.pmk.pojo.User;

public class TestClass<m, v> {
	/**
	 * 返回该类中声明的泛型的类型数组
	 */
	@SuppressWarnings("rawtypes")
	/*@Test*/
	public void getTypeParameters() {
		@SuppressWarnings("unused")
		Map<String, String> map = new HashMap<String, String>();
		TypeVariable[] type = this.getClass().getTypeParameters();
		for (TypeVariable<?> t : type) {
			System.out.println(t.getName());
		}
	}

	/**
	 * 得到class对象
	 */
	@SuppressWarnings("rawtypes")
	//@Test
	public void getclass() {
		// 获得Class对象的方法
		// 1.通过 类型.class 获得，适用于所有类型
		Class str = String.class;
		System.out.println(str.getName());
		Class i = int.class;
		System.out.println(i.getName());
		Class d = Double[].class;
		System.out.println(d.getName());
		// 2.通过 Object的getClass() 方法获得，适用于所有非基本类型
		A a = new A();
		Class ac = a.getClass();
		System.out.println(ac.getName());
		// 3.通过 Class.forName(String name) 方法获得
		try {
			Class ac1 = Class.forName("com.zy.pmk.pojo.A");
			System.out.println(ac1.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // 注意要用全限定名
	}

	/**
	 * 只有当类具有公有的无参构造方法才能成功
	 */
	//@Test
	public void newInstance() {
		try {
			User u = User.class.newInstance();
			u.setPassword("123456");
			u.setUsername("root");
			System.out.println(u);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得该Class的直接父类对应的Class对象，如果该Class是基本类型、接口、注释，则返回null。
	 */
	@SuppressWarnings("rawtypes")
	//@Test
	public void getSuperclass() {
		Class c0 = A.class;//类,返回直接父类,泛型当做普通类处理
		Class c1 = EnumA.class;//枚举类型,默认继承Enum类
		Class c2 = int.class;//基本类型返回null
		Class c3 = int[].class;//数组统一返回 class Object
		Class c4 = InterfaceB.class;//接口也是返回null
		Class c5 = Deprecated.class;//注释类型也返回null,c5.isInterface() 为true
		System.out.println("类 : " + c0.getSuperclass());
		System.out.println("枚举 : " + c1.getSuperclass());
		System.out.println("基本类型 : " + c2.getSuperclass());
		System.out.println("数组 : " + c3.getSuperclass());
		System.out.println("接口 : " + c4.getSuperclass());
		System.out.println("注释 : " + c5.getSuperclass());
	}
	
	@SuppressWarnings("hiding")
	class ClassA<m,v> extends TestClass<m,v>{};
	
	
	/**
	 * 返回父类的Type
	 */
	@SuppressWarnings("rawtypes")
	//@Test
	public void getGenericSuperclass () {
		Class c0 = ClassA.class;
        Class c1 = EnumA.class;//枚举类型,默认继承Enum类
        Class c2 = int.class;//基本类型返回null
        Class c3 = ClassA[].class;//数组统一返回 class Object
        Class c4 = InterfaceB.class;//接口也是返回null
        Class c5 = Deprecated.class;//注释类型也返回null
        System.out.println("类 : " + c0.getGenericSuperclass());
        System.out.println("枚举 : " + c1.getGenericSuperclass());
        System.out.println("基本类型 : " + c2.getGenericSuperclass());
        System.out.println("数组 : " + c3.getGenericSuperclass());
        System.out.println("接口 : " + c4.getGenericSuperclass());
        System.out.println("注释 : " + c5.getGenericSuperclass());
        System.out.println(c2.isInterface());
	}
	
	/**
	 * 得到当前类直接实现的接口
	 */
	@SuppressWarnings("rawtypes")
	//@Test
	public void getInterfaces() {
		Class<?>[] clazz = A.class.getInterfaces();
		for(Class c : clazz) {
			System.out.println(c.getName());
		}
	}
	
	/**
	 * 得到当前类直接实现的接口的类型
	 */
	//@Test
	public void getGenericInterfaces () {
		Type[] clazz = A.class.getGenericInterfaces();
		for(Type c : clazz) {
			System.out.println(c.getTypeName());
			if(c instanceof ParameterizedType) {
				ParameterizedType parameterizedType  = (ParameterizedType) c;
				Type[] t = parameterizedType.getActualTypeArguments();
				System.out.println(t[0]);
			}
		}
	}
	
}
