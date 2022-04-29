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

    private static class FactoryTest2<Child> {
        private ExtendsParentChildTest.Child p;
        public FactoryTest2(ExtendsParentChildTest.Child parent) {
            this.p = parent;
        }

        void invokeMethod() {
            p.callMethod(p);
        }
    }

    public static void main(String[] args) {
        AbstractTest.A a = new AbstractTest.A();
        Parent parent = new Parent();
        Child child = new Child();
        FactoryTest factoryTest = new FactoryTest(parent);
        factoryTest.invokeMethod();

        FactoryTest2<Child> factoryTest2 = new FactoryTest2<>(child);
        factoryTest2.invokeMethod();
    }
}
