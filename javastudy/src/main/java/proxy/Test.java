package proxy;

public class Test {

	public static void main(String[] args) {
		Person pp = new FatMan();
		Person p = ProxyTest.getPersonProxy(pp);
		p.run();
	}

}
