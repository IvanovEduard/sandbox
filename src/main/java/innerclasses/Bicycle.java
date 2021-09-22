package innerclasses;

/**
 * Created by Eduard Ivanov on 8/8/21
 */
public class Bicycle {
    private String name;
    private long count;

    public Bicycle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void start() {
        System.out.println("Let's go!");
    }

    public class HandleBar {
        private String name;

        public void goRight() {
            String name = Bicycle.this.name;

            System.out.println(name + " Go Right");
        }

        public void goLeft() {
            String name = Bicycle.HandleBar.this.name;
            System.out.println(this.name + "Go Left");
        }

        public String handleException() {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "a";
            } finally {
                return "b";
            }
        }
    }

    public static class GeneralActions {
        public static void executeGeneralActions(String biName) {
            Bicycle bicycle = new Bicycle(biName);
            bicycle.start();
            HandleBar handleBar = bicycle.new HandleBar();
            handleBar.goRight();
            handleBar.goLeft();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bicycle bicycle = (Bicycle) o;
        if (null instanceof Object) {
            return true;
        }

        return name != null ? name.equals(bicycle.name) : bicycle.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }
}
