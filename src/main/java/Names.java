import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Names {
    private final List<Name> value;

    public Names(List<String> names) {
        validate(names);
        this.names = from(names);
    }

    private List<Name> from(List<String> names) {
        return names.stream()
                    .map(Name::new)
                    .toList();
    }

    private void validate(List<String> value) {
        validateDuplicated(value);
    }

    private void validateDuplicated(List<String> names) {
        Set<String> duplicateSize = new HashSet<>(names);
        if (duplicateSize.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 포함되어 있습니다.");
        }
    }

    public List<Name> getValue() {
        return names;
    }
}
