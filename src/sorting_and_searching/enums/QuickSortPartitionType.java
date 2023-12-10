package sorting_and_searching.enums;

public enum QuickSortPartitionType {
    LAST_AS_PIVOT_METHOD1("Last element as pivot with method 1"),
    LAST_AS_PIVOT_METHOD2("Last element as pivot with method 2"),
    ;

    private final String optionName;

    QuickSortPartitionType(String optionName) {
        this.optionName = optionName;
    }

    @Override
    public String toString() {
        return optionName;
    }
}
