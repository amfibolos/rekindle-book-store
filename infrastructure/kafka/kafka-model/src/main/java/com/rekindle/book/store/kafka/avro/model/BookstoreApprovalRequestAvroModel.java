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
public class BookstoreApprovalRequestAvroModel extends
    org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {

  private static final long serialVersionUID = -609465073555326559L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse(
      "{\"type\":\"record\",\"name\":\"BookstoreApprovalRequestAvroModel\",\"namespace\":\"com.rekindle.book.store.kafka.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"bookstoreId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"bookstoreOrderStatus\",\"type\":{\"type\":\"enum\",\"name\":\"BookstoreOrderStatus\",\"symbols\":[\"PAID\"]}},{\"name\":\"products\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Product\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"logicalType\":\"uuid\"},{\"name\":\"quantity\",\"type\":\"int\"}]}}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}");

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  private static final SpecificData MODEL$ = new SpecificData();

  static {
    MODEL$.addLogicalTypeConversion(
        new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<BookstoreApprovalRequestAvroModel> ENCODER =
      new BinaryMessageEncoder<BookstoreApprovalRequestAvroModel>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<BookstoreApprovalRequestAvroModel> DECODER =
      new BinaryMessageDecoder<BookstoreApprovalRequestAvroModel>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   *
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<BookstoreApprovalRequestAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   *
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<BookstoreApprovalRequestAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified
   * {@link SchemaStore}.
   *
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<BookstoreApprovalRequestAvroModel> createDecoder(
      SchemaStore resolver
  ) {
    return new BinaryMessageDecoder<BookstoreApprovalRequestAvroModel>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this BookstoreApprovalRequestAvroModel to a ByteBuffer.
   *
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a BookstoreApprovalRequestAvroModel from a ByteBuffer.
   *
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a BookstoreApprovalRequestAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of
   *                             this class
   */
  public static BookstoreApprovalRequestAvroModel fromByteBuffer(
      java.nio.ByteBuffer b
  ) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private String id;
  private String sagaId;
  private String bookstoreId;
  private String orderId;
  private BookstoreOrderStatus bookstoreOrderStatus;
  private java.util.List<Product> products;
  private java.math.BigDecimal price;
  private java.time.Instant createdAt;

  /**
   * Default constructor.  Note that this does not initialize fields to their default values from
   * the schema.  If that is desired then one should use <code>newBuilder()</code>.
   */
  public BookstoreApprovalRequestAvroModel() {
  }

  /**
   * All-args constructor.
   *
   * @param id                   The new value for id
   * @param sagaId               The new value for sagaId
   * @param bookstoreId          The new value for bookstoreId
   * @param orderId              The new value for orderId
   * @param bookstoreOrderStatus The new value for bookstoreOrderStatus
   * @param products             The new value for products
   * @param price                The new value for price
   * @param createdAt            The new value for createdAt
   */
  public BookstoreApprovalRequestAvroModel(
      String id, String sagaId, String bookstoreId, String orderId,
      BookstoreOrderStatus bookstoreOrderStatus, java.util.List<Product> products,
      java.math.BigDecimal price, java.time.Instant createdAt
  ) {
    this.id = id;
    this.sagaId = sagaId;
    this.bookstoreId = bookstoreId;
    this.orderId = orderId;
    this.bookstoreOrderStatus = bookstoreOrderStatus;
    this.products = products;
    this.price = price;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
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
        return bookstoreId;
      case 3:
        return orderId;
      case 4:
        return bookstoreOrderStatus;
      case 5:
        return products;
      case 6:
        return price;
      case 7:
        return createdAt;
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
          null,
          new org.apache.avro.Conversions.DecimalConversion(),
          new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
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
        bookstoreId = value$ != null ? value$.toString() : null;
        break;
      case 3:
        orderId = value$ != null ? value$.toString() : null;
        break;
      case 4:
        bookstoreOrderStatus = (BookstoreOrderStatus) value$;
        break;
      case 5:
        products = (java.util.List<Product>) value$;
        break;
      case 6:
        price = (java.math.BigDecimal) value$;
        break;
      case 7:
        createdAt = (java.time.Instant) value$;
        break;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   *
   * @return The value of the 'id' field.
   */
  public String getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   *
   * @param value the value to set.
   */
  public void setId(String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'sagaId' field.
   *
   * @return The value of the 'sagaId' field.
   */
  public String getSagaId() {
    return sagaId;
  }


  /**
   * Sets the value of the 'sagaId' field.
   *
   * @param value the value to set.
   */
  public void setSagaId(String value) {
    this.sagaId = value;
  }

  /**
   * Gets the value of the 'bookstoreId' field.
   *
   * @return The value of the 'bookstoreId' field.
   */
  public String getBookstoreId() {
    return bookstoreId;
  }


  /**
   * Sets the value of the 'bookstoreId' field.
   *
   * @param value the value to set.
   */
  public void setBookstoreId(String value) {
    this.bookstoreId = value;
  }

  /**
   * Gets the value of the 'orderId' field.
   *
   * @return The value of the 'orderId' field.
   */
  public String getOrderId() {
    return orderId;
  }


  /**
   * Sets the value of the 'orderId' field.
   *
   * @param value the value to set.
   */
  public void setOrderId(String value) {
    this.orderId = value;
  }

  /**
   * Gets the value of the 'bookstoreOrderStatus' field.
   *
   * @return The value of the 'bookstoreOrderStatus' field.
   */
  public BookstoreOrderStatus getBookstoreOrderStatus() {
    return bookstoreOrderStatus;
  }


  /**
   * Sets the value of the 'bookstoreOrderStatus' field.
   *
   * @param value the value to set.
   */
  public void setBookstoreOrderStatus(BookstoreOrderStatus value) {
    this.bookstoreOrderStatus = value;
  }

  /**
   * Gets the value of the 'products' field.
   *
   * @return The value of the 'products' field.
   */
  public java.util.List<Product> getProducts() {
    return products;
  }


  /**
   * Sets the value of the 'products' field.
   *
   * @param value the value to set.
   */
  public void setProducts(java.util.List<Product> value) {
    this.products = value;
  }

  /**
   * Gets the value of the 'price' field.
   *
   * @return The value of the 'price' field.
   */
  public java.math.BigDecimal getPrice() {
    return price;
  }


  /**
   * Sets the value of the 'price' field.
   *
   * @param value the value to set.
   */
  public void setPrice(java.math.BigDecimal value) {
    this.price = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   *
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   *
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Creates a new BookstoreApprovalRequestAvroModel RecordBuilder.
   *
   * @return A new BookstoreApprovalRequestAvroModel RecordBuilder
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Creates a new BookstoreApprovalRequestAvroModel RecordBuilder by copying an existing Builder.
   *
   * @param other The existing builder to copy.
   * @return A new BookstoreApprovalRequestAvroModel RecordBuilder
   */
  public static Builder newBuilder(Builder other) {
    if (other == null) {
      return new Builder();
    } else {
      return new Builder(other);
    }
  }

  /**
   * Creates a new BookstoreApprovalRequestAvroModel RecordBuilder by copying an existing
   * BookstoreApprovalRequestAvroModel instance.
   *
   * @param other The existing instance to copy.
   * @return A new BookstoreApprovalRequestAvroModel RecordBuilder
   */
  public static Builder newBuilder(BookstoreApprovalRequestAvroModel other) {
    if (other == null) {
      return new Builder();
    } else {
      return new Builder(other);
    }
  }

  /**
   * RecordBuilder for BookstoreApprovalRequestAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends
      org.apache.avro.specific.SpecificRecordBuilderBase<BookstoreApprovalRequestAvroModel>
      implements org.apache.avro.data.RecordBuilder<BookstoreApprovalRequestAvroModel> {

    private String id;
    private String sagaId;
    private String bookstoreId;
    private String orderId;
    private BookstoreOrderStatus bookstoreOrderStatus;
    private java.util.List<Product> products;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;

    /**
     * Creates a new Builder
     */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     *
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
      if (isValidValue(fields()[2], other.bookstoreId)) {
        this.bookstoreId = data().deepCopy(fields()[2].schema(), other.bookstoreId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.orderId)) {
        this.orderId = data().deepCopy(fields()[3].schema(), other.orderId);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.bookstoreOrderStatus)) {
        this.bookstoreOrderStatus = data().deepCopy(fields()[4].schema(),
            other.bookstoreOrderStatus);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.products)) {
        this.products = data().deepCopy(fields()[5].schema(), other.products);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.price)) {
        this.price = data().deepCopy(fields()[6].schema(), other.price);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[7].schema(), other.createdAt);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing BookstoreApprovalRequestAvroModel instance
     *
     * @param other The existing instance to copy.
     */
    private Builder(BookstoreApprovalRequestAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.bookstoreId)) {
        this.bookstoreId = data().deepCopy(fields()[2].schema(), other.bookstoreId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.orderId)) {
        this.orderId = data().deepCopy(fields()[3].schema(), other.orderId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.bookstoreOrderStatus)) {
        this.bookstoreOrderStatus = data().deepCopy(fields()[4].schema(),
            other.bookstoreOrderStatus);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.products)) {
        this.products = data().deepCopy(fields()[5].schema(), other.products);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.price)) {
        this.price = data().deepCopy(fields()[6].schema(), other.price);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[7].schema(), other.createdAt);
        fieldSetFlags()[7] = true;
      }
    }

    /**
     * Gets the value of the 'id' field.
     *
     * @return The value.
     */
    public String getId() {
      return id;
    }


    /**
     * Sets the value of the 'id' field.
     *
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
     *
     * @return True if the 'id' field has been set, false otherwise.
     */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
     * Clears the value of the 'id' field.
     *
     * @return This builder.
     */
    public Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
     * Gets the value of the 'sagaId' field.
     *
     * @return The value.
     */
    public String getSagaId() {
      return sagaId;
    }


    /**
     * Sets the value of the 'sagaId' field.
     *
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
     *
     * @return True if the 'sagaId' field has been set, false otherwise.
     */
    public boolean hasSagaId() {
      return fieldSetFlags()[1];
    }


    /**
     * Clears the value of the 'sagaId' field.
     *
     * @return This builder.
     */
    public Builder clearSagaId() {
      sagaId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
     * Gets the value of the 'bookstoreId' field.
     *
     * @return The value.
     */
    public String getBookstoreId() {
      return bookstoreId;
    }


    /**
     * Sets the value of the 'bookstoreId' field.
     *
     * @param value The value of 'bookstoreId'.
     * @return This builder.
     */
    public Builder setBookstoreId(String value) {
      validate(fields()[2], value);
      this.bookstoreId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
     * Checks whether the 'bookstoreId' field has been set.
     *
     * @return True if the 'bookstoreId' field has been set, false otherwise.
     */
    public boolean hasBookstoreId() {
      return fieldSetFlags()[2];
    }


    /**
     * Clears the value of the 'bookstoreId' field.
     *
     * @return This builder.
     */
    public Builder clearBookstoreId() {
      bookstoreId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
     * Gets the value of the 'orderId' field.
     *
     * @return The value.
     */
    public String getOrderId() {
      return orderId;
    }


    /**
     * Sets the value of the 'orderId' field.
     *
     * @param value The value of 'orderId'.
     * @return This builder.
     */
    public Builder setOrderId(String value) {
      validate(fields()[3], value);
      this.orderId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
     * Checks whether the 'orderId' field has been set.
     *
     * @return True if the 'orderId' field has been set, false otherwise.
     */
    public boolean hasOrderId() {
      return fieldSetFlags()[3];
    }


    /**
     * Clears the value of the 'orderId' field.
     *
     * @return This builder.
     */
    public Builder clearOrderId() {
      orderId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
     * Gets the value of the 'bookstoreOrderStatus' field.
     *
     * @return The value.
     */
    public BookstoreOrderStatus getBookstoreOrderStatus() {
      return bookstoreOrderStatus;
    }


    /**
     * Sets the value of the 'bookstoreOrderStatus' field.
     *
     * @param value The value of 'bookstoreOrderStatus'.
     * @return This builder.
     */
    public Builder setBookstoreOrderStatus(BookstoreOrderStatus value) {
      validate(fields()[4], value);
      this.bookstoreOrderStatus = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
     * Checks whether the 'bookstoreOrderStatus' field has been set.
     *
     * @return True if the 'bookstoreOrderStatus' field has been set, false otherwise.
     */
    public boolean hasBookstoreOrderStatus() {
      return fieldSetFlags()[4];
    }


    /**
     * Clears the value of the 'bookstoreOrderStatus' field.
     *
     * @return This builder.
     */
    public Builder clearBookstoreOrderStatus() {
      bookstoreOrderStatus = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
     * Gets the value of the 'products' field.
     *
     * @return The value.
     */
    public java.util.List<Product> getProducts() {
      return products;
    }


    /**
     * Sets the value of the 'products' field.
     *
     * @param value The value of 'products'.
     * @return This builder.
     */
    public Builder setProducts(java.util.List<Product> value) {
      validate(fields()[5], value);
      this.products = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
     * Checks whether the 'products' field has been set.
     *
     * @return True if the 'products' field has been set, false otherwise.
     */
    public boolean hasProducts() {
      return fieldSetFlags()[5];
    }


    /**
     * Clears the value of the 'products' field.
     *
     * @return This builder.
     */
    public Builder clearProducts() {
      products = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
     * Gets the value of the 'price' field.
     *
     * @return The value.
     */
    public java.math.BigDecimal getPrice() {
      return price;
    }


    /**
     * Sets the value of the 'price' field.
     *
     * @param value The value of 'price'.
     * @return This builder.
     */
    public Builder setPrice(java.math.BigDecimal value) {
      validate(fields()[6], value);
      this.price = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
     * Checks whether the 'price' field has been set.
     *
     * @return True if the 'price' field has been set, false otherwise.
     */
    public boolean hasPrice() {
      return fieldSetFlags()[6];
    }


    /**
     * Clears the value of the 'price' field.
     *
     * @return This builder.
     */
    public Builder clearPrice() {
      price = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
     * Gets the value of the 'createdAt' field.
     *
     * @return The value.
     */
    public java.time.Instant getCreatedAt() {
      return createdAt;
    }


    /**
     * Sets the value of the 'createdAt' field.
     *
     * @param value The value of 'createdAt'.
     * @return This builder.
     */
    public Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[7], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
     * Checks whether the 'createdAt' field has been set.
     *
     * @return True if the 'createdAt' field has been set, false otherwise.
     */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[7];
    }


    /**
     * Clears the value of the 'createdAt' field.
     *
     * @return This builder.
     */
    public Builder clearCreatedAt() {
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BookstoreApprovalRequestAvroModel build() {
      try {
        BookstoreApprovalRequestAvroModel record = new BookstoreApprovalRequestAvroModel();
        record.id = fieldSetFlags()[0] ? this.id : (String) defaultValue(fields()[0]);
        record.sagaId = fieldSetFlags()[1] ? this.sagaId : (String) defaultValue(fields()[1]);
        record.bookstoreId =
            fieldSetFlags()[2] ? this.bookstoreId : (String) defaultValue(fields()[2]);
        record.orderId = fieldSetFlags()[3] ? this.orderId : (String) defaultValue(fields()[3]);
        record.bookstoreOrderStatus = fieldSetFlags()[4] ? this.bookstoreOrderStatus
            : (BookstoreOrderStatus) defaultValue(fields()[4]);
        record.products = fieldSetFlags()[5] ? this.products
            : (java.util.List<Product>) defaultValue(fields()[5]);
        record.price =
            fieldSetFlags()[6] ? this.price : (java.math.BigDecimal) defaultValue(fields()[6]);
        record.createdAt =
            fieldSetFlags()[7] ? this.createdAt : (java.time.Instant) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<BookstoreApprovalRequestAvroModel>
      WRITER$ = (org.apache.avro.io.DatumWriter<BookstoreApprovalRequestAvroModel>) MODEL$.createDatumWriter(
      SCHEMA$);

  @Override
  public void writeExternal(java.io.ObjectOutput out)
      throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<BookstoreApprovalRequestAvroModel>
      READER$ = (org.apache.avro.io.DatumReader<BookstoreApprovalRequestAvroModel>) MODEL$.createDatumReader(
      SCHEMA$);

  @Override
  public void readExternal(java.io.ObjectInput in)
      throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










