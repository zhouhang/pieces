package com.pieces.service.utils.predicate;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.collections.functors.NullPredicate;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created by wangbin on 2016/8/12.
 */
public class RegularPredicate implements Predicate, Serializable {

    private static final long serialVersionUID = 5633766978029907089L;
    private Pattern pattern;

    public static Predicate getInstance(Object object) {
        return (Predicate)(object == null? NullPredicate.INSTANCE:new EqualPredicate(object));
    }

    public RegularPredicate(Pattern pattern) {
        this.pattern=pattern;
    }

    public boolean evaluate(Object object) {
       return pattern.matcher(object.toString()).matches();
    }


}
