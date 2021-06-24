package org.schemaspy.output.dot.schemaspy.relatives;

import java.util.HashSet;
import java.util.Set;
import org.schemaspy.model.TableColumn;

public final class Include implements ExclusionFilter {

    private final Columns columns;

    public Include(final Columns columns) {
        this.columns = columns;
    }

    public Iterable<Verdict> children() {
        Set<Verdict> result = new HashSet<>();
        for (TableColumn column : this.columns.value()) {
            for (TableColumn childColumn : column.getChildren()) {
                if (childColumn.isAllExcluded()) {
                    continue;
                }
                result.add(new Verdict(column, childColumn));
            }
        }
        return result;
    }

    public Iterable<Verdict> parents() {
        Set<Verdict> result = new HashSet<>();
        for (TableColumn column : this.columns.value()) {
            for (TableColumn parentColumn : column.getParents()) {
                if (parentColumn.isAllExcluded()) {
                    continue;
                }
                result.add(new Verdict(column, parentColumn));
            }
        }
        return result;
    }
}
