import spock.genesis.Gen
import spock.genesis.transform.Iterations
import spock.lang.Ignore
import spock.lang.Specification

class MathSpec extends Specification {
    @Iterations(10)
    def "an int converted to a double should have no fractional part"() {
        given:
        def d = i.toDouble()
        expect:
        Math.rint(d) == d
        where:
        i << Gen.integer
    }

    @Iterations(10)
    def "addition is commutative for integer"() {
        expect:
        a + b == b + a
        where:
        a << Gen.integer
        b << Gen.integer
    }

    @Iterations(10)
    def "multiplication is commutative for integer"() {
        expect:
        a * b == b * a
        where:
        a << Gen.integer
        b << Gen.integer
    }

    @Iterations(5)
    @Ignore('enable to see counter-examples')
    def "subtraction is not commutative for integer"() {
        expect:
        a - b == b - a
        where:
        a << Gen.integer
        b << Gen.integer
    }

    def "subtraction is not commutative example"() {
        expect:
        4 - 2 != 2 - 4
    }

    def "subtraction is not commutative property"() {
        given:
        def ita = Gen.integer.iterator()
        def itb = Gen.integer.iterator()
        expect:
        (1..10).any {
            def a = ita.next()
            def b = itb.next()
            a - b != b - a
        }
    }

    @Iterations(10)
    def "subtraction is not commutative but is ignoring sign"() {
        expect:
        (a - b).abs() == (b - a).abs()
        where:
        a << Gen.integer
        b << Gen.integer
    }

    @Iterations(10)
    def "addition is associative for integer"() {
        expect:
        (a + b) + c == a + (b + c)
        where:
        a << Gen.integer
        b << Gen.integer
        c << Gen.integer
    }

    @Iterations(10)
    def "zero is the identity for integer"() {
        expect:
        a + 0 == a
        0 + a == a
        where:
        a << Gen.integer
    }

    @Iterations(10)
    def "subtracting any integer from itself gives zero"() {
        expect:
        a - a == 0
        where:
        a << Gen.integer
    }
}
