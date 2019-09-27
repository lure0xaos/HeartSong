package gargoyle.heartsong.model.app;

import java.util.Arrays;

public enum Letter {
    OTHER('#'),
    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H'),
    I('I'),
    J('J'),
    K('K'),
    L('L'),
    M('M'),
    N('N'),
    O('O'),
    P('P'),
    Q('Q'),
    R('R'),
    S('S'),
    T('T'),
    U('U'),
    V('V'),
    W('W'),
    X('X'),
    Y('Y'),
    Z('Z'),
    ;

    private final char chr;

    Letter(char chr) {
        this.chr = chr;
    }

    public static Letter forTitle(String title) {
        return Arrays.stream(values())
                .filter(letter -> !title.isEmpty() && letter.chr == Character.toUpperCase(title.charAt(0)))
                .findFirst()
                .orElse(OTHER);
    }

    public static Letter forChar(String chr) {
        return Arrays.stream(values())
                .filter(letter -> !chr.isEmpty() && letter.chr == Character.toUpperCase(chr.charAt(0)))
                .findFirst()
                .orElse(OTHER);
    }

    public char getChr() {
        return chr;
    }
}
