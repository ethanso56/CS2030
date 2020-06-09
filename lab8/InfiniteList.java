import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.Optional;

public interface InfiniteList<T> {
    
    public static <T> InfiniteList<T> generate(Supplier<? extends T> s) {
         return InfiniteListImpl.generate(s);
    }

    public static <T> InfiniteList<T> iterate(T seed, Function<T, T> next) {
        return InfiniteListImpl.iterate(seed, next);
    }
    
    public void forEach(Consumer<? super T> action);

    public InfiniteList<T> limit(long maxSize);

    public Object[] toArray();

    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper);

    public InfiniteList<T> filter(Predicate<? super T> pred);

    public InfiniteList<T> takeWhile(Predicate<? super T> pred);
    
    public long count();

    public Optional<T> reduce(BinaryOperator<T> accumulator);

    public T reduce(T identity, BinaryOperator<T> accumulator);
    
    public Optional<T> get();

}
