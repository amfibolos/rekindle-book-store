/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.rekindle.book.store.kafka.avro.model;
@org.apache.avro.specific.AvroGenerated
public enum OrderApprovalStatus implements org.apache.avro.generic.GenericEnumSymbol<OrderApprovalStatus> {
  APPROVED, REJECTED  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"OrderApprovalStatus\",\"namespace\":\"com.rekindle.book.store.kafka.order.avro.model\",\"symbols\":[\"APPROVED\",\"REJECTED\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
