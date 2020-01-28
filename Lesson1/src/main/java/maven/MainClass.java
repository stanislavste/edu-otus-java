package maven;

public class MainClass {
    public static void main(String[] args) {
        B b = new B();
        A a = new B();
        //B c = new A(); так нельзя!!!
        b.f();
        a.f();
        AbleToEat ableToEat = new AbleToEat() {
            @Override
            public void eat() {
                System.out.println("eat");
            }
        };
    }
}
