
import net.jqwik.api.ForAll
import net.jqwik.api.Property

class Fuzzying {
    @Property
    void 'can trim any string'(@ForAll String s) {
        s.trim()
    }
}
