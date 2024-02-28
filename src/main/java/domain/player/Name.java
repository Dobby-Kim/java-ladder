package domain.player;

public record Name(String value) {
    private static final int MAXIMUM_NAME_LENGTH = 5;

    public Name {
        validate(value);
    }

    private void validate(String inputName) {
        validateAvailableLength(inputName);
        validateBlank(inputName);
        validateContainBlankInName(inputName);
    }

    private void validateAvailableLength(String inputName) {
        if (inputName.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("입력값은 5자를 초과할 수 없습니다.");
        }
    }

    private void validateBlank(String initialInput) {
        if (initialInput.isBlank()) {
            throw new IllegalArgumentException("공백으로 이루어진 이름은 입력할 수 없습니다.");
        }
    }

    private void validateContainBlankInName(String inputName) {
        if (inputName.contains(" ")) {
            throw new IllegalArgumentException("중간에 공백이 포함된 이름은 입력할 수 없습니다.");
        }
    }

    public boolean isAll() {
        return value.equals("all");
    }

    @Override
    public String toString() {
        return this.value;
    }

}
