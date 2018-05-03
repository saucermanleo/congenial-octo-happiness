package com.zy.pmk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zy.pmk.pojo.A;
import com.zy.pmk.pojo.EnumA;
import com.zy.pmk.pojo.InterfaceB;
import com.zy.pmk.pojo.User;

/**
 * @author Zangy
 *         Class类，表示Java的基础类型（类+基本类型），是对Java类的抽象，描述的是类的信息，包括类的修饰符（public/private/protect/default/static/final等）
 *         、类的类型（一般类、枚举、注释、接口、数组等）、类的构造器、类的字段、类的方法等信息。所以在看待Class相关问题的时候应该从所有类的共性这个角度去看。
 *         注意，这里的类不是平常我们说的狭义上的类（class），而是广义上，包括了像接口、注释、数组等类型的类，下面若不特殊声明，类代表着广义上的类。
 *         每一个类在JVM内存中，有一个唯一的对应的Class对象，这个对象有着该类的描述信息，所以运行时可以通过该Class类对象进行相应的操作，这就是Java的反射了。
 * @param <m>
 * @param <v>
 */
public class TestClass<m, v> {
	/**
	 * 返回该类中声明的泛型的类型数组
	 */
	@SuppressWarnings("rawtypes")
	/* @Test */
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
	// @Test
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
	// @Test
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
	// @Test
	public void getSuperclass() {
		Class c0 = A.class;// 类,返回直接父类,泛型当做普通类处理
		Class c1 = EnumA.class;// 枚举类型,默认继承Enum类
		Class c2 = int.class;// 基本类型返回null
		Class c3 = int[].class;// 数组统一返回 class Object
		Class c4 = InterfaceB.class;// 接口也是返回null
		Class c5 = Deprecated.class;// 注释类型也返回null,c5.isInterface() 为true
		System.out.println("类 : " + c0.getSuperclass());
		System.out.println("枚举 : " + c1.getSuperclass());
		System.out.println("基本类型 : " + c2.getSuperclass());
		System.out.println("数组 : " + c3.getSuperclass());
		System.out.println("接口 : " + c4.getSuperclass());
		System.out.println("注释 : " + c5.getSuperclass());
	}

	@SuppressWarnings("hiding")
	class ClassA<m, v> extends TestClass<m, v> {
	};

	/**
	 * 返回父类的Type
	 */
	@SuppressWarnings("rawtypes")
	// @Test
	public void getGenericSuperclass() {
		Class c0 = ClassA.class;
		Class c1 = EnumA.class;// 枚举类型,默认继承Enum类
		Class c2 = int.class;// 基本类型返回null
		Class c3 = ClassA[].class;// 数组统一返回 class Object
		Class c4 = InterfaceB.class;// 接口也是返回null
		Class c5 = Deprecated.class;// 注释类型也返回null
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
	// @Test
	public void getInterfaces() {
		Class<?>[] clazz = A.class.getInterfaces();
		for (Class c : clazz) {
			System.out.println(c.getName());
		}
	}

	/**
	 * 得到当前类直接实现的接口的类型
	 */
	// @Test
	public void getGenericInterfaces() {
		Type[] clazz = A.class.getGenericInterfaces();
		for (Type c : clazz) {
			System.out.println(c.getTypeName());
			if (c instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) c;
				Type[] t = parameterizedType.getActualTypeArguments();
				System.out.println(t[0]);
			}
		}
	}

	/**
	 * 得到类的信息
	 */
	@Test
	public void getxxx() {
		//得到包名
		Package p = A.class.getPackage();
		System.out.println(p.getName());
		//得到属性
		Class<A> clzz = A.class;
		Field[] fields = clzz.getDeclaredFields();
		for (Field f : fields) {
			String mod = Modifier.toString(f.getModifiers());
			String name = f.getName();
			Class<?> t = f.getType();
			System.out.println(mod + " " + t.getSimpleName() + " " + name);
		}
		//得到构造器
		Constructor<?>[] cs = A.class.getDeclaredConstructors();
		for (int j = 0; j < cs.length; j++) {
			StringBuilder sb = new StringBuilder();
			sb.append(Modifier.toString(cs[j].getModifiers()));
			sb.append(" ");
			sb.append(A.class.getSimpleName() + "(");
			Class<?>[] ps = cs[j].getParameterTypes();
			for (int i = 0; i < ps.length; i++) {
				if (i == ps.length - 1) {
					sb.append(ps[i].getSimpleName() + " arg" + i);
				} else {
					sb.append(ps[i].getSimpleName() + " arg" + i + " , ");
				}
			}
			sb.append(")");
			System.out.println(sb);
		}
		//得到方法
		Method[] methods = A.class.getDeclaredMethods();
		for (Method m : methods) {
			String s = "";
			int modefier = m.getModifiers();
			Class<?> returntypeclz = m.getReturnType();
			String returntype = returntypeclz.getSimpleName();
			String mod = Modifier.toString(modefier);
			String methodname = m.getName();
			s = mod + " " + returntype + " " + methodname + "(";
			Class<?>[] parameters = m.getParameterTypes();
			for (int i = 0; i < parameters.length; i++) {
				if (i == parameters.length - 1) {
					s = s + parameters[i].getSimpleName() + " arg" + i;
				} else {
					s = s + parameters[i].getSimpleName() + " arg" + i + " , ";
				}
			}
			s = s + ")";
			Class<?>[] exceptions = m.getExceptionTypes();
			if (exceptions.length > 0)
				s = s + " throws  ";
			for (Class<?> c : exceptions) {
				s = s + c.getSimpleName();
			}
			System.out.println(s);
		}
	}

}
