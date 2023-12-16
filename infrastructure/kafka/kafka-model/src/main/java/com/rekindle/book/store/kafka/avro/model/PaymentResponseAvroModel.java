/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package com.rekindle.book.store.kafka.avro.model;


import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@org.apache.avro.specific.AvroGenerated
public class PaymentResponseAvroModel extends org.apache.avro.specific.SpecificRecordBase implements
    org.apache.avro.specific.SpecificRecord {

  private static final long serialVersionUID = -717442760865654103L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse(
      "{\"type\":\"record\",\"name\":\"PaymentResponseAvroModel\",\"namespace\":\"com.rekindle.book.store.kafka.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"paymentId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"paymentStatus\",\"type\":{\"type\":\"enum\",\"name\":\"PaymentStatus\",\"symbols\":[\"COMPLETED\",\"CANCELLED\",\"FAILED\"]}},{\"name\":\"failureMessages\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]}");

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  private static final SpecificData MODEL$ = new SpecificData();

  static {
    MODEL$.addLogicalTypeConversion(
        new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<PaymentResponseAvroModel> ENCODER =
      new BinaryMessageEncoder<PaymentResponseAvroModel>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PaymentResponseAvroModel> DECODER =
      new BinaryMessageDecoder<PaymentResponseAvroModel>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PaymentResponseAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PaymentResponseAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PaymentResponseAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PaymentResponseAvroModel>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PaymentResponseAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PaymentResponseAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PaymentResponseAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PaymentResponseAvroModel fromByteBuffer(
      java.nio.ByteBuffer b
  ) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private String id;
  private String sagaId;
  private String paymentId;
  private String customerId;
  private String orderId;
  private java.math.BigDecimal price;
  private java.time.Instant createdAt;
  private PaymentStatus paymentStatus;
  private java.util.List<String> failureMessages;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PaymentResponseAvroModel() {
  }

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param sagaId The new value for sagaId
   * @param paymentId The new value for paymentId
   * @param customerId The new value for customerId
   * @param orderId The new value for orderId
   * @param price The new value for price
   * @param createdAt The new value for createdAt
   * @param paymentStatus The new value for paymentStatus
   * @param failureMessages The new value for failureMessages
   */
  public PaymentResponseAvroModel(
      String id, String sagaId, String paymentId, String customerId, String orderId,
      java.math.BigDecimal price, java.time.Instant createdAt, PaymentStatus paymentStatus,
      java.util.List<String> failureMessages
  ) {
    this.id = id;
    this.sagaId = sagaId;
    this.paymentId = paymentId;
    this.customerId = customerId;
    this.orderId = orderId;
    this.price = price;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
    this.paymentStatus = paymentStatus;
    this.failureMessages = failureMessages;
  }

  public SpecificData getSpecificData() {
    return MODEL$;
  }

  public org.apache.avro.Schema getSchema() {
    return SCHEMA$;
  }

  // Used by DatumWriter.  Applications should not call.
  public Object get(int field$) {
    switch (field$) {
      case 0:
        return id;
      case 1:
        return sagaId;
      case 2:
        return paymentId;
      case 3:
        return customerId;
      case 4:
        return orderId;
      case 5:
        return price;
      case 6:
        return createdAt;
      case 7:
        return paymentStatus;
      case 8:
        return failureMessages;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[]{
          null,
          null,
          null,
          null,
          null,
          new org.apache.avro.Conversions.DecimalConversion(),
          new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
          null,
          null,
          null
      };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$, Object value$) {
    switch (field$) {
      case 0:
        id = value$ != null ? value$.toString() : null;
        break;
      case 1:
        sagaId = value$ != null ? value$.toString() : null;
        break;
      case 2:
        paymentId = value$ != null ? value$.toString() : null;
        break;
      case 3:
        customerId = value$ != null ? value$.toString() : null;
        break;
      case 4:
        orderId = value$ != null ? value$.toString() : null;
        break;
      case 5:
        price = (java.math.BigDecimal) value$;
        break;
      case 6:
        createdAt = (java.time.Instant) value$;
        break;
      case 7:
        paymentStatus = (PaymentStatus) value$;
        break;
      case 8:
        failureMessages = (java.util.List<String>) value$;
        break;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public String getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'sagaId' field.
   * @return The value of the 'sagaId' field.
   */
  public String getSagaId() {
    return sagaId;
  }


  /**
   * Sets the value of the 'sagaId' field.
   * @param value the value to set.
   */
  public void setSagaId(String value) {
    this.sagaId = value;
  }

  /**
   * Gets the value of the 'paymentId' field.
   * @return The value of the 'paymentId' field.
   */
  public String getPaymentId() {
    return paymentId;
  }


  /**
   * Sets the value of the 'paymentId' field.
   * @param value the value to set.
   */
  public void setPaymentId(String value) {
    this.paymentId = value;
  }

  /**
   * Gets the value of the 'customerId' field.
   * @return The value of the 'customerId' field.
   */
  public String getCustomerId() {
    return customerId;
  }


  /**
   * Sets the value of the 'customerId' field.
   * @param value the value to set.
   */
  public void setCustomerId(String value) {
    this.customerId = value;
  }

  /**
   * Gets the value of the 'orderId' field.
   * @return The value of the 'orderId' field.
   */
  public String getOrderId() {
    return orderId;
  }


  /**
   * Sets the value of the 'orderId' field.
   * @param value the value to set.
   */
  public void setOrderId(String value) {
    this.orderId = value;
  }

  /**
   * Gets the value of the 'price' field.
   * @return The value of the 'price' field.
   */
  public java.math.BigDecimal getPrice() {
    return price;
  }


  /**
   * Sets the value of the 'price' field.
   * @param value the value to set.
   */
  public void setPrice(java.math.BigDecimal value) {
    this.price = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Gets the value of the 'paymentStatus' field.
   * @return The value of the 'paymentStatus' field.
   */
  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }


  /**
   * Sets the value of the 'paymentStatus' field.
   * @param value the value to set.
   */
  public void setPaymentStatus(PaymentStatus value) {
    this.paymentStatus = value;
  }

  /**
   * Gets the value of the 'failureMessages' field.
   * @return The value of the 'failureMessages' field.
   */
  public java.util.List<String> getFailureMessages() {
    return failureMessages;
  }


  /**
   * Sets the value of the 'failureMessages' field.
   * @param value the value to set.
   */
  public void setFailureMessages(java.util.List<String> value) {
    this.failureMessages = value;
  }

  /**
   * Creates a new PaymentResponseAvroModel RecordBuilder.
   * @return A new PaymentResponseAvroModel RecordBuilder
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Creates a new PaymentResponseAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PaymentResponseAvroModel RecordBuilder
   */
  public static Builder newBuilder(Builder other) {
    if (other == null) {
      return new Builder();
    } else {
      return new Builder(other);
    }
  }

  /**
   * Creates a new PaymentResponseAvroModel RecordBuilder by copying an existing PaymentResponseAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new PaymentResponseAvroModel RecordBuilder
   */
  public static Builder newBuilder(PaymentResponseAvroModel other) {
    if (other == null) {
      return new Builder();
    } else {
      return new Builder(other);
    }
  }

  /**
   * RecordBuilder for PaymentResponseAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends
      org.apache.avro.specific.SpecificRecordBuilderBase<PaymentResponseAvroModel>
      implements org.apache.avro.data.RecordBuilder<PaymentResponseAvroModel> {

    private String id;
    private String sagaId;
    private String paymentId;
    private String customerId;
    private String orderId;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;
    private PaymentStatus paymentStatus;
    private java.util.List<String> failureMessages;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.paymentId)) {
        this.paymentId = data().deepCopy(fields()[2].schema(), other.paymentId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.customerId)) {
        this.customerId = data().deepCopy(fields()[3].schema(), other.customerId);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.orderId)) {
        this.orderId = data().deepCopy(fields()[4].schema(), other.orderId);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.price)) {
        this.price = data().deepCopy(fields()[5].schema(), other.price);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[6].schema(), other.createdAt);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.paymentStatus)) {
        this.paymentStatus = data().deepCopy(fields()[7].schema(), other.paymentStatus);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (isValidValue(fields()[8], other.failureMessages)) {
        this.failureMessages = data().deepCopy(fields()[8].schema(), other.failureMessages);
        fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
    }

    /**
     * Creates a Builder by copying an existing PaymentResponseAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(PaymentResponseAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.paymentId)) {
        this.paymentId = data().deepCopy(fields()[2].schema(), other.paymentId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.customerId)) {
        this.customerId = data().deepCopy(fields()[3].schema(), other.customerId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.orderId)) {
        this.orderId = data().deepCopy(fields()[4].schema(), other.orderId);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.price)) {
        this.price = data().deepCopy(fields()[5].schema(), other.price);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[6].schema(), other.createdAt);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.paymentStatus)) {
        this.paymentStatus = data().deepCopy(fields()[7].schema(), other.paymentStatus);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.failureMessages)) {
        this.failureMessages = data().deepCopy(fields()[8].schema(), other.failureMessages);
        fieldSetFlags()[8] = true;
      }
    }

    /**
     * Gets the value of the 'id' field.
     * @return The value.
     */
    public String getId() {
      return id;
    }


    /**
     * Sets the value of the 'id' field.
     * @param value The value of 'id'.
     * @return This builder.
     */
    public Builder setId(String value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
     * Checks whether the 'id' field has been set.
     * @return True if the 'id' field has been set, false otherwise.
     */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
     * Clears the value of the 'id' field.
     * @return This builder.
     */
    public Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
     * Gets the value of the 'sagaId' field.
     * @return The value.
     */
    public String getSagaId() {
      return sagaId;
    }


    /**
     * Sets the value of the 'sagaId' field.
     * @param value The value of 'sagaId'.
     * @return This builder.
     */
    public Builder setSagaId(String value) {
      validate(fields()[1], value);
      this.sagaId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
     * Checks whether the 'sagaId' field has been set.
     * @return True if the 'sagaId' field has been set, false otherwise.
     */
    public boolean hasSagaId() {
      return fieldSetFlags()[1];
    }


    /**
     * Clears the value of the 'sagaId' field.
     * @return This builder.
     */
    public Builder clearSagaId() {
      sagaId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
     * Gets the value of the 'paymentId' field.
     * @return The value.
     */
    public String getPaymentId() {
      return paymentId;
    }


    /**
     * Sets the value of the 'paymentId' field.
     * @param value The value of 'paymentId'.
     * @return This builder.
     */
    public Builder setPaymentId(String value) {
      validate(fields()[2], value);
      this.paymentId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
     * Checks whether the 'paymentId' field has been set.
     * @return True if the 'paymentId' field has been set, false otherwise.
     */
    public boolean hasPaymentId() {
      return fieldSetFlags()[2];
    }


    /**
     * Clears the value of the 'paymentId' field.
     * @return This builder.
     */
    public Builder clearPaymentId() {
      paymentId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
     * Gets the value of the 'customerId' field.
     * @return The value.
     */
    public String getCustomerId() {
      return customerId;
    }


    /**
     * Sets the value of the 'customerId' field.
     * @param value The value of 'customerId'.
     * @return This builder.
     */
    public Builder setCustomerId(String value) {
      validate(fields()[3], value);
      this.customerId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
     * Checks whether the 'customerId' field has been set.
     * @return True if the 'customerId' field has been set, false otherwise.
     */
    public boolean hasCustomerId() {
      return fieldSetFlags()[3];
    }


    /**
     * Clears the value of the 'customerId' field.
     * @return This builder.
     */
    public Builder clearCustomerId() {
      customerId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
     * Gets the value of the 'orderId' field.
     * @return The value.
     */
    public String getOrderId() {
      return orderId;
    }


    /**
     * Sets the value of the 'orderId' field.
     * @param value The value of 'orderId'.
     * @return This builder.
     */
    public Builder setOrderId(String value) {
      validate(fields()[4], value);
      this.orderId = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
     * Checks whether the 'orderId' field has been set.
     * @return True if the 'orderId' field has been set, false otherwise.
     */
    public boolean hasOrderId() {
      return fieldSetFlags()[4];
    }


    /**
     * Clears the value of the 'orderId' field.
     * @return This builder.
     */
    public Builder clearOrderId() {
      orderId = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
     * Gets the value of the 'price' field.
     * @return The value.
     */
    public java.math.BigDecimal getPrice() {
      return price;
    }


    /**
     * Sets the value of the 'price' field.
     * @param value The value of 'price'.
     * @return This builder.
     */
    public Builder setPrice(java.math.BigDecimal value) {
      validate(fields()[5], value);
      this.price = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
     * Checks whether the 'price' field has been set.
     * @return True if the 'price' field has been set, false otherwise.
     */
    public boolean hasPrice() {
      return fieldSetFlags()[5];
    }


    /**
     * Clears the value of the 'price' field.
     * @return This builder.
     */
    public Builder clearPrice() {
      price = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
     * Gets the value of the 'createdAt' field.
     * @return The value.
     */
    public java.time.Instant getCreatedAt() {
      return createdAt;
    }


    /**
     * Sets the value of the 'createdAt' field.
     * @param value The value of 'createdAt'.
     * @return This builder.
     */
    public Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[6], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
     * Checks whether the 'createdAt' field has been set.
     * @return True if the 'createdAt' field has been set, false otherwise.
     */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[6];
    }


    /**
     * Clears the value of the 'createdAt' field.
     * @return This builder.
     */
    public Builder clearCreatedAt() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
     * Gets the value of the 'paymentStatus' field.
     * @return The value.
     */
    public PaymentStatus getPaymentStatus() {
      return paymentStatus;
    }


    /**
     * Sets the value of the 'paymentStatus' field.
     * @param value The value of 'paymentStatus'.
     * @return This builder.
     */
    public Builder setPaymentStatus(PaymentStatus value) {
      validate(fields()[7], value);
      this.paymentStatus = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
     * Checks whether the 'paymentStatus' field has been set.
     * @return True if the 'paymentStatus' field has been set, false otherwise.
     */
    public boolean hasPaymentStatus() {
      return fieldSetFlags()[7];
    }


    /**
     * Clears the value of the 'paymentStatus' field.
     * @return This builder.
     */
    public Builder clearPaymentStatus() {
      paymentStatus = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
     * Gets the value of the 'failureMessages' field.
     * @return The value.
     */
    public java.util.List<String> getFailureMessages() {
      return failureMessages;
    }


    /**
     * Sets the value of the 'failureMessages' field.
     * @param value The value of 'failureMessages'.
     * @return This builder.
     */
    public Builder setFailureMessages(java.util.List<String> value) {
      validate(fields()[8], value);
      this.failureMessages = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
     * Checks whether the 'failureMessages' field has been set.
     * @return True if the 'failureMessages' field has been set, false otherwise.
     */
    public boolean hasFailureMessages() {
      return fieldSetFlags()[8];
    }


    /**
     * Clears the value of the 'failureMessages' field.
     * @return This builder.
     */
    public Builder clearFailureMessages() {
      failureMessages = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PaymentResponseAvroModel build() {
      try {
        PaymentResponseAvroModel record = new PaymentResponseAvroModel();
        record.id = fieldSetFlags()[0] ? this.id : (String) defaultValue(fields()[0]);
        record.sagaId = fieldSetFlags()[1] ? this.sagaId : (String) defaultValue(fields()[1]);
        record.paymentId = fieldSetFlags()[2] ? this.paymentId : (String) defaultValue(fields()[2]);
        record.customerId =
            fieldSetFlags()[3] ? this.customerId : (String) defaultValue(fields()[3]);
        record.orderId = fieldSetFlags()[4] ? this.orderId : (String) defaultValue(fields()[4]);
        record.price =
            fieldSetFlags()[5] ? this.price : (java.math.BigDecimal) defaultValue(fields()[5]);
        record.createdAt =
            fieldSetFlags()[6] ? this.createdAt : (java.time.Instant) defaultValue(fields()[6]);
        record.paymentStatus =
            fieldSetFlags()[7] ? this.paymentStatus : (PaymentStatus) defaultValue(fields()[7]);
        record.failureMessages = fieldSetFlags()[8] ? this.failureMessages
            : (java.util.List<String>) defaultValue(fields()[8]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PaymentResponseAvroModel>
      WRITER$ = (org.apache.avro.io.DatumWriter<PaymentResponseAvroModel>) MODEL$.createDatumWriter(
      SCHEMA$);

  @Override
  public void writeExternal(java.io.ObjectOutput out)
      throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PaymentResponseAvroModel>
      READER$ = (org.apache.avro.io.DatumReader<PaymentResponseAvroModel>) MODEL$.createDatumReader(
      SCHEMA$);

  @Override
  public void readExternal(java.io.ObjectInput in)
      throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










