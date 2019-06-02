import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.IntRange

class Idempotent {
    @Property
    void 'trim is idempotent'(
            @ForAll String s,
            @ForAll @IntRange(max=10) Integer i
    ) {
        def once = s.trim()
        def many = once
        i.times { many = many.trim() }
        assert once == many
    }

    @Property
    void 'unique is idempotent'(
            @ForAll List<@IntRange(mix=0, max=100) Integer> items,
            @ForAll @IntRange(max=10) Integer i
    ) {
        def once = items.toUnique()
        def many = once
        i.times { many = many.toUnique() }
        assert once == many
    }

//    @Property
    void 'map putAll is idempotent'(
            // TODO
    ) {
/*
        def once = initial.putAll(items)
        def many = once
        i.times { many = many.putAll(items) }
        assert once == many
*/
    }

    @Property
    void 'sort is idempotent'(
            @ForAll List<@IntRange(mix=0, max=100) Integer> items,
            @ForAll @IntRange(max=10) Integer i
    ) {
        def once = items.sort()
        def many = once
        i.times { many = many.sort() }
        assert once == many
    }
}
