package org.journerist.testutils.parseStrategy;

import org.journerist.testutils.parseStrategy.interfaces.ConstraintTagStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleTagHibernateConstraintStrategy extends RegexpHibernateConstraintTagStrategy {

    private static final String regexp = "<(?<relation>one-to-one|many-to-one)(\\d|[a-zA-Z]|\\=|\\\"|\\s|\\-|\\.|\\n|\\r\\n)*?class=\"(?<entityClass>(\\w|\\.)*)\"(\\d|\\w|\\=|\\\"|\\s|\\-|\\.|\\n|\\r\\n)*?(\\/>)";

    @Override
    protected String getRegexpString() {
        return regexp;
    }

}
