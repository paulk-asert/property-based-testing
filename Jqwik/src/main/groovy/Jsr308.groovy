//@Grab('net.jqwik:jqwik:1.1.4')
import groovy.transform.CompileStatic
import net.jqwik.api.ForAll
import net.jqwik.api.Property
//import net.jqwik.api.Report
//import net.jqwik.api.Reporting
import net.jqwik.api.constraints.IntRange;

@CompileStatic
class Jsr308 {
    @Property
//    @Report([Reporting.GENERATED, Reporting.FALSIFIED])
    boolean 'size zero or positive'(
            @ForAll List<@IntRange(min=0, max=10) Integer> items
    ) {
        items.size() >= 0
    }
}
/*
    RuntimeVisibleAnnotations:
      0: #31()
    RuntimeVisibleParameterAnnotations:
      parameter 0:
        0: #32()
*/
