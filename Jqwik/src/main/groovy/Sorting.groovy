import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Report
import net.jqwik.api.Reporting
import net.jqwik.api.constraints.IntRange

class Sorting {
    @Property
    boolean 'sort preserves size'(
            @ForAll List<String> items
    ) {
        items.size() == items.sort().size()
    }

    @Property
    void 'sort is idempotent'(
            @ForAll List<@IntRange(min=0, max=100) Integer> items,
            @ForAll @IntRange(max=10) Integer i
    ) {
        def once = items.sort()
        def many = once
        i.times { many = many.sort() }
        assert once == many
    }

    @Property
    void 'sort actually sorts'(
            @ForAll List<@IntRange(mix=0, max=100) Integer> items
    ) {
        def sorted = items.sort()
        assert sorted.collate(2, 1, false).every { first, second ->
            first <= second
        }
    }

    @Property
    @Report([Reporting.GENERATED, Reporting.FALSIFIED])
    void 'show broken sort detected'(
            @ForAll List<@IntRange(mix=0, max=100) Integer> items
    ) {
        def sorted = brokenSort(items)
        assert sorted.toSorted() == sorted
    }

    def brokenSort(List items) {
        if (items.size() == 2) return items
        items.sort()
    }
}
