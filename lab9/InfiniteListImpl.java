import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.Optional;
import java.util.ArrayList;

public class InfiniteListImpl<T> implements InfiniteList<T> {

    private final Lazy<T> head;
    private final Lazy<InfiniteListImpl<T>> tail;

    protected InfiniteListImpl() {
        this.head = Lazy.ofNullable(null);
        this.tail = Lazy.generate(() -> new EmptyList<T>());
    }

    private InfiniteListImpl(Lazy<T> head, Lazy<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        Lazy<T> newHead = Lazy.generate(s);
        Lazy<InfiniteListImpl<T>> newTail = Lazy.generate(() -> InfiniteListImpl.generate(s));
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        Lazy<T> newHead = Lazy.ofNullable(seed);
        Lazy<InfiniteListImpl<T>> newTail = Lazy.generate(() -> InfiniteListImpl.iterate(next.apply(seed), next));
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    @Override
    public InfiniteListImpl<T> peek() {
        head.get().ifPresent(System.out::println);
        return tail.get().orElseThrow();
    }

    @Override
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = head.map(mapper);
        Lazy<InfiniteListImpl<R>> newTail = Lazy.generate(() -> tail.get().orElseThrow().map(mapper));
        return new InfiniteListImpl<R>(newHead, newTail);
    }

    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> pred) {
        Lazy<T> newHead = head.filter(pred);
        Lazy<InfiniteListImpl<T>> newTail = Lazy.generate(() -> tail.get().orElseThrow().filter(pred));
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    @Override
    public InfiniteListImpl<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<T>();
        } else if (n == 1) {
            return new InfiniteListImpl<T>(head, Lazy.generate(() -> head.get().isPresent()
                        ? new EmptyList<>()
                        : tail.get().orElseThrow().limit(n)));
        } else {
            return new InfiniteListImpl<T>(head, tail.map(x -> head.get().isPresent()
                        ? x.limit(n - 1)
                        : x.limit(n)));
        }
    }
    @Override
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> pred) {
        Lazy<T> filtered = head.filter(pred);
        return new InfiniteListImpl<>(filtered, Lazy.generate(() -> head.get().isPresent() && filtered.get().isEmpty()
                    ? new EmptyList<T>()
                    : tail.get().orElseThrow().takeWhile(pred)));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> curr = this;
        while(!curr.isEmpty()) {
            curr.head.get().ifPresent(action);
            curr = curr.tail.get().orElseThrow();
        }
    }
    @Override
    public Object[] toArray() {
        ArrayList<Object> list = new ArrayList<>();
        forEach(x -> list.add(x));
        return list.toArray();
    }

    @Override
    public long count() {
        return map(x -> 1L).reduce(0L, (x, y) -> x + y);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> result = head.get();
        InfiniteListImpl<T> curr = tail.get().orElseThrow();
        while (!curr.isEmpty()) {
            Optional<T> head = curr.head.get();
            if (result.isEmpty()) {
                result = head;
            } else if (head.isPresent()) {
                result = Optional.ofNullable(accumulator.apply(result.orElseThrow(), head.orElseThrow()));
            }
            curr = curr.tail.get().orElseThrow();
        }
        return result;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        U result = identity;
        InfiniteListImpl<T> curr = this;
        while(!curr.isEmpty()) {
            Optional<T> head = curr.head.get();
            if (head.isPresent()) {
                result = accumulator.apply(result, head.orElseThrow());
            }
            curr = curr.tail.get().orElseThrow();
        }
        return result;
    }
}
