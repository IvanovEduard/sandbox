package generics;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Eduard Ivanov on 8/7/21
 */
public class GenericsSandBox <T extends Number, V>{

    private T type;
    private V value;

    public GenericsSandBox(T type, V value) {
        this.type = type;
        this.value = value;
    }

    public <Input, Return> List<Return> convert(List<Input> value, Function<Input, Return> convertFunction) {

        Function<Input, Return> function = (input) -> (Return) String.valueOf(input);
        return value.stream().map(value1 -> convertFunction.apply(value1)).collect(Collectors.toList());
    }


    public V getValue(T type) {
        V value = null;
        return value;
    }

    public List<? super T> toList(List<T> coll) {
        return coll;
    }

    public <E> List<E> backE(List<? extends Number> itemsNumber) {
        int i = itemsNumber.get(0).intValue();
        return (List<E>) List.of(i);
    }



}
