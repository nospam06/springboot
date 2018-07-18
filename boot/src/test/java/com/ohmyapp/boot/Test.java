package com.ohmyapp.boot;

/**
 * Emerald on 11/18/2016.
 */
public class Test {
    // valid format 9999.99, 9.000E09, -1111.99, -9.99E-1, 999.9E9- "", -E, -., -E, 1-, 9.9E
    public boolean isNumber(String input) {
        boolean minus = false;
        boolean exp = false;
        boolean minusexp = false;
        boolean dec = false;
        boolean digitExists = false;
        int count = 0;
        int expCount = 1;
        if (input.isEmpty()) {
            return false;
        }
        if (input.endsWith("E")) {
            return false;
        }
        for (char digit : input.toCharArray()) {
            int pos = "-.E0123456789".indexOf(digit);
            if (pos < 0) {
                return false;
            }
            if (pos > 2) {
                digitExists = true;
            }
            if (pos == 0) {
                if (!exp) {
                    if (minus) {
                        return false;
                    } else {
                        if (count> 0) {
                            return false;
                        }
                        minus = true;
                    }
                } else {
                    if (minusexp) {
                        return false;
                    } else {
                        if (expCount > 0) {
                            return false;
                        }
                        ++expCount;
                        minusexp = true;
                    }
                }
            }
            if (pos == 1) {
                if (dec) {
                    return false;
                } else {
                    dec = true;
                }

            }
            if (pos == 2) {
                if (exp) {
                    return false;
                } else {
                    exp = true;
                    expCount = 0;
                }
            }
            ++count;
        }
        return digitExists;
    }
}
