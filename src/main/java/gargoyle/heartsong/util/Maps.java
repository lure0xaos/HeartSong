package gargoyle.heartsong.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Maps {
    private Maps() {
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> of(Object... args) {
        int argsLength = args.length;
        Map<K, V> map = new HashMap<>(argsLength / 2);
        for (int i = 0; i < argsLength; i += 2) {
            map.put((K) args[i], (V) args[i + 1]);
        }
        return Collections.unmodifiableMap(map);
    }

    @SuppressWarnings("TypeMayBeWeakened")
    public static <R, T> List<R> map(List<? extends T> list, Function<T, ? extends R> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }
}
