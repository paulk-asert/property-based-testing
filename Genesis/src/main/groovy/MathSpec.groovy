//@Grab('com.nagternal:spock-genesis:0.6.0;transitive=false')
//@Grab('org.spockframework:spock-core:1.2-groovy-2.5;transitive=false')
import spock.genesis.Gen
import spock.genesis.transform.Iterations
import spock.lang.Specification
//import static java.lang.Math.round

class MathSpec extends Specification {
    @Iterations(100)
    def "test int to double"() {
        given:
            def d = i.toDouble()
        expect:
            Math.rint(d) == d
        where:
            i << Gen.integer
    }

    @Iterations(100)
    def "test long to double"() {
        given:
            def d = l.toDouble()
        expect:
            Math.rint(d) == d
        where:
            l << Gen.long
    }

    @Iterations(100)
    def "test BigInteger to double"() {
        given:
            def d = bi.toDouble()
        expect:
            Math.rint(d) == d
        where:
            l << Gen.bi
    }
}
