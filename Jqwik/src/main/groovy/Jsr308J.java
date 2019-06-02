import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

import java.util.List;
//import net.jqwik.api.Report;
//import net.jqwik.api.Reporting;

public class Jsr308J {
    @Property
//    @Report({Reporting.GENERATED, Reporting.FALSIFIED})
    public boolean sizeZeroOrPositive(
            @ForAll List<@IntRange(min=0, max=10) Integer> items
    ) {
        return items.size() >= 0;
    }
}
/*
    RuntimeVisibleAnnotations:
      0: #22()
    RuntimeVisibleTypeAnnotations:
      0: #24(#25=I#26,#27=I#28): METHOD_FORMAL_PARAMETER, param_index=0, location=[TYPE_ARGUMENT(0)]
    RuntimeVisibleParameterAnnotations:
      parameter 0:
        0: #30()
*/