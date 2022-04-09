package io.github.vitalikulsha.javawebproject.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Application configuration parameters
 */
public class ConfigParameter {
    public static final int ITEMS_ON_PAGE = 5;
    public static final String ENCODING = "UTF-8";
    public static final String CONTENT_TYPE= "text/html; charset=UTF-8";

    private ConfigParameter() {
    }

    public static List<Integer> getPages(int count){
        int numPage = (count / ConfigParameter.ITEMS_ON_PAGE)
                + (count % ConfigParameter.ITEMS_ON_PAGE == 0 ? 0 : 1);
        return IntStream.range(1, numPage + 1)
                .boxed()
                .collect(Collectors.toList());
    }
}
