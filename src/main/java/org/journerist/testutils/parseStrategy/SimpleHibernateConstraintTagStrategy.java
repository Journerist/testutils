package org.journerist.testutils.parseStrategy;

import org.journerist.testutils.parseStrategy.interfaces.ConstraintTagStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleHibernateConstraintTagStrategy extends RegexpHibernateConstraintTagStrategy {

    private static final String regexp = "<(?<relation>one-to-one|many-to-one)(.|\\n)*?class=\"(?<entityClass>(\\w|\\.)*)\"(.|\\n)*?(<\\/)?(one-to-one|many-to-one)?>(<\\/one-to-one\\>|many-to-one\\>)?";

    @Override
    protected String getRegexpString() {
        return regexp;
    }

}
