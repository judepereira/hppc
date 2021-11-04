/*! #set($TemplateOptions.ignored = ($TemplateOptions.isKTypeAnyOf("DOUBLE", "FLOAT", "BYTE"))) !*/
package com.carrotsearch.hppc;

/**
 * Same tests as those in {@link KTypeVTypeHashMap}, but with {@link KTypeVTypeHashMap#concurrent} set to true.
 */
/*! ${TemplateOptions.generatedAnnotation} !*/
public class KTypeVTypeConcurrentHashMapTest<KType, VType> extends KTypeVTypeHashMapTest<KType, VType> {

  @Override
  protected KTypeVTypeHashMap<KType, VType> newInstance() {
    return new KTypeVTypeHashMap<>(true);
  }
}
