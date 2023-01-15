package project_16x16;

public enum DebugType {
    OFF, ALL, INFO_ONLY;

    private static DebugType[] vals = values();

    public DebugType next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static DebugType get(int value) {
        return values()[value];
    }
}
