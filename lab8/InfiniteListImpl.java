import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.Optional;
import java.util.ArrayList;

public abstract class InfiniteListImpl<T> implements InfiniteList<T> {

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return Optional.ofNullable(s.get());
            }
            public boolean hasNext() {
                return true;
            }
        };
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, Function<T, T> next) {
        return new InfiniteListImpl<T>() {
            T element = seed;
            boolean first = true;
            public Optional<T> get() {
                if (first) {
                    first = false;
                } else {
                    element = next.apply(element);
                }
                return Optional.ofNullable(element);
            }

            public boolean hasNext() {
                return true;
            }

        };
    }

    public void forEach(Consumer<? super T> action) {
        while (hasNext()) {
            get().ifPresent(action);
        }
    }

    public InfiniteListImpl<T> limit(long maxSize) throws IllegalArgumentException {
        if (maxSize < 0) {
            throw new IllegalArgumentException(String.format("%d", maxSize));
        }

        return new InfiniteListImpl<T>() {
            long count = 0;
            long limit = maxSize;

            public Optional<T> get() {
                Optional<T> curr = InfiniteListImpl.this.get();
                if (count < limit && curr.isPresent()) {
                    count++;
                    return curr;
                } else {
                    return Optional.empty();
                }
            }

            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext() && (count < limit);
            }
        };

    }

    public Object[] toArray() {
        ArrayList<Object> list = new ArrayList<>();
        forEach(x -> list.add(x));
        return list.toArray();
    }

    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        return new InfiniteListImpl<R>() {
            public Optional<R> get() {
                return InfiniteListImpl.this.get().map(mapper);
            }
            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext();
            }
        };
    }

    public InfiniteListImpl<T> filter(Predicate<? super T> pred) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return InfiniteListImpl.this.get().filter(pred);
            }
            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext();
            }
        };
    }

    public InfiniteList<T> takeWhile(Predicate<? super T> pred) {
        return new InfiniteListImpl<T>() {
            private Optional<T> next = InfiniteListImpl.this.get();

            public Optional<T> get() {
                Optional<T> curr = next;
                next = InfiniteListImpl.this.get();
                return curr;
            }

            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext() && next.filter(pred).isPresent();
            }
        };
    }

    public long count() {
        return map(x -> 1L).reduce(0L, (x, y) -> x + y);
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> result = InfiniteListImpl.this.get();
        while (hasNext()) {
            result = result.map(x -> accumulator.apply(x, InfiniteListImpl.this.get().get()));
        }
        return result;
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T result = identity;
        while (hasNext()) {
            Optional<T> next = get();
            if (next.isPresent()) {
                result = accumulator.apply(result, next.get());
            }
        }
        return result;
    }

    public abstract Optional<T> get();

    public abstract boolean hasNext();

}
