import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.IntRange

class Invariant {
    @Property
    boolean 'sort preserves size'(
            @ForAll List<String> items
    ) {
        items.size() == items.sort().size()
    }

    @Property
    boolean 'collect preserves size'(
            @ForAll List<String> items
    ) {
        items.size() == items.collect{ it.size() }.size()
    }
}
