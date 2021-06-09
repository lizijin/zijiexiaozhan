package com.xianlai.ad.plugin;

import java.util.List;
import java.util.Map;

/**
 * @author xc
 * @time 19-3-1.
 */
public class AddTryCatchExtension {
    public Map<String, List<String>> hookPoint;

    public Map<String, String> exceptionHandler;

    @Override
    public String toString() {
        return "AddTryCatchExtension{" +
                "hookPoint=" + hookPoint +
                ", exceptionHandler=" + exceptionHandler +
                '}';
    }
}
