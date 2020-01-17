package maven;

public class MainClass {
    public static void main(String[] args) {
        B b = new B();
        A a = new B();
        b.f();
        a.f();
    }
}
