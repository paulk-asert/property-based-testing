//@Grab('net.jqwik:jqwik:1.1.3')
import net.jqwik.api.*

import java.math.RoundingMode

import static java.lang.Math.rint

//import net.jqwik.api.constraints.*

class MathTest {
    @Property
//    @Report(Reporting.GENERATED)
    boolean 'absolute value of all numbers is positive'(@ForAll int anInteger) {
//    boolean 'absolute value of all -ve numbers is positive'(@ForAll @Positive int anInteger) {
        Math.abs(anInteger) >= 0
//        Math.abs(-anInteger) >= 0
//        Math.abs(anInteger) >= 0 || anInteger == Integer.MIN_VALUE
    }

    @Property
//    @Report(Reporting.FALSIFIED)
    void 'round up should be greater than round down'(
        @ForAll Double d
    ) {
        BigDecimal bd = new BigDecimal(d);
        def up = bd.setScale(2, RoundingMode.HALF_UP)
        def down = bd.setScale(2, RoundingMode.HALF_DOWN)
        assert up >= down
    }

    @Property
//    @Report(Reporting.FALSIFIED)
    void 'p should be greater than round down'(
        @ForAll Double d
    ) {
        BigDecimal bd = new BigDecimal(d);
        def up = bd.setScale(2, RoundingMode.UP)
        def down = bd.setScale(2, RoundingMode.HALF_UP)
        assert Math.abs(up) >= Math.abs(down)
    }
}