import net.jqwik.api.ForAll
import net.jqwik.api.Property
//import net.jqwik.api.constraints.IntRange

class RoundTrip {
    @Property
    void 'sort preserves size'(
            @ForAll String s
    ) {
//        def padding = '='
//        def hasEvenLength = s.size() % 2 == 0
        def bytes = s.bytes.encodeBase64().toString().decodeBase64()
        assert s == new String(bytes)
    }
}
