package org.journerist.testutils.parseStrategy;

import org.journerist.testutils.parseStrategy.interfaces.ConstraintTagStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiLineHibernateConstraintTagStrategy extends RegexpHibernateConstraintTagStrategy {

    private static final String regexp = "<set(.|\\n)*?<(?<relation>\\w+-\\w+-\\w+).*(class|entity-name)=\"(?<entityClass>(\\w|\\.)*)\"(.|\\n)*?<\\/set>";

    @Override
    protected String getRegexpString() {
        return regexp;
    }
}
