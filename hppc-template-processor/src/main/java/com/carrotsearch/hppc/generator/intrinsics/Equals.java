/*
 * HPPC
 *
 * Copyright (C) 2010-2020 Carrot Search s.c.
 * All rights reserved.
 *
 * Refer to the full license file "LICENSE.txt":
 * https://github.com/carrotsearch/hppc/blob/master/LICENSE.txt
 */
package com.carrotsearch.hppc.generator.intrinsics;

import com.carrotsearch.hppc.generator.TemplateOptions;
import com.carrotsearch.hppc.generator.Type;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Equals extends AbstractIntrinsicMethod {
  @Override
  public void invoke(
      Matcher m,
      StringBuilder sb,
      TemplateOptions templateOptions,
      String genericCast,
      ArrayList<String> arguments) {

    if (arguments.size() != 2 && arguments.size() != 3) {
      throw new RuntimeException(
          format(
              "Expected exactly 2 or 3 arguments but was %d: %s(%s)",
              arguments.size(), m.group(), arguments));
    }

    String v2 = arguments.remove(arguments.size() - 1);
    String v1 = arguments.remove(arguments.size() - 1);

    Type type = inferTemplateType(m, templateOptions, genericCast);
    switch (type) {
      case GENERIC:
        String comparer = arguments.isEmpty() ? "java.util.Objects" : arguments.get(0);
        sb.append(format("%s.equals(%s, %s)", comparer, v1, v2));
        break;

      case FLOAT:
        sb.append(format("(Float.floatToIntBits(%s) == Float.floatToIntBits(%s))", v1, v2));
        break;

      case DOUBLE:
        sb.append(format("(Double.doubleToLongBits(%s) == Double.doubleToLongBits(%s))", v1, v2));
        break;

      case BYTE:
      case SHORT:
      case CHAR:
      case INT:
      case LONG:
        sb.append(format("((%s) == (%s))", v1, v2));
        break;

      default:
        throw unreachable();
    }
  }
}
