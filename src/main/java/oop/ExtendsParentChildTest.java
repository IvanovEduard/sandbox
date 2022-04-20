package oop;

public class ExtendsParentChildTest {

    private static class Parent {
        int a = 2;

        public void callMethod(Parent p) {
            System.out.println("parent");
        }
    }

    private static class Child extends Parent {
        int a = 3;

        public void callMethod(Child p) {
            System.out.println("child1");
        }

        public void callMethod(Parent p) {
            System.out.println("child2 a=" + a);
        }
    }

    private static class FactoryTest {
        private Parent p;
        private Child ch;
        public FactoryTest(Parent parent) {
            this.p = parent;
        }

        void invokeMethod() {
            p.callMethod(p);
        }
    }

    public static void main(String[] args) {
        Parent parent = new Parent();
        Parent child = new Child();
        FactoryTest factoryTest = new FactoryTest(parent);
        factoryTest.invokeMethod();

        FactoryTest factoryTest2 = new FactoryTest(child);
        factoryTest2.invokeMethod();
    }
}
