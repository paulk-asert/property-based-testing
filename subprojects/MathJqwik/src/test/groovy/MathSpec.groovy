import net.jqwik.api.Arbitraries
import net.jqwik.api.Disabled
import net.jqwik.api.ForAll
import net.jqwik.api.Example
import net.jqwik.api.Property

class MathSpec {
    @Property
    def "an int converted to a double should have no fractional part"(@ForAll Integer i) {
        def d = i.toDouble()
        assert Math.rint(d) == d
    }

    @Property
    def "addition is commutative for integer"(@ForAll Integer a, @ForAll Integer b) {
        assert a + b == b + a
    }

    @Property
    def "multiplication is commutative for integer"(@ForAll Integer a, @ForAll Integer b) {
        assert a * b == b * a
    }

    @Property
    @Disabled('enable to see counter-examples')
    def "subtraction is not commutative for integer"(@ForAll Integer a, @ForAll Integer b) {
        assert a - b == b - a
    }

    @Example
    boolean "subtraction is not commutative example"() {
        4 - 2 != 2 - 4
    }

    @Example
    void "subtraction is not commutative property"() {
        def r = new Random()
        def ita = Arbitraries.integers().generator(1)
        def itb = Arbitraries.integers().generator(2)
        assert (1..10).any {
            def a = ita.next(r).value()
            def b = itb.next(r).value()
            a - b != b - a
        }
    }

    @Property
    def "subtraction is not commutative but is ignoring sign"(@ForAll Integer a, @ForAll Integer b) {
        assert (a - b).abs() == (b - a).abs()
    }

    @Property
    def "addition is associative for integer"(@ForAll Integer a, @ForAll Integer b, @ForAll Integer c) {
        assert (a + b) + c == a + (b + c)
    }

    @Property
    def "zero is the identity for integer"(@ForAll Integer a) {
        assert a + 0 == a
        assert 0 + a == a
    }

    @Property
    def "subtracting any integer from itself gives zero"(@ForAll Integer a) {
        assert a - a == 0
    }
}
